package com.ming.time;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import com.ming.crf.Time;


public class TimeFetcher {
		
	private class Pair { 
		public  int first;
		public  int second;
	}
	
	private Tagger tagger;
	
	public  TimeFetcher(String textModelFilename,String textModelCharSet) {	
		boolean textModelIsGZipped = false;
		textModelFilename = "model1213.txt";
		textModelCharSet = "UTF-8";
		File textModelFile = new File(textModelFilename);
		tagger = new CRFTagger(textModelFile, textModelIsGZipped, textModelCharSet);
		
	}
	
	private String[] segment(String text) throws IOException {
		if (text == null || text.length() == 0) {
			return null;
		}
		
		StringToCRFTexter stct = new StringToCRFTexter("charFirst.dic", "charLast.dic", "charNum.dic","charConjun.dic");
		
		List<String[]> features = stct.strToCRF(text);
		
		String[] tags = null;
		if (!features.isEmpty()) {
			tags = tagger.tag(features);
		}	
		
		return tags;
	}
	
	
	private List<Pair> postHandle(String[] tags){
		
		if(tags == null)
			return null;
		
		List<Pair> res = new ArrayList<Pair>();
		
		int pre = 0, cur = 0;
		boolean isDetected = false;
		
		Pair tempPair = new Pair();
		
		for(int i = 0; i < tags.length; ++i){
			if(!isDetected && !tags[i].equals("O")){
				isDetected = true;
				tempPair.first = i;
			}
			else if(isDetected && tags[i].equals("B")){
				isDetected = true;
				tempPair.second = i;
				res.add(tempPair);
				tempPair = new Pair();
				tempPair.first = i;
			}
			else if(isDetected && tags[i].equals("I") )
				continue;
			else if(isDetected && tags[i].equals("E") ){
				isDetected = false;
				tempPair.second = i+1;
				res.add(tempPair);
				tempPair = new Pair();
			}
			else if(isDetected && tags[i].equals("O")){
				isDetected = false;
				tempPair.second = i;
				res.add(tempPair);
				tempPair = new Pair();
			}
		}
		
		return res;
	}
	
	
	public List<String>pairToValue(List<Pair> pairs,String str){
		if(str == null)
			return Collections.EMPTY_LIST;
		List<String> res = new ArrayList<String>();
		
		for(int i = 0; i < pairs.size(); ++i){
			res.add(str.substring(pairs.get(i).first, pairs.get(i).second));
		}
		return res;
	}

	private static Pattern readModel(String file) throws Exception {
		ObjectInputStream in = new ObjectInputStream(new BufferedInputStream 
				(new GZIPInputStream (new FileInputStream(file))));
		return readModel(in);
	}

	private static Pattern readModel(ObjectInputStream in) throws Exception {

		Pattern p = (Pattern) in.readObject();
		return p=Pattern.compile(p.pattern());
	
	}

	
	public Time fetchTime(String in) {
		in = "^" + in;
        Time time=new Time();	
		List<String> res=null;
        	
			try {
				res = pairToValue(postHandle(segment(in)), in);
				if(res == null)
				{
					Pattern p = readModel("time.m");
					System.out.println(p.toString());
					Matcher m = p.matcher(in);
					while(m.find())
						res.add(m.group());
				}	
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			time.setValues(res);
		return time;
		//return pairToValue(postHandle(segment(in)), in);
	}
	
	public static void main(String[] args) throws Exception {
		File f = new File("");
		String s = "明天晚上11";
		TimeFetcher t = new TimeFetcher("", "");
		List<String> res = t.fetchTime(s).getValues();
		for(String ss: res)
			System.out.println(ss);
		System.out.println("done");
		
	}
}
