package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.DisburseDao;
import com.github.pagehelper.PageHelper;
import com.pojo.Disburse;

@Service
public class DisburseServiceImpl implements DisburseService{

	@Autowired
	private DisburseDao disburseDao;
	
	@Override
	public List<Disburse> queryAllDisburse(int pageNum,int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		return disburseDao.queryAllDisburse();
	}

	@Override
	public int addDisburse(Disburse disburse) {
		// TODO Auto-generated method stub
		return disburseDao.addDisburse(disburse);
	}

	@Override
	public Disburse queryDisburseById(int id) {
		// TODO Auto-generated method stub
		return disburseDao.queryDisburseById(id);
	}

	@Override
	public int deleteDisburse(int id) {
		// TODO Auto-generated method stub
		return disburseDao.deleteDisburse(id);
	}

	@Override
	public List<Disburse> searchDisburse(int pageNum, int pageSize, String property, String keyword) {
		PageHelper.startPage(pageNum, pageSize);
		return disburseDao.disburseSearch(property, keyword);
	}

}
