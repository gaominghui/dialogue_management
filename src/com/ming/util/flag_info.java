package com.ming.util;

public class flag_info {
	
	public static int  all_0(int[]flag){
		int len=flag.length;
		for(int i=0;i<len;i++){
			if(flag[i]==0)continue;
			else return 0;
		}
		return 1;
	}
	public static int  all_1(int[]flag){
		int len=flag.length;
		for(int i=0;i<len;i++){
			if(flag[i]==1)continue;
			else return 0;
		}
		return 1;
	}
	public static int  all_2(int[]flag){
		int len=flag.length;
		for(int i=0;i<len;i++){
			if(flag[i]==2)continue;
			else return 0;
		}
		return 1;
	}
	
	public static int  all_0_except_price(int[]flag){
		int len=flag.length;
		for(int i=0;i<len-1;i++){
			if(flag[i]==0)continue;
			else return 0;
		}
		return 1;
	}
	public static int  all_1_except_price(int[]flag){
		int len=flag.length;
		for(int i=0;i<len-1;i++){
			if(flag[i]==1)continue;
			else return 0;
		}
		return 1;
	}
	public static int  all_2_except_price(int[]flag){
		int len=flag.length;
		for(int i=0;i<len-1;i++){
			if(flag[i]==2)continue;
			else return 0;
		}
		return 1;
	}
	
	public static int exist_1(int[]flag){
		int len=flag.length;
		for(int i=0;i<len;i++){
			if(flag[i]==1) return 1;
		}
		return 0;
	}
	
	public static int exist_0(int[]flag){
		int len=flag.length;
		for(int i=0;i<len;i++){
			if(flag[i]==0) return 1;
		}
		return 0;
	}
	public static int exist_2(int[]flag){
		int len=flag.length;
		for(int i=0;i<len;i++){
			if(flag[i]==2) return 1;
		}
		return 0;
	}
	
	public static int exist_0_except_price(int[]flag){
		int len=flag.length;
		for(int i=0;i<len-1;i++){
			if(flag[i]==0) return 1;
		}
		return 0;
	}
	public static int exist_1_except_price(int[]flag){
		int len=flag.length;
		for(int i=0;i<len-1;i++){
			if(flag[i]==1) return 1;
		}
		return 0;
	}
	public static int exist_2_except_price(int[]flag){
		int len=flag.length;
		for(int i=0;i<len-1;i++){
			if(flag[i]==2) return 1;
		}
		return 0;
	}
	public static int first_0(int[]flag){
		int len=flag.length;
		for(int i=0;i<len;i++){
			if(flag[i]==0){
				return i;
			}
		}
		return -1;
	}
	public static int first_1(int[]flag){
		int len=flag.length;
		for(int i=0;i<len;i++){
			if(flag[i]==1){
				return i;
			}
		}
		return -1;
	}
	public static int first_2(int[]flag){
		int len=flag.length;
		for(int i=0;i<len;i++){
			if(flag[i]==2){
				return i;
			}
		}
		return -1;
	}
	public static int first_0_except_price(int[]flag){
		int len=flag.length;
		for(int i=0;i<len-1;i++){
			if(flag[i]==0){
				return i;
			}
		}
		return -1;
	}
	public static int first_1_except_price(int[]flag){
		int len=flag.length;
		for(int i=0;i<len-1;i++){
			if(flag[i]==1){
				return i;
			}
		}
		return -1;
	}
	public static void modify(int[]flag,int key,int value){
		if(key>flag.length){
			System.out.println("flag_info.modify out of range");
		}else 
			flag[key]=value;
		
	}
	
	/*
	public static void main(String[]args){
		int []flag=new int[]{2,2,2,2};
		flag_info fi=new flag_info();
		System.out.println(fi.all_0(flag));
		System.out.println(fi.all_2(flag));
		System.out.println(fi.exist_1(flag));
	}
	*/
}
