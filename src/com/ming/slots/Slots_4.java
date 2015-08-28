package com.ming.slots;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.List;
import java.util.LinkedList;

import com.ming.actions.user_attitudes;
import com.ming.crf.Fetch;
import com.ming.util.Activity_slots_info;

public class Slots_4 {
	/**
	 * 变量及其get,set方法
	 * 
	 */
	private List<String> MainLocation;
	private List<String> MainPerNum;
	private List<String> MainTime;
	private List<String> MainTimePeriod;
	private List<String> MainPrice;

	private List<String> minor_bai_fang;
	private List<String> minor_mian_ji;
	private List<String> minor_dang_ci;
	private List<String> minor_chang_kuan_gao;
	private List<String> minor_cha_xie;
	private List<String> minor_shi_nei_wai;

	public void printMain(){
		//打印存储的值
	
		for(int i=0;i<this.getMainLocation().size();i++){
			System.out.print(this.getMainLocation().get(i)+"\t");
		}
		System.out.println();
		for(int i=0;i<this.getMainPerNum().size();i++){
			System.out.print(this.getMainPerNum().get(i)+"\t");
		}
		System.out.println();
		for(int i=0;i<this.getMainTime().size();i++){
			System.out.print(this.getMainTime().get(i)+"\t");
		}
		System.out.println();
		for(int i=0;i<this.getMainTimePeriod().size();i++){
			System.out.print(this.getMainTimePeriod().get(i)+"\t");
		}
		System.out.println();
		for(int i=0;i<this.getMainPrice().size();i++){
			System.out.print(this.getMainPrice().get(i)+"\t");
		}
		System.out.println();
		
		
	}
	public int getMainSlotNum() {
		return mainSlotNum;
	}

	public void setMainSlotNum(int mainSlotNum) {
		this.mainSlotNum = mainSlotNum;
	}

	public int getMinorSlotNum() {
		return minorSlotNum;
	}

	public void setMinorSlotNum(int minorSlotNum) {
		this.minorSlotNum = minorSlotNum;
	}

	private int mainSlotNum = 5;
	private int minorSlotNum = 6;

	public List<String> getTemp_MainLocation() {
		return temp_MainLocation;
	}

	public void setTemp_MainLocation(List<String> temp_MainLocation) {
		this.temp_MainLocation = temp_MainLocation;
	}

	public List<String> getTemp_MainPerNum() {
		return temp_MainPerNum;
	}

	public void setTemp_MainPerNum(List<String> temp_MainPerNum) {
		this.temp_MainPerNum = temp_MainPerNum;
	}

	public List<String> getTemp_MainTime() {
		return temp_MainTime;
	}

	public void setTemp_MainTime(List<String> temp_MainTime) {
		this.temp_MainTime = temp_MainTime;
	}

	public List<String> getTemp_MainTimePeriod() {
		return temp_MainTimePeriod;
	}

	public void setTemp_MainTimePeriod(List<String> temp_MainTimePeriod) {
		this.temp_MainTimePeriod = temp_MainTimePeriod;
	}

	public List<String> getTemp_MainPrice() {
		return temp_MainPrice;
	}

	public void setTemp_MainPrice(List<String> temp_MainPrice) {
		this.temp_MainPrice = temp_MainPrice;
	}

	public List<String> getTemp_minor_bai_fang() {
		return temp_minor_bai_fang;
	}

	public void setTemp_minor_bai_fang(List<String> temp_minor_bai_fang) {
		this.temp_minor_bai_fang = temp_minor_bai_fang;
	}

	public List<String> getTemp_minor_mian_ji() {
		return temp_minor_mian_ji;
	}

	public void setTemp_minor_mian_ji(List<String> temp_minor_mian_ji) {
		this.temp_minor_mian_ji = temp_minor_mian_ji;
	}

	public List<String> getTemp_minor_dang_ci() {
		return temp_minor_dang_ci;
	}

	public void setTemp_minor_dang_ci(List<String> temp_minor_dang_ci) {
		this.temp_minor_dang_ci = temp_minor_dang_ci;
	}

	public List<String> getTemp_minor_chang_kuan_gao() {
		return temp_minor_chang_kuan_gao;
	}

	public void setTemp_minor_chang_kuan_gao(
			List<String> temp_minor_chang_kuan_gao) {
		this.temp_minor_chang_kuan_gao = temp_minor_chang_kuan_gao;
	}

	public List<String> getTemp_minor_cha_xie() {
		return temp_minor_cha_xie;
	}

	public void setTemp_minor_cha_xie(List<String> temp_minor_cha_xie) {
		this.temp_minor_cha_xie = temp_minor_cha_xie;
	}

