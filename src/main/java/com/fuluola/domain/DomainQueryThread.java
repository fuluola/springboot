/**
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: bubugao yunhou</p>
 */
package com.fuluola.domain;

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
			System.out.println(who.query(domain));
			System.out.println(">>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
