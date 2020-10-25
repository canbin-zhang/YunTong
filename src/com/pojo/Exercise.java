package com.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Exercise {
	private int id;//自增id
	private String sid;//学员id
	private String exer_lid;//练习驾照
	private String exer_subject;//练习科目
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date exer_date;    //练习日期
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getExer_lid() {
		return exer_lid;
	}
	public void setExer_lid(String exer_lid) {
		this.exer_lid = exer_lid;
	}
	public String getExer_subject() {
		return exer_subject;
	}
	public void setExer_subject(String exer_subject) {
		this.exer_subject = exer_subject;
	}
	public Date getExer_date() {
		return exer_date;
	}
	public void setExer_date(Date exer_date) {
		this.exer_date = exer_date;
	}
	public Exercise(int id, String sid, String exer_lid, String exer_subject, Date exer_date) {
		super();
		this.id = id;
		this.sid = sid;
		this.exer_lid = exer_lid;
		this.exer_subject = exer_subject;
		this.exer_date = exer_date;
	}
	public Exercise() {
		super();
		// TODO 自动生成的构造函数存根
	}
	
}
