package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.Car_refuelDao;
import com.github.pagehelper.PageHelper;
import com.pojo.Car_refuel;

@Service
public class Car_refuelServiceImpl implements Car_refuelService{
	
	@Autowired
	private Car_refuelDao car_refuelDao;

	@Override
	public List<Car_refuel> queryAllCar_refuel(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return car_refuelDao.queryAllCar_refuel();
	}

	@Override
	public int addCar_refuel(Car_refuel car_refuel) {
		
		return car_refuelDao.addCar_refuel(car_refuel);
	}

	@Override
	public Car_refuel queryCar_refuelById(int id) {
		
		return car_refuelDao.queryCar_refuelById(id);
	}

	@Override
	public int deleteCar_refuel(int id) {
	
		return car_refuelDao.deleteCar_refuel(id);
	}

	@Override
	public List<Car_refuel> searchCar_refuel(int pageNum, int pageSize, String property, String keyword) {
		PageHelper.startPage(pageNum, pageSize);
		return car_refuelDao.searchCar_refuel(property, keyword);
	}
	
}
