package com.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Exam {
	private int id;//自增id
	private String sid;//学员id
	private String exam_lid;//考试驾照类型
	private String exam_subject;//考试科目
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date exam_date;    //考试日期
	private int pass;//是否通过
	public int getid() {
		return id;
	}
	public String getexam_lid() {
		return exam_lid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public void setExam_lid(String exam_lid) {
		this.exam_lid = exam_lid;
	}
	public String getExam_subject() {
		return exam_subject;
	}
	public void setExam_subject(String exam_subject) {
		this.exam_subject = exam_subject;
	}
	public Date getExam_date() {
		return exam_date;
	}
	public void setExam_date(Date exam_date) {
		this.exam_date = exam_date;
	}
	public int getPass() {
		return pass;
	}
	public void setPass(int pass) {
		this.pass = pass;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Exam() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public Exam(String sid, String exam_lid,String exam_subject, Date exam_date, int pass) {
		super();
		this.id = id;
		this.sid = sid;
		this.exam_lid = exam_lid;
		this.exam_subject = exam_subject;
		this.exam_date = exam_date;
		this.pass = pass;
	}
	@Override
	public String toString() {
		return "Exam [id=" + id + ", sid=" + sid + ", exam_lid=" + exam_lid + ", exam_subject=" + exam_subject
				+ ", exam_date=" + exam_date + ", pass=" + pass + "]";
	}
	
}
