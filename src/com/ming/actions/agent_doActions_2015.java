package com.ming.actions;

import com.ming.util.*;
import com.ming.crf.Fetch;
import com.ming.slots.*;

import java.io.IOException;
import java.util.*;
public class agent_doActions_2015 {
	
	//private XmlParse xmlparser =new XmlParse();
	//xmlparse都是工具类，在com.ming.web中
	private XmlParse_confirm xmlparser_confirm =new XmlParse_confirm();
	private XmlParse_confirmN xmlparser_confirmN =new XmlParse_confirmN();
	private XmlParse_confirm_select xmlparser_confirm_select =new XmlParse_confirm_select();
	//底层MDP返回的动作集合
	private List<agent_actions> actions;
	//每一种动作和对应的个数
	private Hashtable<agent_actions,Integer>table;
	

	public Fetch getFetch() {
		return fetch;
	}



	public void setFetch(Fetch fetch) {
		this.fetch = fetch;
	}
	private Fetch fetch;
	//构造函数
	public agent_doActions_2015(){
		fetch=new Fetch();
	}

	
	
	public List<agent_actions> getAction() {
		return actions;
	}
	//setAction()在Client_moniduihua中有显式的调用
	public void setAction(List<agent_actions> list) {
		/*
		 * for循环计算每一种动作的次数
		 */
		this.actions = list;
		int request=0;
		int confirm=0;
		int re_request=0;
		int dis_request=0;
		int re_confirm=0;
		
		for(agent_actions action:list){
			if(action.equals(agent_actions.request)){
				request+=1;
			}
			if(action.equals(agent_actions.confirm)){
				confirm+=1;
			}
			if(action.equals(agent_actions.re_request)){
				re_request+=1;
			}if(action.equals(agent_actions.dis_request)){
				dis_request+=1;
			}if(action.equals(agent_actions.re_confirm)){
				re_confirm+=1;
			}
		}
			if(table==null){
				table=new Hashtable<agent_actions,Integer>();	
			}else {
				table.clear();
			}
			
			
			table.put(agent_actions.request, request);
			table.put(agent_actions.confirm, confirm);
			table.put(agent_actions.re_request, re_request);
			table.put(agent_actions.dis_request, dis_request);
			table.put(agent_actions.re_confirm, re_confirm);
		
		
	}
	
	
	public String act(Slots_4 Slots_4){
		/*
		 * act 是这个类的主函数
		 */
		
		//activity_his 保存上一次的activity
		int []activity_his=new int[Slots_4.getMainSlotNum()];
		for(int i=0;i<activity_his.length;i++){
			activity_his[i]=Slots_4.getActivity_flag()[i];
		}
		//int []activity_his=Slots_4.getActivity_flag();
		
		//更新槽值
		Slots_refresh_4.refresh(Slots_4, this.getAction(), fetch);
		
		
		//Activity_slots全部置位0
		Activity_slots_info.reset(Slots_4.getActivity_flag());
		String ret="";
		//最终动作类型
		Final_actions final_action=action_strategy.match(this.getAction(), table);
		/////////////////////////
		//根据最终动作类型选择执行的动作
		
		if(final_action.equals(Final_actions.End)){
			ret="预订成功，谢谢合作，我们会尽快跟您联系的。";
			
		}
		if(final_action.equals(Final_actions.requestN)){
			
			ret=request(Slots_4,table.get(agent_actions.request));
		}
		if(final_action.equals(Final_actions.ConfirmN)){
			ret=confirmN(Slots_4,activity_his,table.get(agent_actions.confirm));
		}
		if(final_action.equals(Final_actions.re_confirm_re_request)){
			ret=re_confirm_re_reqeust(Slots_4,activity_his,table);
		}
		if(final_action.equals(Final_actions.re_confirm_dis_request)){
			ret=re_confirm_dis_request(Slots_4,activity_his,table);
		}
		if(final_action.equals(Final_actions.re_confirm_requestN)){
			ret=re_confirm_requestN(Slots_4,activity_his,table);
		}
		if(final_action.equals(Final_actions.confirmN_re_request)){
			ret=confirmN_re_request(Slots_4,activity_his,table);
		}

		if(final_action.equals(Final_actions.confirmN_dis_request)){
			ret=confirmN_dis_request(Slots_4,activity_his,table);
		}
		if(final_action.equals(Final_actions.confirmN_requestN)){
			ret=confirmN_requestN(Slots_4,activity_his,table);
		}
		if(final_action.equals(Final_actions.re_confirm)){
			ret=re_confirm(Slots_4,activity_his,table,null);
		}
		if(final_action.equals(Final_actions.re_request)){
			ret=re_request(Slots_4,activity_his,table);
		}
		if(final_action.equals(Final_actions.dis_request)){
			ret=dis_request(Slots_4,activity_his,table);
		}
		
		return ret;

	}
	

