package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pojo.Askoff;
import com.pojo.Car_refuel;



public interface AskoffService {
	public int addAskoff(Askoff askoff);
	public List<Askoff> askoffListByEid(String eid,int pageNum,int pageSize);
	public Askoff queryaskoffById(int id);
	public String operatorNameByEid(String eid);
	public List<Askoff> askoffList(int pageNum,int pageSize);
	public int askoffPass(int id,String operator_id);
	public List<Askoff> toAskoffStatus(int pageNum,int pageSize);
	public List<Askoff> toAtPostStatus(int pageNum,int pageSize);
	public int deleteAskoff(int id);
	public List<Askoff> searchAskoff(int pageNum,int pageSize,String property,String keyword);

}
