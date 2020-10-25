package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pojo.Salary;

public interface SalaryService {
	
	public List<Salary> queryAllSalary(int pageNum,int pageSize);
	public boolean addSalary(Salary salary);
	public Salary querySalaryById(int id);
	public int deleteSalaryById(int id);
	public List<Salary> querySalaryByEid(String eid,int pageNum,int pageSize);
	public List<Salary> salarySearch(String start_date,String end_date,int pageNum,int pageSize);
}
