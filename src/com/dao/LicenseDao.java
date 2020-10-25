package com.dao;

import com.pojo.Student;
import com.pojo.License;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface LicenseDao {
    public List<License> queryAllLicense();
    public License queryLicenseByLid(String lid);
    public int addLicense(License license);
    public int updateLicense(License license);
}