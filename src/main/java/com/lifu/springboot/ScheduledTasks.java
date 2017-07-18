/**
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: bubugao yunhou</p>
 */
package com.lifu.springboot;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author fuluola
 *
 */
@Component
@Configurable
@EnableScheduling
public class ScheduledTasks{
	
	private static Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
	@Autowired
	private DomainRepository domainRepo;
	//30秒执行一次
    @Scheduled(fixedRate = 1000 * 600)
    public void reportCurrentTime(){
    	logger.info("开始批量域名信息采集");
    	domainRepo.processBatchDomain();
    	logger.info("批量域名信息采集结束");
    }

    //每1分钟执行一次
//    @Scheduled(cron = "0 */1 *  * * * ")
//    public void reportCurrentByCron(){
//        System.out.println ("Scheduling Tasks Examples By Cron: The time is now " + dateFormat ().format (new Date ()));
//        
//    }
//
//    private SimpleDateFormat dateFormat(){
//        return new SimpleDateFormat ("HH:mm:ss");
//    }
    
}