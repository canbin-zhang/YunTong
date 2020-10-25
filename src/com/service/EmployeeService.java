package com.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.pojo.Employee;
import com.pojo.Post;

public interface EmployeeService {
	public Employee addEmployee(Employee employee, Date em_date,HttpServletRequest request);
	public boolean deleteEmployee(String eid,Date dm_date,String reason);
	public String sendEmail(String email);
	public int resetPassword(String email,String password);
	public int resetPasswordByEid(String eid,String password);
	public int resetPasswordByEmail(String email,String password);
	public Employee queryEmployeeByEid(String eid);
	public Post queryPostByEid(String eid);
	public List<Employee> queryAllEmployee(int pageNum,int pageSize);
	public boolean updateEmployee(Employee employee,HttpServletRequest request);
	public List<Employee> searchEmployee(int pageNum,int pageSize,String property,String keyword);
	public List<Employee> queryEmployeeByPid(String pid,int pageNum,int pageSize);
	public List<Employee>queryEmployeeByPostName(String name);
}
