package com.pojo;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
public class Studentlist {//招生记录表
	private String sid;//学员id
	private String eid;//所属教练id
	private String operator_id;  //操作员id
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date sm_date;    //入学时间
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
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
	public Date getSm_date() {
		return sm_date;
	}
	public void setSm_date(Date sm_date) {
		this.sm_date = sm_date;
	}
	public Studentlist(String sid, String eid, String operator_id, Date sm_date) {
		super();
		this.sid = sid;
		this.eid = eid;
		this.operator_id = operator_id;
		this.sm_date = sm_date;
	}
	public Studentlist() {
		super();
		// TODO 自动生成的构造函数存根
	}
    
}
