package com.ming.util;
import java.util.*;
public class pos_neg {
	String[]pos_str=new String[]{"是","对","好","行","可"};
	static HashSet<Character> set=new HashSet<Character>();
	static{
		set.add('不');
		set.add('错');
	}
	public   boolean attitue(String sentence){
		if(sentence==null){
			return true;
		}
		int count=0;
		for(int i=0;i<sentence.length();i++){
			if(set.contains(sentence.charAt(i))){
				count++;
			}
		}
		if(count%2==0)
			return true;
		else return false;
	
	}
	public static void main(String []args){
		String sen="不错";
	//	System.out.println(sen+" "+attitue(sen));
	}
}
