package com.ming.util;

import java.io.File;
import java.util.*;
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

public class XmlParse_confirm_select {

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

			int random = (int) (Math.random() * count);
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
		XmlParse_confirm_select parser = new XmlParse_confirm_select();
		List<String>list=new LinkedList<String>();
		list.add("北京");
		list.add("上海");
		list.add("天津");
		String sentence=parser.out_sentence("src/sentences_confirm_select.xml",0);
		sentence=sentence.replaceAll("XXXXX", list.get(0));
		for(int i=1;i<list.size();i++){
			sentence+="还是"+list.get(i)+",";
		}
		sentence=sentence.substring(0,sentence.length()-1)+"呢?";
		System.out.println(sentence);
	}

}
