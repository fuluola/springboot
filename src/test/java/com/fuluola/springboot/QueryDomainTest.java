/**
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: bubugao yunhou</p>
 */
package com.fuluola.springboot;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.fuluola.domain.DomainQueryThread;

/**
 * @author fuluola
 *
 */
public class QueryDomainTest {
	
	public static void main(String[] args) throws Exception {
		 BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("E:\\域名采集项目\\11.txt"),"UTF-8"));
		 String line = "";
			while ((line = reader.readLine()) != null) {
				line=line.replace("www.", "");
				new Thread(new DomainQueryThread(line)).start();
			}
			reader.close();
	}
}
