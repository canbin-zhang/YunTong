package com.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Dimission {  //离职记录表

    private String eid;  //员工id
    private String operator_id;  //操作员id

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dm_date;    //入职时间
    private String reason;  //原因
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
	public Date getDm_date() {
		return dm_date;
	}
	public void setDm_date(Date dm_date) {
		this.dm_date = dm_date;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public Dimission() {
		super();
	}
	public Dimission(String eid, String operator_id, Date dm_date, String reason) {
		super();
		this.eid = eid;
		this.operator_id = operator_id;
		this.dm_date = dm_date;
		this.reason = reason;
	}
	@Override
	public String toString() {
		return "Employeelistoff [eid=" + eid + ", operator_id=" + operator_id + ", dm_date=" + dm_date + ", reason="
				+ reason + "]";
	}
    
	
}