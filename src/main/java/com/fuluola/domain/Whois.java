/**
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: bubugao yunhou</p>
 */
package com.fuluola.domain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Whois {  
//	  if ("com".equals(tld)) {  
//          server = "whois.verisign-grs.com";  
//      } else 
    private static final int DEFAULT_PORT = 43;  
    static String[] urls = new String[]{"cnhnb.com","csdn.net","yunhou.com"};
      //grs-whois.hichina.com whois.paycenter.com.cn
    public String query(String domain) throws Exception {  
        String server = "";  
        String tld = getTLD(domain);  
      if ("net".equals(tld)) {  
            server = "whois.networksolutions.com";  
        } else if ("org".equals(tld)) {  
            server = "whois.pir.org";  
        } else if ("cn".equals(tld)) {  
            server = "whois.cnnic.cn";  
        } else if ("jp".equals(tld)) {  
            server = "whois.jprs.jp";  
        } else if ("kr".equals(tld)) {  
            server = "whois.kr";  
        }else if("io".equals(tld)){
        	server = "whois.nic.io";
        }
        return query(domain, server);  
    }  
      
    public String query(String domain, String server) throws Exception {  
        Socket socket = new Socket(server, DEFAULT_PORT);  
        String lineSeparator = "\r\n";  
  
        PrintWriter out = new PrintWriter(socket.getOutputStream());  
        out.println(domain);  
        out.flush();  
  
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));  
        StringBuilder ret = new StringBuilder();  
        String line;  
        while ((line = in.readLine()) != null) {  
            ret.append(line + lineSeparator);  
        }  
        socket.close();  
        return ret.toString();  
    }  
      
    private String getTLD(String domain) {  
        final int index;  
        return (domain == null || (index = domain.lastIndexOf('.') + 1) < 1) ? domain  
                : (index < (domain.length())) ? domain.substring(index) : "";  
    }  
      
    public static void main(String[] args) throws Exception {  
        Whois w = new Whois();  
      //  System.out.println(w.query("spring.io")); 
        long start=System.currentTimeMillis();
        for(int i=0;i<10;i++){
        	
        	System.out.println(w.query(urls[i%urls.length]));  
        }
        System.out.println("用时: "+(System.currentTimeMillis()-start));
//        System.out.println(w.query("apache.org"));  
      //  System.out.println(w.query("360.cn"));          //china  
//        System.out.println(w.query("nicovideo.jp"));         //japan  
      //  System.out.println(w.query("laneige.co.kr"));   //korea  
    }  
      
}  