package com.ming.crf;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import com.ming.time.TimeFetcher;
import com.ming.slots.*;
public class Fetch {

	/**
	 * 五个属性和他们的get,set方法
	 * 
	 */
	public Attribute getLocation() {
		return location;
	}

	public void setLocation(Attribute location) {
		this.location = location;
	}

	public Attribute getPernum() {
		return pernum;
	}

	public void setPernum(Attribute pernum) {
		this.pernum = pernum;
	}

	public Attribute getPrice() {
		return price;
	}

	public void setPrice(Attribute price) {
		this.price = price;
	}

	public Attribute getTime() {
		return time;
	}

	public void setTime(Attribute time) {
		this.time = time;
	}

	public Attribute getTimePeriod() {
		return timeperiod;
	}

	public void setTimePeriod(Attribute timeperiod) {
		this.timeperiod = timeperiod;
	}
	Attribute timeperiod;
	Attribute location;
	Attribute pernum;
	Attribute price;
	Attribute time;
	//HowLong howlong;
	LocationFetcher locationfetcher=new LocationFetcher();
	TimeFetcher timefetcher = new TimeFetcher("", "");
	
	public  Attribute find(int i){
		/*
		 * find()函数返回对应的属性的值
		 * 0返回location
		 * 1返回人数
		 * 2返回时间
		 * 3返回时长
		 * 4返回预算
		 */
		switch(i){
		case 0:
			Attribute location=this.getLocation();
			return location;
		case 1:
			Attribute pernum=this.getPernum();
			return pernum;
		case 2:
			Attribute time=this.getTime();
			return time;
		case 3:
			Attribute timeperiod=this.getTimePeriod();
			return timeperiod;
		case 4:
			Attribute price=this.getPrice();
			return price;
		default:
			return null;
		}
	}

	public  void fetch(String sentence,int[]activity_flag) throws IOException{
		//获取人数
		PerNum pernum1;
		   Pattern pattern = Pattern.compile("[0-9]*"); 
		   //这里有一个判断，意思是如果询问了时间和预算并且句子里不包含“人，名，位”就不提取了，因为很有可能提取到一个东西不是人数。
		if((activity_flag[4]==1||activity_flag[2]==1||activity_flag[4]==3||activity_flag[2]==3)&&!sentence.contains("人")
				&&!sentence.contains("名")&&!sentence.contains("位")){
		pernum1=new PerNum();
	}else {
				pernum1=PerNumFetcher.fetchPerNum(sentence);
				//人数的常识判断，与会的人数不会少于3个
				List<String>list_pernum=pernum1.getValues();
				Iterator<String>iterator=list_pernum.iterator();
				while(iterator.hasNext()){
					String temp=iterator.next();
					if(pattern.matcher(temp).matches()){
						if(Integer.parseInt(temp)<4){
							iterator.remove();
						}
					}else iterator.remove();
					
				}//while
				pernum1.setValues(list_pernum);
		}
	
		//获取日期
		Time time1;
		//这里有一个if判断，意思是如果询问了人数和预算并且句子里不包含“月周。。。。”就不提取
		if((activity_flag[1]==1||activity_flag[4]==1||activity_flag[1]==3||activity_flag[4]==3)&&(!sentence.contains("号")&&
				!sentence.contains("月")&&!sentence.contains("周")&&!sentence.contains("星期")&&
				!sentence.contains("天"))){
			time1=new Time();
		}else{
			
			time1=timefetcher.fetchTime(sentence);
			//时间的常识判读,不应该全部由数字组成
			List<String>list_time=time1.getValues();
			Iterator<String>iterator=list_time.iterator();
			while(iterator.hasNext()){
				String temp=iterator.next();
				if(pattern.matcher(temp).matches()){
					iterator.remove();
				}
				
			}//while
			pernum1.setValues(list_time);
			
		}
		//取多长时间
		TimePeriod timeperiod1=TimePeriodFetcher.fetchTimePeriod(sentence);
		//获取报价
		Price price1;
		//同上边的两个判断
	if((activity_flag[1]==1||activity_flag[2]==1||activity_flag[1]==3||activity_flag[2]==3)&&!sentence.contains("元")&&!sentence.contains("块")
			&&!sentence.contains("刀")){
		
	 price1=new Price();
	}else{
		
			 price1=PriceFetcher.fetchPrice(sentence);
			//预算的常识判断，字符串应该由数字组成除去最后一位
				List<String>list_price=price1.getValues();
				  Pattern pattern2 = Pattern.compile("[0-9]*[元|块|刀]?(.*)$"); 
				Iterator<String>iterator=list_price.iterator();
				while(iterator.hasNext()){
					String temp=iterator.next();
					boolean bool1=pattern.matcher(temp).matches();
					boolean bool2=pattern2.matcher(temp).matches();
					//找到第一个不是数字的字符
					int k=0;
					for(;k<temp.length();k++){
						if(!Character.isDigit(temp.charAt(k)))break;
					}
					if(bool1||bool2){
						if(bool1&&bool2&&Integer.parseInt(temp)<100){
							iterator.remove();
						}
						if(bool1&&!bool2&&Integer.parseInt(temp)<100){
							iterator.remove();
						}
						
						if(!bool1&&bool2&&Integer.parseInt(temp.substring(0,k))<100){
							iterator.remove();
						}
						
					}else iterator.remove();
					
				}//while
				pernum1.setValues(list_price);
			 
	}
		//提取地点
		Location location1=locationfetcher.fetchLoc(sentence);
		
		this.setLocation(location1.duplicat_remove());
		this.setPernum(pernum1.duplicat_remove());
		this.setTime(time1.duplicat_remove());
		this.setTimePeriod(timeperiod1.duplicat_remove());
		this.setPrice(price1.duplicat_remove());
		
	
	}
	
	public   void print() throws IOException {
		// TODO Auto-generated method stub
	/*
	 * 打印方法
	 */
		
		
		///print location
		for(int i=0;i<5;i++){
			
			if(this.find(i)!=null){
				if(this.find(i).getValues()!=null){
					System.out.print("attribue： ");
					for(String str:this.find(i).getValues()){
						System.out.println(str);
					}
					
				}
				else
					System.out.println("attribue.values is null");
			}
			else
				System.out.println("attribue is null");
			
		}
	}
	
		public static void main(String[]args) throws IOException{
			Fetch fetch=new Fetch();
			int[]flags=new int[]{0,0,0,0,0};
			fetch.fetch("20000元左右",flags);

			if(fetch.getLocation().getValues().size()>0){
				for(String location:fetch.getLocation().getValues()){
					System.out.println("location:"+location);
				}
			}
			if(fetch.getPernum().getValues().size()>0){
				for(String location:fetch.getPernum().getValues()){
					System.out.println("pernum: "+location);
				}
			}
			if(fetch.getTime().getValues().size()>0){
				for(String location:fetch.getTime().getValues()){
					System.out.println("time "+location);
				}
			}
			if(fetch.getTimePeriod().getValues().size()>0){
				for(String location:fetch.getTimePeriod().getValues()){
					System.out.println("timeperiod "+location);
				}
			}
			
			if(fetch.getPrice().getValues().size()>0){
				for(String location:fetch.getPrice().getValues()){
					System.out.println("price "+location);
				}
			}
			System.out.println("done-------------");
		}

}
