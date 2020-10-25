package com.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Car {
	private String cid;//车牌号
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date cdate;//购入日期
	private double cmoney;//购入金额
	private String eid;//采购人员id
	private String status;//车辆状态
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public double getCmoney() {
		return cmoney;
	}
	public void setCmoney(double cmoney) {
		this.cmoney = cmoney;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	
	/**
	 * @param cid
	 * @param cdate
	 * @param cmoney
	 * @param eid
	 * @param status
	 */
	public Car(String cid, Date cdate, double cmoney, String eid, String status) {
		super();
		this.cid = cid;
		this.cdate = cdate;
		this.cmoney = cmoney;
		this.eid = eid;
		this.status = status;
	}
	/**
	 * 
	 */
	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
