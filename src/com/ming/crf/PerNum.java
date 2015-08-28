package com.ming.crf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
/**
 * 
 * @author www2620552
 * 人数提取函数的返回实体
 */
public class PerNum  extends Attribute{
	private ArrayList<String> values;
	PerNum(){
		values = new ArrayList<>();
	}
	public ArrayList<String> getValues() {
		
		return values;
	}
	public void setValues(ArrayList<String> values) {
		/*
		for(String str:values){
			this.values.add(str);
		}
		*/
		this.values = values;
	}


		
	
}
