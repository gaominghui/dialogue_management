package com.ming.crf;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;






/**
 * 
 * @author www2620552
 * 这个类是对已经感知到的含有时间表达式的部分进行处理得到时间
 * 
 */


public class TimeNormalizer {
	//待处理的时间表达式
	public String Time_Expression=null;
//	存储年月日的时间结果
	public TimePoint _tp=new TimePoint();
	private int NUM_OF_TIME_CHUNKS = 3;
	

	private String Time_Norm="";
	public int[] time_full;
	
	
	/**
	 * 对xx年进行提取
	 */
	public void norm_setyear()
	{
		String rule="[0-9]{2}(?=年)";
		Pattern pattern=Pattern.compile(rule);
		Matcher match=pattern.matcher(Time_Expression);
		if(match.find())
		{
			_tp.tunit[0]=Integer.parseInt(match.group());
			if(_tp.tunit[0] >= 0 && _tp.tunit[0] < 100){
				if(_tp.tunit[0]<30)
					_tp.tunit[0] += 2000;
				else
					_tp.tunit[0] += 1900;
			}
			
		}
		
		rule="[0-9]?[0-9]{3}(?=年)";
		
		pattern=Pattern.compile(rule);
		match=pattern.matcher(Time_Expression);
		if(match.find())
		{
			_tp.tunit[0]=Integer.parseInt(match.group());
		}
	}
	
	/**
	 * 对xx月进行提取
	 */
	public void norm_setmonth()
	{
		String rule="((10)|(11)|(12)|([1-9]))(?=月)";
		Pattern pattern=Pattern.compile(rule);
		Matcher match=pattern.matcher(Time_Expression);
		if(match.find())
		{
			_tp.tunit[1]=Integer.parseInt(match.group());
		}	
	}
	
	/**
	 * 对xx日进行提取
	 */
	public void norm_setday()
	{
		String rule="((?<!\\d))([0-3][0-9]|[1-9])(?=(日|天))";
		Pattern pattern=Pattern.compile(rule);
		Matcher match=pattern.matcher(Time_Expression);
		if(match.find())
		{
			_tp.tunit[2]=Integer.parseInt(match.group());
		}	
	}
	
	
	
