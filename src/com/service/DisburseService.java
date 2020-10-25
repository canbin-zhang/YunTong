package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pojo.Disburse;
import com.pojo.Income;

public interface DisburseService {
	
	public List<Disburse> queryAllDisburse(int pageNum,int pageSize);
	public int addDisburse(Disburse disburse);
	public Disburse queryDisburseById(int id);
	public int deleteDisburse(int id);
	public List<Disburse> searchDisburse(int pageNum,int pageSize,String property,String keyword);

}
