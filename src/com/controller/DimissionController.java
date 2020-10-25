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
import com.pojo.Dimission;
import com.service.DimissionService;

@Controller
@RequestMapping("/Dimission")
@RequiresAuthentication
@RequiresRoles(value={"人事部","admin"},logical = Logical.OR)
public class DimissionController {
      
	@Autowired
	private DimissionService dimissionService;
	
	@RequestMapping("/dimissionList")
	public ModelAndView dimissionList(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,ModelAndView mv) {
		List<Dimission> dimissionlist=dimissionService.queryAllDimission(pageNum,pageSize);
		PageInfo pageInfo=new PageInfo(dimissionlist); //分页
		mv.addObject("pageInfo",pageInfo);
		mv.setViewName("dimissionList");
		return mv;
	}
	
	@RequestMapping("/dimissionSearch")
	public ModelAndView dimissionSearch(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,@RequestParam("property")String property,@RequestParam("keyword")String keyword,ModelAndView mv){
		List<Dimission> dimissionSearch=dimissionService.dimissionSearch(pageNum, pageSize, property, keyword);
		PageInfo pageInfo=new PageInfo(dimissionSearch);
		mv.addObject("pageInfo",pageInfo);
		mv.addObject("property",property);
		mv.addObject("keyword",keyword);
		mv.setViewName("dimissionSearch");
		return mv;
	}
}
