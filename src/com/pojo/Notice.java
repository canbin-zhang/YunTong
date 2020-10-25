package com.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class Notice {
	
	private int id;//唯一标识
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;//创建时间
	
	private String detail;//描叙
	
	private String operator_id;//操作人员id
	
	private MultipartFile nphotofile;//图片文件
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getOperator_id() {
		return operator_id;
	}
	public void setOperator_id(String operator_id) {
		this.operator_id = operator_id;
	}
	public MultipartFile getNphotofile() {
		return nphotofile;
	}
	public void setNphotofile(MultipartFile nphotofile) {
		this.nphotofile = nphotofile;
	}
	public String getNphoto() {
		return nphoto;
	}
	public void setNphoto(String nphoto) {
		this.nphoto = nphoto;
	}
	private String nphoto;//图片路径
	/**
	 * @param id
	 * @param date
	 * @param detail
	 * @param operator_id
	 * @param nphotofile
	 * @param nphoto
	 */
	public Notice(int id, Date date, String detail, String operator_id, MultipartFile nphotofile, String nphoto) {
		super();
		this.id = id;
		this.date = date;
		this.detail = detail;
		this.operator_id = operator_id;
		this.nphotofile = nphotofile;
		this.nphoto = nphoto;
	}
	/**
	 * 
	 */
	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
