/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package style;

import document.JNode;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author WillianWozniak
 */
public class Style
{

    private JNode jnode;

    public Style(JNode jnode) {
        this.jnode = jnode;
    }

    public HashMap<String, String> get() {
        HashMap<String, String> data = new HashMap();

        String style = this.jnode.getAttribute("style");

        if (style == null) {
            return null;
        }

        String[] listas = style.trim().split(";");
        String[] propriedades;
        String key, value;
        for (String lista : listas) {
            propriedades = lista.split(":");
            key = propriedades[0].trim();
            value = propriedades[1].trim();

            data.put(key, value);
        }

        return data;
    }

    private void set(HashMap<String, String> style) {
        ArrayList<String> lista = new ArrayList();
        for (Map.Entry<String, String> value : style.entrySet()) {
            lista.add(value.getKey() + ":" + value.getValue() + ";");
        }
        this.jnode.setAttribute("style", String.join(" ", lista));
    }

    public HashMap<String, Object> getLeft() {
        HashMap<String, String> style = this.get();

        if (style == null || !style.containsKey("left")) {
            return null;
        }

        String left = style.get("left");

        HashMap<String, Object> data = new HashMap();

        data.put("unit", (left.contains("%") ? "%" : "px"));

        left = left.replaceAll((String) data.get("unit"), "");

        data.put("value", Double.parseDouble(left));

        return data;
    }

    public void setLeft(String left) {
        HashMap<String, String> style = this.get();
        style.put("left", left);
        this.set(style);
    }

    public HashMap<String, Object> getRight() {
        HashMap<String, String> style = this.get();

        if (style == null || !style.containsKey("right")) {
            return null;
        }

        String right = style.get("right");

        HashMap<String, Object> data = new HashMap();

        data.put("unit", (right.contains("%") ? "%" : "px"));

        right = right.replaceAll((String) data.get("unit"), "");

        data.put("value", Double.parseDouble(right));

        return data;
    }

    public void setRight(String right) {
        HashMap<String, String> style = this.get();
        style.put("right", right);
        this.set(style);
    }

    public HashMap<String, Object> getTop() {
        HashMap<String, String> style = this.get();

        if (style == null || !style.containsKey("top")) {
            return null;
        }

        String top = style.get("top");

        HashMap<String, Object> data = new HashMap();

        data.put("unit", (top.contains("%") ? "%" : "px"));

        top = top.replaceAll((String) data.get("unit"), "");

        data.put("value", Double.parseDouble(top));

        return data;
    }

    public void setTop(String top) {
        HashMap<String, String> style = this.get();
        style.put("top", top);
        this.set(style);
    }

    public HashMap<String, Object> getBottom() {
        HashMap<String, String> style = this.get();

        if (style == null || !style.containsKey("bottom")) {
            return null;
        }

        String bottom = style.get("bottom");

        HashMap<String, Object> data = new HashMap();

        data.put("unit", (bottom.contains("%") ? "%" : "px"));

        bottom = bottom.replaceAll((String) data.get("unit"), "");

        data.put("value", Double.parseDouble(bottom));

        return data;
    }

    public void setBottom(String bottom) {
        HashMap<String, String> style = this.get();
        style.put("bottom", bottom);
        this.set(style);
    }

    public HashMap<String, Object> getWidth() {
        HashMap<String, String> style = this.get();

        if (style == null || !style.containsKey("width")) {
            return null;
        }

        String width = style.get("width");

        HashMap<String, Object> data = new HashMap();

        data.put("unit", (width.contains("%") ? "%" : "px"));

        width = width.replaceAll((String) data.get("unit"), "");

        data.put("value", Double.parseDouble(width));

        return data;
    }

    public void setWidth(String width) {
        HashMap<String, String> style = this.get();
        style.put("width", width);
        this.set(style);
    }

    public HashMap<String, Object> getHeight() {
        HashMap<String, String> style = this.get();

        if (style == null || !style.containsKey("height")) {
            return null;
        }

        String height = style.get("height");

        HashMap<String, Object> data = new HashMap();

        data.put("unit", (height.contains("%") ? "%" : "px"));

        height = height.replaceAll((String) data.get("unit"), "");

        data.put("value", Double.parseDouble(height));

        return data;
    }

    public void setHeight(String height) {
        HashMap<String, String> style = this.get();
        style.put("height", height);
        this.set(style);
    }

    public String getTransform() {
        HashMap<String, String> style = this.get();
        if (style == null || !style.containsKey("transform")) {
            return null;
        }
        
        return style.get("transform");
    }

    public void setTransform(String transform) {
        HashMap<String, String> style = this.get();
        style.put("transform", transform);
        this.set(style);
    }

    public String getPosition() {
        HashMap<String, String> style = this.get();
        if (style == null || !style.containsKey("position")) {
            return null;
        }
        return style.get("position");
    }

    public void setPosition(String position) {
        HashMap<String, String> style = this.get();
        style.put("position", position);
        this.set(style);
    }

}
