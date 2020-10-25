package com.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Salary {
	private int id; //唯一标识
	private String eid;//员工id
	private String operator_id;//操作员ID
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date grant_date;//发放日期
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date salary_month;//工资月份
	
	private double  salary_money ;//工资金额
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOperator_id() {
		return operator_id;
	}
	public void setOperator_id(String operator_id) {
		this.operator_id = operator_id;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public Date getGrant_date() {
		return grant_date;
	}
	public void setGrant_date(Date grant_date) {
		this.grant_date = grant_date;
	}
	public Date getSalary_month() {
		return salary_month;
	}
	public void setSalary_month(Date salary_month) {
		this.salary_month = salary_month;
	}
	public double getSalary_money() {
		return salary_money;
	}
	public void setSalary_money(double salary_money) {
		this.salary_money = salary_money;
	}
	
	/**
	 * @param id
	 * @param eid
	 * @param operator_id
	 * @param grant_date
	 * @param salary_month
	 * @param salary_money
	 */
	public Salary(int id, String eid, String operator_id, Date grant_date, Date salary_month, double salary_money) {
		super();
		this.id = id;
		this.eid = eid;
		this.operator_id = operator_id;
		this.grant_date = grant_date;
		this.salary_month = salary_month;
		this.salary_money = salary_money;
	}
	/**
	 * 
	 */
	public Salary() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
