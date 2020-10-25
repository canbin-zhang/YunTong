package com.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pojo.Employeelist;

public interface EmployeelistService {

	public Employeelist queryEmployeelistByEid(String eid) throws ParseException;
	public List<Employeelist> queryAllEmployeelist(int pageNum,int pageSize);
	public List<Employeelist> employeelistSearch(int pageNum,int pageSize,String property,String keyword);
}
