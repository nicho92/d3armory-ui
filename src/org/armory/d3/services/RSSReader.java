package org.armory.d3.services;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class RSSReader {

    /**
     * Parser le fichier XML
     * @param feedurl URL du flux RSS
     */
    public String parse() {
        try {
        	String feedurl="https://code.google.com/feeds/p/d3armory-ui/svnchanges/basic";
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

    /**
     * Afficher une Date GML au format francais
     * @param gmtDate
     * @return
     */
    public String GMTDateToFrench(String gmtDate) {
        try {
            SimpleDateFormat dfGMT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
            dfGMT.parse(gmtDate);
            SimpleDateFormat dfFrench = new SimpleDateFormat("EEEE, d MMMM yyyy HH:mm:ss", Locale.FRANCE);
            return dfFrench.format(dfGMT.getCalendar().getTime());
        } catch (ParseException ex) {
            Logger.getLogger(RSSReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    /**
     * Exemple
     * @param args
     */
    public static void main(String[] args) {
        RSSReader reader = new RSSReader();
        reader.parse();
    }
}