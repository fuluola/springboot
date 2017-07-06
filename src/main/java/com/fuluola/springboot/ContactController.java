/**
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: bubugao yunhou</p>
 */
package com.fuluola.springboot;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fuluola.domain.DomainInfoService;
import com.fuluola.model.QueryDomainRespMessage;

/**
 * @author fuluola
 *
 */
@Controller
@RequestMapping("/home")
public class ContactController {

	private ContactRepository contactRepo;
	
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
	
	@ResponseBody
	@RequestMapping(value="domain",method=RequestMethod.GET)
	public QueryDomainRespMessage domain(String domain){
		return domainService.domainInfoQuery(domain);
	}
}
