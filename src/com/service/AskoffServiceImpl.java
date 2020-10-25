package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AskoffDao;
import com.github.pagehelper.PageHelper;
import com.pojo.Askoff;

@Service
public class AskoffServiceImpl implements AskoffService{
	
	@Autowired
	private AskoffDao askoffDao;

	@Override
	public int addAskoff(Askoff askoff) {
		askoff.setPass(0);
		return askoffDao.addAskoff(askoff);
	}

	@Override
	public List<Askoff> askoffListByEid(String eid, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return askoffDao.askoffListByEid(eid);
	}

	@Override
	public Askoff queryaskoffById(int id) {
		return askoffDao.queryaskoffById(id);
	}

	@Override
	public String operatorNameByEid(String eid) {
		// TODO Auto-generated method stub
		return askoffDao.operatorNameByEid(eid);
	}

	@Override
	public List<Askoff> askoffList(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return askoffDao.askoffList();
	}

	@Override
	public int askoffPass(int id, String operator_id) {
		
		return askoffDao.askoffPass(id, operator_id);
	}

	@Override
	public List<Askoff> toAskoffStatus(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return askoffDao.toAskoffStatus();
	}

	@Override
	public List<Askoff> toAtPostStatus(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return askoffDao.toAtPostStatus();
	}

	@Override
	public int deleteAskoff(int id) {
		return askoffDao.deleteAskoff(id);
	}

	@Override
	public List<Askoff> searchAskoff(int pageNum, int pageSize, String property, String keyword) {
		PageHelper.startPage(pageNum, pageSize);
		return askoffDao.searchAskoff(property, keyword);
	}

}
