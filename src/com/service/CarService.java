package com.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pojo.Car;

public interface CarService {
	public List<Car> CarList(int pageNum,int pageSize);
	public int addCar(Car car);
	public Car queryCarByCid(String cid);
	public List<Car> searchCar(int pageNum,int pageSize,String property,String keyword);
	public int deleteCar(String cid);
	public int updateCar(Car car);
	public int queryCountCarByCid(String cid);
}
