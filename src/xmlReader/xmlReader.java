package xmlReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * test for reading the target element in a xml-file.
 * @author Mikael Dahlin
 *
 */
public class xmlReader {

	public static void main(String[] args) {
		
		try {
			// Create a DocumentBuilder.
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			// Declaration of variables.
			Document doc = dBuilder.parse(new File("sma_gentext.xml"));
			Node node = null;
			PrintWriter pw = new PrintWriter(new File("target.txt"));
			String targetId = "42007";
			String text = "";
			
			// Locate and store target text.
			NodeList nodes = doc.getElementsByTagName("trans-unit");
			
			for(int i = 0;i < nodes.getLength(); i++) {
				
				if(nodes.item(i).getAttributes().item(0).getTextContent().equals(targetId)) {
				
					while(nodes.item(i).hasChildNodes()) {
						node = nodes.item(i).getFirstChild();
						
						if(node.getNodeName().equals("target")) {
							text = node.getTextContent();
						}
						nodes.item(i).removeChild(node);
					}
										
				}
			}
			
			// Write to file and console.
			pw.write(text);
			pw.close();
			System.out.println(text);
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
	}

}
