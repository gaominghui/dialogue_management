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


public class Client_moniDuihua {
	//agent_do_Actions_2015类根据当前状态决定系统要说的话
	agent_doActions_2015 agent_action_2015 = new agent_doActions_2015();
	//底层策略类
	Strategy strategy = new Strategy();
	//检测用户态度类
	pos_neg p_n=new pos_neg();
	
	//临时变量
	StringBuffer sentence;
	Map<String, String> map1 = new HashMap<String, String>();
	
	//对话输出的文件和名称
	File log=new File(new File("").getAbsolutePath()+"/WebContent/Logs/"+System.currentTimeMillis()+".txt");
	//File log=new File(this.getClass().getClassLoader().getResource("")+"/Logs/"+System.currentTimeMillis()+".txt");
	FileOutputStream fos=null;
	OutputStreamWriter osw=null;
	BufferedWriter bw=null;
	
	public  String  duihua(String user_sentence,Slots_4 slots_4) throws JSONException, IOException {
		 
			sentence=new StringBuffer();
			//获取用户态度
			user_attitudes attitude;
			if(p_n.attitue(user_sentence)){
				attitude=user_attitudes.agree;
			}else 
				attitude=user_attitudes.disagree;
		
			JSONObject jo = new JSONObject();
			//end变量决定对话是否结束，决定网页上的最终的一些变化比如对话结束后不能再输入，指示变量就是end
			boolean end=false;
			//agent_action_2015类里有一个Fetch类实例，fetch类用来提取信息和存储信息
			agent_action_2015.getFetch().fetch(user_sentence,slots_4.getActivity_flag());
			//输出底层动作
			List<agent_actions>list=strategy.match(slots_4, agent_action_2015.getFetch(), attitude);
	
			agent_action_2015.setAction(list);
			//上层MDP最终要产生的动作
			/*
			 * Final_actions,是枚举类，上层MDP的所有动作，在com.ming.actions包
			 * action_strategy是策略类，作用同strtegy类
			 * 实际上这里不用显示的写出因为在agent_action_2015有这个方法
			 * 但是为了能在文件里也能知道系统最终的动作是什么就在这里又写了一遍
			 */
			Final_actions final_action=action_strategy.match(list);
			//sentence是最终产生的语言
			sentence.append(agent_action_2015.act(slots_4));
			
			
			
			//	写入到文件
			try{
				fos=new FileOutputStream(log,true);
				osw=new OutputStreamWriter(fos);
				bw=new BufferedWriter(osw);
				bw.write("user	:	"+user_sentence+"\n");
				bw.write("agent:	"+sentence+"\n");
				bw.write("slots_state:"+"\n");
				for(int i=0;i<slots_4.getMainSlotNum();i++){
					bw.write(slots_4.getMainState()[i]+"\t");
				}
				bw.write("\n"+"activity_flag:"+"\n");
				
				for(int i=0;i<slots_4.getMainSlotNum();i++ ){
					bw.write(slots_4.getActivity_flag()[i]+"\t");
				}
				bw.write("\n");
				for(agent_actions action:list){
					bw.write(action.toString()+"\t");
				}
				bw.write("\n");
				bw.write(final_action.toString()+"\n"+"-----------------------------\n");
				
				bw.flush();
			}catch(IOException exception){
				exception.printStackTrace();
			}
			//对话结束只会产生一句话"预订成功，谢谢合作，我们会尽快跟您联系的。"
			//如果产生这句话就是结束了
			if(sentence.toString().equals("预订成功，谢谢合作，我们会尽快跟您联系的。")){
				end=true;
			}
			//通过网络输出给用户
			jo.put("sentence", sentence.toString());
			
			String locations=slots_4.attributeTOString(0);
		     String perNum=slots_4.attributeTOString(1);
		     String date=slots_4.attributeTOString(2);
		     String duration=slots_4.attributeTOString(3);
		     String price=slots_4.attributeTOString(4);
		
		     map1.put("meet-location", locations);
		     map1.put("meet-number",perNum);
		     map1.put("meet-date", date);
		     map1.put("meet-duration",duration);
		     map1.put("meet-price",price);
		     jo.put("slots", map1);
		 //如果对话结束需要把提取到的信息写到文件里
		  //还需要把slots_4清空
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
				log=new File(new File("").getAbsolutePath()+"/WebContent/Logs/"+System.currentTimeMillis()+".txt");
			    slots_4.clear();
		     }
		   
			return jo.toString();
			
		
		
		 
		 
	}

	public static void main(String[] args) throws JSONException, IOException {
		Client_moniDuihua mD = new Client_moniDuihua();
		Slots_4 slots=new Slots_4();
		System.out.println(mD.duihua("三天的会议,",slots));

	}

}
