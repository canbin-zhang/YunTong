package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pojo.Car;
import com.pojo.Car_use;


public interface Car_useService {
	public int addCar_use(Car_use car_use);
	public Car_use queryCar_useByCidCu_date(String cid,String cu_date);
	public List<Car_use> queryAllCar_use(int pageNum,int pageSize);
	public Car_use queryCar_useById(int id);
	public int deleteCar_use(int id);
	public List<Car_use> searchCar_use(int pageNum,int pageSize,String property,String keyword);

}
