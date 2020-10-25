package com.service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.dao.DimissionDao;
import com.pojo.Dimission;

@Service
public class DimissionServiceImpl implements DimissionService{

    @Autowired
    private DimissionDao dimissionDao;
    
	@Override
	public Dimission queryDimissionByEid(String eid) throws ParseException {
		return dimissionDao.queryDimissionByEid(eid);
	}

	@Override
	public List<Dimission> queryAllDimission(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return dimissionDao.queryAllDimission();
	}

	@Override
	public List<Dimission> dimissionSearch(int pageNum, int pageSize, String property, String keyword) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		if(property.equals("ename")) {
			return dimissionDao.dimissionSearchByName(property, keyword);
		}
		return dimissionDao.dimissionSearch(property, keyword);
	}
	}


	
