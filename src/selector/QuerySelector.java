/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selector;

import document.JNode;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author WillianWozniak
 */
public class QuerySelector
{

    private JNode jnode;

    public QuerySelector(JNode jnode) {
        this.jnode = jnode;
    }

    public JNode get(String selectorText) {
        String[] matches = selectorText.trim().split(" ");

        JNode[] jnodes = getList(jnode, matches);
        
        return jnodes.length > 0 ? jnodes[0] : null;
    }

    public JNode[] getAll(String selectorText) {
        String[] matches = selectorText.trim().split(" ");

        return getList(jnode, matches);
    }

    private JNode[] getList(JNode jnode, String[] matches) {
        List<JNode> list = new ArrayList();
        runnerMatches(list, this.jnode, matches, 0);

        JNode[] jnodes = new JNode[list.size()];

        for (int i = 0; i < list.size(); i++) {
            jnodes[i] = list.get(i);
        }
        
        return jnodes;
    }
    
    private JNode runnerMatches(List<JNode> list, JNode jnode, String[] matches, int i) {
        if (Match.getTagName(jnode, matches[i])) {
            return runnerMatched(list, jnode, matches, i);
        }
        if (Match.getID(jnode, matches[i])) {
            return runnerMatched(list, jnode, matches, i);
        }
        if (Match.getClassName(jnode, matches[i])) {
            return runnerMatched(list, jnode, matches, i);
        }
        if (Match.getAttribute(jnode, matches[i])) {
            return runnerMatched(list, jnode, matches, i);
        }
        if (Match.getSequence(jnode, matches[i])) {
            return runnerMatched(list, jnode, matches, i);
        }

        return runnerMatchesChilds(list, jnode, matches, i);
    }

    private JNode runnerMatched(List<JNode> list, JNode jnode, String[] matches, int i) {
        i++;
        if (i == matches.length) {
            list.add(jnode);
            --i;
        }
        return runnerMatchesChilds(list, jnode, matches, i);
    }

    private JNode runnerMatchesChilds(List<JNode> list, JNode jnode, String[] matches, int i) {
        JNode[] childs = jnode.getChildren();
        for (JNode child : childs) {
            runnerMatches(list, child, matches, i);
        }
        return jnode;
    }
    
}
