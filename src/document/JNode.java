/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package document;

import parser.DOMParser;
import transform.DOMMatrix;
import style.Style;
import selector.ClassList;
import org.w3c.dom.Node;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Element;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.OutputKeys;
import java.io.StringWriter;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.NodeList;
import java.util.ArrayList;
import selector.QuerySelector;

/**
 *
 * @author WillianWozniak
 */
public class JNode
{

    private Node node;
    private Element element;
    public Style style;
    public DOMMatrix domMatrix;
    public ClassList classList;
    public QuerySelector querySelector;

    public JNode(Node node) {
        this.node = node;
        this.element = (Element) node;
        this.style = new Style(this);
        this.domMatrix = new DOMMatrix(this);
        this.classList = new ClassList(this);
        this.querySelector = new QuerySelector(this);
    }

    public Node getNode() {
        return this.node;
    }

    public Node getElement() {
        return this.element;
    }

    public String getTagName() {
        return this.node.getNodeName();
    }

    // Se houver algum nó que seja do tipo 3, irá ocorrer um erro, pois espera-se um Node
    public JNode[] getChildNodes() {
        NodeList childs = this.node.getChildNodes();
        JNode[] lista = new JNode[childs.getLength()];

        for (int i = 0; i < lista.length; i++) {
            lista[i] = new JNode(childs.item(i));
        }

        return lista;
    }

    // Se houver algum nó que seja do tipo 3, não irá colocar
    public JNode[] getChildren() {
        NodeList childs = this.node.getChildNodes();
        ArrayList<Node> arrList = new ArrayList();

        for (int i = 0; i < childs.getLength(); i++) {
            if (childs.item(i).getNodeType() == childs.item(i).ELEMENT_NODE) {
                arrList.add(childs.item(i));
            }
        }

        JNode[] lista = new JNode[arrList.size()];

        for (int i = 0; i < arrList.size(); i++) {
            lista[i] = new JNode(arrList.get(i));
        }

        return lista;
    }

    public JDocument getOwnerDocument() {
        return new JDocument(this.node.getOwnerDocument());
    }

    public JNode[] getElementsByTagName(String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        JNode[] lista = new JNode[nodeList.getLength()];

        for (int i = 0; i < lista.length; i++) {
            lista[i] = new JNode(nodeList.item(i));
        }

        return lista;
    }

    // Aqui usar métodos nativos para ser mais rápido,
    public String getInnerHTML() {
        NodeList childs = this.node.getChildNodes();
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < childs.getLength(); i++) {
            str.append(new JNode(childs.item(i)).getOuterHTML());
        }
        return str.toString();
    }

    // Aqui usar métodos nativos para ser mais rápido
    public void setInnerHTML(String html) {
        this.removerChilds();

        JDocument jdoc = new DOMParser().parserFromString(html, "text/html");

        Node jbody = jdoc.getDocument().getElementsByTagName("body").item(0);

        NodeList childsBody = jbody.getChildNodes();

        Node nodeImported;
        for (int i = 0; i < childsBody.getLength(); i++) {
            nodeImported = this.node.getOwnerDocument().importNode(childsBody.item(i), true);
            this.node.appendChild(nodeImported);
        }
    }

    public void removerChilds() {
        NodeList childs = this.node.getChildNodes();
        for (int i = 0; i < childs.getLength(); i++) {
            this.node.removeChild(childs.item(i));
        }
    }
    
    public JNode replacerWith(JNode jnode) { //
        if (this.node.getOwnerDocument() == jnode.getNode().getOwnerDocument()) {
            Element parent = (Element) this.element.getParentNode();
            parent.replaceChild(jnode.getNode(), this.node);
            return jnode;
        } else {
            return this.replacerWith(this.importarNode(jnode, true));
        }
    }

    public JNode importarNode(JNode jnode, boolean deep) {
        Node nodeImported = this.node.getOwnerDocument().importNode(jnode.getNode(), deep);
        return new JNode(nodeImported);
    }

    public JNode setAppendChild(JNode jnode) {
        if (this.node.getOwnerDocument() == jnode.getNode().getOwnerDocument()) {
            this.node.appendChild(jnode.getNode());
            return jnode;
        } else {
            return this.setAppendChild(this.importarNode(jnode, true));
        }
    }

    public void setRemoveChild(JNode jnode) {
        this.node.removeChild(jnode.getNode());
    }

    public int getNodeType() {
        return this.getNode().getNodeType();
    }

    public void setAttribute(String name, String value) {
        try {
            this.element.setAttribute(name, value);
        } catch (Exception ex) {
        }
    }

    public String getAttribute(String name) {
        try {
            return this.element.getAttribute(name);
        } catch (Exception ex) {
            return this.getAttributeNode(name);
        }
    }

    public String getAttributeNode(String name) {
        NamedNodeMap attrs = node.getAttributes();
        if (attrs == null) {
            return null;
        }
        Node nodeAttr = attrs.getNamedItem(name);
        if (nodeAttr != null) {
            return nodeAttr.getNodeValue();
        }
        return null;
    }

    public String getOuterHTML() {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.METHOD, "html");
            StringWriter writer = new StringWriter();
            StreamResult sr = new StreamResult(writer);
            DOMSource ds = new DOMSource(this.node);
            transformer.transform(ds, sr);

            return writer.getBuffer().toString();
        } catch (TransformerException ex) {
        }
        return "";
    }
    
    // Por enquanto não se faz necessário utilizar este
    protected static String normalizarOuterHTML(Node node, String html)
    {
        String nodeName = node.getNodeName();
        if (html.endsWith("</" + nodeName + ">")) {
    		return html;
        } else {
            return html.replaceAll("(<"+ nodeName +".*?>)", "$1</" + nodeName + ">");
        }
    }


}
