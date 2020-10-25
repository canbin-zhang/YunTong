package com.controller;

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
import com.pojo.Employee;
import com.pojo.Post;
import com.pojo.Salary;
import com.service.EmployeeService;
import com.service.EmployeeServiceImpl;
import com.service.PostService;
import com.service.PostServiceImpl;
import com.service.SalaryService;
import com.service.SalaryServiceImpl;

@Controller
@RequestMapping("/Salary")
@RequiresAuthentication
public class SalaryController {
	
	@Autowired
	private SalaryService salaryServiceImpl;
	
	@Autowired
	private EmployeeService employeeServiceImpl;  //验证员工是否存在和提示应发放金额
	
	@Autowired
	private PostService postServiceImpl;
	
	@RequiresRoles(value={"财务","admin"},logical= Logical.OR)
	@RequestMapping("/salaryList")
	public ModelAndView salaryList(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,ModelAndView mv) {
		List<Salary> salarylist=salaryServiceImpl.queryAllSalary(pageNum, pageSize);
		PageInfo pageInfo=new PageInfo(salarylist); //分页
		mv.addObject("pageInfo",pageInfo);
		mv.setViewName("salaryList");
		return mv;
	}
	
	@RequiresRoles(value={"财务"})
	@RequestMapping("/toAddSalary")
	public String toAddSalary() {
		return "addSalary";
	}
	
	@RequiresRoles(value={"财务"})
	@ResponseBody
	@RequestMapping("/verifyEmployee")
	public String verifyEmployee(@RequestParam("eid")String eid) {
		Post post=employeeServiceImpl.queryPostByEid(eid);
		if(post==null) {
			return "<font color='red'><strong>无该员工信息，请重新输入</strong></font>";
		}
		return "<font color='green'><strong>该员工岗位是："+post.getPname()+"。应发金额为："+post.getSalary()+"</strong></font>";
	}
	
	@RequiresRoles(value={"财务"})
	@RequestMapping("/addSalary")
	public String addSalary(@ModelAttribute("salary")Salary salary,RedirectAttributes attributes) {
		if(salaryServiceImpl.addSalary(salary)) {
			attributes.addFlashAttribute("successMessage", "添加记录成功！");
		}else {
			attributes.addFlashAttribute("failedMessage", "添加记录失败！请检查后重新添加！");
		}
		
		return "redirect:/Salary/salaryList?pageNum=1&pageSize=8";
	}
	
	@RequiresRoles(value={"财务"})
	@RequestMapping("/toDeleteSalary")
	public ModelAndView toDeleteSalary(@RequestParam("id")int id,ModelAndView mv) {
		Salary salary=salaryServiceImpl.querySalaryById(id);
		mv.addObject("salary", salary);
		mv.setViewName("deleteSalary");
		return mv;
	}
	
	@RequiresRoles(value={"财务"})
	@RequestMapping("/deleteSalary")
	public String deleteSalary(@RequestParam("id")int id,RedirectAttributes attributes) {
		if(salaryServiceImpl.deleteSalaryById(id)==1) {
			attributes.addFlashAttribute("successMessage", "操作成功！！！");
		}else {
			attributes.addFlashAttribute("failedMessage", "操作失败，请重新操作！！！");
		}
		return "redirect:/Salary/salaryList?pageNum=1&pageSize=8";
	}
	
	@RequiresRoles(value={"财务","admin"},logical= Logical.OR)
	@RequestMapping("/postSalaryList")
	public ModelAndView postSalaryList(int pageNum,int pageSize,ModelAndView mv) {
		List<Post> postlist=postServiceImpl.queryAllPost(pageNum, pageSize);
		PageInfo pageInfo=new PageInfo(postlist); //分页
		mv.addObject("pageInfo", pageInfo);
		mv.setViewName("postSalaryList");
		return mv;
	}
	
	@RequiresRoles(value={"财务"})
	@RequestMapping("/toupdatePostSalary")
	public ModelAndView toupdatePostSalary(@RequestParam("pid")String pid,ModelAndView mv) {
		Post post=postServiceImpl.queryPostByPid(pid);
		mv.addObject("post", post);
		mv.setViewName("updatePostSalary");
		return mv;
	}
	
	@RequiresRoles(value={"财务"})
	@RequestMapping("/updatePostSalary")
	public String updatePostSalary(@RequestParam("pid")String pid, @RequestParam("salary_update")String salary_update,RedirectAttributes attributes) {
		if(postServiceImpl.updatePostSalary(pid, salary_update)==1) {
			attributes.addFlashAttribute("successMessage", "操作成功！！！");
		}else {
			attributes.addFlashAttribute("failedMessage", "操作失败，请重新操作一次！！！");
		}
		return "redirect:/Salary/postSalaryList?pageNum=1&pageSize=8";
	}
	
	@RequiresRoles(value={"财务","admin"},logical= Logical.OR)
	@RequestMapping("/salarySearch")
	public ModelAndView salarySearch(@RequestParam("start_date")String start_date,@RequestParam("end_date")String end_date,@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,ModelAndView mv) {
		List<Salary> salarylist=salaryServiceImpl.salarySearch(start_date, end_date, pageNum, pageSize);
		PageInfo pageInfo=new PageInfo(salarylist); //分页
		mv.addObject("pageInfo",pageInfo);
		mv.addObject("start_date",start_date);
		mv.addObject("end_date",end_date);
		mv.setViewName("salarySearch");
		return mv;
	}
}
