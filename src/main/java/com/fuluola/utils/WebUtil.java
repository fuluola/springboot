package com.fuluola.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/** 
 * @description 
 * @author  fuzhuan fu.luola@qq.com
 * @date 2017年6月26日 
 */
public class WebUtil {

	public static final String CONTENT_TYPE_JSON = "application/json;charset=utf-8";
	public static final String CONTENT_TYPE_XML = "application/xml;charset=utf-8";
	public static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded;charset=utf-8";
	
	public static final String SEPARATOR = "\n";
	public static String get(String path,String keyValue) throws IOException{
		
		URL url = null;
		if(StringUtils.isEmpty(keyValue)){
			url = new URL(path.trim());
		}else{
			url = new URL(path.trim()+"?"+keyValue);
		}
        //打开连接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(30 * 1000);
        conn.setReadTimeout(80 * 1000);
        conn.setUseCaches(false);    
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
		String line = "";
		while ((line = reader.readLine()) != null) {
			buffer.append(line).append(SEPARATOR);
		}
		reader.close();
		conn.disconnect();
		return buffer.toString();
	}
	/**
	 * Post方式请求
	 * @param url 请求地址
	 * @param data 参数
	 * @param contentType 数据类型
	 * 		  1 CONTENT_TYPE_FORM
	 * 		  2 CONTENT_TYPE_XML
	 * 		  3 CONTENT_TYPE_JSON
	 * @return xml数据格式
	 * @throws Exception
	 */
	public static String post(String url, String data, String contentType) throws Exception {
		StringBuffer buffer = new StringBuffer();
		URL getUrl = new URL(url);
		HttpURLConnection connection = (HttpURLConnection)getUrl.openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", contentType);
		connection.setRequestProperty("Connection", "Keep-Alive"); 
		connection.setUseCaches(false); 
		connection.setConnectTimeout(10000);
		connection.connect();
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
		out.write(data);
		out.flush();
		out.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
		String line = "";
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		reader.close();
		connection.disconnect();
		return buffer.toString();
		
	}
	
	/***
	 * 
	 * @param url
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String post(String url, String data) throws Exception {
		 return post(url, data, CONTENT_TYPE_FORM);
	}
}
