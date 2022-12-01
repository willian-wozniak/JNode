/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selector;

import document.JNode;

/**
 *
 * @author WillianWozniak
 */
public class ClassList
{
 
    private JNode jnode;
    public ClassList(JNode jnode) {
        this.jnode = jnode;
    }
    
    public String[] getClassList() {
        String clazz = this.jnode.getAttribute("class");
        if (clazz.isEmpty()) return new String[0];
        String[] listas = clazz.trim().split(" ");
        for (int i = 0; i < listas.length; i++) {
            listas[i] = listas[i].trim();
        }
        return listas;
    }
    
}
