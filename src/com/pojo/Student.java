package com.pojo;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

public class Student {
	@Length(min=10,max=10,message = "身份证位数不对")
	private String sid; //学员身份证
	private String sname;//学员姓名
	private String ssex;//学员性别
	@Length(min=11,max=11,message = "手机号码不是11位")
	private String sphone;//学员手机
	private String subject;//目前练习科目
	private MultipartFile sphotofile;//照片文件
	private String sphoto;//学员照片路径
	private String lid;//驾照类型id
	
	public String getLid() {
		return lid;
	}
	public void setLid(String lid) {
		this.lid = lid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSsex() {
		return ssex;
	}
	public void setSsex(String ssex) {
		this.ssex = ssex;
	}
	public String getSphone() {
		return sphone;
	}
	public void setSphone(String sphone) {
		this.sphone = sphone;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public MultipartFile getSphotofile() {
		return sphotofile;
	}
	public void setSphotofile(MultipartFile sphotofile) {
		this.sphotofile = sphotofile;
	}
	public String getSphoto() {
		return sphoto;
	}
	public void setSphoto(String sphoto) {
		this.sphoto = sphoto;
	}
	public Student() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public Student(String sid, String sname, String ssex, String sphone, String subject, MultipartFile sphotofile,
			String sphoto) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.ssex = ssex;
		this.sphone = sphone;
		this.subject = subject;
		this.sphotofile = sphotofile;
		this.sphoto = sphoto;
	}
	
}
