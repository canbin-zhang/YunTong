package com.service;

import com.dao.LicenseDao;
import com.dao.PostDao;
import com.github.pagehelper.PageHelper;
import com.pojo.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class LicenseServiceImpl implements LicenseService{
	@Autowired
    private LicenseDao licenseDao;
	
	
	@Override
	public List<License> queryAllLicense() {
		// TODO 自动生成的方法存根
		return licenseDao.queryAllLicense();
	}

	@Override
	public List<License> queryAllLicense(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return licenseDao.queryAllLicense();
	}

	@Override
	public License queryLicenseByLid(String lid) {
		// TODO 自动生成的方法存根
		return licenseDao.queryLicenseByLid(lid);
	}

	@Transactional
	@Override
	public int addLicense(License license) {
		if("".equals(license.getLmoney())||"".equals(license.getLid())||"".equals(license.getLname())) {
			return 0;
		}else {
			return licenseDao.addLicense(license);
		}
	}

	@Override
	public int updateLicense(License license) {
		if("".equals(license.getLmoney())||"".equals(license.getLid())||"".equals(license.getLname())) {
			return 0;
		}
		return licenseDao.updateLicense(license);
	}

}
