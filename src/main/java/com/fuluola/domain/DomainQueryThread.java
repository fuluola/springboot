/**
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: bubugao yunhou</p>
 */
package com.fuluola.domain;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;

/**
 * @author fuluola
 *
 */
public class DomainQueryThread implements Runnable{

	String domain;
	public DomainQueryThread(String domain){
		this.domain = domain;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		
		Whois who = new Whois();
		try {
			PrintStream pr=null;
			String outstr = who.query(domain);
			if("".equals(outstr)){
				FileWriter output = new FileWriter("E:\\软件是用来赚钱的\\域名采集\\失败.txt",true);
				output.append(domain+"\n");
				output.flush();
				output.close();
			}else{
				
				File output = new File("E://软件是用来赚钱的//域名采集//output//"+domain+".txt");
//				if(!output.exists()){
//					output.mkdirs();
//				}
				pr = new PrintStream(new FileOutputStream(output));
				pr.print(outstr);
				pr.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
