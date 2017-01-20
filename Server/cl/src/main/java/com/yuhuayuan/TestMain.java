package com.yuhuayuan;

public class TestMain {

	public static void main(String[] args) {
	
		float value = (float)(Math.round(1003.2345f*100)/100);
		float num=(float)(Math.round(1003.2345f*100)/100);//如果要求精确4位就*10000然后/10000
		System.out.println((float)Math.round(1003.2345f*100)/100);
		
		
	}
}
