package com.ming.crf;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class PerNumFetcher {
	/**
	 * 
	 * @param sen 待处理的句子
	 * @return    PerNum实体，PerNum.values中包含句子中所含有的可能表达人数的数字
	 */
	public static PerNum fetchPerNum(String sen) {
		PerNum pr = new PerNum();
		ArrayList<String> reStrings = new ArrayList<String>();
		/*
		if(!sen.contains("人")&&!sen.contains("位")&&!sen.contains("名")&&!sen.contains("个")){
			pr.setValues(reStrings);
			return pr;
		}
*/
		sen = StringPreHandler.delKeyword(sen, "\\s+");
		
		String[] senStrings = sen.split("[。|，|！|；|,|.|;|?|!]");
		//包含几的模式
		
		String patternWithJ = "几?[一二三四五六七八九十]?[十百千万]?(好?几)[十百千万]?[人个名]?(.*((--)|(——)|到|(-)|至|~|(或者))[一二三四五六七八九十]?[十百千万]?(好?几)[十百千万]?[人个名位]?)?";
		Pattern p = Pattern.compile(patternWithJ);
		ArrayList<String> tmpReStrings = new ArrayList<String>();
		for(String s : senStrings){
			
			if(s.contains("元") || s.contains("块") || s.contains("刀")||s.contains("一下")||s.contains("1下")||
					s.contains("日")||	s.contains("年")||	s.contains("月")||	s.contains("号")||
					s.contains("价")||s.contains("预算")||s.contains("钱")||s.contains("天")||s.contains("周")||
					s.contains("时")||s.contains("星期")||s.contains("晚上"))continue;
			Matcher m = p.matcher(s);
			while(m.find())
				tmpReStrings.add(m.group(0));
			if(tmpReStrings.size()!=0){
				reStrings.addAll(tmpReStrings);
				tmpReStrings.clear();
			}	
		}
		if(reStrings.size()!=0){
			pr.setValues(reStrings);
		return pr;
		}
		
		
		
		sen = StringPreHandler.numberTranslator(sen);
		senStrings = sen.split("[。|，|！|；|,|.|;|?|!]");
		
//		指示范围的正则表达式
		String pScale = "(\\d+)[人个]?((--)|(——)|到|(-)|至|~)(\\d+)[人个位]?";
		p = Pattern.compile(pScale);
		
		for(String s:senStrings){
		
			if(s.contains("元") || s.contains("块") || s.contains("刀")||s.contains("一下")||s.contains("1下")||
					s.contains("日")||	s.contains("年")||	s.contains("月")||	s.contains("号")||
					s.contains("价")||s.contains("预算")||s.contains("钱")||s.contains("天")||s.contains("周")||
					s.contains("时")||s.contains("星期")||s.contains("晚上"))continue;
			Matcher m = p.matcher(s);
			while(m.find())
				tmpReStrings.add(m.group(0));
			if(tmpReStrings.size()!=0){
				reStrings.addAll(tmpReStrings);
				tmpReStrings.clear();
			}
			
		}
		if(reStrings.size()!=0){
			pr.setValues(reStrings);
			
			return pr;
		}
	
//		指示点的正则表达式
		String[] senStringss = sen.split("[。|，|！|？|；|,|.|;|?|!]");
		String pPoint = "(\\d+(?=(((左右)|几|多)?(个|位|人)?人?(左右)?)))";
		for(String s:senStringss){
			if(s.contains("元") || s.contains("块") || s.contains("刀")||s.contains("一下")||s.contains("1下")||
					s.contains("日")||	s.contains("年")||	s.contains("月")||	s.contains("号")||
					s.contains("价")||s.contains("预算")||s.contains("钱")||s.contains("天")||s.contains("周")||
					s.contains("时")||s.contains("星期")||s.contains("晚上"))continue;
			p = Pattern.compile(pPoint);
			Matcher m = p.matcher(s);
			while (m.find()) {
				reStrings.add(m.group(0));
			}
		}
		
		
//		String pPoint = "(\\d+(?=(个|多)?人?))|(\\d+(?=(余|多|(左右))?名?))";
		
	
		pr.setValues(reStrings);
	
		return pr;
	
	}

	public static void main(String []args) throws IOException {
		PerNumFetcher ptFetcher = new PerNumFetcher();

		
		String string = "300";
		PerNum pn=ptFetcher.fetchPerNum(string);

	
		PerNum pp=new PerNum();
		List<String>res=pn.getValues();

	//	System.out.println(res.size());
		for(String s:res)
			System.out.println(s);
		System.out.println("done");
		
	}
}
