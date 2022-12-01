/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selector;

import document.JNode;
import strings.Strings;
import java.util.HashMap;

/**
 *
 * @author WillianWozniak
 */
public class Match
{
    

    public static boolean getTagName(JNode jnode, String match) {
        return jnode.getTagName().equals(match);
    }

    public static boolean getID(JNode jnode, String match) {
        String id = jnode.getAttribute("id");
        return ("#" + id).equals(match);
    }

    public static boolean getClassName(JNode jnode, String match) {
        String[] classList = jnode.classList.getClassList();
        String[] matches = match.substring(1, match.length()).split("\\.");
        int matched = 0;

        for (String clazz : classList) {
            for (String clazzMatch : matches) {
                if (clazz.equals(clazzMatch)) {
                    matched++;
                }
            }
        }

        return matched == matches.length;
    }

    public static boolean getAttribute(JNode jnode, String match) {
        match = match.replaceAll("\\]", "");
        match = match.substring(1, match.length());
        String[] matches = match.split("\\[");
        int matched = 0;

        String[] props;
        String attValue;

        for (String att : matches) {
            props = att.split("=");
            attValue = jnode.getAttribute(props[0]);
            if (!attValue.isEmpty()) {
                if (("\"" + attValue + "\"").equals(props[1])) {
                    matched++;
                }
            }
        }
        return matched == matches.length;
    }
    
    public static boolean getSequence(JNode jnode, String match) {
        
         HashMap<String, String> attribute = getAttributeSequence(match);
         HashMap<String, String> className = getClassNameSequence(match);
        
        boolean matched = false;
        
        if (!attribute.get("value").isEmpty())
            matched = getAttribute(jnode, attribute.get("value"));
        if (!className.get("value").isEmpty())
            matched = getClassName(jnode, className.get("value"));
                    
        return matched;
    }
    
    private static  HashMap<String, String> getAttributeSequence(String match) {
        String attributes = "";
        String value;
        while (match.contains("[") && match.contains("]")) {
            value = Strings.getOn(match, "[", "]");
            match = match.replaceAll(("\\[" + value + "\\]"), "");
            attributes += "[" + value + "]";
        }
        
        HashMap<String, String> data = new HashMap();
        data.put("match", match);
        data.put("value", attributes);
        
        return data;
    }
    
    private static HashMap<String, String> getClassNameSequence(String match) {
        String className = "";
        String value;
        while (match.contains(".")) {
            value = Strings.getOn(match + ".", ".", ".");
            match = match.replaceAll(("\\." + value), "");
            className += "." + value;
        }
        
        HashMap<String, String> data = new HashMap();
        data.put("match", match);
        data.put("value", className);
        
        return data;
    }

}
