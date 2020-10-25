package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.pojo.Disburse;
import com.pojo.Income;
import com.pojo.Salary;

@Repository
@Mapper
public interface DisburseDao {
	public List<Disburse> queryAllDisburse();
	public int addDisburse(Disburse disburse);
	public Disburse queryDisburseById(int id);
	public int deleteDisburse(int id);
	public List<Disburse> disburseSearch(@Param("property")String property,@Param("keyword") String keyword);

}
