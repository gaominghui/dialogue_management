package com.ming.time;


import java.util.List;

public interface Tagger {
	String[] tag(List<String[]> features);
}
