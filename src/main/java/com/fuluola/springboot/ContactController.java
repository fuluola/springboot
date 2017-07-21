/**
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: bubugao yunhou</p>
 */
package com.fuluola.springboot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fuluola.utils.ObjectMapperFactory;

/**
 * @author fuluola
 *
 */
@Controller
@RequestMapping("/home")
public class ContactController {

	private static final Logger logger = LoggerFactory.getLogger(ContactController.class);
	private ContactRepository contactRepo;
    
	@Autowired
	private DomainRepository domainRepo;
	@Autowired
	public ContactController(ContactRepository contactRepo){
		this.contactRepo = contactRepo;
	}
	
    
	@RequestMapping(method=RequestMethod.GET)
	public String home(Map<String,Object> model){
		List<Contact> contacts = contactRepo.findAll();
		model.put("contacts", contacts);
		return "home";
	}

	@RequestMapping(value="importInit",method=RequestMethod.GET)
	public String domainImport(Map<String,Object> model){
		return "domainImport";
	}
	
	@ResponseBody
	@RequestMapping(value="importDomain",method=RequestMethod.POST)
	public String importDomain(@RequestParam(value = "file", required = true) MultipartFile file) throws IOException{
		int totalRow = 0;
		String resultStr = "",line;
        String fileName=file.getOriginalFilename();
        logger.info("导入文件名:"+fileName);
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
        if(!"txt".equals(suffix)){
        	resultStr="请导入文本格式文件";
        }else{
        	BufferedReader in = new BufferedReader(new InputStreamReader( file.getInputStream(),"utf-8"));
          	while((line=in.readLine())!=null){
          		int succRow = domainRepo.insertDomain(line);
          		totalRow+=succRow;
          		if(succRow==1){
          			logger.info(line+" 成功插入数据库!");
          		}
         	}
          	resultStr="成功导入"+totalRow+"个域名!";
        }
		return resultStr;
	}
	
	@RequestMapping(value="domainResult",method=RequestMethod.GET)
	public Object domainResult(Map<String,Object> model){
	//	List<Map<String,Object>> resultMap = domainRepo.pageQueryDomainInfo(null);
	//	model.put("model", resultMap);
		return "pageDomainInfo";
	}
	
	@ResponseBody
	@RequestMapping(value="domainList",method=RequestMethod.POST)
	public Object domainList(Map<String,Object> model,String paramStr,HttpServletRequest request) throws JsonProcessingException{
	
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String param = request.getParameter("param");
		if(!StringUtils.isEmptyOrWhitespace(param)){
			model.put("param", param);
		}
		Integer start = (Integer.parseInt(page)-1)*Integer.parseInt(rows);
		model.put("start",start);
		model.put("rows",Integer.parseInt(rows));
		List<Map<String,Object>> resultMap = domainRepo.pageQueryDomainInfo(model);
		Integer total = domainRepo.pageQueryDomainTotal(param);
		model.clear();
		model.put("total",total);
		model.put("rows", resultMap);
		String json = ObjectMapperFactory.JSON.writeValueAsString(model);
		return json;
	}
	
	@ResponseBody
	@RequestMapping(value="updateRemark",method=RequestMethod.POST)
	public Object updateRemark(String domain,String remark){
		if(StringUtils.isEmptyOrWhitespace(remark)){
			return "备注为空";
		}
		domainRepo.updateRemark(remark, domain);
		return "SUCCESS";
	}
}