	/**
	 * 对格式化的
	 * 2013-08-31
	 * 2013/08/31
	 * 2013.08.31
	 * 时间进行提取
	 */
	public void norm_setTotal()
	{
		String rule;
		Pattern pattern;
		Matcher match;
		String[] tmp_parser;
		String tmp_target;
		
		
		//2013-08-31
		rule = "[0-9]?[0-9]?[0-9]{2}-((10)|(11)|(12)|(0?[1-9]))-((?<!\\d))([0-3][0-9]|[1-9])";
		pattern=Pattern.compile(rule);
		match=pattern.matcher(Time_Expression);
		if(match.find())
		{
//			System.out.println("found  1");WS
			tmp_parser=new String[3];
			tmp_target=match.group();
			tmp_parser=tmp_target.split("-");
			_tp.tunit[0]=Integer.parseInt(tmp_parser[0]);
			_tp.tunit[1]=Integer.parseInt(tmp_parser[1]);
			_tp.tunit[2]=Integer.parseInt(tmp_parser[2]);
			return;
		}
		
//		2013/08/31
		rule="[0-9]?[0-9]?[0-9]{2}/((10)|(11)|(12)|(0?[1-9]))/((?<!\\d))([0-3][0-9]|[1-9])";
		pattern=Pattern.compile(rule);
		match=pattern.matcher(Time_Expression);
		if(match.find())
		{
//			System.out.println("found  6");
			tmp_parser=new String[3];
			tmp_target=match.group();
			tmp_parser=tmp_target.split("/");
			for(String s : tmp_parser)
				System.out.println(s);
			_tp.tunit[0]=Integer.parseInt(tmp_parser[0]);
			_tp.tunit[1]=Integer.parseInt(tmp_parser[1]);
			_tp.tunit[2]=Integer.parseInt(tmp_parser[2]);
			return;
		}
		
		
//		2013.08.01
		rule="[0-9]?[0-9]?[0-9]{2}\\.((10)|(11)|(12)|(0?[1-9]))\\.((?<!\\d))([0-3][0-9]|[1-9])";
		pattern=Pattern.compile(rule);
		match=pattern.matcher(Time_Expression);
		if(match.find())
		{
			tmp_parser=new String[3];
			tmp_target=match.group();
			tmp_parser=tmp_target.split("\\.");
			_tp.tunit[0]=Integer.parseInt(tmp_parser[0]);
			_tp.tunit[1]=Integer.parseInt(tmp_parser[1]);
			_tp.tunit[2]=Integer.parseInt(tmp_parser[2]);
			return;
		}
		

		
			
	}
	
	
	
	
	/**
	 * 将最后获得的时间点转化为xx年xx月xx日的模式
	 */
	public void Time_Normalization()
	{
		norm_setyear();
		norm_setmonth();
		norm_setday();
		norm_setTotal();

		
		
		
		String [] time_grid=new String[NUM_OF_TIME_CHUNKS];
		
		int tunitpointer = NUM_OF_TIME_CHUNKS - 1;
		while (tunitpointer>=0 && _tp.tunit[tunitpointer]<0)
		{
			tunitpointer--;
		}
		for (int i=0;i<tunitpointer;i++)
		{
			if (_tp.tunit[i]<0)
				_tp.tunit[i]=Integer.parseInt(time_grid[i]);
		}
		String[] _result_tmp=new String[NUM_OF_TIME_CHUNKS];
		_result_tmp[0]=String.valueOf(_tp.tunit[0]);
//		�������ֶ�
		if (_tp.tunit[0]>=10 &&_tp.tunit[0]<100)
		{
			_result_tmp[0]="19"+String.valueOf(_tp.tunit[0]);
		}
		if (_tp.tunit[0]>0 &&_tp.tunit[0]<10)
		{
			_result_tmp[0]="200"+String.valueOf(_tp.tunit[0]);
		}
		
		for (int i = 1; i < NUM_OF_TIME_CHUNKS; i++) {
            _result_tmp[i] = String.valueOf(_tp.tunit[i]);
        }

        Calendar cale = Calendar.getInstance();			//leverage a calendar object to figure out the final time
        cale.clear();
        if (Integer.parseInt(_result_tmp[0]) != -1) {
            Time_Norm += _result_tmp[0] + "年";
            cale.set(Calendar.YEAR, Integer.valueOf(_result_tmp[0]));
            if (Integer.parseInt(_result_tmp[1]) != -1) {
                Time_Norm += _result_tmp[1] + "月";
                cale.set(Calendar.MONTH, Integer.valueOf(_result_tmp[1]) - 1);
                if (Integer.parseInt(_result_tmp[2]) != -1) {
                    Time_Norm += _result_tmp[2] + "日";
                    cale.set(Calendar.DAY_OF_MONTH, Integer.valueOf(_result_tmp[2]));
                }
            }
        }
		
		time_full = _tp.tunit.clone();
		
	}
	
	public class TimePoint
	{
		int [] tunit={-1,-1,-1};
	}
	
	
	public Pattern readPattern(String path) {
		File file = new File(path);
		String regexString = "";
		try{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String tempString =null;
			
			while((tempString=reader.readLine())!=null){
				regexString+=tempString;
//				System.out.println(tempString);
			}
		}catch(Exception e){
			System.err.println("Open pattern file error!");
		}
		
		Pattern pattern = Pattern.compile(regexString);

		return pattern 	;
}
	
	
	
	
	
	

	public String getTime_Expression() {
		return Time_Expression;
	}

	public void setTime_Expression(String time_Expression) {
		Time_Expression = time_Expression;
	}

	public String getTime_Norm() {
		return Time_Norm;
	}

	public void setTime_Norm(String time_Norm) {
		Time_Norm = time_Norm;
	}
	
//	public static void main(String[] args) throws IOException {
//		String  teString = "08/31/2013吧";
//		
//		TimeNormalizer tFetcher = new TimeNormalizer();
//		tFetcher.Time_Expression = teString;
//		tFetcher.readPattern("haha");
//		tFetcher.norm_setTotal();
//		Pattern pattern = tFetcher.readPattern("haha");
//		
//		
////		pattern = Pattern.compile(regexString);
//		Matcher matcher = pattern.matcher(teString);
//		if(matcher.find())
//			System.out.println(matcher.group());
//		
//
//	
//		
//	}

}


