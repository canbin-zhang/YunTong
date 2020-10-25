package com.service;
import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pojo.Dimission;


public interface DimissionService {
	
	public Dimission queryDimissionByEid(String eid) throws ParseException;
	public List<Dimission> queryAllDimission(int pageNum,int pageSize);
	public List<Dimission> dimissionSearch(int pageNum,int pageSize,String property,String keyword);
}
