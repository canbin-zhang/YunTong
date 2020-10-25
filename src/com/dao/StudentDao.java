package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.pojo.License;
import com.pojo.Student;
@Repository
@Mapper
public interface StudentDao {
	public int addStudent(Student student);
	public License queryLicenseBySid(String sid);
	public List<Student> queryAllStudent();
	public Student queryStudentBySid(String sid);
	public List<Student> queryStudentByLid(String lid);
	public int updateStudent(Student student);
	public List<Student> searchStudent(@Param("property")String property,@Param("keyword") String keyword);//根据property属性名的关键字keyword模糊查询
    public List<Student> queryStudentlistByEid(String eid);
    public int queryStudentBySphone(String sphone);
    public int updateStudentSubject(@Param("sid")String sid,@Param("exam_subject")String exam_subject);
    public List<Student> searchStudentByEid(@Param("property")String property, @Param("keyword")String keyword, @Param("eid")String eid);
}
