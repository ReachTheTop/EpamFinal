package com.epam.project.db.model;

import java.util.List;

import com.epam.project.db.model.validator.Validator;

public class FaqCategory extends Validator {
	private Integer id;
	private String category;
	private List<Article> faq;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Article> getFaq() {
		return faq;
	}

	public void setFaq(List<Article> faq) {
		this.faq = faq;
	}

}
