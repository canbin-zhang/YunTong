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
import com.pojo.Disburse;
import com.pojo.Employee;
import com.pojo.Income;
import com.service.DisburseService;
import com.service.DisburseServiceImpl;
import com.service.EmployeeService;


@Controller
@RequestMapping("/Disburse")
@RequiresAuthentication
public class DisburseController {
	
	@Autowired
	private DisburseService disburseService;
	@Autowired
	private EmployeeService employeeservice;

	@RequestMapping("/disburseList")
	public ModelAndView disburseList(@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize,ModelAndView mv) {
		List<Disburse> disburselist=disburseService.queryAllDisburse(pageNum,pageSize);
		PageInfo pageInfo=new PageInfo(disburselist); //分页
		mv.addObject("pageInfo",pageInfo);
		mv.setViewName("disburseList");
		return mv;
	}
	
	@RequiresRoles(value={"财务"})
	@RequestMapping("/toAddDisburse")
	public String toAddDisburse() {
		return "addDisburse";
	}
	
	@RequiresRoles(value={"财务"})
	@ResponseBody
	@RequestMapping("/verifyEmployee")
	public String verifyEmployee(@RequestParam("eid")String eid) {
		Employee employee=employeeservice.queryEmployeeByEid(eid);
		if(employee==null) {
			return "<font color='red'><strong>无该员工信息，请重新输入</strong></font>";
		}return "OK";
	}
	
	@RequiresRoles(value={"财务"})
	@RequestMapping("/addDisburse")
	public String addDisburse(@ModelAttribute("disburse")Disburse disburse,RedirectAttributes attributes) {
		if(disburseService.addDisburse(disburse)==0) {
			attributes.addFlashAttribute("failedMessage","操作失败，请重新再试一次");
			return "redirect:/Disburse/disburseList?pageNum=1&pageSize=8";
		}else {
			attributes.addFlashAttribute("successMessage","操作成功！！");
			return "redirect:/Disburse/disburseList?pageNum=1&pageSize=8";
		}
	}
	
	@RequiresRoles(value={"财务","admin"},logical= Logical.OR)
	@RequestMapping("/disburseDetail")
	public ModelAndView disburseDetail(@RequestParam("id")int id,ModelAndView mv) {
		Disburse disburse=disburseService.queryDisburseById(id);
		mv.addObject("disburse", disburse);
		mv.setViewName("disburseDetail");
		return mv;
	}

	
	@RequiresRoles(value={"财务"})
	@RequestMapping("/toDeleteDisburse")
	public ModelAndView toDeleteDisburse(@RequestParam("id")int id,ModelAndView mv) {
		Disburse disburse=disburseService.queryDisburseById(id);
		mv.addObject("disburse", disburse);
		mv.setViewName("deleteDisburse");
		return mv;
	}
	
	@RequiresRoles(value={"财务"})
	@RequestMapping("/deleteDisburse")
	public String deleteDisburse(@RequestParam("id")int id,RedirectAttributes attributes) {
		if(disburseService.deleteDisburse(id)>0) {
			attributes.addFlashAttribute("successMessage","操作成功！！");
			return "redirect:/Disburse/disburseList?pageNum=1&pageSize=8";
		}else {
			attributes.addFlashAttribute("failedMessage","操作失败，请重新再试一次");
			return "redirect:/Disburse/disburseList?pageNum=1&pageSize=8";
		}
	}
	@RequiresRoles(value={"财务","admin"},logical= Logical.OR)
	@RequestMapping("/disburseSearch")
	public ModelAndView disburseSearch(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,@RequestParam("property")String property,@RequestParam("keyword")String keyword,ModelAndView mv) {
		List<Disburse> disburselist=disburseService.searchDisburse(pageNum, pageSize, property, keyword);
		PageInfo pageInfo=new PageInfo(disburselist); //分页
		mv.addObject("pageInfo",pageInfo);
		mv.addObject("property",property);
		mv.addObject("keyword",keyword);
		mv.setViewName("disburseSearch");
		return mv;
	}
	
	
}
