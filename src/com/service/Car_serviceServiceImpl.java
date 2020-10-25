package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.Car_serviceDao;
import com.github.pagehelper.PageHelper;
import com.pojo.Car_service;

@Service
public class Car_serviceServiceImpl implements Car_serviceService{
	
	@Autowired
	private Car_serviceDao car_serviceDao;

	@Override
	public List<Car_service> queryAllCar_service(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return car_serviceDao.queryAllCar_service();
	}

	@Override
	public int addCar_service(Car_service car_service) {
		return car_serviceDao.addCar_service(car_service);
	}

	@Override
	public Car_service queryCar_serviceById(int id) {
		// TODO Auto-generated method stub
		return car_serviceDao.queryCar_serviceById(id);
	}

	@Override
	public int deleteCar_service(int id) {
		// TODO Auto-generated method stub
		return car_serviceDao.deleteCar_service(id);
	}

	@Override
	public List<Car_service> searchCar_service(int pageNum, int pageSize, String property, String keyword) {
		PageHelper.startPage(pageNum, pageSize);
		return car_serviceDao.searchCar_service(property, keyword);
	}

}
