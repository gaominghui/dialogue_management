package com.ming.actions;

import java.util.Hashtable;
import java.util.List;

public class action_strategy {
	
	/**
	 * 上层MDP(生成最终的对话)的策略，记录状态到动作的映射
	 * 
	 */

	public static  Final_actions match(List<agent_actions>list,Hashtable<agent_actions,Integer>table){
		int request=table.get(agent_actions.request);
		int confirm=table.get(agent_actions.confirm);
		int re_request=table.get(agent_actions.re_request);
		int dis_request=table.get(agent_actions.dis_request);
		int re_confirm=table.get(agent_actions.re_confirm);
		
		if(confirm==0&&re_confirm==0&&request==0&&re_request==0&&dis_request==0){
			//第一种情况，五个动作均为0
			return Final_actions.End;
		}
		if(confirm==0&&re_confirm==0&&request>0&&re_request==0&&dis_request==0){
			//第二种情况，只有requeset
			return Final_actions.requestN;
		}
		if(confirm>0&&re_confirm==0&&request==0&&re_request==0&&dis_request==0){
			//第三种情况，只有confirm
			return Final_actions.ConfirmN;
		}
		if(confirm>0&&re_confirm==0&&request>0&&re_request==0&&dis_request==0){
			//第四种情况，只有confirm和request
			return Final_actions.confirmN_requestN;
		}
		if(re_confirm>0&&request>0&&re_request==0&&dis_request==0){
			//第五种情况，confirm>0,re_confirm>0,request>0
			return Final_actions.re_confirm_requestN;
		}
		if(re_confirm>0&&re_request>0&&dis_request==0){
			//第六种情况，
			return Final_actions.re_confirm_re_request;
		}
		if(re_confirm>0&&re_request==0&&dis_request>0){
			//第七种情况，
			return Final_actions.re_confirm_dis_request;
		}
		if(re_confirm>0&&re_request>0&&dis_request>0){
			//第八种情况，
			return Final_actions.re_confirm_dis_request;
		}
		if(confirm>0&&re_confirm==0&&re_request>0&&dis_request==0){
			//第九种情况，
			return Final_actions.confirmN_re_request;
		}
		if(confirm>0&&re_confirm==0&&re_request==0&&dis_request>0){
			//第十种情况，
			return Final_actions.confirmN_dis_request;
		}
		if(confirm>0&&re_confirm==0&&re_request>0&&dis_request>0){
			//第十一种情况，
			return Final_actions.confirmN_dis_request;
		}
		if(re_confirm>0&&request==0&&re_request==0&&dis_request==0){
			//第十二种情况，
			return Final_actions.re_confirm;
		}
		if(confirm==0&&re_confirm==0&&re_request>0&&dis_request==0){
			//第十三种情况，
			return Final_actions.re_request;
		}
		if(confirm==0&&re_confirm==0&&re_request==0&&dis_request>0){
			//第十四种情况，
			return Final_actions.dis_request;
		}
		if(confirm==0&&re_confirm==0&&re_request>0&&dis_request>0){
			//第十四种情况，
			return Final_actions.dis_request;
		}
		return null;
		
		
		
	}
	
	
	
	public static  Final_actions match(List<agent_actions>list){
		
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
		
		
		if(confirm==0&&re_confirm==0&&request==0&&re_request==0&&dis_request==0){
			//第一种情况，五个动作均为0
			return Final_actions.End;
		}
		if(confirm==0&&re_confirm==0&&request>0&&re_request==0&&dis_request==0){
			//第二种情况，只有requeset
			return Final_actions.requestN;
		}
		if(confirm>0&&re_confirm==0&&request==0&&re_request==0&&dis_request==0){
			//第三种情况，只有confirm
			return Final_actions.ConfirmN;
		}
		if(confirm>0&&re_confirm==0&&request>0&&re_request==0&&dis_request==0){
			//第四种情况，只有confirm和request
			return Final_actions.confirmN_requestN;
		}
		if(re_confirm>0&&request>0&&re_request==0&&dis_request==0){
			//第五种情况，confirm>0,re_confirm>0,request>0
			return Final_actions.re_confirm_requestN;
		}
		if(re_confirm>0&&re_request>0&&dis_request==0){
			//第六种情况，
			return Final_actions.re_confirm_re_request;
		}
		if(re_confirm>0&&re_request==0&&dis_request>0){
			//第七种情况，
			return Final_actions.re_confirm_dis_request;
		}
		if(re_confirm>0&&re_request>0&&dis_request>0){
			//第八种情况，
			return Final_actions.re_confirm_dis_request;
		}
		if(confirm>0&&re_confirm==0&&re_request>0&&dis_request==0){
			//第九种情况，
			return Final_actions.confirmN_re_request;
		}
		if(confirm>0&&re_confirm==0&&re_request==0&&dis_request>0){
			//第十种情况，
			return Final_actions.confirmN_dis_request;
		}
		if(confirm>0&&re_confirm==0&&re_request>0&&dis_request>0){
			//第十一种情况，
			return Final_actions.confirmN_dis_request;
		}
		if(re_confirm>0&&request==0&&re_request==0&&dis_request==0){
			//第十二种情况，
			return Final_actions.re_confirm;
		}
		if(confirm==0&&re_confirm==0&&re_request>0&&dis_request==0){
			//第十三种情况，
			return Final_actions.re_request;
		}
		if(confirm==0&&re_confirm==0&&re_request==0&&dis_request>0){
			//第十四种情况，
			return Final_actions.dis_request;
		}
		if(confirm==0&&re_confirm==0&&re_request>0&&dis_request>0){
			//第十四种情况，
			return Final_actions.dis_request;
		}
		return null;
		
		
		
	}
}
