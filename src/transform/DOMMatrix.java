/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transform;

import document.JNode;
import java.util.HashMap;

/**
 *
 * @author WillianWozniak
 */
public class DOMMatrix
{
    
    private JNode jnode;

    public DOMMatrix(JNode jnode) {
        this.jnode = jnode;
    }

    public HashMap<String, Double> get() {
        HashMap<String, Double> data = new HashMap() {
            {
                put("m41", 0.0);
                put("m42", 0.0);
            }
        };

        
        if (jnode.style.getTransform() == null) {
            return data;
        }
        
        Transform transform = new Transform(jnode.style.getTransform());

        if (transform.translateX != null) 
            data.put("m41", transform.translateX.getValue());
        if (transform.translateY != null) 
            data.put("m42", transform.translateY.getValue());
        
        return data;
    }

}
