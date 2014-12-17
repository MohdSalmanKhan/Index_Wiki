package com.parsing.main;

import java.io.IOException;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class Driver {

	public static void main(String[] args) throws IOException , SAXException {
		long startTime = System.currentTimeMillis();
		XMLReader p = XMLReaderFactory.createXMLReader();
		p.setContentHandler(new Parser(args[1]));
		p.parse(args[0]);
		long endTime = System.currentTimeMillis(); 
		long totalTime = endTime - startTime; 
		System.out.println(totalTime);
	}
}
