package com.dao;

import com.pojo.Employeelist;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface EmployeelistDao {
    public int addEmploylist(Employeelist employeelist);
    public Employeelist queryEmployeelistByEid(String eid);
    public List<Employeelist> queryAllEmployeelist();
    public List<Employeelist> employeelistSearch(@Param("property")String property,@Param("keyword")String keyword);
    public List<Employeelist> employeelistSearchByName(@Param("property")String property,@Param("keyword")String keyword);
}
