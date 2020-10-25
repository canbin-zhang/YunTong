package com.dao;

import com.pojo.Dimission;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface DimissionDao {
	
	public int addDimission(Dimission dimission);
	public Dimission queryDimissionByEid(String eid);
	public List<Dimission> queryAllDimission();
	public List<Dimission> dimissionSearch(@Param("property")String property,@Param("keyword")String keyword);
	public List<Dimission> dimissionSearchByName(@Param("property")String property,@Param("keyword")String keyword);
	}





    
   