package com.ming;

import java.io.*;
public class OUT {
	/**
	 * 
	 * 模拟输出，废弃类
	 * 
	 * @param file
	 * @param sentence
	 */
	public static void agent_out(String file,String sentence){
		try {
			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			FileWriter writer = new FileWriter(file, true);
			writer.write(sentence+"\n");
			//writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[]args){
		agent_out("./src/agent_out","nihao,zhongguo");
	}
}
