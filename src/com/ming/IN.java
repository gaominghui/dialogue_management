package com.ming;

import java.io.*;
import java.util.regex.*;

public class IN {
	/*
	 * 本地测试的时候用来做用户输入，已经废弃
	 */
	public  String[] user_in(String sentence){
		sentence=sentence.substring(2);
		Pattern pattern=Pattern.compile("[,|，]");
		String[]s=new String[5];
		String[]splits=pattern.split(sentence);
		int i=0;
		for(;i<splits.length;i++){
			if(splits[i].equals("")||splits[i].equals("\\s")||splits.equals(" ")){
				s[i]=null;
				continue;
			}
			s[i]=splits[i];
		}
		while(i<5){
			s[i]=null;
			i++;
		}
		return s;
	}
	public  String user_in_sentence(String file){
		String string= null;
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String str = null;
			while ((str = br.readLine()) != null) {
				string= str;
		
			}
			br.close();
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return string;
	}
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IN in=new IN();
		String sentence=in.user_in_sentence("./src/user_in.txt");
		String[]slots=in.user_in(sentence);
		System.out.println(sentence);
		for(String slot:slots){
			System.out.println(slot);
		}
		
	}
	*/

}
