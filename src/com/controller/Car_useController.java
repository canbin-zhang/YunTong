package com.controller;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.pagehelper.PageInfo;
import com.pojo.Car;
import com.pojo.Car_use;
import com.pojo.Employee;
import com.service.Car_useService;
import com.service.EmployeeService;


@Controller
@RequestMapping("/Car_use")
@RequiresAuthentication

public class Car_useController {
	
	@Autowired
	private Car_useService car_useService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequiresRoles(value={"车管员","admin"},logical = Logical.OR)
	@RequestMapping("/car_useList")
	public ModelAndView car_useList(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,ModelAndView mv) {
		List<Car_use> car_useList=car_useService.queryAllCar_use(pageNum, pageSize);
		PageInfo pageInfo=new PageInfo(car_useList); //分页
		mv.addObject("pageInfo",pageInfo);
		mv.setViewName("car_useList");
		return mv;
	}
	
	@RequiresRoles(value={"车管员"})
	@RequestMapping("/toDeleteCar_use")
	public ModelAndView toDeleteCar_use(@RequestParam("id")int id,ModelAndView mv) {
		Car_use car_use=car_useService.queryCar_useById(id);
		mv.addObject("car_use", car_use);
		mv.setViewName("deleteCar_use");
		return mv;
	}
	
	@RequiresRoles(value={"车管员"})
	@RequestMapping("/deleteCar_use")
	public String deleteCar_use(@RequestParam("id")int id,RedirectAttributes attributes) {
		if(car_useService.deleteCar_use(id)==1) {
			attributes.addFlashAttribute("successMessage", "操作成功！");
		}else {
			attributes.addFlashAttribute("failedMessage", "操作失败！请检查后重新操作！");
		}
		return "redirect:/Car_use/car_useList?pageNum=1&pageSize=8";
		
	}
	@RequiresRoles(value={"车管员","admin"},logical = Logical.OR)
	@RequestMapping("/car_useSearch")
	public ModelAndView car_useSearch(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,@RequestParam("property")String property,@RequestParam("keyword")String keyword,ModelAndView mv) {
		List<Car_use> car_uselist=car_useService.searchCar_use(pageNum, pageSize, property, keyword);
		PageInfo pageInfo=new PageInfo(car_uselist); //分页
		mv.addObject("pageInfo",pageInfo);
		mv.addObject("property",property);
		mv.addObject("keyword",keyword);
		mv.setViewName("car_useSearch");
		return mv;
	}

}
