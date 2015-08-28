package com.ming.util;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.ProcessingInstruction;
import org.dom4j.VisitorSupport;
import org.dom4j.io.SAXReader;
import org.dom4j.Node;

public class XmlParse {

	public  String out_sentence(String file) {
		SAXReader reader = new SAXReader();
		String ret=null;
		File f = new File(file);
		try {
			Document doc = reader.read(f);
			Element root = doc.getRootElement();
			int count = 0;
			for (Iterator i = root.elementIterator("sentence"); i.hasNext();) {
				Element foo = (Element) i.next();
				count++;
			}
			int random = (int)(Math.random() * count);
			if(random==count)random--;
			int j = 0;
			for (Iterator i = root.elementIterator("sentence"); i.hasNext();) {
				Element foo = (Element) i.next();
				
				if (j == random) {
					ret=foo.elementText("value");
					break;
				}
				j++;
			}

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	public static void main(String[] args) {
		XmlParse parser = new XmlParse();
		System.out.println(parser.out_sentence("src/sentences_request.xml"));
	}

}
