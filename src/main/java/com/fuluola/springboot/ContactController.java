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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fuluola.domain.DomainInfoService;
import com.fuluola.utils.ParseResultDomainInfo;

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
	
    @Autowired
	private DomainInfoService domainService ;
    
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
		String resultStr = "导入成功!",line;
        String fileName=file.getOriginalFilename();
        logger.info("导入文件名:"+fileName);
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
        if(!"txt".equals(suffix)){
        	resultStr="请导入文本格式文件";
        }else{
        	BufferedReader in = new BufferedReader(new InputStreamReader( file.getInputStream()));
          	while((line=in.readLine())!=null){
          		domainRepo.insertDomain(line);
         	}
        }
		return resultStr;
	}
}
