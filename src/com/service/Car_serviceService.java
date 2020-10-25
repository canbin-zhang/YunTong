package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pojo.Car_service;
import com.pojo.Car_use;


public interface Car_serviceService {
	
	public List<Car_service> queryAllCar_service(int pageNum,int pageSize);
	public int addCar_service(Car_service car_service);
	public Car_service queryCar_serviceById(int id);
	public int deleteCar_service(int id);
	public List<Car_service> searchCar_service(int pageNum,int pageSize,String property,String keyword);

}