	public String re_confirm_re_reqeust(Slots_4 slots_4, int[] activity_his,
			Hashtable<agent_actions, Integer> table) {
		// TODO Auto-generated method stub
		int re_confirmNum=table.get(agent_actions.re_confirm);
		int re_requestNum=table.get(agent_actions.re_request);
		agent_actions re_confirm_zuhe=null;
		if(re_confirmNum==1){
			re_confirm_zuhe=agent_actions.re_request;
			return re_confirm(slots_4,activity_his,table,re_confirm_zuhe)+
					re_request(slots_4,activity_his,table);
		}else{
			return re_confirm(slots_4,activity_his,table,re_confirm_zuhe);
		}
		
	}



	public String re_confirm_dis_request(Slots_4 slots_4, int[] activity_his,
			Hashtable<agent_actions, Integer> table) {
		// TODO Auto-generated method stub
		int re_confirmNum=table.get(agent_actions.re_confirm);
		int dis_requestNum=table.get(agent_actions.dis_request);
		agent_actions re_confirm_zuhe=null;
		if(re_confirmNum==1){
			re_confirm_zuhe=agent_actions.dis_request;
			return re_confirm(slots_4,activity_his,table,re_confirm_zuhe)+
					dis_request(slots_4,activity_his,table);
		}else{
			return re_confirm(slots_4,activity_his,table,re_confirm_zuhe);
		}
		
	}



	public String re_confirm_requestN(Slots_4 slots_4, int[] activity_his,
			Hashtable<agent_actions, Integer> table) {
		// TODO Auto-generated method stub
		int re_confirmNum=table.get(agent_actions.re_confirm);
		agent_actions re_confirm_zuhe=null;
		if(re_confirmNum==1){
			re_confirm_zuhe=agent_actions.request;
			return re_confirm(slots_4,activity_his,table,re_confirm_zuhe)
					+request(slots_4,1);
			
		}else{
			return re_confirm(slots_4,activity_his,table,re_confirm_zuhe);
		}
	}



	private String confirmN_re_request(Slots_4 slots_4, int[] activity_his,
			Hashtable<agent_actions, Integer> table) {
		// TODO Auto-generated method stub
		int re_requestNum=table.get(agent_actions.re_request);
		int ConfirmNum=table.get(agent_actions.confirm);
		
		if(ConfirmNum==1){
			int his1Num=0;
			for(int i=0;i<activity_his.length;i++){
				if(activity_his[i]==1)
					his1Num++;
			}
			
			int len=slots_4.getMainSlotNum();
		
			int ret=-1;
			if(his1Num>0){
				for(int i=0;i<len;i++){
					if(activity_his[i]==1&&this.getAction().get(i).equals(agent_actions.confirm)){
						ret=i;break;
					}
				}//for
			}//if
			if(ret==-1){
				for(int i=0;i<len;i++){
					if(this.getAction().get(i).equals(agent_actions.confirm)){
						ret=i;break;
					}
				}//for
			}
			
			
			return confirm(slots_4,ret,"sentences_confirm_re_request_confirm.xml")+
					re_request(slots_4,activity_his,table);
		}else{
			return confirmN(slots_4, activity_his, ConfirmNum);
		}
		
		
	}



