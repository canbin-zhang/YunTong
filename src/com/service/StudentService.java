package com.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.pojo.Employee;
import com.pojo.License;
import com.pojo.Student;

public interface StudentService {
	public Student addStudent(Student student,String eid, Date sm_date,HttpServletRequest request);
	public Student queryStudentBySid(String sid);
	public License queryLicenseBySid(String sid);
	public boolean updateStudent(Student student,HttpServletRequest request);
	public List<Student> queryAllStudent(int pageNum,int pageSize);
	public List<Student> queryStudentByLid(String lid,int pageNum,int pageSize);
	public List<Student> searchStudent(int pageNum,int pageSize,String property,String keyword);
    public List<Student> queryStudentlistByEid(int pageNum, int pageSize,String eid);
    public List<Student> searchStudentByEid(int pageNum, int pageSize, String property, String keyword, String eid);
}
