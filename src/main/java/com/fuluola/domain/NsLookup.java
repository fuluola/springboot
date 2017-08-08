package com.fuluola.domain;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.stereotype.Service;

import com.fuluola.model.AddressMessage;
import com.fuluola.utils.ObjectMapperFactory;
import com.fuluola.utils.WebUtil;

/** 
 * @description 
 * @author fz
 * @date 2017年6月26日 
 */
@Service
public class NsLookup {

	private final static String IP_ADDRESS_URL = "http://ip.taobao.com/service/getIpInfo.php";
	public static void main(String[] args) throws IOException {
		 NsLookup ns = new NsLookup();
		 String name = "0913399972.com";
		 String ip = ns.lookUpIP(name);
		 System.out.println(ip);
		 System.out.println(ns.getAddressCityByIp("ip="+ip));
	}
	
	public  String lookUpIP(String host){
		InetAddress addresses;
		try {
			addresses = InetAddress.getByName(host);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return null;
		}
		return addresses.getHostAddress();
	}
	
	public String getAddressCityByIp(String ip) {
		String json;
		AddressMessage address;
		try {
			json = WebUtil.get(IP_ADDRESS_URL,ip);
			address = ObjectMapperFactory.JSON.readValue(json, AddressMessage.class);
			if(address.getCode()==0){
				if("中国".equals(address.getData().getCountry())){
					return address.getData().getCity();
				}else{
					return address.getData().getCountry();
				}
			}
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
