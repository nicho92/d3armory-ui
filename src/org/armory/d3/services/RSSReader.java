package org.armory.d3.services;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class RSSReader {

	public static enum TYPERSS {ATOM,FEED};
	
    /**
     * Parser le fichier XML
     * @param feedurl URL du flux RSS
     */
    public String updateD3armoryRssParser(String rss) {
        try {
        	String feedurl=rss;
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            URL url = new URL(feedurl);
            Document doc = builder.parse(url.openStream());
            NodeList nodes = null;
            Element element = null;
         
            StringBuffer temp = new StringBuffer();
            	
            nodes = doc.getElementsByTagName("entry");
           
            for (int i = 0; i < nodes.getLength(); i++) {
                element = (Element) nodes.item(i);
                String date = readNode(element, "updated").substring(0,readNode(element, "updated").lastIndexOf("T"));
                String title = readNode(element, "title");
                if(!title.endsWith("[No log message]"))
                	temp.append("<b>" + date + "</b> : ").append(title).append("<br/>");
            } //for
            return temp.toString();
        } catch (Exception ex) {
        	ex.printStackTrace();
        	return null;
        } 
		
    }
    
    
    public String getRss(String rss) {
        try {
        	String feedurl=rss;
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            URL url = new URL(feedurl);
            Document doc = builder.parse(url.openStream());
            NodeList nodes = null;
            Element element = null;
         
            StringBuffer temp = new StringBuffer();
            	
            nodes = doc.getElementsByTagName("item");
           
            for (int i = 0; i < nodes.getLength(); i++) {
                element = (Element) nodes.item(i);
                String title = readNode(element, "title");
               	temp.append(title).append("\n");
            } //for
            return temp.toString();
        } catch (Exception ex) {
        	System.out.println(ex);
        	return null;
        } 
		
    }
    
    

    /**
     * Méthode permettant de retourner ce que contient d'un noeud
     * @param _node le noeud principal
     * @param _path suite des noms des noeud sans espace séparer par des "|"
     * @return un string contenant le valeur du noeud voulut
     */
    public String readNode(Node _node, String _path) {

        String[] paths = _path.split("\\|");
        Node node = null;

        if (paths != null && paths.length > 0) {
            node = _node;

            for (int i = 0; i < paths.length; i++) {
                node = getChildByName(node, paths[i].trim());
            }
        }

        if (node != null) {
            return node.getTextContent();
        } else {
            return "";
        }
    }

    /**
     * renvoye le nom d'un noeud fils a partir de son nom
     * @param _node noeud pricipal
     * @param _name nom du noeud fils
     * @return le noeud fils
     */
    public Node getChildByName(Node _node, String _name) {
        if (_node == null) {
            return null;
        }
        NodeList listChild = _node.getChildNodes();

        if (listChild != null) {
            for (int i = 0; i < listChild.getLength(); i++) {
                Node child = listChild.item(i);
                if (child != null) {
                    if ((child.getNodeName() != null && (_name.equals(child.getNodeName()))) || (child.getLocalName() != null && (_name.equals(child.getLocalName())))) {
                        return child;
                    }
                }
            }
        }
        return null;
    }
}