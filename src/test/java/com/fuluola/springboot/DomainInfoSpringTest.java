package com.fuluola.springboot;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

import com.fuluola.Application;
import com.fuluola.domain.NsLookup;
import com.fuluola.domain.WhoisService;

/** 
 * @description 
 * @author  fuzhuan fu.luola@qq.com
 * @date 2017年7月6日 
 */
public class DomainInfoSpringTest {

    protected final static Logger logger = LoggerFactory.getLogger(DomainInfoSpringTest.class);
    ConfigurableApplicationContext ctx = null;
	private NsLookup nslookUp = null;
	private WhoisService whois = null;
	
	@Before
    public void setUp() throws Exception {
        ctx = SpringApplication.run(Application.class);
        whois = ctx.getBean(WhoisService.class);
        nslookUp = ctx.getBean(NsLookup.class);
    }
	
	@Test
	public void testSome() {
		System.out.println(ctx);
	}
}
