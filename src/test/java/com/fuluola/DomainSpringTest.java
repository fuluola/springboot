package com.fuluola;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lifu.domain.DomainInfoService;
import com.lifu.domain.WhoisService;
import com.lifu.model.QueryDomainRespMessage;
import com.lifu.springboot.DomainRepository;
import com.lifu.Application;

/** 
 * @description 
 * @author  fuzhuan fu.luola@qq.com
 * @date 2017年7月6日 
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)  
public class DomainSpringTest {

    protected final static Logger logger = LoggerFactory.getLogger(DomainSpringTest.class);

    @Autowired
	private WhoisService whoisService ;
	@Autowired
	private DomainInfoService infoService;
	@Autowired
	private DomainRepository domainRepo;
	
	@Test
	public void test() {
		QueryDomainRespMessage msg = infoService.domainInfoQuery("www.telstek.com");
		System.out.println(msg);
	}
	
//	@Test
	public void test2(){
		domainRepo.insertDomain("www.开始批量域名信息采集88.com");
	}
}
