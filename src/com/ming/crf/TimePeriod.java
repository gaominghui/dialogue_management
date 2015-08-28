package com.ming.crf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class TimePeriod extends Attribute{
	ArrayList<String> values;
	public TimePeriod() {
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
