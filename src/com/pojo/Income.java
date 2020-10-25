package com.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Income {
	
	private int id;//唯一标识
	private String operator_id;//操作员id
	private String eid;//来源员工id
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date income_date;//收入日期
	private double income_money;//收入金额
	private String income_detail;//收入明细
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
	public Date getIncome_date() {
		return income_date;
	}
	public void setIncome_date(Date income_date) {
		this.income_date = income_date;
	}
	public double getIncome_money() {
		return income_money;
	}
	public void setIncome_money(double income_money) {
		this.income_money = income_money;
	}
	public String getIncome_detail() {
		return income_detail;
	}
	public void setIncome_detail(String income_detail) {
		this.income_detail = income_detail;
	}
	/**
	 * @param id
	 * @param operator_id
	 * @param eid
	 * @param income_date
	 * @param income_money
	 * @param income_detail
	 */
	public Income(int id, String operator_id, String eid, Date income_date, double income_money, String income_detail) {
		super();
		this.id = id;
		this.operator_id = operator_id;
		this.eid = eid;
		this.income_date = income_date;
		this.income_money = income_money;
		this.income_detail = income_detail;
	}
	/**
	 * 
	 */
	public Income() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