	public String confirmN_dis_request(Slots_4 slots_4, int[] activity_his,
			Hashtable<agent_actions, Integer> table) {
		// TODO Auto-generated method stub
		int dis_requestNum=table.get(agent_actions.dis_request);
		if(dis_requestNum==1){
			int his1Num=0;
			for(int i=0;i<activity_his.length;i++){
				if(activity_his[i]==1)
					his1Num++;
			}
			
			int len=slots_4.getMainSlotNum();
			//double d=0.0;
			int ret=-1;
			if(his1Num>0){
				for(int i=0;i<len;i++){
					if(activity_his[i]==1&&this.getAction().get(i).equals(agent_actions.confirm)){
						
						ret=i;break;
					}
				}//for
			}//if
			if(ret==-1){
				for(int i=0;i<len;i++){
					if(this.getAction().get(i).equals(agent_actions.confirm)){
						
						ret=i;break;
					}
				}//for
			}//else
			/*
			System.out.println(ret);
			String string1=confirm(slots_4,ret,"sentences_confirm.xml");
			System.out.println(string1);
			String string2=dis_request(slots_4,activity_his,table);
			System.out.println(string2);
			return string1+string2;
			*/
			return 	dis_request(slots_4,activity_his,table)+
					confirm(slots_4,ret,"sentences_confirm.xml");
			
			
		}else{
			return dis_request(slots_4,activity_his,table);
		}
	}



	public String confirmN_requestN(Slots_4 slots_4, int[] activity_his,
			Hashtable<agent_actions, Integer> table) {
		// TODO Auto-generated method stub
		
		int his1Num=0;
		for(int i=0;i<activity_his.length;i++){
			if(activity_his[i]==1)
				his1Num++;
		}
		
		int len=slots_4.getMainSlotNum();
		double d=0.0;
		int ret=-1;
		if(his1Num>0){
			for(int i=0;i<len;i++){
				if(activity_his[i]==1&&this.getAction().get(i).equals(agent_actions.confirm)){
					ret=i;break;
				}
			}//for
		}//if
		if(ret==-1){
			for(int i=0;i<len;i++){
				if(this.getAction().get(i).equals(agent_actions.confirm)){
					ret=i;break;
				}
			}//for
		}//else
		return confirm(slots_4,ret,"sentences_confirm_request_confirm.xml")+request(slots_4,1);
		
	}



	public String re_confirm(Slots_4 slots_4, int[] activity_his,
			Hashtable<agent_actions, Integer> table,agent_actions re_confirm_zuhe) {
		// TODO Auto-generated method stub
		int re_confirmNum=table.get(agent_actions.re_confirm);
		if(re_confirmNum==1){
			int re_confirmindex=-1;
			for(int i=0;i<this.getAction().size();i++){
				if(this.getAction().get(i).equals(agent_actions.re_confirm)){
					re_confirmindex=i;
					break;
				}
			}//for
			Activity_slots_info.modify(slots_4.getActivity_flag(),re_confirmindex,2);
			if(re_confirm_zuhe==null)
				return confirm(slots_4,re_confirmindex,"sentences_confirm.xml");
			else if(re_confirm_zuhe.equals(agent_actions.dis_request))
				return confirm(slots_4,re_confirmindex,"sentences_confirm_request_confirm.xml");
			else if(re_confirm_zuhe.equals(agent_actions.request))
				return confirm(slots_4,re_confirmindex,"sentences_confirm_request_confirm.xml");
			else if(re_confirm_zuhe.equals(agent_actions.re_request))
				return confirm(slots_4,re_confirmindex,"sentences_confirm_re_request_confirm.xml");
			else return "this is a bug"; 
		}else{
			int re_confirm_index1=-1;
			int re_confirm_index2=-1;
			for(int i=0;i<this.getAction().size();i++){
				if(this.getAction().get(i).equals(agent_actions.re_confirm)){
					re_confirm_index1=i;break;
				}
			}
			
			for(int i=re_confirm_index1+1;i<this.getAction().size();i++){
				if(this.getAction().get(i).equals(agent_actions.re_confirm)){
					re_confirm_index2=i;break;
				}
			}//for
			return confirmTwoorSelect(slots_4,re_confirm_index1,re_confirm_index2);
			
		}
		
	}
	

