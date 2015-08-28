package com.ming.crf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimePeriodFetcher {
	static Pattern timePeriodPattern;
	/*
	public TimePeriodFetcher(String path) {
		// TODO Auto-generated constructor stub
		File file = new File(path);
		String regexString = "";
		try{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String tempString =null;
			while((tempString=reader.readLine())!=null){
				regexString+=tempString;
			}
		}catch(Exception e){
			System.err.println("Open pattern file error!");
		}
		
		timePeriodPattern = Pattern.compile(regexString);
		return;
	}
	*/
	public static TimePeriod fetchTimePeriod(String sen) {
		//File file = new File("TimePeriod");
		String regexString ="(\\d+|半|全|两|几)个?(天|周|(星期)|(礼拜)|(小时))";
		/*
		try{
			
			 //BufferedReader reader=new BufferedReader(new InputStreamReader(new FileInputStream("TimePeriod"),"utf8"));
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String tempString =null;
			while((tempString=reader.readLine())!=null){
				regexString+=tempString;
			}
			
		}catch(Exception e){
			System.err.println("Open pattern file error!");
		}
		
		System.out.println(regexString);
		*/
		timePeriodPattern = Pattern.compile(regexString);
		TimePeriod tPeriod = new TimePeriod();
		ArrayList<String> resArrayList = tPeriod.getValues();
		if(sen==null)
			return tPeriod;
		sen = StringPreHandler.delKeyword(sen, "\\s+");
		sen = StringPreHandler.numberTranslator(sen);
		String []senStrings = sen.split("[。|，|！|？|；|,|.|;|?|!]");
		Matcher matcher = null;
		for(String s:senStrings){
			if(s.contains("钱")||s.contains("价")||s.contains("元"))
				continue;
			matcher = timePeriodPattern.matcher(s);
			while(matcher.find()){
				resArrayList.add(matcher.group());
			}		
		}
		return tPeriod;
	}
	public static void main(String[] args) {
		String string = "七八天";
		//TimePeriodFetcher tpf = new TimePeriodFetcher("TimePeriod");
		TimePeriod tp = TimePeriodFetcher.fetchTimePeriod(string);
		for(String s: tp.getValues()){
			System.out.println(s);
		}
		System.out.println("done");
	}
}
