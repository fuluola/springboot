package com.fuluola.model;

import java.util.List;

/** 
 * @description 
 * @author  fuzhuan fu.luola@qq.com
 * @date 2017年7月4日 
 */
public class HtmlHead {

	private String title;
	private String keywords;
	private List<String> metas ;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public List<String> getMetas() {
		return metas;
	}
	public void setMetas(List<String> metas) {
		this.metas = metas;
	}
	
	
}
