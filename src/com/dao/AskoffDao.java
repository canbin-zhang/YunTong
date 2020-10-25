package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.pojo.Askoff;
import com.pojo.Car_service;

@Repository
@Mapper
public interface AskoffDao {
	
	public int addAskoff(Askoff askoff);
	public List<Askoff> askoffListByEid(@Param("eid")String eid);
	public Askoff queryaskoffById(@Param("id")Integer id);
	public String operatorNameByEid(@Param("eid")String eid);
	public List<Askoff> askoffList();
	public int askoffPass(@Param("id")int id,@Param("operator_id")String operator_id);
	public List<Askoff> toAskoffStatus();//需要调整为请假状态的员工列表
	public List<Askoff> toAtPostStatus();//需要调整为请假状态的员工列表
	public int deleteAskoff(int id);//撤销请假申请
	public List<Askoff> searchAskoff(@Param("property")String property,@Param("keyword") String keyword);//根据property属性名的关键字keyword模糊查询)

}
