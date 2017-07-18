package com.lifu.domain;

import java.io.IOException;

import com.lifu.utils.WebUtil;

/** 
 * @description 
 * @author  fuzhuan fu.luola@qq.com
 * @date 2017年6月26日 
 */
public class AddressIp {
	
	private final static String IP_ADDRESS_URL = "http://ip.taobao.com/service/getIpInfo.php";
	
	public static String getAddressByIp(String ip) throws IOException {
		String json = WebUtil.get(IP_ADDRESS_URL,ip);
		return json;
	}
	
	public static void main(String[] args) throws IOException {
		//AddressMessage address = ObjectMapperFactory.JSON.readValue(getAddressByIp("ip=113.57.228.95"), AddressMessage.class);
	//	System.out.println(address.getData().getCity()); 
		//System.out.println(getAddressByIp("ip=113.57.228.95"));
		System.out.println(WebUtil.getHtmlHead("www.mccookhumanesociety.com"));
	}
 
}