	public List<String> getTemp_minor_shi_nei_wai() {
		return temp_minor_shi_nei_wai;
	}

	public void setTemp_minor_shi_nei_wai(List<String> temp_minor_shi_nei_wai) {
		this.temp_minor_shi_nei_wai = temp_minor_shi_nei_wai;
	}
	/**
	 *每次提取到的值都放到temp_MainLocation
	 *只有确认过后才会放到对应的MainLocation
	 *就是说MainLocation只存贮最终结果
	 */
	private List<String> temp_MainLocation;
	private List<String> temp_MainPerNum;
	private List<String> temp_MainTime;
	private List<String> temp_MainTimePeriod;
	private List<String> temp_MainPrice;

	private List<String> temp_minor_bai_fang;
	private List<String> temp_minor_mian_ji;
	private List<String> temp_minor_dang_ci;
	private List<String> temp_minor_chang_kuan_gao;
	private List<String> temp_minor_cha_xie;
	private List<String> temp_minor_shi_nei_wai;

	/*
	 * private Map map=new HashMap();
	 * 
	 * public Slots(){ map.put(0, MainLocation); map.put(1, MainPerNum);
	 * map.put(2, MainTimePeriod); map.put(3, MainTimePeriodPeriod); map.put(4,
	 * MainPrice); map.put(5, minor_bai_fang); map.put(6, minor_mian_ji);
	 * map.put(7, minor_dang_ci); map.put(8, minor_chang_kuan_gao); map.put(9,
	 * minor_cha_xie); map.put(10, minor_shi_nei_wai); }
	 */
	public String find(int i) {
		switch (i) {
		case 0:
			return "MainLocation";
		case 1:
			return "MainPerNum";
		case 2:
			return "MainTime";
		case 3:
			return "MainTimePeriod";
		case 4:
			return "MainPrice";
		case 5:
			return "minor_bai_fang";
		case 6:
			return "minor_mian_ji";
		case 7:
			return "minor_dang_ci";
		case 8:
			return "minor_chang_kuan_gao";
		case 9:
			return "minor_cha_xie";
		case 10:
			return "minor_shi_nei_wai";
		default:
			return "wrong";

		}
	}
	//通过名字获得结果
	public List<String> get(String key) {
		List<String> ret = null;
		if (key.equals("MainLocation")) {
			ret = this.getMainLocation();
		} else if (key.equals("MainPerNum")) {
			ret = this.getMainPerNum();
		} else if (key.equals("MainTime")) {
			ret = this.getMainTime();
		} else if (key.equals("MainTimePeriod")) {
			ret = this.getMainTimePeriod();
		} else if (key.equals("MainPrice")) {
			ret = this.getMainPrice();
		} else if (key.equals("minor_bai_fang")) {
			ret = this.getMinor_bai_fang();
		} else if (key.equals("minor_mian_ji")) {
			ret = this.getMinor_mian_ji();
		} else if (key.equals("minor_dang_ci")) {
			ret = this.getMinor_dang_ci();
		} else if (key.equals("minor_chang_kuan_gao")) {
			ret = this.getMinor_chang_kuan_gao();
		} else if (key.equals("minor_cha_xie")) {
			ret = this.getMinor_cha_xie();
		} else if (key.equals("minor_shi_nei_wai")) {
			ret = this.getMinor_shi_nei_wai();
		}
		return ret;
	}
	
