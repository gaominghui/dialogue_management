package com.ming.crf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
/**
 * @author www2620552
 * 地点提取函数的返回结构体
 */
public class Location  extends Attribute{
	private ArrayList<String> values;
	public Location(){
		values=new ArrayList<String>();
	}

	public ArrayList<String> getValues() {
		return values;
	}

	public void setValues(ArrayList<String> values) {
		this.values = values;
	}
	

	
	
}
