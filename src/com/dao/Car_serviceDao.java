package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.pojo.Car_service;
import com.pojo.Car_use;

@Repository
@Mapper
public interface Car_serviceDao {
	public List<Car_service> queryAllCar_service();
	public int addCar_service(Car_service car_service);
	public Car_service queryCar_serviceById(int id);
	public int deleteCar_service(int id);
	public List<Car_service> searchCar_service(@Param("property")String property,@Param("keyword") String keyword);//根据property属性名的关键字keyword模糊查询)

}
