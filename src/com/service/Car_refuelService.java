package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pojo.Car_refuel;
import com.pojo.Car_service;

public interface Car_refuelService{

	public List<Car_refuel> queryAllCar_refuel(int pageNum,int pageSize);
	public int addCar_refuel(Car_refuel car_refuel);
	public Car_refuel queryCar_refuelById(int id);
	public int deleteCar_refuel(int id);
	public List<Car_refuel> searchCar_refuel(int pageNum,int pageSize,String property,String keyword);

}
