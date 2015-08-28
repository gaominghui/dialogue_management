package com.ming.time;
//package bamboo;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;


public class StringToCRFTexter {
	private
		HashSet<String> firstDic;
		HashSet<String> lastDic;
		HashSet<String> conDic;
		HashSet<String> numDic;
		static final int featureSize = 6;
	
	public StringToCRFTexter(String firstPath,String lastPath,String numPath,String conPath) throws IOException{
		firstDic = readDic(firstPath);
		lastDic = readDic(lastPath);
		conDic = readDic(conPath);
		numDic = readDic(numPath);
	}
	
	public HashSet<String> readDic(String path) throws IOException{
		HashSet<String> result = new HashSet();
		File f = new File(path);
		InputStreamReader read = new InputStreamReader (new FileInputStream(f),"UTF-8");  
		BufferedReader reader = null;
		try{
			reader = new BufferedReader(read);
			String tempStr = null;
			while( (tempStr = reader.readLine())!=null ){
				tempStr = tempStr.trim();
				result.add(tempStr);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(reader!=null)
				reader.close();
		}
		return result;
	}
	
	public ArrayList<String[]> strToCRF(String str){
		ArrayList<String[]> res = new ArrayList<String[]>();
		String curStr = null , tempStr = null;
		
		for(int i = 0; i < str.length(); ++i){
			String tempStrArray[] = new String[featureSize];
			tempStrArray[0] = str.substring(i, i+1);
			if(firstDic.contains(tempStrArray[0]))
				tempStrArray[1] = "1";
			else
				tempStrArray[1] = "0";;
			if(lastDic.contains(tempStrArray[0]))
				tempStrArray[2] = "1";
			else
				tempStrArray[2] = "0";
			if(numDic.contains(tempStrArray[0]))
				tempStrArray[3] = "1";
			else
				tempStrArray[3] = "0";
			if(conDic.contains(tempStrArray[0]))
				tempStrArray[4] = "1";
			else
				tempStrArray[4] = "0";
			
			res.add(tempStrArray);
		}
		
		
		return res;
	}
	
	
	public static void main(String []argv) throws IOException{
		String str = "我想三月底去杭州";
		StringToCRFTexter stc = new StringToCRFTexter("charFirst.dic", "charLast.dic", "charNum.dic","charConjun.dic");
		
		
		
		
		
	}
}
