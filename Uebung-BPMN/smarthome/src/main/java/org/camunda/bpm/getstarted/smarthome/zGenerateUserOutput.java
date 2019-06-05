package org.camunda.bpm.getstarted.smarthome;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class zGenerateUserOutput {

	public static void generateUserOutput(String pathToImage, String cloudiness, String blendClosedDegree)
		   throws ParserConfigurationException, SAXException, IOException, TransformerException {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(false);
		factory.setValidating(false);
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		File file = new File("../webapps/smarthome-0.0.1-SNAPSHOT/forms/datenEinsehen.html");
		
		Document document = builder.parse(file);
		Element definitions = document.getDocumentElement();
		NodeList fields = definitions.getElementsByTagName("td");
		
		Node cloudinessNode = fields.item(1);
		cloudinessNode.setTextContent(cloudiness + "%");
		Node blendClosedDegreeNode = fields.item(3);
		blendClosedDegreeNode.setTextContent(blendClosedDegree + "%");
				
		//Output
		TransformerFactory tfact = TransformerFactory.newInstance();
		Transformer tform = tfact.newTransformer();
		tform.setOutputProperty(OutputKeys.INDENT, "yes");

		tform.transform(new DOMSource(document), new StreamResult(file));
		
	}
	
}
