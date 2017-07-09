package com.fuluola;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fuluola.domain.DomainInfoService;
import com.fuluola.domain.WhoisService;
import com.fuluola.model.QueryDomainRespMessage;

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
	
	@Test
	public void test() {
		QueryDomainRespMessage msg = infoService.domainInfoQuery("webmasterhome.cn");
		System.out.println(msg);
	}
}
