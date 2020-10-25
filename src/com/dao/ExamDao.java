package com.dao;

import java.util.List;

import com.pojo.Exam;
import com.pojo.Student;
import com.pojo.Studentlist;

public interface ExamDao {
	public int addExam(Exam exam);
	public int updateExam(Exam exam);
	public List<Exam> queryExamByEid(String eid);
	public List<Exam> queryAllExam();
	public Exam queryExamById(String id);
}
