package com.ming;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 使用json-lib构造和解析Json数据
 * 
 * @author Alexia
 * @date 2013/5/23
 * 
 */
public class OrgJsonTest {

    /**
     * 练习类，没有用了
     * 
     * @return
     * @throws JSONException
     */
    public static String BuildJson() throws JSONException {

        // JSON格式数据解析对象
        JSONObject jo = new JSONObject();
        boolean end=false;
        jo.put("end", end);
        jo.put("sentence", "你好");
        
        // 下面构造两个map、一个list和一个Employee对象
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("MainLocation", "北京海淀");
        map1.put("MainPerNum", "200");
        map1.put("MainTime", "1989-10-1");
        map1.put("MainTimePeriod", "几个小时");
        map1.put("MainPrice", "20000");
    

     
        JSONArray ja = new JSONArray();
        ja.put(map1);

        jo.put("slots", ja);

        return jo.toString();

    }

    /**
     * 解析Json数据
     * 
     * @param jsonString
     *            Json数据字符串
     * @throws JSONException
     * @throws ParseException
     */
    public static void ParseJson(String jsonString) throws JSONException,
            ParseException {

        JSONObject jo = new JSONObject(jsonString);
        JSONArray ja = jo.getJSONArray("slots");
        boolean end=jo.getBoolean("end");
        String sentence=jo.getString("sentence");
   //     System.out.println("\n将Json数据解析为Map：");
        System.out.println("end "+end);
        System.out.println("sentence "+sentence);
        System.out.println("MainLocation: " + ja.getJSONObject(0).getString("MainLocation")
                + " MainPerNum: " + ja.getJSONObject(0).getString("MainPerNum") 
                + " MainTime: " + ja.getJSONObject(0).getString("MainTime") 
                + " MainTimePeriod: " + ja.getJSONObject(0).getString("MainTimePeriod") 
                + " MainPrice: " + ja.getJSONObject(0).getString("MainPrice") );

      

    }

    /**
     * @param args
     * @throws JSONException
     * @throws ParseException
     */
    public static void main(String[] args) throws JSONException, ParseException {
        // TODO Auto-generated method stub

        ParseJson(BuildJson());
    }

}


class Employee{
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	String name;
	String sex;
	int age;
}