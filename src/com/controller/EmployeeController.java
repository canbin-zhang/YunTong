package com.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.github.pagehelper.PageInfo;
import com.pojo.Post;
import com.pojo.Salary;

import com.service.SalaryService;

import com.service.NoticeService;

import com.service.PostService;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pojo.Employee;
import com.pojo.Employeelist;
import com.pojo.Notice;
import com.service.EmployeeService;
import com.service.EmployeelistService;

@Controller
@RequestMapping("/Employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeserviceimpl;

	@Autowired
	private PostService postserviceimpl;
	
	@Autowired
	private EmployeelistService employeelistServiceImpl;
	
	@Autowired
	private SalaryService salaryServiceImpl;
	
	@Autowired
	private NoticeService noticeServiceImpl;
	
	@RequestMapping("/login")
	public ModelAndView login(@ModelAttribute("employee")Employee employee,ModelAndView mv) throws IOException {
		//使用shiro进行验证登录
		Subject subject=SecurityUtils.getSubject();
		Session session=subject.getSession();
		UsernamePasswordToken token=new UsernamePasswordToken(employee.getEid(),employee.getEpassword());
		//登录
		subject.login(token);
		//获取Employee的岗位名称
		if("admin".equals(employee.getEid())) {
			employee.setEname("admin");
			employee.setEphoto("admin.png");
			employee.setStatus("在职");
			mv.addObject("pname",employee.getEid());
			session.setAttribute("pname",employee.getEid());
		}else {
			//获取Employee的所有信息
			employee=employeeserviceimpl.queryEmployeeByEid(employee.getEid());
			Post post=employeeserviceimpl.queryPostByEid(employee.getEid());
			mv.addObject("pname", post.getPname());
			session.setAttribute("pname",post.getPname());
		}
		//获取公告
		List<Notice> noticeList=noticeServiceImpl.queryAllNotice();
		//放进mv中
		mv.addObject("employee", employee);
		mv.addObject("noticeList", noticeList);
		session.setAttribute("employee",employee);	
		mv.setViewName("index");
		return mv;
		
	}
	
	@RequiresAuthentication
	@RequiresRoles(value={"人事部","admin"},logical = Logical.OR)
	@RequestMapping("/toaddEmployee")
	public ModelAndView toaddEmployee(ModelAndView mv) {
		List<Post> posts= postserviceimpl.queryAllPost();
		mv.addObject("posts",posts);
		mv.setViewName("addEmployee");
		return mv;
	}
	
	@RequiresAuthentication
	@RequiresRoles(value={"人事部","admin"},logical = Logical.OR)
	@RequestMapping("/addEmployee")
	public String addEmployee(@Valid @ModelAttribute("Employee") Employee employee, String em_date,BindingResult result, RedirectAttributes attributes,HttpServletRequest request) throws ParseException {
		//表单验证
		if(result.hasErrors()) {
			attributes.addFlashAttribute("failedMessage", result.getFieldErrors().get(0).getDefaultMessage());
			return "redirect:/Employee/toaddEmployee";
		}
		//将表单的String转为date
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date date=simpleDateFormat.parse(em_date);
		Employee e=employeeserviceimpl.addEmployee(employee,date,request);
		if(e==null) {
			attributes.addFlashAttribute("failedMessage","操作失败，请重新再试一次");
			return "redirect:/Employee/toaddEmployee";
		}else {
			attributes.addFlashAttribute("successMessage",e);
			return "redirect:/Employee/toEmployeeList?pageNum=1&pageSize=8";
		}
	}
	
	@RequestMapping("/forgetPwd")
	public String forgetPwd(){
		return "lookForPwd";
	}
	@RequestMapping("/sendEmail")
	public String sendMail(String email) {
		return employeeserviceimpl.sendEmail(email);
	}
	
	@RequiresAuthentication
	@RequestMapping("/toResetPwd")
	public ModelAndView toResetPwd(@RequestParam("eid")String eid,ModelAndView mv) {
		Employee employee=employeeserviceimpl.queryEmployeeByEid(eid);
		mv.addObject("employee", employee);
		mv.setViewName("resetPassword");
		return mv;
	}
	
	@ResponseBody
	@PostMapping("/verifypassword")
	public String verifypassword(@Valid @ModelAttribute("employee") Employee employee,BindingResult result) {
		if(result.hasErrors()) {
			return result.getFieldErrors().get(0).getDefaultMessage();
		}
		else return "OK";
	}
	
	@ResponseBody
	@PostMapping("/verifypassword2")
	public String verifypassword2(String epassword,String password2) {
		if(epassword.equals(password2)) {
			return "OK";
		}
		else return "两个密码不一致";
	}
	
	
	@RequestMapping("/resetPassword")
	public String resetPassword(@RequestParam("eid") String eid,@RequestParam("epassword") String epassword,HttpSession session) throws IOException{
		if(employeeserviceimpl.resetPasswordByEid(eid, epassword)!=0) {
			session.setAttribute("successMessage","密码修改成功，请重新登录！！");
			return "redirect:/";
		}
		return "index";
	}
	
	@PostMapping("/lookForPwd")
	public String lookForPwd(@RequestParam("epassword") String epassword,@RequestParam("email")String email,HttpSession session) throws IOException{
		if(employeeserviceimpl.resetPasswordByEmail(email, epassword)!=0) {
			session.setAttribute("successMessage","密码修改成功，请重新登录！！");
			return "redirect:/";
		}
		return "index";
	}
	
	@RequiresAuthentication
	@RequiresRoles(value={"人事部","admin"},logical = Logical.OR)
	@RequestMapping("/toEmployeeList")
	public ModelAndView toEmployeeList(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,ModelAndView mv){
		List<Employee> employeelist=employeeserviceimpl.queryAllEmployee(pageNum,pageSize);
		PageInfo pageInfo=new PageInfo(employeelist); //分页
		mv.addObject("pageInfo",pageInfo);
		mv.setViewName("employeeList");
		return mv;
	}
	
	@RequiresAuthentication
	@RequestMapping("/employeeDetail")
	public ModelAndView employeeDetail(@RequestParam("eid")String eid,ModelAndView mv) throws ParseException{
		Employee employee=employeeserviceimpl.queryEmployeeByEid(eid);
		Post post=employeeserviceimpl.queryPostByEid(eid);
		Employeelist employeelist=employeelistServiceImpl.queryEmployeelistByEid(eid);
		mv.addObject("employee",employee);
		mv.addObject("post",post);
		mv.addObject("employeelist", employeelist);
		mv.setViewName("employeeDetail");
		return  mv;
	}
	
	@RequiresAuthentication
	@RequiresRoles(value={"人事部"})
	@RequestMapping("/toDeleteEmployee")
	public ModelAndView toDeleteEmployee(@RequestParam("eid")String eid,ModelAndView mv) {
		Employee employee=employeeserviceimpl.queryEmployeeByEid(eid);
		Post post=employeeserviceimpl.queryPostByEid(eid);
		mv.addObject("employee",employee);
		mv.addObject("post",post);
		mv.setViewName("deleteEmployee");
		return  mv;
	}
	
	@RequiresAuthentication
	@RequiresRoles(value={"人事部"})
	@RequestMapping("/deleteEmployee")
	public String deleteEmployee(String eid,String dm_date,String reason,RedirectAttributes attributes) throws ParseException {
		//将表单的String转为date
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date date=simpleDateFormat.parse(dm_date);
		
		if(employeeserviceimpl.deleteEmployee(eid, date, reason)) {
			attributes.addFlashAttribute("deleteMessage","成功删除员工："+eid+"!!!");
		}else {
			attributes.addFlashAttribute("deleteMessage","删除失败，请再次操作！");
		}
		return "redirect:/Employee/toEmployeeList?pageNum=1&pageSize=7";
	}
	
	@RequiresAuthentication
	@RequiresRoles(value={"人事部"})
	@RequestMapping("/toUpdateEmployee")
	public ModelAndView toUpdateEmployee(@RequestParam("eid")String eid,ModelAndView mv) {
		Employee employee=employeeserviceimpl.queryEmployeeByEid(eid);
		Post post=employeeserviceimpl.queryPostByEid(eid);
		List<Post> posts=postserviceimpl.queryAllPost();
		mv.addObject("employee",employee);
		mv.addObject("employeePost",post);
		mv.addObject("posts",posts);
		mv.setViewName("updateEmployee");
		return  mv;
	}
	
	@RequiresAuthentication
	@RequiresRoles(value={"人事部"})
	@RequestMapping("/updateEmployee")
	public String updateEmployee(Employee employee,RedirectAttributes attributes,HttpServletRequest request) {
		if(employeeserviceimpl.updateEmployee(employee, request)) {
			attributes.addFlashAttribute("updateMessage","修改成功!!!");
		}else {
			attributes.addFlashAttribute("updateMessage","修改失败，请再次操作！");
		}
		return "redirect:/Employee/toEmployeeList?pageNum=1&pageSize=7";
	}
	
	@RequiresAuthentication
	@RequiresRoles(value={"人事部","admin"},logical = Logical.OR)
	@RequestMapping("/employeeSearch")
	public ModelAndView employeeSearch(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,@RequestParam("property")String property,@RequestParam("keyword")String keyword,ModelAndView mv) {
		List<Employee> employeelist=employeeserviceimpl.searchEmployee(pageNum, pageSize, property, keyword);
		PageInfo pageInfo=new PageInfo(employeelist); //分页
		mv.addObject("pageInfo",pageInfo);
		mv.addObject("property",property);
		mv.addObject("keyword",keyword);
		mv.setViewName("employeeSearch");
		return mv;
	}
	
	@RequestMapping("/logout")
	public String logout() {
		//使用shiro进行注销登录
	    Subject subject=SecurityUtils.getSubject();
	    subject.logout();
	    return "redirect:/";
	}
	
	@RequiresAuthentication
	@RequestMapping("/salaryListByEid")
	public ModelAndView  salaryListByEid(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,@RequestParam("eid")String eid,ModelAndView mv) {
		List<Salary> salarylist=salaryServiceImpl.querySalaryByEid(eid, pageNum, pageSize);
		PageInfo pageInfo=new PageInfo(salarylist); //分页
		mv.addObject("pageInfo",pageInfo);
		mv.setViewName("salaryListByEid");
		return mv;
	}
	
	
	@PostMapping("/clearSession")
	public void clearSession() {
		Subject subject=SecurityUtils.getSubject();
		Session session=subject.getSession();
		session.removeAttribute("message");
		session.removeAttribute("successMessage");
		session.removeAttribute("resetSuccess");
	}
	
	@RequestMapping("/index")
	public ModelAndView index(ModelAndView mv) {
		//获取公告
		List<Notice> noticeList=noticeServiceImpl.queryAllNotice();
		//放进mv中
		
		mv.addObject("noticeList", noticeList);
		
		mv.setViewName("index");
		return mv;
	}
	
	
	
}
