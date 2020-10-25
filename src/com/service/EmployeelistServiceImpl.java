package com.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.dao.EmployeelistDao;
import com.github.pagehelper.PageHelper;
import com.pojo.Employeelist;

@Service
public class EmployeelistServiceImpl implements EmployeelistService {

	@Autowired
	private EmployeelistDao employeelistDao;
	
	@Override
	public Employeelist queryEmployeelistByEid(String eid) throws ParseException {	
 		
		return employeelistDao.queryEmployeelistByEid(eid);
	}

	@Override
	public List<Employeelist> queryAllEmployeelist(int pageNum,int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		return employeelistDao.queryAllEmployeelist();
	}

	@Override
	public List<Employeelist> employeelistSearch(int pageNum, int pageSize, String property, String keyword) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		if(property.equals("ename")) {
			return employeelistDao.employeelistSearchByName(property, keyword);
		}
		return employeelistDao.employeelistSearch(property, keyword);
	}
		
}
