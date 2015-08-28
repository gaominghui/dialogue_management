package com.ming.crf;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import NETagger.*;

//import NETagger.NETagger;

public class LocationFetcher {
	/**
	 * 
	 * @param senToParse 待处理的语句
	 * @return Location实体 主要Location实体里面有一个ArrayList存储结果
	 */
	
	public LocationFetcher(){
		try {
			NETagger.Init("crf_2w0w_3uni3bi", null, null);
		} catch (FileNotFoundException e) {
			System.out.println("crf fail to initialize with file error");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("crf fail to initialize with class not found");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("crf fail to initialize with class not IOException");
			e.printStackTrace();
		}
		
	}
	
	public  Location fetchLoc(String senToParse) {
		Location location = new Location();
		ArrayList<String> reStrings = location.getValues();
		
		if(senToParse==null)
			return location;
		
		senToParse = StringPreHandler.delKeyword(senToParse, "\\s+");
		senToParse += "。";
		java.lang.String taggedString = NETagger.getTaggedTXT(senToParse);
		Pattern pattern = Pattern.compile("(?<=<LOC>).*?(?=</LOC>)");
		Matcher m = pattern.matcher(taggedString);
		while(m.find()){
			reStrings.add(m.group());
		}
		return location;
	}
	public static void main(String[]args){
		LocationFetcher fetcher=new LocationFetcher();
		
		String[]str=new String[]{"好吧这是一个神奇的bug","你好，请问在浦东有什么场地推荐下啊？容纳200人左右 ","上海"};
		for(int i=0;i<str.length;i++){
			Location location=fetcher.fetchLoc(str[i]);
			if(location.getValues().size()==0)
				System.out.println("没有地点槽");
			else {
				for(String string :location.getValues()){
					System.out.println(string);
				}
			}
		}

		
		System.out.println("done");

	}
}
