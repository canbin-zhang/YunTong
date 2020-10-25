package com.utils;

import java.util.Random;

public class MyUtils {
	
	//生成10位的随机字符串，用在用户首次注册时给予密码
	public static String randomString() {
		char code[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
				'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
				'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
				'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '2',
				'3', '4', '5', '6', '7', '8', '9' };
		String result="";
		for(int i=0;i<10;i++) {
			result += code[new Random().nextInt(code.length-1)];
		}
		return result;
	}
}