	//修改结果值
	public void modify(String key, List<String> value) {
		if (key.equals("MainLocation")) {
			this.setMainLocation(value);
		} else if (key.equals("MainPerNum")) {
			this.setMainPerNum(value);
		} else if (key.equals("MainTime")) {
			this.setMainTime(value);
		} else if (key.equals("MainTimePeriod")) {
			this.setMainTimePeriod(value);
		} else if (key.equals("MainPrice")) {
			this.setMainPrice(value);
		} else if (key.equals("minor_bai_fang")) {
			this.setMinor_bai_fang(value);
		} else if (key.equals("minor_mian_ji")) {
			this.setMinor_mian_ji(value);
		} else if (key.equals("minor_dang_ci")) {
			this.setMinor_dang_ci(value);
		} else if (key.equals("minor_chang_kuan_gao")) {
			this.setMinor_chang_kuan_gao(value);
		} else if (key.equals("minor_cha_xie")) {
			this.setMinor_cha_xie(value);
		} else if (key.equals("minor_shi_nei_wai")) {
			this.setMinor_shi_nei_wai(value);
		}

	}
	//获得中间值
	public List<String> getTemp(String key) {
		List<String> ret = null;
		if (key.equals("MainLocation")) {
			ret = this.getTemp_MainLocation();
		} else if (key.equals("MainPerNum")) {
			ret = this.getTemp_MainPerNum();
		} else if (key.equals("MainTime")) {
			ret = this.getTemp_MainTime();
		} else if (key.equals("MainTimePeriod")) {
			ret = this.getTemp_MainTimePeriod();
		} else if (key.equals("MainPrice")) {
			ret = this.getTemp_MainPrice();
		} else if (key.equals("minor_bai_fang")) {
			ret = this.getTemp_minor_bai_fang();
		} else if (key.equals("minor_mian_ji")) {
			ret = this.getTemp_minor_mian_ji();
		} else if (key.equals("minor_dang_ci")) {
			ret = this.getTemp_minor_dang_ci();
		} else if (key.equals("minor_chang_kuan_gao")) {
			ret = this.getTemp_minor_chang_kuan_gao();
		} else if (key.equals("minor_cha_xie")) {
			ret = this.getTemp_minor_cha_xie();
		} else if (key.equals("minor_shi_nei_wai")) {
			ret = this.getTemp_minor_shi_nei_wai();
		}
		return ret;
	}
	//修改中间值
	public void modifyTemp(String key, List<String> value) {
		if (key.equals("MainLocation")) {
				this.setTemp_MainLocation(value);
		} else if (key.equals("MainPerNum")) {
			this.setTemp_MainPerNum(value);
		} else if (key.equals("MainTime")) {
			this.setTemp_MainTime(value);
		} else if (key.equals("MainTimePeriod")) {
			this.setTemp_MainTimePeriod(value);
		} else if (key.equals("MainPrice")) {
			this.setTemp_MainPrice(value);
		} else if (key.equals("minor_bai_fang")) {
			this.setTemp_minor_bai_fang(value);
		} else if (key.equals("minor_mian_ji")) {
			this.setTemp_minor_mian_ji(value);
		} else if (key.equals("minor_dang_ci")) {
			this.setTemp_minor_dang_ci(value);
		} else if (key.equals("minor_chang_kuan_gao")) {
			this.setTemp_minor_chang_kuan_gao(value);
		} else if (key.equals("minor_cha_xie")) {
			this.setTemp_minor_cha_xie(value);
		} else if (key.equals("minor_shi_nei_wai")) {
			this.setTemp_minor_shi_nei_wai(value);
		}

	}

	//添加中间值
	public void addTemp(String key, List<String> value) {
		if (key.equals("MainLocation")) {
				this.getTemp_MainLocation().addAll(value);
		} else if (key.equals("MainPerNum")) {
			this.getTemp_MainPerNum().addAll(value);
		} else if (key.equals("MainTime")) {
			this.getTemp_MainTime().addAll(value);
		} else if (key.equals("MainTimePeriod")) {
			this.getTemp_MainTimePeriod().addAll(value);
		} else if (key.equals("MainPrice")) {
			this.getTemp_MainPrice().addAll(value);
		} else if (key.equals("minor_bai_fang")) {
			this.getTemp_minor_bai_fang().addAll(value);
		} else if (key.equals("minor_mian_ji")) {
			this.getTemp_minor_mian_ji().addAll(value);
		} else if (key.equals("minor_dang_ci")) {
			this.getTemp_minor_dang_ci().addAll(value);
		} else if (key.equals("minor_chang_kuan_gao")) {
			this.getTemp_minor_chang_kuan_gao().addAll(value);
		} else if (key.equals("minor_cha_xie")) {
			this.getTemp_minor_cha_xie().addAll(value);
		} else if (key.equals("minor_shi_nei_wai")) {
			this.getTemp_minor_shi_nei_wai().addAll(value);
		}

	}

	public List<String> getMainLocation() {
		return MainLocation;
	}

	public void setMainLocation(List<String> MainLocation) {
		this.MainLocation = MainLocation;
	}

	public  List<String> getMainPerNum() {
		return MainPerNum;
	}

	public void setMainPerNum(List<String> MainPerNum) {
		this.MainPerNum = MainPerNum;
	}

	public  List<String> getMainTime() {
		return MainTime;
	}

	public void setMainTime(List<String> MainTime) {
		this.MainTime = MainTime;
	}

	public List<String> getMainTimePeriod() {
		return MainTimePeriod;
	}

	public void setMainTimePeriod(List<String> MainTimePeriod) {
		this.MainTimePeriod = MainTimePeriod;
	}

	public List<String> getMainPrice() {
		return MainPrice;
	}

	public void setMainPrice(List<String> MainPrice) {
		this.MainPrice = MainPrice;
	}

