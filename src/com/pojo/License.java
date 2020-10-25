package com.pojo;

import java.util.List;

public class License {
	private String lid; //驾照类型id
	private String lname;//驾照类型名字
	private double lmoney;//学费
	private List<Student> studentlist; //学员
	public String getLid() {
		return lid;
	}
	public void setLid(String lid) {
		this.lid = lid;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public double getLmoney() {
		return lmoney;
	}
	public void setLmoney(double lmoney) {
		this.lmoney = lmoney;
	}

	public License(String lid, String lname, double lmoney, List<Student> studentlist) {
		super();
		this.lid = lid;
		this.lname = lname;
		this.lmoney = lmoney;
		this.studentlist = studentlist;
	}
	public License() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public List<Student> getStudentlist() {
		return studentlist;
	}
	public void setStudentlist(List<Student> studentlist) {
		this.studentlist = studentlist;
	}

	
}
