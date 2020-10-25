package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.Car_useDao;
import com.github.pagehelper.PageHelper;
import com.pojo.Car_use;

@Service
public class Car_useServiceImpl implements Car_useService{

	@Autowired
	private  Car_useDao car_useDao;

	@Override
	public int addCar_use(Car_use car_use) {
		
		return car_useDao.addCar_use(car_use);
	}

	@Override
	public Car_use queryCar_useByCidCu_date(String cid, String cu_date) {
		// TODO Auto-generated method stub
		return car_useDao.queryCar_useByCidCu_date(cid, cu_date);
	}

	@Override
	public List<Car_use> queryAllCar_use(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		return car_useDao.queryAllCar_use();
	}

	@Override
	public Car_use queryCar_useById(int id) {
		
		return car_useDao.queryCar_useById(id);
	}

	@Override
	public int deleteCar_use(int id) {
		// TODO Auto-generated method stub
		return car_useDao.deleteCar_use(id);
	}

	@Override
	public List<Car_use> searchCar_use(int pageNum, int pageSize, String property, String keyword) {
		PageHelper.startPage(pageNum, pageSize);
		return car_useDao.searchCar_use(property,keyword);
	}
	
}
