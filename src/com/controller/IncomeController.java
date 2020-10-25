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
import com.pojo.Income;
import com.pojo.Salary;
import com.pojo.Disburse;
import com.pojo.Employee;
import com.service.IncomeServiceImpl;
import com.service.EmployeeService;
import com.service.EmployeeServiceImpl;
import com.service.IncomeService;

@Controller
@RequestMapping("/Income")
@RequiresAuthentication
public class IncomeController {
	
	@Autowired
	private IncomeService incomeServiceImpl;
	@Autowired
	private EmployeeService employeeserviceimpl;
	
	@RequiresRoles(value={"财务","admin"},logical= Logical.OR)
	@RequestMapping("/incomeList")
	public ModelAndView incomeList(@RequestParam("pageNum")int pageNum,@RequestParam("pageSize")int pageSize,ModelAndView mv) {
		List<Income> incomelist=incomeServiceImpl.queryAllIncome(pageNum,pageSize);
		PageInfo pageInfo=new PageInfo(incomelist); //分页
		mv.addObject("pageInfo",pageInfo);
		mv.setViewName("incomeList");
		return mv;
	}
	
	@RequiresRoles(value={"财务"})
	@RequestMapping("/toAddIncome")
	public String toAddIncome() {
		return "addIncome";
	}
	
	@RequiresRoles(value={"财务"})
	@ResponseBody
	@RequestMapping("/verifyEmployee")
	public String verifyEmployee(@RequestParam("eid")String eid) {
		Employee employee=employeeserviceimpl.queryEmployeeByEid(eid);
		if(employee==null) {
			return "<font color='red'><strong>无该员工信息，请重新输入</strong></font>";
		}return "OK";
	}
	
	@RequiresRoles(value={"财务"})
	@RequestMapping("/addIncome")
	public String addIncome(@ModelAttribute("income")Income income,RedirectAttributes attributes) {
		if(incomeServiceImpl.addIncome(income)==0) {
			attributes.addFlashAttribute("failedMessage","操作失败，请重新再试一次");
			return "redirect:/Income/incomeList?pageNum=1&pageSize=8";
		}else {
			attributes.addFlashAttribute("successMessage","操作成功！！");
			return "redirect:/Income/incomeList?pageNum=1&pageSize=8";
		}
	}
	
	@RequiresRoles(value={"财务","admin"},logical= Logical.OR)
	@RequestMapping("/incomeDetail")
	public ModelAndView incomeDetail(@RequestParam("id")int id,ModelAndView mv) {
		Income income=incomeServiceImpl.queryIncomeById(id);
		mv.addObject("income", income);
		mv.setViewName("incomeDetail");
		return mv;
	}
	
	
	@RequiresRoles(value={"财务"})
	@RequestMapping("/toDeleteIncome")
	public ModelAndView toDeleteIncome(@RequestParam("id")int id,ModelAndView mv) {
		Income income=incomeServiceImpl.queryIncomeById(id);
		mv.addObject("income", income);
		mv.setViewName("deleteIncome");
		return mv;
	}
	
	@RequiresRoles(value={"财务"})
	@RequestMapping("/deleteIncome")
	public String deleteDisburse(@RequestParam("id")int id,RedirectAttributes attributes) {
		if(incomeServiceImpl.deleteIncome(id)>0) {
			attributes.addFlashAttribute("successMessage","操作成功！！");
			return "redirect:/Income/incomeList?pageNum=1&pageSize=8";
		}else {
			attributes.addFlashAttribute("failedMessage","操作失败，请重新再试一次");
			return "redirect:/Income/incomeList?pageNum=1&pageSize=8";
		}
	}
	
	@RequiresRoles(value={"财务","admin"},logical= Logical.OR)
	@RequestMapping("/incomeSearch")
	public ModelAndView incomeSearch(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,@RequestParam("property")String property,@RequestParam("keyword")String keyword,ModelAndView mv) {
		List<Income> incomelist=incomeServiceImpl.searchIncome(pageNum, pageSize, property, keyword);
		PageInfo pageInfo=new PageInfo(incomelist); //分页
		mv.addObject("pageInfo",pageInfo);
		mv.addObject("property",property);
		mv.addObject("keyword",keyword);
		mv.setViewName("incomeSearch");
		return mv;
	}
	
}
