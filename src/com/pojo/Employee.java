package com.pojo;



import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

public class Employee {
	
	@Length(min=10,max=10,message = "身份证位数不对")
	private String eid; //员工身份证

	@Length(min=10,max=10,message = "密码长度不是10位")
	private String epassword;//账号密码
	
	private String ename;//员工姓名
	
	private String esex;//员工性别
	
	@Length(min=11,max=11,message = "手机号码不是11位")
	private String ephone;//员工手机
	
	private String email;//员工邮箱
	
	@Min(value = 0,message = "请选择岗位")
	private int pid;//岗位编号
	
	private String status;//员工状态
	
	private MultipartFile ephotofile;//照片文件
	
	private String ephoto;//员工照片路径
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * 
	 */
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param eid
	 * @param epassword
	 * @param ename
	 * @param esex
	 * @param ephone
	 * @param ephotofile
	 * @param ephoto
	 * @param pid
	 */
	
	public String getEid() {
		return eid;
	}
	
	/**
	 * @param eid
	 * @param epassword
	 * @param ename
	 * @param esex
	 * @param ephone
	 * @param email
	 * @param status
	 * @param ephotofile
	 * @param ephoto
	 * @param pid
	 */
	public Employee(String eid, String epassword, String ename, String esex, String ephone, String email, String status,
			MultipartFile ephotofile, String ephoto, int pid) {
		super();
		this.eid = eid;
		this.epassword = epassword;
		this.ename = ename;
		this.esex = esex;
		this.ephone = ephone;
		this.email = email;
		this.status = status;
		this.ephotofile = ephotofile;
		this.ephoto = ephoto;
		this.pid = pid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getEpassword() {
		return epassword;
	}
	public void setEpassword(String epassword) {
		this.epassword = epassword;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getEsex() {
		return esex;
	}
	public void setEsex(String esex) {
		this.esex = esex;
	}
	public String getEphone() {
		return ephone;
	}
	public void setEphone(String ephone) {
		this.ephone = ephone;
	}
	public MultipartFile getEphotofile() {
		return ephotofile;
	}
	public void setEphotofile(MultipartFile ephotofile) {
		this.ephotofile = ephotofile;
	}
	public String getEphoto() {
		return ephoto;
	}
	public void setEphoto(String ephoto) {
		this.ephoto = ephoto;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	

}