	public List<String> getMinor_bai_fang() {
		return minor_bai_fang;
	}

	public void setMinor_bai_fang(List<String> minor_bai_fang) {
		this.minor_bai_fang = minor_bai_fang;
	}

	public List<String> getMinor_mian_ji() {
		return minor_mian_ji;
	}

	public void setMinor_mian_ji(List<String> minor_mian_ji) {
		this.minor_mian_ji = minor_mian_ji;
	}

	public List<String> getMinor_dang_ci() {
		return minor_dang_ci;
	}

	public void setMinor_dang_ci(List<String> minor_dang_ci) {
		this.minor_dang_ci = minor_dang_ci;
	}

	public List<String> getMinor_chang_kuan_gao() {
		return minor_chang_kuan_gao;
	}

	public void setMinor_chang_kuan_gao(List<String> minor_chang_kuan_gao) {
		this.minor_chang_kuan_gao = minor_chang_kuan_gao;
	}

	public List<String> getMinor_cha_xie() {
		return minor_cha_xie;
	}

	public void setMinor_cha_xie(List<String> minor_cha_xie) {
		this.minor_cha_xie = minor_cha_xie;
	}

	public List<String> getMinor_shi_nei_wai() {
		return minor_shi_nei_wai;
	}

	public void setMinor_shi_nei_wai(List<String> minor_shi_nei_wai) {
		this.minor_shi_nei_wai = minor_shi_nei_wai;
	}

	public int[] getActivity_flag() {
		return activity_flag;
	}

	public void setActivity_flag(int[] activity_flag) {
		this.activity_flag = activity_flag;
	}
	//获得槽的状态没有值为0，temp有值，非temp没有值1，非temp有值2
	public int[] getMainState(){
		int len=this.getMainSlotNum();
		int[]ret=new int[len];
		for(int i=0;i<len;i++){
			if(this.getTemp(this.find(i))==null&&this.get(this.find(i))==null){
				ret[i]=0;
			}
			else if(this.getTemp(this.find(i))!=null&&this.get(this.find(i))==null){
				ret[i]=1;
			}
			else if(this.get(this.find(i))!=null){
				
				ret[i]=2;
			}
		}
		return ret;
	}
	//通过名字获得槽的状态
	public int getSlotMainState(String slotName){
	
		if(this.getTemp(slotName)==null&&this.get(slotName)==null)
			return 0;
		else if(this.getTemp(slotName)!=null&&this.get(slotName)==null)
			return 1;
		else  return 2;
		
	
	}
	//通过代号获得槽的状态
	public int getSlotMainState(int slotNum){
		String key=this.find(slotNum);
		return getSlotMainState(key);
	}
	public String attributeTOString(int i){
		StringBuffer sb=new StringBuffer();
	     if(this.getSlotMainState(i)==0){
	    	 sb.append("");
	     }else if(this.getSlotMainState(i)==1){
	    	 sb.append("?");
	    	 for(String temp:this.getTemp(this.find(i))){
		    	 sb.append(temp+" ");
		     }
	     }else{
	    	 sb.append("!");
	    	   for(String temp:this.get(this.find(i))){
			    	 sb.append(temp+" ");
			     }
	     }
	     return sb.toString();
	}
	//activity_flag
	/*
	 * 一共有三种情况1,表示提问希望由0到1
	 * 2，表示确认，希望由1到2
	 * 3，表示再次提问（re_request）想有0到1
	 */
	private int[] activity_flag = new int[] { 0, 0, 0, 0, 0 };
	//清空所有值
	public void clear() {
		// TODO Auto-generated method stub
		this.setMainLocation(null);
		this.setMainPerNum(null);
		this.setMainTime(null);
		this.setMainTimePeriod(null);
		this.setMainPrice(null);
		this.setMinor_bai_fang(null);
		this.setMinor_mian_ji(null);
		this.setMinor_dang_ci(null);
		this.setMinor_chang_kuan_gao(null);
		this.setMinor_cha_xie(null);
		this.setMinor_shi_nei_wai(null);
		
		this.setTemp_MainLocation(null);
		this.setTemp_MainPerNum(null);
		this.setTemp_MainPrice(null);
		this.setTemp_MainTime(null);
		this.setTemp_MainTimePeriod(null);
		this.setTemp_minor_bai_fang(null);
		this.setTemp_minor_cha_xie(null);
		this.setTemp_minor_chang_kuan_gao(null);
		this.setTemp_minor_dang_ci(null);
		this.setTemp_minor_mian_ji(null);
		this.setTemp_minor_shi_nei_wai(null);
		
	}

}
