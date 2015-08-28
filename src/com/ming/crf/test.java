package com.ming.crf;
import java.util.*;
public class test {
	public static void main(String[]args){
		String sen="300人";
if(sen==null || sen.contains("元") || sen.contains("块") || sen.contains("刀")||
sen.contains("日")||	sen.contains("年")||	sen.contains("月")||	sen.contains("号")||
sen.contains("价")||sen.contains("预算")||sen.contains("钱")||sen.contains("天")||
sen.contains("时")||sen.contains("星期")||sen.contains("晚上"))
	System.out.println(sen);
else System.out.println("else");
		
	}
}
