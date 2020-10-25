package com.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Car_use {

	private int id;//唯一标识
	private String cid;//车牌号
	private String eid;//教练ID
	private String operator_id;//车管员id；
	 
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date cu_date;//车辆使用日期

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

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	public String getOperator_id() {
		return operator_id;
	}

	public void setOperator_id(String operator_id) {
		this.operator_id = operator_id;
	}

	public Date getCu_date() {
		return cu_date;
	}

	public void setCu_date(Date cu_date) {
		this.cu_date = cu_date;
	}

	/**
	 * @param id
	 * @param cid
	 * @param eid
	 * @param operator_id
	 * @param cu_date
	 */
	public Car_use(int id, String cid, String eid, String operator_id, Date cu_date) {
		super();
		this.id = id;
		this.cid = cid;
		this.eid = eid;
		this.operator_id = operator_id;
		this.cu_date = cu_date;
	}

	/**
	 * 
	 */
	public Car_use() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
