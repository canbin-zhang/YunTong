package com.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.pojo.Car_service;
import com.pojo.Car_use;
import com.service.CarService;
import com.service.Car_serviceService;


@Controller
@RequestMapping("/Car_service")
@RequiresAuthentication

public class Car_serviceController {
	
	@Autowired
	private Car_serviceService car_serviceService;
	
	@Autowired
	private CarService carService;
	
	@RequiresRoles(value={"车管员","admin"},logical = Logical.OR)
	@RequestMapping("/car_serviceList")
	public ModelAndView car_serviceList(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,ModelAndView mv) {
		List<Car_service> car_serviceList=car_serviceService.queryAllCar_service(pageNum, pageSize);
		PageInfo pageInfo=new PageInfo(car_serviceList); //分页
		mv.addObject("pageInfo",pageInfo);
		mv.setViewName("car_serviceList");
		return mv;
	}
	
	@RequiresRoles(value={"车管员"})
	@RequestMapping("/toAddCar_service")
	public ModelAndView toAddCar_service(ModelAndView mv) {
		mv.setViewName("addCar_service");
		return mv;
	}

	@ResponseBody
	@RequestMapping("/verifyCar")
	public String verifyCar(@RequestParam("cid")String cid) {
		
		if(carService.queryCountCarByCid(cid)==0) {
			return "<font color='red'><strong>该车牌号不存在，请重新输入！</strong></font>";
			
		}else {
		
			return "<font color='green'><strong>OK</strong></font>";
		}
	}
	
	@ResponseBody
	@RequestMapping("/verifyStart_date")
	public String verifyStart_date(String start_date) {
		if(start_date.length()<2) {  //判断是否为空
			return "<font color='red'><strong>请先输入开始日期！</strong></font>";
		}
		return "<font color='green'><strong>OK</strong></font>";
	}
	
	@ResponseBody
	@RequestMapping("/verifyEnd_date")
	public String verifyEnd_date(String start_date,String end_date) throws ParseException {
		if(end_date.length()<2) {  //判断是否为空
			return "<font color='red'><strong>请先输入结束日期！</strong></font>";
		}else {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate=df.parse(start_date);
			Date endDate=df.parse(end_date);
			if(startDate.compareTo(endDate)==1) {
				return "<font color='red'><strong>请输入正确的结束日期！</strong></font>";
			}else {
				return "<font color='green'><strong>OK</strong></font>";
			}
		}
	}
	
	@RequiresRoles(value={"车管员"})
	@RequestMapping("/addCar_service")
	public String addCar_service(@ModelAttribute("car_service")Car_service car_service,String start_date,String end_date,RedirectAttributes attributes) throws ParseException {
		//将表单的String转为date
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date startdate=simpleDateFormat.parse(start_date);
		Date enddate=simpleDateFormat.parse(end_date);
		car_service.setStart_date(startdate);
		car_service.setEnd_date(enddate);
		if(car_serviceService.addCar_service(car_service)==1) {
			attributes.addFlashAttribute("successMessage", "添加记录成功！");
		}else {
			attributes.addFlashAttribute("failedMessage", "添加记录失败！请检查后重新添加！");
		}
		
		return "redirect:/Car_service/car_serviceList?pageNum=1&pageSize=8";
	}
	
	@RequiresRoles(value={"车管员"})
	@RequestMapping("/toDeleteCar_service")
	public ModelAndView toDeleteCar_service(@RequestParam("id")int id,ModelAndView mv) {
		Car_service car_service=car_serviceService.queryCar_serviceById(id);
		mv.addObject("car_service", car_service);
		mv.setViewName("deleteCar_service");
		return mv;
	}
	
	@RequiresRoles(value={"车管员"})
	@RequestMapping("/deleteCar_service")
	public String deleteCar_service(@RequestParam("id")int id,RedirectAttributes attributes) {
		if(car_serviceService.deleteCar_service(id)==1) {
			attributes.addFlashAttribute("successMessage", "操作成功！");
		}else {
			attributes.addFlashAttribute("failedMessage", "操作失败！请检查后重新操作！");
		}
		return  "redirect:/Car_service/car_serviceList?pageNum=1&pageSize=8";
		
	}
	
	@RequiresRoles(value={"车管员","admin"},logical = Logical.OR)
	@RequestMapping("/car_serviceSearch")
	public ModelAndView car_serviceSearch(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,@RequestParam("property")String property,@RequestParam("keyword")String keyword,ModelAndView mv) {
		List<Car_service> car_servicelist=car_serviceService.searchCar_service(pageNum, pageSize, property, keyword);
		PageInfo pageInfo=new PageInfo(car_servicelist); //分页
		mv.addObject("pageInfo",pageInfo);
		mv.addObject("property",property);
		mv.addObject("keyword",keyword);
		mv.setViewName("car_serviceSearch");
		return mv;
	}
	
	
}
