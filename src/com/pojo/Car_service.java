package com.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Car_service {//车辆检修记录表
	
	private int id;//唯一标识
	private String cid;//车牌号
	private String operator_id;//车管员id
	 
   @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date start_date;//开始日期
   
   @DateTimeFormat(pattern = "yyyy-MM-dd")
   private Date end_date;//结束日期

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

/**
 * @param id
 * @param cid
 * @param operator_id
 * @param start_date
 * @param end_date
 */
public Car_service(int id, String cid, String operator_id, Date start_date, Date end_date) {
	super();
	this.id = id;
	this.cid = cid;
	this.operator_id = operator_id;
	this.start_date = start_date;
	this.end_date = end_date;
}

/**
 * 
 */
public Car_service() {
	super();
	// TODO Auto-generated constructor stub
}
	

}
