package com.ming.crf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Iterator;
public class Attribute {
	private List<String> values;
	public Attribute(){
		values=new ArrayList<String>();
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		//duplicate_remove();
		this.values = values;
	}
	public Attribute duplicat_remove(){
		
		Attribute ret=new Attribute();
		List<String>list=this.getValues();
		Iterator ite=list.iterator();
		List<String>temp=new ArrayList<String>();
		while(ite.hasNext()){
			String value=(String) ite.next();
			if(!temp.contains(value)){
				temp.add(value);
			}
		}
		ret.setValues(temp);
		return ret;
		
	}


}
