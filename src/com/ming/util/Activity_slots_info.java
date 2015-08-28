package com.ming.util;

public class Activity_slots_info {
	public static int slot_confirm(int[]activity){
		int len=activity.length;
		for(int i=0;i<len-1;i++){
			if(activity[i]==2){
				return i;
			}
		}
		return -1;
	}
	public static int slot_request(int[]activity){
		int len=activity.length;
		for(int i=0;i<len-1;i++){
			if(activity[i]==1){
				return i;
			}
		}
		return -1;
	}
	public static boolean is_confirm(int[]activity,int i){
		if(activity[i]==2){
			return true;
		}
		else return false;
	}
	public static boolean is_request(int[]activity,int i){
		if(activity[i]==1){
			return true;
		}
		else return false;
	}
	public static void modify(int[]activity,int slot,int value){
		activity[slot]=value;
	}
	
	public static void reset(int[]activity){
		int len=activity.length;
		for(int i=0;i<len;i++){
			activity[i]=0;
		}
	}
}
