/**
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: bubugao yunhou</p>
 */
package com.fuluola.springboot;

import java.io.IOException;

import com.lifu.model.HtmlHead;
import com.lifu.utils.WebUtil;

/**
 * @author fuluola
 *
 */
public class UtilTest {

    private static String getTLD(String domain) {  
        final int index;  
        return (domain == null || (index = domain.lastIndexOf('.') + 1) < 1) ? domain  
                : (index < (domain.length())) ? domain.substring(index) : "";  
    }  
    
    public static void main(String[] args) throws IOException {

//    	String domain = "baidu.com".replace("www.", "");
//    	HtmlHead hh = WebUtil.getHtmlHead("http://www.mi.com");
//		System.out.println(hh.getTitle());
		System.out.println(WebUtil.get("http://www.cnhnb.com",""));
    	
    	
	}
}
