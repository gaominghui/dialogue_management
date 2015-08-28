package com.ming.actions;

import com.ming.util.*;
import com.ming.crf.Fetch;
import com.ming.slots.*;

import java.io.IOException;
import java.util.*;
public class agent_doActions_4 {
	
	/**
	 * 原来的doActions类，已废弃，不用看
	 * 被agent_doActions_2015类代替
	 */
	
	//private XmlParse xmlparser =new XmlParse();
	private XmlParse_confirm xmlparser_confirm =new XmlParse_confirm();
	private XmlParse_confirm_select xmlparser_confirm_select =new XmlParse_confirm_select();
	private List<agent_actions> actions;

	public Fetch getFetch() {
		return fetch;
	}



	public void setFetch(Fetch fetch) {
		this.fetch = fetch;
	}
	private Fetch fetch;
	public agent_doActions_4(){
		fetch=new Fetch();
	}

	
	
	public List<agent_actions> getAction() {
		return actions;
	}
	public void setAction(List<agent_actions> action) {
		this.actions = action;
	}
	public  void refresh(Slots_4 slots_4, List<agent_actions> message) {
		// TODO Auto-generated method stub
		for(int i=0;i<slots_4.getMainSlotNum();i++){
			agent_actions action=message.get(i);
			if(action==agent_actions.request){
				//do noting
			}
			if(action==agent_actions.re_request||action==agent_actions.dis_request){
				if(slots_4.getTemp(slots_4.find(i))!=null){
					slots_4.modifyTemp(slots_4.find(i), null);
				}
			}
			if(action==agent_actions.confirm){
				if(slots_4.getTemp(slots_4.find(i))==null){
					slots_4.modifyTemp(slots_4.find(i),fetch.find(i).getValues());
				}
			}
			if(action==agent_actions.re_confirm){
				slots_4.modifyTemp(slots_4.find(i),fetch.find(i).getValues());
			}
			
			if(action==agent_actions.done){
				List<String>value=fetch.find(i).getValues();
				List<String>old_value=slots_4.get(slots_4.find(i));
				List<String>ret=new ArrayList<String>();
				for(String temp:value){
					if(old_value.contains(temp)){
						ret.add(temp);
					}
				}
				slots_4.modify(slots_4.find(i), ret);
			}
			if(action==agent_actions.ok){
				//do nothing
			}
		}
		
	}
	public String act(List<agent_actions> message,Slots_4 Slots_4){
		/*
		try {
			fetch.print();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		*/
		Slots_refresh_4.refresh(Slots_4, message, fetch);
		Activity_slots_info.reset(Slots_4.getActivity_flag());
		String ret="";
		boolean request_flag=message.contains(agent_actions.request);
		boolean confirm_flag=message.contains(agent_actions.confirm);
		boolean re_request_flag=message.contains(agent_actions.re_request);
		boolean re_confirm_flag=message.contains(agent_actions.re_confirm);
		boolean dis_request_flag=message.contains(agent_actions.dis_request);
		if(re_confirm_flag){
			for(int i=0;i<message.size();i++){
				if(message.get(i)==agent_actions.re_confirm){
					ret+=re_confirm(Slots_4,i);
				}
			}
		}
		if(dis_request_flag&&!confirm_flag){
			for(int i=0;i<message.size();i++){
				if(message.get(i)==agent_actions.dis_request){
					ret+=dis_request(Slots_4,i);
				}
			}
		}
		if(dis_request_flag&&confirm_flag){
			for(int i=0;i<message.size();i++){
				if(message.get(i)==agent_actions.dis_request){
					ret+=dis_request(Slots_4,i);
				}
			}
			ret+=confirm(Slots_4,false,false);
		}
		if(!dis_request_flag&&!request_flag&&!confirm_flag&&!re_request_flag&&!re_confirm_flag)
			ret="谢谢合作,我们会尽快跟您联系的";
		if(!dis_request_flag&&!re_confirm_flag&&confirm_flag&&!request_flag&&!re_request_flag){
			//没有需要再次确认的，有需要确认的，且没有需要request的 也没有需要re_request的
			ret+=confirm(Slots_4,false,false);
		}
		if(!dis_request_flag&&!re_confirm_flag&&confirm_flag&&request_flag&&!re_request_flag){
			//没有需要再次确认的，有需要确认的，也有需要request的 也没有需要re_request的
			ret+=confirm(Slots_4,true,false);
		
		}
		if(!dis_request_flag&&!re_confirm_flag&&confirm_flag&&!request_flag&&re_request_flag){
			//没有需要再次确认的，有需要确认的，且没有需要request的,但有需要re_request_flag
			ret+=confirm(Slots_4,false,true);
	
		}
		if(!dis_request_flag&&!re_confirm_flag&&confirm_flag&&request_flag&&re_request_flag){
			//没有需要再次确认的，有需要确认的，且没有需要request的,但有需要re_request_flag
			ret+=confirm(Slots_4,true,true);
	
		}


		
		if(!dis_request_flag&&re_request_flag){
			for(int i=0;i<message.size();i++){
				if(message.get(i)==agent_actions.re_request)
					ret+=re_request(Slots_4,i);
			}
		}
	
		if(!dis_request_flag&&!re_request_flag&&request_flag){
			ret+=request(Slots_4);
		}
		
				
		
		return ret;

	}
	private String dis_request(Slots_4 slots_4, int i) {
		// TODO Auto-generated method stub
		Activity_slots_info.modify(slots_4.getActivity_flag(),i,1);
		String sentence=xmlparser_confirm.out_sentence("src/sentences_dis_request.xml", i);
		return sentence;
	}



