package com.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Disburse {
	private int id;//唯一标识
	private String operator_id;//操作员id
	private String eid;// 申请员工
	 
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date disburse_date;//支出日期
	
	private double disburse_money;//支出金额
	private String disburse_detail;//支出明细
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
	public Date getDisburse_date() {
		return disburse_date;
	}
	public void setDisburse_date(Date disburse_date) {
		this.disburse_date = disburse_date;
	}
	public double getDisburse_money() {
		return disburse_money;
	}
	public void setDisburse_money(double disburse_money) {
		this.disburse_money = disburse_money;
	}
	public String getDisburse_detail() {
		return disburse_detail;
	}
	public void setDisburse_detail(String disburse_detail) {
		this.disburse_detail = disburse_detail;
	}
	/**
	 * @param id
	 * @param operator_id
	 * @param eid
	 * @param disburse_date
	 * @param disburse_money
	 * @param disburse_detail
	 */
	public Disburse(int id, String operator_id, String eid, Date disburse_date, double disburse_money,
			String disburse_detail) {
		super();
		this.id = id;
		this.operator_id = operator_id;
		this.eid = eid;
		this.disburse_date = disburse_date;
		this.disburse_money = disburse_money;
		this.disburse_detail = disburse_detail;
	}
	public Disburse( String operator_id, String eid, Date disburse_date, double disburse_money,
			String disburse_detail) {
		super();
		
		this.operator_id = operator_id;
		this.eid = eid;
		this.disburse_date = disburse_date;
		this.disburse_money = disburse_money;
		this.disburse_detail = disburse_detail;
	}
	/**
	 * 
	 */
	public Disburse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
