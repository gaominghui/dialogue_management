package com.ming.crf;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PriceFetcher {
	public static Price fetchPrice(String sen){
		Price price = new Price();
		ArrayList<String> reStrings = price.getValues();
		ArrayList<String> tmpStrings = null;
		if(sen==null)
			return price;
		sen = StringPreHandler.delKeyword(sen, "\\s+");

		String[] senStrings = sen.split("[。|，|！|？|；|,|.|;|?|!]");
		for(String s:senStrings){
			tmpStrings = fetchScalePrice(s);
			if(tmpStrings.size()==0)
				tmpStrings.addAll(fetchPointPrice(s));
			reStrings.addAll(tmpStrings);
			tmpStrings.clear();
		}

		return price;
	}

	
	private static ArrayList<String> fetchPointPrice(String s) {
		ArrayList<String> reStrings = new ArrayList<String>();
		if((s==null || s.contains("人") || s.contains("名") || s.contains("位")||s.contains("一下")||s.contains("1下")
				||s.contains("天")||s.contains("星期")||s.contains("周")
				||s.contains("年")||s.contains("月")||s.contains("日")||s.contains("号"))
				)
			return reStrings;

		Matcher matcher = null;
		
		Pattern patternWithJ = Pattern.compile("几?[一二三四五六七八九十]?[十百千万]?(好?几)[十百千万]?[元块刀]?(.*((--)|(——)|到|(-)|至|~|(或者))[一二三四五六七八九十]?[十百千万]?(好?几)[十百千万]?[元块刀]?)?![天|小时|星期|月|上午|下午]");
		matcher = patternWithJ.matcher(s);
		
		if(matcher.find())
			reStrings.add(matcher.group(0));
		if(reStrings.size()!=0)
			return reStrings;
		
		Pattern pointPattern = Pattern.compile("((大概)|(大约)|(估计)|(约莫)|(大致)|(大体)|约)?(\\d+)((左右)|(上下))?元?块?((左右)|(上下))?");

		s = StringPreHandler.numberTranslator(s);
		matcher = pointPattern.matcher(s);
		while (matcher.find()) {
			reStrings.add(matcher.group(0));
		}
	
//		if(!matcher.find())
//			return reStrings;
//		if(matcher.group(3)!=null)
//			reStrings.add(matcher.group(3));
//		if(matcher.group(4)!=null)
//			reStrings.add(matcher.group(4));
//		if(matcher.group(6)!=null)
//			reStrings.add(matcher.group(6));
		
		return reStrings;
	}
	
	
	private static ArrayList<String> fetchScalePrice(String s) {
		ArrayList<String> reStrings = new ArrayList<String>();
		if(s==null || s.contains("人") || s.contains("名") || s.contains("位")||s.contains("一下")||s.contains("1下"))
			return reStrings;
		if(s.contains("年")||s.contains("月")||s.contains("日")||s.contains("号")||s.contains("天")||s.contains("星期")||s.contains("周"))
			return reStrings;
		Pattern pointPattern = Pattern.compile("(\\d+)\\D+(\\d+)");
		Matcher matcher = null;
//		boolean isAboutPrice = false;
//		if(s.contains("价") || s.contains("预算") 
//				|| s.contains("费")||s.contains("元"))
//		isAboutPrice = true;
		if(true){
			matcher = pointPattern.matcher(s);
			if(matcher.find()){
				reStrings.add(matcher.group(1) + "-" + matcher.group(2));
			}
		}

		return reStrings;
	}
	public static void main(String[] args) throws IOException {
		
		String tmpString="20000元左右吧";
		Price price = fetchPrice(tmpString);
		ArrayList<String> senArrayList  = price.getValues();
		for(String s:senArrayList){
			System.out.println(s+" aaa");
		}
		
		
	}
}
