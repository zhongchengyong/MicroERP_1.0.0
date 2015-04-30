package com.andbase.demo.model;

import com.ab.model.AbResult;

import java.util.List;

/**
 * 
 *
 */
public class ArticleListResult extends AbResult {

	private List<Article> items;

	public List<Article> getItems() {
		return items;
	}

	public void setItems(List<Article> items) {
		this.items = items;
	}
	
	

}
