package com.fuluola.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fuluola.model.DomainObject;
import com.fuluola.model.HtmlHead;
import com.fuluola.model.QueryDomainRespMessage;
import com.fuluola.utils.WebUtil;
import com.mysql.jdbc.StringUtils;

/** 
 * @description 
 * @author  fuzhuan fu.luola@qq.com
 * @date 2017年7月4日 
 */
@Service
public class DomainInfoService {


	private static Logger logger = LoggerFactory.getLogger(DomainInfoService.class);
	
	private String PR_URL = "http://pr.chinaz.com/";
	
	@Autowired
	private NsLookup nslookUp;
	
	@Autowired
	private WhoisService whois;
	
	public QueryDomainRespMessage domainInfoQuery(String domain) {
		logger.info("开始采集whois信息,域名："+domain);
		QueryDomainRespMessage respMessage = whois.query(domain);
		if(respMessage==null)
			return null;
		if(respMessage.getCode()==null || respMessage.getDomainObject()==null){
			return null;
		}
		DomainObject domainInfo = respMessage.getDomainObject();
		domainInfo.setDomainName(domain);
		logger.info("开始采集ip地址信息,域名："+domain);
		String ip = nslookUp.lookUpIP(domain);
		if(ip!=null){
			String address = nslookUp.getAddressCityByIp("ip="+ip);
			domainInfo.setIpAddress(address);
		}
		logger.info("开始采集网页信息,域名："+domain);
		HtmlHead hh = WebUtil.getHtmlHead(domain);
		if(hh!=null){
			domainInfo.setTitle(hh.getTitle());
			domainInfo.setKeywords(hh.getKeywords());
			domainInfo.setDescription(hh.getDescription());
		}
		domainInfo.setIp(ip);
		domainInfo.setGooglePR(getGooglePR(domain));
		return respMessage;
		
	}
	
	public String getGooglePR(String domain)  {
		Runtime rt = Runtime.getRuntime();
		Process p;
		try {
			p = rt.exec("phantomjs.exe D:\\dev_soft\\phantomjs-2.1.1-windows\\netsniff2.js "+PR_URL+domain);
			InputStream is = p.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line=null;
			String result = "";
			while((line=br.readLine())!=null){
				result += line;
			}
			if(!StringUtils.isNullOrEmpty(result)){
				
				String pr=result.substring(result.lastIndexOf(".")-1, result.lastIndexOf("."));
				return pr;
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}
