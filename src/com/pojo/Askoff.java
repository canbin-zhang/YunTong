package com.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Askoff {  //请假记录表
	private int id;//请假记录表的自增id，作为唯一标识	
	private String operator_id;   //操作员id
	private String eid;//员工id
	
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date start_date;//开始日期
	 
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date end_date;//结束日期
	 
	private String reason;//请假原因
	
	private int pass;//是否批准
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
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getPass() {
		return pass;
	}
	public void setPass(int pass) {
		this.pass = pass;
	}
	
	/**
	 * @param id
	 * @param operator_id
	 * @param eid
	 * @param start_date
	 * @param end_date
	 * @param reason
	 * @param pass
	 */
	public Askoff(int id, String operator_id, String eid, Date start_date, Date end_date, String reason, int pass) {
		super();
		this.id = id;
		this.operator_id = operator_id;
		this.eid = eid;
		this.start_date = start_date;
		this.end_date = end_date;
		this.reason = reason;
		this.pass = pass;
	}
	/**
	 * 
	 */
	public Askoff() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
