/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package document;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 *
 * @author WillianWozniak
 */
public class JDocument
{
    
    private Document doc;
    
    public JDocument(Document doc)
    {
        this.doc = doc;
    }
    
    public Document getDocument() {
        return this.doc;
    }
    
    public JNode[] getElementsByTagName(String tagName) {
        NodeList nodeList = this.doc.getElementsByTagName(tagName);
        JNode[] lista = new JNode[nodeList.getLength()];
        
        for (int i = 0; i < lista.length; i++) {
            lista[i] = new JNode(nodeList.item(i));
        }
        
        return lista;
    }

    
}