	public String re_request(Slots_4 slots_4, int[] activity_his,
			Hashtable<agent_actions, Integer> table) {
		// TODO Auto-generated method stub
		int re_requestNum=table.get(agent_actions.re_request);
		if(re_requestNum==1||re_requestNum==2){
			int re_request_index=-1;
			for(int i=0;i<this.getAction().size();i++){
				if(this.getAction().get(i).equals(agent_actions.re_request)){
					re_request_index=i;break;
				}
			}
			Activity_slots_info.modify(slots_4.getActivity_flag(),re_request_index,3);
			String sentence=xmlparser_confirm.out_sentence("sentences_re_request_2015.xml", re_request_index);
			return sentence;
		}else{
			if(re_requestNum==5){
				return request(slots_4,5);
			}else {
				int re_request_index1=-1;
				int re_request_index2=-1;
				for(int i=0;i<this.getAction().size();i++){
					if(this.getAction().get(i).equals(agent_actions.re_request)){
						re_request_index1=i;break;
					}
				}
				
				for(int i=re_request_index1+1;i<this.getAction().size();i++){
					if(this.getAction().get(i).equals(agent_actions.re_request)){
						re_request_index2=i;break;
					}
				}//for
				//System.out.println(re_request_index1+" "+re_request_index2);
				Activity_slots_info.modify(slots_4.getActivity_flag(),re_request_index1,3);
				Activity_slots_info.modify(slots_4.getActivity_flag(),re_request_index2,3);
				
				String slots=slots_4.find(re_request_index1).substring(4)+"_"+
						slots_4.find(re_request_index2).substring(4);
				//System.out.println(slots);
				String sentence=xmlparser_confirmN.out_sentence("sentences_request_N.xml", slots);
				//System.out.println(sentence);
				return sentence;
				
				
				
			}//else
			
		}
	}



	public String dis_request(Slots_4 slots_4, int[] activity_his,
			Hashtable<agent_actions, Integer> table) {
		// TODO Auto-generated method stub
		int dis_requestNum=table.get(agent_actions.dis_request);
		if(dis_requestNum==1){
			int dis_request_index=-1;
			for(int i=0;i<this.getAction().size();i++){
				if(this.getAction().get(i).equals(agent_actions.dis_request)){
					dis_request_index=i;break;
				}
			}
			Activity_slots_info.modify(slots_4.getActivity_flag(),dis_request_index,1);
			String sentence=xmlparser_confirm.out_sentence("sentences_dis_request.xml", dis_request_index);
			return sentence;
		}else{
			int dis_request_index1=-1;
			int dis_request_index2=-1;
			for(int i=0;i<this.getAction().size();i++){
				if(this.getAction().get(i).equals(agent_actions.dis_request)){
					dis_request_index1=i;break;
				}
			}
			
			for(int i=dis_request_index1+1;i<this.getAction().size();i++){
				if(this.getAction().get(i).equals(agent_actions.dis_request)){
					dis_request_index2=i;break;
				}
			}//for
			Activity_slots_info.modify(slots_4.getActivity_flag(),dis_request_index1,1);
			Activity_slots_info.modify(slots_4.getActivity_flag(),dis_request_index2,1);
			return "您不同意确认，究竟是哪个错了呢？是"+ToChineseUtil.toChinese(dis_request_index1)
			+"还是"+ToChineseUtil.toChinese(dis_request_index2)+"呢？还是都错了？";
		}
		
	}



