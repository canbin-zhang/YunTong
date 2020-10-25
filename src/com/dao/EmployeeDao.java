package com.dao;

import java.util.List;
import java.util.Set;

import com.pojo.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.pojo.Employee;

@Repository
@Mapper
public interface EmployeeDao {
	public int addEmployee(Employee employee);
	public Employee queryEmployeeByEid(String eid);
	public Post queryPostByEid(String eid);
	public Employee queryEmployeeByEmail(String email);
	public int resetPassword(@Param("email")String email,@Param("password")String password);
	public List<Employee> queryAllEmployee();
	public int deleteEmployeeByEid(String eid);
	public int updateEmployee(Employee employee);
	public List<Employee> searchEmployee(@Param("property")String property,@Param("keyword") String keyword);//根据property属性名的关键字keyword模糊查询
	public List<Employee> queryEmployeeByPid(String pid);
	public String queryEmployeeStatus(String eid);
	public String queryEnameByEid(String eid);
	public List<Employee>queryEmployeeByPostName(String name);
	public int resetPasswordByEid(@Param("eid")String eid,@Param("password")String password);
}
