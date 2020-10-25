package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.pojo.Car;

@Repository
@Mapper
public interface CarDao {
	public List<Car> queryAllCar();
	public int addCar(Car car);
	public Car queryCarByCid(@Param("cid")String cid);
	public int deleteCar(String cid);
	public List<Car> searchCar(@Param("property")String property,@Param("keyword") String keyword);//根据property属性名的关键字keyword模糊查询)
	public int updateCar(Car car);
	public int queryCountCarByCid(@Param("cid")String cid);
}
