package com.fuluola.domain;

import java.net.InetAddress;
import java.net.UnknownHostException;

/** 
 * @description 
 * @author  fuzhuan fu.luola@qq.com
 * @date 2017年6月26日 
 */
public class NsLookup {


	public static void main(String[] args) {
		
		 String name = "www.taobao.com";
		 System.out.println(lookupIP(name));
	}
	
	public static String lookupIP(String host){
		InetAddress addresses;
		try {
			addresses = InetAddress.getByName(host);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return null;
		}
		return addresses.getHostAddress();
	}
}
