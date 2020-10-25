package com.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.dao.StudentlistDao;
import com.github.pagehelper.PageHelper;
import com.pojo.Student;
import com.pojo.Studentlist;

@Service
public class StudentlistServiceImpl implements StudentlistService {

	@Autowired
	private StudentlistDao studentlistDao;
	
	@Override
	public Studentlist queryStudentlistBySid(String sid) throws ParseException {	
 		
		return studentlistDao.queryStudentlistBySid(sid);
	}



		
}
