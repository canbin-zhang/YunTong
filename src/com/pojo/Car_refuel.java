package com.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Car_refuel {//车辆加油记录表
	private int id;//唯一标识
	private String cid;//车牌号
	private String operator_id;//车管员
	private double refuel_money;//花费金额
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date refuel_date;//加油日期

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getOperator_id() {
		return operator_id;
	}

	public void setOperator_id(String operator_id) {
		this.operator_id = operator_id;
	}

	public double getRefuel_money() {
		return refuel_money;
	}

	public void setRefuel_money(double refuel_money) {
		this.refuel_money = refuel_money;
	}

	public Date getRefuel_date() {
		return refuel_date;
	}

	public void setRefuel_date(Date refuel_date) {
		this.refuel_date = refuel_date;
	}

	/**
	 * 
	 */
	public Car_refuel() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param cid
	 * @param operator_id
	 * @param refuel_money
	 * @param refuel_date
	 */
	public Car_refuel(int id, String cid, String operator_id, double refuel_money, Date refuel_date) {
		super();
		this.id = id;
		this.cid = cid;
		this.operator_id = operator_id;
		this.refuel_money = refuel_money;
		this.refuel_date = refuel_date;
	}
	
}
