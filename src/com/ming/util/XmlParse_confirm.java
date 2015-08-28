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

public class XmlParse_confirm {

	public String out_sentence(String file,int flag) {
		SAXReader reader = new SAXReader();
		File f = new File(file);
		String ret=null;
		try {
			Document doc = reader.read(f);
			Element root = doc.getRootElement();
			int count = 0;
			for (Iterator i = root.elementIterator("sentence"); i.hasNext();) {
				Element foo = (Element) i.next();
				if(Integer.parseInt(foo.elementText("flag"))==flag){
					count++;
				}
				//System.out.println(foo.elementText("flag"));
				//System.out.println("id:" + foo.elementText("id"));
				//System.out.println("value:" + foo.elementText("value"));
				//System.out.println(foo.elementText("value"));
			}

			int random = (int) ((Math.random() * count));
			if(random==count)random-=1;
		
			int j = 0;
			for (Iterator i = root.elementIterator("sentence"); i.hasNext();) {
				Element foo = (Element) i.next();
				if(Integer.parseInt(foo.elementText("flag"))==flag){
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
		XmlParse_confirm parser = new XmlParse_confirm();
		System.out.println(parser.out_sentence("src/sentences_request.xml",2));
	}

}
