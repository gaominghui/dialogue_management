package com.ming.slots;

import java.util.ArrayList;

import java.util.List;

import com.ming.actions.agent_actions;
import com.ming.actions.user_attitudes;
import com.ming.crf.Fetch;
import com.ming.util.Activity_slots_info;
import com.ming.util.flag_info;

public class Slots_refresh_4 {

	/**
	 * 通过底层MDP的动作来更新槽的值
	 * 
	 * 
	 */
	public static void refresh(Slots_4 slots_4, List<agent_actions> message,Fetch fetch) {
		// TODO Auto-generated method stub
		
		for(int i=0;i<slots_4.getMainSlotNum();i++){
			agent_actions action=message.get(i);
			//如果是request说明槽没有值
			if(action==agent_actions.request){
				//do noting
			}
			else if(action==agent_actions.re_request||action==agent_actions.dis_request){
				//原来的值不对，删掉
				if(slots_4.getTemp(slots_4.find(i))!=null){
					slots_4.modifyTemp(slots_4.find(i), null);
				}
			}
			else if(action==agent_actions.confirm){
				//如果没有值，则添加
				if(slots_4.getTemp(slots_4.find(i))==null){
					slots_4.modifyTemp(slots_4.find(i),fetch.find(i).getValues());
				}else{
					slots_4.addTemp(slots_4.find(i), fetch.find(i).getValues());
				}
			}
			else if(action==agent_actions.re_confirm){
				//修改原来的值
				slots_4.modifyTemp(slots_4.find(i),fetch.find(i).getValues());
			}
			
			else if(action==agent_actions.done){
				//完成
				List<String>value=fetch.find(i).getValues();
				List<String>old_value=slots_4.getTemp(slots_4.find(i));
				
				List<String>ret=new ArrayList<String>();
				if(value==null||value.size()==0){
					
					slots_4.modify(slots_4.find(i), old_value);
					//System.out.println(slots_4.get(slots_4.find(i)).get(0));
				}
				else{
					//if(old_value==null)System.out.println("old_vl");
					for(String temp:value){
						if(old_value.contains(temp)){
							ret.add(temp);
						}
					}
					slots_4.modify(slots_4.find(i), ret);
				}
				
			}
			/*
			if(action==agent_actions.ok){
				//do nothing
			}
			*/
		}
		
	}

}
