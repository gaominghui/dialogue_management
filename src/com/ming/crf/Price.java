package com.ming.crf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
/**
 * 
 * @author www2620552
 * 价格提取函数的返回实体
 */
public class Price  extends Attribute{
	ArrayList<String> values;
	public Price() {
		// TODO Auto-generated constructor stub
		values = new ArrayList<>();
	}
	public ArrayList<String> getValues() {
		return values;
	}
	public void setValues(ArrayList<String> values) {
		this.values = values;
	}

}
