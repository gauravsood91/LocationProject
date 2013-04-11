package parsing;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class ParseXML {
	String distance;
	public ParseXML(){}
	public String parseXML(String str) throws SAXException, IOException{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			org.w3c.dom.Document dom = db.parse(str);
			
			System.out.println(dom.getDocumentElement().getNodeName());
			
			NodeList nodeList = dom.getElementsByTagName("row");
			org.w3c.dom.Node node = nodeList.item(0);
			System.out.println("\nCurrent Node: "+node.getNodeName());
			
			org.w3c.dom.Element eElement = (org.w3c.dom.Element)node;
			//System.out.println("Distance: "+eElement.getElementsByTagName("value").item(1).getTextContent());
			distance = eElement.getElementsByTagName("value").item(1).getTextContent();
			//get root element from dom
			/*org.w3c.dom.Element docElement = dom.getDocumentElement();
			NodeList nl = docElement.getElementsByTagName("DistanceMatrixResponse");
			Node node = (Node) nl.item(0);
			org.w3c.dom.Element eElement = (org.w3c.dom.Element)node;
			System.out.println("Origin Address: "+eElement.getAttribute("origin_address"));
			*/
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return distance;
	}

}
