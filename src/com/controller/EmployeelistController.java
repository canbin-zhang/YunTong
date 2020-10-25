package com.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.pojo.Employee;
import com.pojo.Employeelist;
import com.service.EmployeelistService;
import com.service.EmployeelistServiceImpl;

@Controller
@RequestMapping("/Employeelist")
@RequiresAuthentication
public class EmployeelistController {
      
	@Autowired
	private EmployeelistService employeelistServiceImpl;
	
	@RequiresRoles(value={"人事部","admin"},logical = Logical.OR)
	@RequestMapping("/employeelistList")
	public ModelAndView employeelistList(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,ModelAndView mv) {
		List<Employeelist> employeelistlist=employeelistServiceImpl.queryAllEmployeelist(pageNum,pageSize);
		PageInfo pageInfo=new PageInfo(employeelistlist); //分页
		mv.addObject("pageInfo",pageInfo);
		mv.setViewName("employeelistList");
		return mv;
	}
	
	@RequiresRoles(value={"人事部","admin"},logical = Logical.OR)
	@RequestMapping("/employeelistSearch")
	public ModelAndView employeelistSearch(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,@RequestParam("property")String property,@RequestParam("keyword")String keyword,ModelAndView mv){
		List<Employeelist> employeelistlist=employeelistServiceImpl.employeelistSearch(pageNum, pageSize, property, keyword);
		PageInfo pageInfo=new PageInfo(employeelistlist);
		mv.addObject("pageInfo",pageInfo);
		mv.addObject("property",property);
		mv.addObject("keyword",keyword);
		mv.setViewName("employeelistSearch");
		return mv;
	}
}
