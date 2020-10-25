package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pojo.Income;
import com.pojo.Student;

public interface IncomeService {
	
	public List<Income> queryAllIncome(int pageNum,int pageSize);
	public int addIncome(Income income);
	public Income queryIncomeById(int id);
	public int deleteIncome(int id);
	public List<Income> searchIncome(int pageNum,int pageSize,String property,String keyword);
}
