package com.dao;
import com.pojo.Student;
import com.pojo.Studentlist;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface StudentlistDao {
    public int addStudentlist(Studentlist studentlist);
    public Studentlist queryStudentlistBySid(String sid);
}
