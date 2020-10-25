package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.pojo.Car;
import com.pojo.Car_use;

@Repository
@Mapper
public interface Car_useDao {
	public int addCar_use(Car_use car_use);
	public List<Car_use> queryAllCar_use();
	public Car_use queryCar_useByCidCu_date(@Param("cid")String cid,@Param("cu_date")String cu_date);
	public Car_use queryCar_useById(int id);
	public int deleteCar_use(int id);
	public List<Car_use> searchCar_use(@Param("property")String property,@Param("keyword") String keyword);//根据property属性名的关键字keyword模糊查询)
}
