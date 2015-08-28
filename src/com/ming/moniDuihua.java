package com.ming;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import com.ming.actions.*;
import com.ming.slots.*;
import com.ming.util.*;
import com.ming.crf.*;

public class moniDuihua {
	/**
	 * 这个类是原来的python,flask的网络调用，一次只能有一个用户测试，所以已经废弃
	 * 没有什么用了，不用看了
	 * 
	 */
	agent_doActions_2015 agent_action_2015 = new agent_doActions_2015();
	Slots_4 slots_4 = new Slots_4();
	Strategy strategy = new Strategy();
	pos_neg p_n=new pos_neg();
	
	StringBuffer sentence;


	Map<String, String> map1 = new HashMap<String, String>();


	File log=new File("./Logs/"+System.currentTimeMillis()+".txt");
	FileOutputStream fos=null;
	OutputStreamWriter osw=null;
	BufferedWriter bw=null;

	public  String  duihua(String user_sentence) throws JSONException, IOException {
		 sentence=new StringBuffer();


		
			user_attitudes attitude;
			if(p_n.attitue(user_sentence)){
				attitude=user_attitudes.agree;
			}else 
				attitude=user_attitudes.disagree;
		
			JSONObject jo = new JSONObject();
			boolean end=false;
		
			agent_action_2015.getFetch().fetch(user_sentence,slots_4.getActivity_flag());

			List<agent_actions>list=strategy.match(slots_4, agent_action_2015.getFetch(), attitude);
			
			agent_action_2015.setAction(list);
			
			for(int i=0;i<list.size();i++){
				System.out.print(list.get(i)+" ");
			}
			System.out.println();
			System.out.print("activity: ");
			for(int i=0;i<slots_4.getMainSlotNum();i++){
				System.out.print(slots_4.getActivity_flag()[i]+" ");
			}
			System.out.println();
			sentence.append(agent_action_2015.act(slots_4));
			
			
			//	sentence="你好，"+sentence;
			try{
				fos=new FileOutputStream(log,true);
				osw=new OutputStreamWriter(fos);
				bw=new BufferedWriter(osw);
				bw.write("user	:	"+user_sentence+"\n");
				bw.write("agent:	"+sentence+"\n");
				bw.flush();
			}catch(IOException exception){
				exception.printStackTrace();
			}
			if(sentence.toString().equals("预订成功，谢谢合作，我们会尽快跟您联系的。")){
				end=true;	
			}
			
			jo.put("sentence", sentence.toString());
			
			String locations=this.slots_4.attributeTOString(0);
		     String perNum=this.slots_4.attributeTOString(1);
		     String date=this.slots_4.attributeTOString(2);
		     String duration=this.slots_4.attributeTOString(3);
		     String price=this.slots_4.attributeTOString(4);
		
		     map1.put("meet-location", locations);
		     map1.put("meet-number",perNum);
		     map1.put("meet-date", date);
		     map1.put("meet-duration",duration);
		     map1.put("meet-price",price);
		     jo.put("slots", map1);
		 
		     if(end){
		    	 jo.put("end", true);
			    bw.write("\n\n地点：");  
			    bw.write(locations.toString());
			    bw.write("\n人数：");
			    bw.write(perNum.toString());
			     bw.write("\n日期：");
			     bw.write(date.toString());
			     bw.write("\n时长：");
			     bw.write(duration.toString());
			     bw.write("\n预算：");
			    bw.write(price.toString());
			 	bw.close();
				log=new File("./Logs/"+System.currentTimeMillis()+".txt");
			    slots_4.clear();
		     }
		   
			return jo.toString();
			
		
		
		 
		 
	}

	public static void main(String[] args) throws JSONException, IOException {
		moniDuihua mD = new moniDuihua();
		System.out.println(mD.duihua("我想咨询一下，深圳福田区450人开会的场地,"));

	}

}
