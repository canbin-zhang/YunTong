package com.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.CarDao;
import com.dao.EmployeeDao;
import com.github.pagehelper.PageHelper;
import com.pojo.Car;

@Service
public class CarServiceImpl implements CarService{

	@Autowired
	private CarDao carDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	public List<Car> CarList(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Car> carList=carDao.queryAllCar();
		for (Car car : carList) {
			car.setEid(employeeDao.queryEnameByEid(car.getEid()));
		}
		return carList;
	}

	@Transactional
	@Override
	public int addCar(Car car){
		
		return carDao.addCar(car);
	}

	@Override
	public Car queryCarByCid(String cid) {
		Car car=carDao.queryCarByCid(cid);
		car.setEid(employeeDao.queryEnameByEid(car.getEid()));
		return car;
	}

	@Transactional
	@Override
	public List<Car> searchCar(int pageNum, int pageSize, String property, String keyword) {
		PageHelper.startPage(pageNum, pageSize);
		List<Car> carList=carDao.searchCar(property, keyword);
		for (Car car : carList) {
			car.setEid(employeeDao.queryEnameByEid(car.getEid()));
		}
		return carList;
	}

	@Transactional
	@Override
	public int deleteCar(String cid) {
		
		return carDao.deleteCar(cid);
	}

	@Transactional
	@Override
	public int updateCar(Car car) {
		// TODO Auto-generated method stub
		return carDao.updateCar(car);
	}

	@Override
	public int queryCountCarByCid(String cid) {
		// TODO Auto-generated method stub
		return carDao.queryCountCarByCid(cid);
	}

}