	private String re_request(Slots_4 slots_4, int i) {
		// TODO Auto-generated method stub
		
		Activity_slots_info.modify(slots_4.getActivity_flag(),i,1);
		String sentence=xmlparser_confirm.out_sentence("src/sentences_re_request.xml", i);
		return sentence;
		
	}



	private String re_confirm(Slots_4 slots_4, int i) {
		// TODO Auto-generated method stub
		Activity_slots_info.modify(slots_4.getActivity_flag(),i,2);
		String slot_=slots_4.find(i);
		List<String> value=slots_4.getTemp(slot_);
		if(value.size()==1){
			String sentence=xmlparser_confirm.out_sentence("src/sentences_confirm.xml",i);
			//System.out.println("slot_is :"+slot_+"value is :"+value.get(0));
			sentence=sentence.replaceAll("XXXXX", value.get(0));
			return sentence;
		}else{
			String sentence=xmlparser_confirm_select.out_sentence("src/sentences_confirm_select.xml",i);
			sentence=sentence.replaceAll("XXXXX", value.get(0));
			for(int j=1;j<value.size();j++){
				sentence+="还是"+value.get(j)+",";
			}
			sentence=sentence.substring(0,sentence.length()-1)+"呢?";
			return sentence;
		}
	}



	public int chooseOneRequest(Slots_4 slots_4){
		int len=slots_4.getMainSlotNum();
		double d=0.0;
		int ret=-1;

		
				for(int i=0;i<len;i++){
					if(slots_4.getSlotMainState(i)==0){
						double temp=Math.random();
						if(temp>d){
							d=temp;
							ret=i;
						}
					}
				}
	
			return ret;
		}
		
	
	
	public int chooseOneConfirm(Slots_4 slots_4){
		int len=slots_4.getMainSlotNum();
		double d=0.0;
		int ret=-1;
		for(int i=0;i<len;i++){
			if(slots_4.getSlotMainState(i)==1){
				double temp=Math.random();
				if(temp>d){
					d=temp;
					ret=i;
				}
			}
		}
		return ret;
		
	}
	
	public String request(Slots_4 slots_4){
		//request的activity 为 1
		int flag=chooseOneRequest(slots_4);
		Activity_slots_info.modify(slots_4.getActivity_flag(),flag,1);
		String sentence=xmlparser_confirm.out_sentence("src/sentences_request.xml", flag);
		return sentence;
	}

	public String confirm(Slots_4 slots_4,boolean request,boolean re_request){
		String file=null;
		if(request&&!re_request)file="src/sentences_confirm_request_confirm.xml";
		if(!request&&re_request)file="src/sentences_confirm_re_request_confirm.xml";
		if(request&&re_request)file="src/sentences_confirm_re_request_confirm.xml";
		if(!request&&!re_request)file=	"src/sentences_confirm.xml";
		//confirm的activity 为 2
		int flag=chooseOneConfirm(slots_4);
		
		Activity_slots_info.modify(slots_4.getActivity_flag(),flag,2);
		String slot_=slots_4.find(flag);
		List<String> value=slots_4.getTemp(slot_);
		if(value.size()==1){
			String sentence=xmlparser_confirm.out_sentence(file,flag);
			//System.out.println("slot_is :"+slot_+"value is :"+value.get(0));
				String temp=value.get(0);
				if(temp.charAt(temp.length()-1)=='人')
					temp=temp.substring(0, temp.length()-1);
			
			sentence=sentence.replaceAll("XXXXX", temp);
			
			return sentence;
		}else{
			String sentence=xmlparser_confirm_select.out_sentence("src/sentences_confirm_select.xml",flag);
			sentence=sentence.replaceAll("XXXXX", value.get(0));
			for(int i=1;i<value.size();i++){
				sentence+="还是"+value.get(i)+",";
			}
			sentence=sentence.substring(0,sentence.length()-1)+"呢?";
			return sentence;
		}
		
		
	}

	

}
