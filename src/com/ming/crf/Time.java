package com.ming.crf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Time  extends Attribute{
	private List<String> values;
	public Time() {
		// TODO Auto-generated constructor stub
		values = new ArrayList<String>();
	}
	public List<String> getValues() {
		return (List<String>) values;
	}
	public void setValues(List<String> values) {
		this.values = values;
	}

	
}
