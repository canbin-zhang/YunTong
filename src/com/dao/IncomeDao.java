package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.pojo.Income;
import com.pojo.Salary;

@Repository
@Mapper
public interface IncomeDao {
	public List<Income> queryAllIncome();
	public int addIncome(Income income);
	public Income queryIncomeById(int id);
	public int deleteIncome(int id);
	public List<Income> incomeSearch(@Param("property")String property,@Param("keyword") String keyword);

	
}
