/**
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: bubugao yunhou</p>
 */
package com.fuluola.springboot;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.fuluola.utils.WebUtil;

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
    	
    	Connection.Response response = Jsoup.connect("http://www.cnhnb.com").execute();
    	Map<String, String> headerMap= response.headers();
    	String body = response.body();
    	Document document = Jsoup.parse(body);
    	String title = document.head().select("title").text();
    	String keywords = document.head().select("meta[name=keywords]").attr("content");
    	System.out.println(title);
    	System.out.println(keywords);

	}
}
