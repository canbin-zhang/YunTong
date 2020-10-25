package com.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Employeelist {  //招聘记录表

    private String eid;  //员工id
    private String operator_id;  //操作员id

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date em_date;    //入职时间
    
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

    public Date getEm_date() {
        return em_date;
    }

    public void setEm_date(Date em_date) {
        this.em_date = em_date;
    }

    public Employeelist(String eid, String operator_id, Date em_date) {
        this.eid = eid;
        this.operator_id = operator_id;
        this.em_date = em_date;
    }

    public Employeelist() {
    }

}
