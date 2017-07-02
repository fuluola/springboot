/**
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: bubugao yunhou</p>
 */
package com.fuluola.utils;

import com.fuluola.model.DomainObject;
import com.mysql.jdbc.StringUtils;

/**
 * @author fuluola
 *
 */

public class ParseResultDomainInfo {

	public static DomainObject parseComDomainInfo(DomainObject obj,String line){
		
		if(line.startsWith("Creation Date:")){
			obj.setCreationDate(line.split(":")[1].trim());
		}else if(line.startsWith("Registrar Registration Expiration Date:")){
			obj.setExpirationDate(line.split(":")[1].trim());
		}else if(line.startsWith("Registrant Name:")){
			obj.setRegistrantName(line.split(":")[1].trim());
		}else if(line.startsWith("Registrant Organization:")){
			String ro = line.split(":")[1];
			obj.setRegistrantOrganization((StringUtils.isEmptyOrWhitespaceOnly(ro)?"":ro).trim());
		}else if(line.startsWith("Registrant Phone:")){
			String rp = line.split(":")[1];
			obj.setRegistrantPhone((StringUtils.isEmptyOrWhitespaceOnly(rp)?"":rp).trim());
		}else if(line.startsWith("Registrant Email:")){
			obj.setRegistrantEmail(line.split(":")[1].trim());
		}else if(line.startsWith("Name Server")){
			obj.setNsServer(line.split(":")[1]);
		}else if(line.startsWith("Name Server")){
			obj.setNsServer(line.split(":")[1]);
		}
		return obj;
	}
}
