package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.DisburseDao;
import com.dao.EmployeeDao;
import com.dao.SalaryDao;
import com.github.pagehelper.PageHelper;
import com.pojo.Disburse;
import com.pojo.Salary;

@Service
public class SalaryServiceImpl implements SalaryService{
	
	@Autowired
	private SalaryDao salaryDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private DisburseDao disburseDao;

	@Override
	public List<Salary> queryAllSalary(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return salaryDao.queryAllSalary();
	}
	
	
	@Transactional
	@Override
	public boolean addSalary(Salary salary) {
		Disburse disburse=new Disburse(salary.getOperator_id(),salary.getEid(),salary.getGrant_date(),salary.getSalary_money(),"工资");
		if(disburseDao.addDisburse(disburse)==1&&salaryDao.addSalary(salary)==1) {
			return true;
		}
		return false;
		
	}

	@Override
	public Salary querySalaryById(int id) {
		// TODO Auto-generated method stub
		return salaryDao.querySalaryById(id);
	}

	@Override
	public int deleteSalaryById(int id) {
		// TODO Auto-generated method stub
		return salaryDao.deleteSalaryById(id);
	}

	@Override
	public List<Salary> querySalaryByEid(String eid, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Salary> salarylist=salaryDao.querySalaryByEid(eid);
		
		for (Salary salary : salarylist) {
			salary.setOperator_id(employeeDao.queryEnameByEid(salary.getOperator_id()));
		}
		
		return salarylist;
	}


	@Override
	public List<Salary> salarySearch(String start_date, String end_date, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return salaryDao.salarySearch(start_date, end_date);
	}

	
	
}
