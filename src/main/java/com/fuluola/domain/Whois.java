/**
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: bubugao yunhou</p>
 */
package com.fuluola.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class Whois {  

    private static final int DEFAULT_PORT = 43;  
    static String[] urls = new String[]{"=baidu.com","ele.me","csdn.net"};
      //grs-whois.hichina.com whois.paycenter.com.cn whois.markmonitor.com whois.verisign-grs.com
    public String query(String domain) throws Exception {  
        String server = "";  
        String tld = getTLD(domain);  
        if ("com".equals(tld)) {  
          server = "whois.verisign-grs.com";  
        } else if ("net".equals(tld)) {  
            server = "whois.networksolutions.com";  
        } else if ("org".equals(tld)) {  
            server = "whois.pir.org";  
        } else if ("cn".equals(tld)) {  
            server = "whois.cnnic.cn";  
        } else if ("jp".equals(tld)) {  
            server = "whois.jprs.jp";  
        } else if ("tw".equals(tld)) {  
            server = "whois.twnic.tw";  
        }else if("io".equals(tld)){
        	server = "whois.nic.io";
        }else if("me".equals(tld)){
        	server = "whois.nic.me";
        }else if("hk".equals(tld)){
        	server = "whois.hkirc.hk";
        }
        return queryComWhoisServer(domain, server);  
    }  
      
    public String queryComWhoisServer(String domain, String server) throws Exception {  
        Socket socket = new Socket();  
        SocketAddress  remoteAddr=new InetSocketAddress(server, DEFAULT_PORT);
        socket.connect(remoteAddr, 15*1000);
        socket.setSoTimeout(10 * 1000);  
        String lineSeparator = "\r\n";  
        PrintWriter out = new PrintWriter(socket.getOutputStream());  
        out.println(domain);  
        out.flush();  
  
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));  
        StringBuilder ret = new StringBuilder();  
        String line;  
        String whoisServer=null;
        while ((line = in.readLine()) != null) {
        	if(line.contains("Whois Server:")){
        		whoisServer=line.substring(line.indexOf(":")+1).trim();
        		break;
        	}
        	//ret.append(line + lineSeparator);  
        	
        }  
        socket.close();  
        if(whoisServer!=null){
        	
            Socket socket2 = new Socket();  
            SocketAddress  remoteAddr2=new InetSocketAddress(whoisServer, DEFAULT_PORT);
            socket2.connect(remoteAddr2, 15*1000);
        	socket2.setSoTimeout(10 * 1000);  
        	out = new PrintWriter(socket2.getOutputStream());  
        	out.println(domain);  
        	out.flush(); 
        	BufferedReader in2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));  
        	while((line=in2.readLine())!=null){
        		ret.append(line + lineSeparator);  
        	}
        	out.close();
        	socket2.close();
        }
        return ret.toString();  
    }  
    
    public String queryWhoisServer(String domain,String server) throws UnknownHostException, IOException {
    	String lineSeparator = "\r\n";  
    	Socket socket = new Socket(server, DEFAULT_PORT);  
    	PrintWriter out = new PrintWriter(socket.getOutputStream());  
    	out = new PrintWriter(socket.getOutputStream());  
    	out.println(domain);  
    	out.flush(); 
    	String line;
    	StringBuffer ret = new StringBuffer();
    	BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));  
    	while((line=in.readLine())!=null){
    		ret.append(line + lineSeparator);  
    	}
    	out.close();
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
       // for(int i=0;i<100;i++){
        	
        System.out.println(w.query("mia.com"));  
      //  }
        System.out.println("用时: "+(System.currentTimeMillis()-start));

    }  
      
}  