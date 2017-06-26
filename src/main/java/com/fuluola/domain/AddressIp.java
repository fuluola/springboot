package com.fuluola.domain;

import java.io.IOException;

import com.fuluola.model.AddressMessage;
import com.fuluola.utils.ObjectMapperFactory;
import com.fuluola.utils.WebUtil;

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
		AddressMessage address = ObjectMapperFactory.JSON.readValue(getAddressByIp("ip=124.232.133.243"), AddressMessage.class);
		System.out.println(address.getData().getCity());
	}
 
}
