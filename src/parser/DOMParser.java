/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import document.JDocument;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.InputSource;
import java.text.Normalizer;
import java.io.StringReader;

/**
 *
 * @author WillianWozniak
 */
public class DOMParser
{
    
    public DOMParser() {}
    
    public JDocument parserFromString(String html, String type) {
        Document doc = null;
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        
        html = normalizar(html);
        html = getHTML(html);
        
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(new InputSource(new StringReader(html)));
        } catch (Exception ex) {}
        
        return new JDocument(doc);
    }
    
    private String normalizar(String html)
    {
        html = normalizarString(html);
        html = normalizarTags(html);
        return html;
    }
    
    private String getHTML(String html) {
        return ""
        + "<!DOCTYPE html ["
        + "    <!ENTITY nbsp \"&#160;\"> "
        + "]>"
        + "<html>"
        + "<head></head>"
        + "<body>"
        + html
        + "</body>"
        + "</html>";
    }
    
    
    private String normalizarString(String string) {
        String temp = Normalizer.normalize(string, java.text.Normalizer.Form.NFD);
        return temp.replaceAll("[^\\p{ASCII}]","");
    }
    
    private String normalizarTags(String html)
    {
        html = html.replaceAll("(<img.*?>)", "$1</img>");
        html = html.replaceAll("(<input.*?>)", "$1</input>");
        return html.replaceAll("(<br.*?>)", "$1</br>");
    }

    
}
