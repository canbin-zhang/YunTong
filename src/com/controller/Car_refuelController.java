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
import com.pojo.Car_refuel;
import com.pojo.Car_service;
import com.service.CarService;
import com.service.Car_refuelService;



@Controller
@RequestMapping("/Car_refuel")
@RequiresAuthentication
public class Car_refuelController {
	
	@Autowired
	private Car_refuelService car_refuelService;
	
	@Autowired
	private CarService carService;
	
	@RequiresRoles(value={"车管员","admin"},logical= Logical.OR)
	@RequestMapping("/car_refuelList")
	public ModelAndView car_refuelList(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,ModelAndView mv) {
		List<Car_refuel> car_refuelList=car_refuelService.queryAllCar_refuel(pageNum, pageSize);
		PageInfo pageInfo=new PageInfo(car_refuelList); //分页
		mv.addObject("pageInfo",pageInfo);
		mv.setViewName("car_refuelList");
		return mv;
	}
	
	@RequiresRoles(value={"车管员"})
	@RequestMapping("/toAddCar_refuel")
	public ModelAndView toAddCar_refuel(ModelAndView mv) {
		mv.setViewName("addCar_refuel");
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
	
	
	@RequiresRoles(value={"车管员"})
	@RequestMapping("/addCar_refuel")
	public String addCar_refuel(@ModelAttribute("car_refuel")Car_refuel car_refuel,String refuel_date,RedirectAttributes attributes) throws ParseException {
		//将表单的String转为date
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date refueldate=simpleDateFormat.parse(refuel_date);
		
		car_refuel.setRefuel_date(refueldate);
		if(car_refuelService.addCar_refuel(car_refuel)==1) {
			attributes.addFlashAttribute("successMessage", "添加记录成功！");
		}else {
			attributes.addFlashAttribute("failedMessage", "添加记录失败！请检查后重新添加！");
		}
		
		return "redirect:/Car_refuel/car_refuelList?pageNum=1&pageSize=8";
	}
	
	@RequiresRoles(value={"车管员"})
	@RequestMapping("/toDeleteCar_refuel")
	public ModelAndView toDeleteCar_refuel(@RequestParam("id")int id,ModelAndView mv) {
		Car_refuel car_refuel=car_refuelService.queryCar_refuelById(id);
		mv.addObject("car_refuel", car_refuel);
		mv.setViewName("deleteCar_refuel");
		return mv;
	}
	
	@RequiresRoles(value={"车管员"})
	@RequestMapping("/deleteCar_refuel")
	public String deleteCar_refuel(@RequestParam("id")int id,RedirectAttributes attributes) {
		if(car_refuelService.deleteCar_refuel(id)==1) {
			attributes.addFlashAttribute("successMessage", "操作成功！");
		}else {
			attributes.addFlashAttribute("failedMessage", "操作失败！请检查后重新操作！");
		}
		return  "redirect:/Car_refuel/car_refuelList?pageNum=1&pageSize=8";
		
	}
	
	@RequiresRoles(value={"车管员","admin"},logical= Logical.OR)
	@RequestMapping("/car_refuelSearch")
	public ModelAndView car_refuelSearch(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,@RequestParam("property")String property,@RequestParam("keyword")String keyword,ModelAndView mv) {
		List<Car_refuel> car_refuellist=car_refuelService.searchCar_refuel(pageNum, pageSize, property, keyword);
		PageInfo pageInfo=new PageInfo(car_refuellist); //分页
		mv.addObject("pageInfo",pageInfo);
		mv.addObject("property",property);
		mv.addObject("keyword",keyword);
		mv.setViewName("car_refuelSearch");
		return mv;
	}
	
	

}
