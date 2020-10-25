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
import com.pojo.Car_use;
import com.pojo.Employee;
import com.pojo.Post;
import com.service.CarService;

import com.service.Car_useService;

import com.service.EmployeeService;



@Controller
@RequestMapping("/Car")
@RequiresAuthentication
@RequiresRoles(value={"车管员"})
public class CarController {
	
	@Autowired
	private CarService carService;
	
	@Autowired 
	private EmployeeService employeeService;
	
	@Autowired
	private Car_useService car_useService;
	
	@RequiresRoles(value={"车管员","admin"},logical = Logical.OR)
	@RequestMapping("/carList")
	public ModelAndView carList(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,ModelAndView mv) {
		List<Car> carList=carService.CarList(pageNum, pageSize);
		PageInfo pageInfo=new PageInfo(carList); //分页
		mv.addObject("pageInfo",pageInfo);
		mv.setViewName("carList");
		return mv;
	}
	
	@RequiresRoles(value={"车管员"})
	@RequestMapping("/toAddCar")
	public ModelAndView toAddCar(ModelAndView mv) {
		mv.setViewName("addCar");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/verifyCar")
	public String verifyCar(@RequestParam("cid")String cid) {
		if("".equals(cid)) {
			return "<font color='red'><strong>车牌号不能为空！</strong></font>";
		}
		if(carService.queryCountCarByCid(cid)==0) {
			return "<font color='green'><strong>OK</strong></font>";
		}
		return "<font color='red'><strong>该车牌号已存在，请重新输入！</strong></font>";
	}
	
	@RequiresRoles(value={"车管员"})
	@RequestMapping("/addCar")
	public String addCar(@ModelAttribute("car")Car car,String cdate,RedirectAttributes attributes) throws ParseException {
		//将表单的String转为date
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date date=simpleDateFormat.parse(cdate);
		car.setCdate(date);
		if(carService.addCar(car)==1) {
			attributes.addFlashAttribute("successMessage", "添加记录成功！");
		}else {
			attributes.addFlashAttribute("failedMessage", "添加记录失败！请检查后重新添加！");
		}
		
		return "redirect:/Car/carList?pageNum=1&pageSize=8";
	}
	
	@RequiresRoles(value={"车管员","admin"},logical = Logical.OR)
	@RequestMapping("/carSearch")
	public ModelAndView carSearch(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,@RequestParam("property")String property,@RequestParam("keyword")String keyword,ModelAndView mv) {
		List<Car> carlist=carService.searchCar(pageNum, pageSize, property, keyword);
		PageInfo pageInfo=new PageInfo(carlist); //分页
		mv.addObject("pageInfo",pageInfo);
		mv.addObject("property",property);
		mv.addObject("keyword",keyword);
		mv.setViewName("carSearch");
		return mv;
	}
	
	@RequiresRoles(value={"车管员"})
	@RequestMapping("/toDeleteCar")
	public ModelAndView toDeleteCar(@RequestParam("cid")String cid,ModelAndView mv) {
		Car car=carService.queryCarByCid(cid);
		mv.addObject("car", car);
		mv.setViewName("deleteCar");
		return mv;
	}
	
	@RequiresRoles(value={"车管员"})
	@RequestMapping("/deleteCar")
	public String deleteCar(@RequestParam("cid")String cid,RedirectAttributes attributes) {
		if(carService.deleteCar(cid)==1) {
			attributes.addFlashAttribute("successMessage", "操作成功！");
		}else {
			attributes.addFlashAttribute("failedMessage", "操作失败！请检查后重新添加！");
		}
		return "redirect:/Car/carList?pageNum=1&pageSize=8";
	}
	
	@RequiresRoles(value={"车管员"})
	@RequestMapping("/toUpdateCar")
	public ModelAndView toUpdateCar(@RequestParam("cid")String cid,ModelAndView mv) {
		Car car=carService.queryCarByCid(cid);
		mv.addObject("car", car);
		mv.setViewName("updateCar");
		return mv;
	}
	
	@RequiresRoles(value={"车管员"})
	@RequestMapping("/updateCar")
	public String updateCar(@ModelAttribute("car")Car car,String cdate,RedirectAttributes attributes) throws ParseException {
		//将表单的String转为date
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date date=simpleDateFormat.parse(cdate);
		car.setCdate(date);
		if(carService.updateCar(car)==1) {
			attributes.addFlashAttribute("successMessage", "操作成功！");
		}else {
			attributes.addFlashAttribute("failedMessage", "操作失败！请检查后重新添加！");
		}
		return "redirect:/Car/carList?pageNum=1&pageSize=8";
	}
	
	@RequiresRoles(value={"车管员"})
	@RequestMapping("/toAddCar_use")
	public ModelAndView toAddCar_use(@RequestParam("cid")String cid,ModelAndView mv) {
		mv.addObject("cid", cid);
		mv.setViewName("addCar_use");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/verifyEmployee")
	public String verifyEmployee(@RequestParam("eid")String eid) {
		Employee  employee=employeeService.queryEmployeeByEid(eid);
		if(employee!=null) {
			return "<font color='green'><strong>OK</strong></font>";
		}
		return "<font color='red'><strong>无该教练，请重新输入ID</strong></font>";
	}
	
	@ResponseBody
	@RequestMapping("/verifyCar_use")
	public String verifyCar_use(String cid,String cu_date) {
		Car_use car_use=car_useService.queryCar_useByCidCu_date(cid, cu_date);
		if(car_use==null) {
			return "<font color='green'><strong>OK</strong></font>";
		}
		return "<font color='red'><strong>该车辆在这天已被使用，请重新选择车辆或日期</strong></font>";
	}
	
	@RequiresRoles(value={"车管员"})
	@RequestMapping("/addCar_use")
	public String addCarUse(@ModelAttribute("car_use")Car_use car_use,String cu_date,RedirectAttributes attributes) throws ParseException {
		//将表单的String转为date
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date date=simpleDateFormat.parse(cu_date);
		car_use.setCu_date(date);
		if(car_useService.addCar_use(car_use)==1) {
			attributes.addFlashAttribute("successMessage", "添加记录成功！");
		}else {
			attributes.addFlashAttribute("failedMessage", "添加记录失败！请检查后重新添加！");
		}
		return "redirect:/Car/carList?pageNum=1&pageSize=8";
		
		}
	
	

}
	
	

