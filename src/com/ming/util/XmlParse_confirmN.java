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

public class XmlParse_confirmN {

	public String out_sentence(String file,String flag) {
		SAXReader reader = new SAXReader();
		File f = new File(file);
		String ret=null;
		try {
			Document doc = reader.read(f);
			Element root = doc.getRootElement();
			int count = 0;
			for (Iterator i = root.elementIterator("sentence"); i.hasNext();) {
				Element foo = (Element) i.next();
				if(foo.elementText("flag").equals(flag)){
					count++;
				}
				
			}
			
			int random = (int)(Math.random() * count);
			if(random==count)random-=1;
			int j = 0;
			for (Iterator i = root.elementIterator("sentence"); i.hasNext();) {
				Element foo = (Element) i.next();
				if(foo.elementText("flag").equals(flag)){
					if (j == random) {
						ret=foo.elementText("value");
						break;
					}
					j++;
					
				}
				
			}

			

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	public static void main(String[] args) {
		XmlParse_confirmN parser = new XmlParse_confirmN();
		System.out.println(parser.out_sentence("sentences_request_N.xml","PerNum_Time"));
	}

}
