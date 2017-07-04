package com.fuluola.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fuluola.model.QueryDomainRespMessage;

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
	private Whois whois;
	
	public QueryDomainRespMessage domainInfoQuery(String domain) {
		QueryDomainRespMessage respMessage = new QueryDomainRespMessage();
		
		
		return respMessage;
		
	}
}
