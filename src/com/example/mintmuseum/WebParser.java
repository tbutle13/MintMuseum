package com.example.mintmuseum;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.tidy.Tidy;
import org.xml.sax.SAXException;

public class WebParser {
	
	public String getTagValue(String name, Element e) {
		NodeList nList = e.getElementsByTagName(name).item(0).getChildNodes();
		Node nValue = (Node) nList.item(0);
		
		return nValue.getNodeValue();
	}
	
	/**
	 * Method for retrieving xml from web compoents
	 * @return 
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	public Document getXML(String url) {
		
	
		Document doc;
		try {
			URLConnection test = new URL(url).openConnection();
	
			File tempFile = new File("temp");
			FileWriter fw = new FileWriter(tempFile);
		
			//Use tidy to fix any xml problems and write xml to a file
			Tidy tidy = new Tidy();
			tidy.setXmlTags(true);
			tidy.parse(test.getInputStream(), fw);
		
			DocumentBuilder docBuild = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			doc = docBuild.parse(tempFile);
			doc.getDocumentElement().normalize();
		} catch (Exception e) {
			e.printStackTrace();
			doc=null;
		}
		return doc;
	}
	
	public List<ArtWork> getArt() {	//Temp will change to real url
		String url = "http://webpages.uncc.edu/~cburke16/test.xml";
		NodeList nl = getXML(url).getElementsByTagName("Paintings");
		List<ArtWork> arts = new ArrayList();
		
		for(int i = 0; i < nl.getLength(); i++) {
			Node mNode = nl.item(i);
			if(mNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elm = (Element) mNode;
				arts.add(new ArtWork(getTagValue("Id", elm), getTagValue("Name", elm), getTagValue("Description", elm),getTagValue("Artist", elm)));
			}
		}
		return arts;
	}
	
	
}