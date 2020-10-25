package com.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pojo.Student;
import com.pojo.Studentlist;

public interface StudentlistService {
	public Studentlist queryStudentlistBySid(String sid) throws ParseException;
}
