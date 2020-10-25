package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.IncomeDao;
import com.github.pagehelper.PageHelper;
import com.pojo.Income;

@Service
public class IncomeServiceImpl implements IncomeService{

	@Autowired
	private IncomeDao incomeDao;
	
	@Override
	public List<Income> queryAllIncome(int pageNum,int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		return incomeDao.queryAllIncome();
	}

	@Transactional
	@Override
	public int addIncome(Income income) {
		// TODO Auto-generated method stub
		return incomeDao.addIncome(income);
	}

	@Override
	public Income queryIncomeById(int id) {
		// TODO Auto-generated method stub
		return incomeDao.queryIncomeById(id);
	}

	@Override
	public int deleteIncome(int id) {
		// TODO Auto-generated method stub
		return incomeDao.deleteIncome(id);
	}

	@Override
	public List<Income> searchIncome(int pageNum, int pageSize, String property, String keyword) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		return incomeDao.incomeSearch(property, keyword);
	}

}
