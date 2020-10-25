package com.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.pojo.Exam;
import com.pojo.Exam;

public interface ExamService {
	public Exam queryExamById(String Id);
	public boolean updateExam(Exam exam);
	public Exam addExam(Exam exam);
	public List<Exam> queryAllExam(int pageNum,int pageSize);
    public List<Exam> queryExamByEid(int pageNum, int pageSize,String eid);
}
