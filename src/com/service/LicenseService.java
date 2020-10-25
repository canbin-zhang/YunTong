package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pojo.License;

public interface LicenseService {
    public List<License> queryAllLicense();
    public List<License> queryAllLicense(int pageNum,int pageSize);
    public License queryLicenseByLid(String lid);
    public int addLicense(License license);
    public int updateLicense(License license);
}
