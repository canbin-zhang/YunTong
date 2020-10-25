package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.pojo.Salary;

@Repository
@Mapper
public interface SalaryDao {
	public List<Salary> queryAllSalary();
	public int addSalary(Salary salary);
	public Salary querySalaryById(int id);
	public int deleteSalaryById(int id);
	public List<Salary> querySalaryByEid(String eid);
	public List<Salary> salarySearch(@Param("start_date")String start_date,@Param("end_date")String end_date);
	
}