	public String confirmN(Slots_4 slots_4, int[] activity_his,  int N) {
		// TODO Auto-generated method stub
		if(N==1){
			int sloti=-1;
			for(int i=0;i<this.getAction().size();i++){
				if(this.getAction().get(i).equals(agent_actions.confirm)){
					sloti=i;break;
				}
			}//for
			Activity_slots_info.modify(slots_4.getActivity_flag(),sloti,2);
			
			return confirm(slots_4,sloti,"sentences_confirm.xml");
			
		}else{
			return chooseTwoConfirm(slots_4, activity_his);
		}
		
		
		
	}


	
	public String chooseTwoConfirm(Slots_4 slots_4,int[]activity_his ){
			int his1Num=0;
			

			for(int i=0;i<activity_his.length;i++){
				if(activity_his[i]==1)
					his1Num++;
			}
			
			int len=slots_4.getMainSlotNum();
			
			int ret1=-1;
			int ret2=-1;
			
			if(his1Num>0){
				for(int i=0;i<len;i++){
					if(activity_his[i]==1&&this.getAction().get(i).equals(agent_actions.confirm)){
						ret1=i;
						break;
					}
				}//for
			}//if
			if(ret1==-1){
				for(int i=0;i<len;i++){
					if(this.getAction().get(i).equals(agent_actions.confirm)){
						ret1=i;break;
					}
				}//for
			}
			
		
			
			for(int i=0;i<len;i++){
				if(this.getAction().get(i).equals(agent_actions.confirm)){
					
					ret2=i;
					if(ret1==ret2){
						continue;
					}else break;
				}
			}
			
			
			if(ret1>ret2){
				int temp=ret1;
				ret1=ret2;
				ret2=temp;
			}
			return confirmTwoorSelect(slots_4,ret1,ret2);
			
		
		
		
	}
	
	
	
	
	public String confirm(Slots_4 slots_4,int flag,String file){
		
		
		//confirm的activity 为 2
		
		Activity_slots_info.modify(slots_4.getActivity_flag(),flag,2);
		String slot_=slots_4.find(flag);
		List<String> value=slots_4.getTemp(slot_);
		if(value.size()==1){
			String sentence=xmlparser_confirm.out_sentence(file,flag);
			//System.out.println("slot_is :"+slot_+"value is :"+value.get(0));
				String temp=value.get(0);
				if(temp.charAt(temp.length()-1)=='人'||temp.charAt(temp.length()-1)=='元')
					temp=temp.substring(0, temp.length()-1);
			
			sentence=sentence.replaceAll("XXXX", temp);
			
			return sentence;
		}else{
			String sentence=xmlparser_confirm_select.out_sentence("sentences_confirm_select.xml",flag);
			sentence=sentence.replaceAll("XXXX", value.get(0));
			for(int i=1;i<value.size();i++){
				sentence+="还是"+value.get(i)+",";
			}
			sentence=sentence.substring(0,sentence.length()-1)+"呢?";
			
			return sentence;
		}
		
		
	}
	
	public String confirmTwoorSelect(Slots_4 slots_4,int flag1,int flag2){
		String file="sentences_confirm_N.xml";
		String slots=slots_4.find(flag1).substring(4)+"_"+slots_4.find(flag2).substring(4);
		//confirm的activity 为 2
		
		
		String slot_1=slots_4.find(flag1);
		List<String> value1=slots_4.getTemp(slot_1);
		String slot_2=slots_4.find(flag2);
		List<String> value2=slots_4.getTemp(slot_2);
		
		if(value1.size()==1&&value2.size()==1){
			Activity_slots_info.modify(slots_4.getActivity_flag(),flag1,2);
			Activity_slots_info.modify(slots_4.getActivity_flag(),flag2,2);
			String sentence=xmlparser_confirmN.out_sentence(file,slots);
			//System.out.println("slot_is :"+slot_+"value is :"+value.get(0));
				String temp=value1.get(0);
				if(temp.charAt(temp.length()-1)=='人'||temp.charAt(temp.length()-1)=='元')
					temp=temp.substring(0, temp.length()-1);
			
			sentence=sentence.replaceAll("XXXX", temp);
			
				String temp2=value2.get(0);
				if(temp2.charAt(temp2.length()-1)=='人'||temp.charAt(temp.length()-1)=='元')
					temp2=temp2.substring(0, temp2.length()-1);
			//System.out.println(flag1+"\t"+flag2);
			//System.out.println(temp+"\t"+temp2);
			sentence=sentence.replaceAll("YYYY", temp2);
			
			return sentence;
		}else{
			String sentence1=null;
			String sentence2=null;
			if(value1.size()>1){
				Activity_slots_info.modify(slots_4.getActivity_flag(),flag1,2);
				sentence1=xmlparser_confirm_select.out_sentence("sentences_confirm_select.xml",flag1);
				sentence1=sentence1.replaceAll("XXXX", value1.get(0));
				for(int i=1;i<value1.size();i++){
					sentence1+="还是"+value1.get(i)+",";
				}
				sentence1=sentence1.substring(0,sentence1.length()-1)+"呢?";
				//return sentence;
			}
			if(value2.size()>1){
				Activity_slots_info.modify(slots_4.getActivity_flag(),flag2,2);
				sentence2=xmlparser_confirm_select.out_sentence("sentences_confirm_select.xml",flag2);
				sentence2=sentence2.replaceAll("XXXX", value1.get(0));
				for(int i=1;i<value1.size();i++){
					sentence2+="还是"+value1.get(i)+",";
				}
				sentence2=sentence2.substring(0,sentence2.length()-1)+"呢?";
				//return sentence;
			}
			if(sentence1==null)return sentence2;
			if(sentence2==null)return sentence1;
			return sentence1+"还有"+sentence2;
			
			
		}
		
		
	}
	

