package com.example.will.common.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Email封裝類
 * 創建者 科幫網
 * 創建時間 2017年7月20日
 *
 */
public class Email {
//	private static final long serialVersionUID = 1L;
	//必填參數
	private String email;//接收方郵件
	private String subject;//主題
	private String content;//郵件內容
	//選填
	private String template;//模板
	private HashMap<String, String> kvMap;// 自定義參數
	
	
	
	public Email() {
		super();
	}
	
	public Email(String email, String subject, String content, String template,
			HashMap<String, String> kvMap) {
		super();
		this.email = email;
		this.subject = subject;
		this.content = content;
		this.template = template;
		this.kvMap = kvMap;
	}


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public HashMap<String, String> getKvMap() {
		return kvMap;
	}
	public void setKvMap(HashMap<String, String> kvMap) {
		this.kvMap = kvMap;
	}
}
