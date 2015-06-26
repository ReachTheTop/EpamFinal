package com.epam.project.db.dao;

import java.util.List;

import com.epam.project.db.model.Article;

public class ArticleCorteg {

	private List<Article> articles;
	private Integer amount;
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