	/**
	 * 选择一个槽询问
	 * 之前有随机性，
	 * 现在没有了随机性，是按顺序的
	 * @param slots_4
	 * @return
	 */
	public int chooseOneRequest(Slots_4 slots_4){
		int len=slots_4.getMainSlotNum();
		
		int ret=-1;
				for(int i=0;i<len;i++){
					if(this.getAction().get(i).equals(agent_actions.request)){
						
						ret=i;
					}
				}
			return ret;
		}
	
	//N是需要request的槽的个数
	public String request(Slots_4 slots_4,int N){
		//request的activity 为 1
		if(N==1){
			int flag=chooseOneRequest(slots_4);
			Activity_slots_info.modify(slots_4.getActivity_flag(),flag,1);
			String sentence=xmlparser_confirm.out_sentence("sentences_request.xml", flag);
			return sentence;
		}else if(N==2){
			String slots="";
			for(int i=0;i<slots_4.getMainState().length;i++){
				if(this.getAction().get(i).equals(agent_actions.request)){
					slots+=slots_4.find(i).substring(4)+"_";
					Activity_slots_info.modify(slots_4.getActivity_flag(),i,1);
				}
			}
			slots=slots.substring(0,slots.length()-1);
			
			String sentence=xmlparser_confirmN.out_sentence("sentences_request_N.xml", slots);
			
			
			return sentence;
		}else if(N==3||N==4){
			for(int i=0;i<slots_4.getMainState().length;i++){
				if(this.getAction().get(i).equals(agent_actions.request)){
					Activity_slots_info.modify(slots_4.getActivity_flag(),i,1);
				}
			}
			String sentence=xmlparser_confirmN.out_sentence("sentences_request_N.xml", "3-4");
			String slotname=slots_4.find(flag_info.first_2(slots_4.getMainState())).substring(4);
			String slotname0=slots_4.find(flag_info.first_0(slots_4.getMainState())).substring(4);
			
			
			if(slotname.equals("Price")){
				sentence=sentence.replace("XXXX", "会议预算");
			}else if(slotname.equals("Location")){
				sentence=sentence.replace("XXXX", "会议地点");
			}else if(slotname.equals("PerNum")){
				sentence=sentence.replace("XXXX", "与会人数");
			}else if(slotname.equals("Time")){
				sentence=sentence.replace("XXXX", "会议日期");
			}else if(slotname.equals("TimePeriod")) {
				sentence=sentence.replace("XXXX", "会议时长");
			}else {
				sentence="this is a bug";
			}
			
			if(slotname0.equals("Price")){
				sentence=sentence.replace("YYYY", "会议预算");
			}else if(slotname0.equals("Location")){
				sentence=sentence.replace("YYYY", "会议地点");
			}else if(slotname0.equals("PerNum")){
				sentence=sentence.replace("YYYY", "与会人数");
			}else if(slotname0.equals("Time")){
				sentence=sentence.replace("YYYY", "会议日期");
			}else if(slotname0.equals("TimePeriod")) {
				sentence=sentence.replace("YYYY", "会议时长");
			}else {
				sentence="this is a bug";
			}
			return sentence;
		}else{
			for(int i=0;i<slots_4.getMainState().length;i++){
				if(this.getAction().get(i).equals(agent_actions.request)){
					Activity_slots_info.modify(slots_4.getActivity_flag(),i,1);
				}
			}
			String sentence=xmlparser_confirmN.out_sentence("sentences_request_N.xml", "5");
			return sentence;
			
		}//else
		
		
	}//request

	
	
	static class ToChineseUtil{
		public static String toChinese(int index){
			switch(index){
				case 0:
					return "会议地点";
				case 1:
					return "与会人数";
				case 2:
					return "会议日期";
				case 3:
					return "会议时长";
				case 4:
					return "会议预算";
				default :
					return null;
				
					
			}
		}
	}
}
