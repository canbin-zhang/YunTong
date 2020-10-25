package com.pojo;

import java.util.List;

public class Post {
	private int pid;//岗位id
	private String pname;//岗位名称
	private int salary;//月薪
	private List<Employee> employeelist; //员工
	
	public List<Employee> getEmployeelist() {
		return employeelist;
	}
	public void setEmployeelist(List<Employee> employeelist) {
		this.employeelist = employeelist;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	/**
	 * @param pid
	 * @param pname
	 * @param salary
	 * @param employeelist
	 */
	public Post(int pid, String pname, int salary, List<Employee> employeelist) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.salary = salary;
		this.employeelist = employeelist;
	}
	/**
	 * 
	 */
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
