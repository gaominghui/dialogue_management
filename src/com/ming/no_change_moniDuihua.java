package com.ming;

import java.io.*;
import java.util.List;
import java.util.Scanner;

import com.ming.actions.*;
import com.ming.slots.*;
import com.ming.util.*;
import com.ming.crf.*;

public class no_change_moniDuihua {
	/**
	 * 最原始的版本, 废弃类
	 */
	
	public  void duihua(Slots_4 slots_4) {

		String message = null;
		
		Strategy strategy = new Strategy();
		IN in=new IN();
		OUT out=new OUT();
		pos_neg p_n=new pos_neg();
		agent_doActions_2015 agent_action_2015 = new agent_doActions_2015();
		while(true ){
		try {
			

			String user_sentence=in.user_in_sentence("./src/user_in.txt");
			

			user_attitudes attitude;
			if(p_n.attitue(user_sentence)){
				attitude=user_attitudes.agree;
			}else 
				attitude=user_attitudes.disagree;
		
			
			System.out.println("user:"+user_sentence);
			agent_action_2015.getFetch().fetch(user_sentence,slots_4.getActivity_flag());
			//Slots_refresh_4.refresh(slots_4, fetch, attitude);
			
			
			/*
			//message=strategy.match(slots_4);
			//System.out.println(message);
			if(message.equals("done")){
				
				return;
			}
			*/
			List<agent_actions>list=strategy.match(slots_4, agent_action_2015.getFetch(), attitude);
			agent_action_2015.setAction(list);
			for(int i=0;i<list.size();i++){
				System.out.print(list.get(i)+" ");
			}
			System.out.println();
			String sentence=agent_action_2015.act(slots_4);
			for(int i=0;i<slots_4.getMainSlotNum();i++){
				System.out.print(slots_4.getMainState()[i]+" ");
			}
			System.out.println();
		
			System.out.print("activity: ");
			for(int i=0;i<slots_4.getMainSlotNum();i++){
				System.out.print(slots_4.getActivity_flag()[i]+" ");
			}
			System.out.println();
			
			System.out.println("agent:"+sentence);
			
			if(sentence.equals("谢谢合作,我们会尽快跟您联系的")){
				
				slots_4.printMain();
				return;
			}
			out.agent_out("./src/agent_out.txt",sentence);
		
			Thread.sleep(20000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 }
		 
	}

	public static void main(String[] args) {
		no_change_moniDuihua mD = new no_change_moniDuihua();
		Slots_4 slots_4 = new Slots_4();
		mD.duihua(slots_4);

	}

}
