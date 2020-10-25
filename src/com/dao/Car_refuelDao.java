package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.pojo.Car_refuel;
import com.pojo.Car_service;


@Repository
@Mapper

public interface Car_refuelDao {
	public List<Car_refuel> queryAllCar_refuel();
	public int addCar_refuel(Car_refuel car_refuel);
	public Car_refuel queryCar_refuelById(int id);
	public int deleteCar_refuel(int id);
	public List<Car_refuel> searchCar_refuel(@Param("property")String property,@Param("keyword") String keyword);//根据property属性名的关键字keyword模糊查询)

}
