package com.ming;


import com.ming.slots.Slots_4;

import java.util.*;

import com.ming.crf.*;
import com.ming.util.Activity_slots_info;
import com.ming.util.flag_info;
import com.ming.actions.*;
public class Strategy {
	
	
	public List<agent_actions> match(Slots_4 slots,Fetch fetch,user_attitudes user_attitude){
		/*根据MDP求出的策略
		 * 输出每一个槽求的动作
		 * 返回的是形式是List<agent_actions>
		 */
		
		//根据slots（历史信息）fetch(当前提取信息)user_attitude(用户态度)找到槽对应的动作
		List<agent_actions>message=new ArrayList<agent_actions>();
		int[]activity=slots.getActivity_flag();
		
		for(int i=0;i<slots.getMainSlotNum();i++){
			String tempStr=slots.find(i);
			boolean fetch_flag=fetch.find(i).getValues()==null||fetch.find(i).getValues().size()==0;//没有提取到新的值
			int slot_state=slots.getSlotMainState(i);
			boolean attitude=user_attitude==user_attitudes.agree;
			boolean contain=false;
			if(slot_state==1&&!fetch_flag){
				List<String>old_value=slots.getTemp(tempStr);
				List<String>fetch_value=fetch.find(i).getValues();
				for(String temp :fetch_value){
					if(old_value.contains(temp)){
						contain=true;
						break;
						}
					}
			}
			if(slot_state==0&&activity[i]==0&&fetch_flag){
				message.add(agent_actions.request);
			}//0
			if(slot_state==0&&activity[i]==0&&!fetch_flag){
				message.add(agent_actions.confirm);
			}//1
			if(slot_state==0&&activity[i]==1&&fetch_flag){
				message.add(agent_actions.re_request);
			}//2
			if(slot_state==0&&activity[i]==1&&!fetch_flag){
				message.add(agent_actions.confirm);
			}//3
			if(slot_state==1&&activity[i]==0&&fetch_flag){
				message.add(agent_actions.confirm);
			}//4
			if(slot_state==1&&activity[i]==0&&!fetch_flag&&contain){
				message.add(agent_actions.done);
			}//5
			/*
			 * mark 
			 * 2015-5-6 this has changed from confirm to re_confirm
			 */
			if(slot_state==1&&activity[i]==0&&!fetch_flag&&!contain){
				message.add(agent_actions.re_confirm);
			}//6
			if(slot_state==1&&activity[i]==2&&fetch_flag&&!attitude){
				message.add(agent_actions.dis_request);
			}//7
			if(slot_state==1&&activity[i]==2&&fetch_flag&&attitude){
				message.add(agent_actions.done);
			}//8
			if(slot_state==1&&activity[i]==2&&!fetch_flag&&!attitude&&!contain){
				message.add(agent_actions.re_confirm);
			}//9
			if(slot_state==1&&activity[i]==2&&!fetch_flag&&!attitude&&contain){
				message.add(agent_actions.done);
			}//10
			if(slot_state==1&&activity[i]==2&&!fetch_flag&&attitude&&!contain){
				message.add(agent_actions.re_confirm);
			}//11
			if(slot_state==1&&activity[i]==2&&!fetch_flag&&attitude&&contain){
				message.add(agent_actions.done);
			}//12
			if(slot_state==2){
				message.add(agent_actions.ok);
			}//13
			if(slot_state==0&&activity[i]==3&&fetch_flag){
				message.add(agent_actions.request);
			}//14
			if(slot_state==0&&activity[i]==3&&!fetch_flag){
				message.add(agent_actions.confirm);
			}//15
			
		}

	
		
		
		
		//return message;
		//System.out.println(message);
		return message;
		
	}
	

}
