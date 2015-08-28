package com.ming.crf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ming.time.TimeFetcher;

public class fileDealer {
	public static void replaceSlots(String in,String out) throws IOException{
		LocationFetcher lcf = new LocationFetcher();
		FileWriter writer = new FileWriter(out, false);
		
		File file = new File(in);
        BufferedReader reader = null;
        reader = new BufferedReader(new FileReader(file));
        String tempString = null;
        // 一次读入一行，直到读入null为文件结束
        while ((tempString = reader.readLine()) != null) {
        	System.out.println(tempString);
        	Location lc = lcf.fetchLoc(tempString);
        	ArrayList<String> locs = lc.getValues();
        	
        	for(String s: locs){
        		tempString = tempString.replaceFirst(s, "地点");
        	}
        	
        	
        	TimePeriodFetcher tpf = new TimePeriodFetcher();
        	TimePeriod tp = tpf.fetchTimePeriod(tempString);
        	ArrayList<String> tps = tp.getValues();
        	
        	for(String s:tps){
        		tempString = tempString.replace(s, "时段");
        	}
        	
       
        	TimeFetcher tfc = new TimeFetcher("", "");
        	List<String> ts = tfc.fetchTime(tempString).getValues();
        	for(String s : ts){
        		tempString = tempString.replace(s,"时间");
        	}
        	
        	writer.write(tempString+"\n");
        }
        reader.close();
  
        writer.close();
        reader.close();
	}
	
	public static void main(String[] args) throws IOException{
		fileDealer.replaceSlots("dataPh", "dataSlots");
	}
	
}
