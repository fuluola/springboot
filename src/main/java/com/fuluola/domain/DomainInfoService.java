package com.fuluola.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fuluola.model.DomainObject;
import com.fuluola.model.HtmlHead;
import com.fuluola.model.QueryDomainRespMessage;
import com.fuluola.utils.WebUtil;

/** 
 * @description 
 * @author  fuzhuan fu.luola@qq.com
 * @date 2017年7月4日 
 */
@Service
public class DomainInfoService {

	@Autowired
	private NsLookup nslookUp;
	
	@Autowired
	private WhoisService whois;
	
	public QueryDomainRespMessage domainInfoQuery(String domain) {
		QueryDomainRespMessage respMessage = whois.query(domain);
		DomainObject domainInfo = respMessage.getDomainObject();
		String ip = nslookUp.lookUpIP(domain);
		String city = nslookUp.getAddressCityByIp("ip="+ip);
		
		HtmlHead hh = WebUtil.getHtmlHead(domain);
		domainInfo.setTitle(hh.getTitle());
		domainInfo.setKeywords(hh.getKeywords());
		domainInfo.setMeta(hh.getDescription());
		domainInfo.setIp(ip);
		domainInfo.setIpAddress(city);
		return respMessage;
		
	}
	
	public static void main(String[] args) {
		DomainInfoService service = new DomainInfoService();
		service.domainInfoQuery("yunhou.com");
	}
}
