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
import com.pojo.Employee;
import com.pojo.License;
import com.service.EmployeeService;
import com.service.EmployeeServiceImpl;
import com.service.LicenseService;
import com.service.LicenseServiceImpl;
import com.service.StudentService;
import com.service.StudentlistServiceImpl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pojo.Student;
import com.pojo.Studentlist;
import com.service.StudentServiceImpl;
import com.service.StudentlistService;

@Controller
@RequiresAuthentication
@RequestMapping("/Student")
public class StudentController {
	
	@Autowired
	private StudentService studentserviceimpl;

	@Autowired
	private LicenseService licenseserviceimpl;
	
	@Autowired
	private EmployeeService employeeserviceimpl;
	@Autowired
	private StudentlistService studentlistServiceImpl;
	
	@RequiresRoles(value={"业务员"})
	@RequestMapping("/toaddStudent")
	public ModelAndView toaddStudent(ModelAndView mv) {
		List<License> licenses= licenseserviceimpl.queryAllLicense();
		List<Employee> employees= employeeserviceimpl.queryEmployeeByPostName("教练");
		for (Employee employee : employees) {
			if("离职".equals(employee.getStatus())) {
				employees.remove(employee);
			}
		}
		mv.addObject("licenses",licenses);
		mv.addObject("employees",employees);
		mv.setViewName("addStudent");
		return mv;
	}
	
	@RequiresRoles(value={"业务员"})
	@RequestMapping("/addStudent")
	public String addStudent(@Valid @ModelAttribute("Student") Student student,String eid, String sm_date, RedirectAttributes attributes,HttpServletRequest request) throws ParseException {
		//将表单的String转为date
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date date=simpleDateFormat.parse(sm_date);
		Student s=studentserviceimpl.addStudent(student,eid,date,request);
		if(s==null) {
			attributes.addFlashAttribute("failedMessage","操作失败，请重新再试一次");
			return "redirect:/Student/toaddStudent";
		}else {
			attributes.addFlashAttribute("successMessage",s);
			return "redirect:/Student/toStudentList?pageNum=1&pageSize=8";
		}
	}
	
	@RequiresRoles(value={"业务员","admin"},logical = Logical.OR)
	@RequestMapping("/toStudentList")
	public ModelAndView toStudentList(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,String eid,ModelAndView mv){
		List<Student> studentlist=studentserviceimpl.queryAllStudent(pageNum,pageSize);
		PageInfo pageInfo=new PageInfo(studentlist); //分页
		mv.addObject("pageInfo",pageInfo);
		mv.setViewName("studentList");
		return mv;
	}
	
	@RequiresRoles(value={"教练"})
	@RequestMapping("/tocoachStudentList")
	public ModelAndView tocoachStudentList(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,String eid,ModelAndView mv){
		List<Student> coachstudentlist=studentserviceimpl.queryStudentlistByEid(pageNum,pageSize,eid);
		PageInfo pageInfo=new PageInfo(coachstudentlist); //分页
		mv.addObject("pageInfo",pageInfo);
		mv.setViewName("coachStudentList");
		return mv;
	}
	
	@RequiresRoles(value={"业务员","教练","admin"},logical = Logical.OR)
	@RequestMapping("/studentDetail")
	public ModelAndView studentDetail(@RequestParam("sid")String sid,String eid,ModelAndView mv) throws ParseException{
		Student student=studentserviceimpl.queryStudentBySid(sid);
		License license=studentserviceimpl.queryLicenseBySid(sid);	
		Studentlist studentlist=studentlistServiceImpl.queryStudentlistBySid(sid);
		Employee employee=employeeserviceimpl.queryEmployeeByEid(studentlist.getEid());
		mv.addObject("student",student);
		mv.addObject("license",license);
		mv.addObject("employee",employee);
		mv.addObject("studentlist", studentlist);
		mv.setViewName("studentDetail");
		return  mv;
	}
	

	@RequiresRoles(value={"业务员"})
	@RequestMapping("/toUpdateStudent")
	public ModelAndView toUpdateStudent(@RequestParam("sid")String sid,ModelAndView mv) {
		Student student=studentserviceimpl.queryStudentBySid(sid);
		License license=studentserviceimpl.queryLicenseBySid(sid);
		List<License> licenses=licenseserviceimpl.queryAllLicense();
		mv.addObject("student",student);
		mv.addObject("studentLicense",license);
		mv.addObject("licenses",licenses);
		mv.setViewName("updateStudent");
		return  mv;
	}
	
	
	
	@RequiresRoles(value={"业务员"})
	@RequestMapping("/updateStudent")
	public String updateStudent(@Valid  Student student,RedirectAttributes attributes,HttpServletRequest request) {
		if(studentserviceimpl.updateStudent(student, request)) {
			attributes.addFlashAttribute("updateMessage","修改成功!!!");
		}else {
			attributes.addFlashAttribute("updateMessage","修改失败，请再次操作！");
		}
		return "redirect:/Student/toStudentList?pageNum=1&pageSize=8";
	}
	
	
	@RequiresRoles(value={"业务员","教练","admin"},logical = Logical.OR)
	@RequestMapping("/studentSearch")
	public ModelAndView studentSearch(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,@RequestParam("property")String property,@RequestParam("keyword")String keyword,ModelAndView mv) {
		List<Student> studentlist=studentserviceimpl.searchStudent(pageNum, pageSize, property, keyword);
		PageInfo pageInfo=new PageInfo(studentlist); //分页
		mv.addObject("pageInfo",pageInfo);
		mv.addObject("property",property);
		mv.addObject("keyword",keyword);
		mv.setViewName("studentSearch");
		return mv;
	}
	
	@RequiresRoles(value={"教练"})
	@RequestMapping("/coachStudentSearch")
	public ModelAndView coachStudentSearch(@RequestParam("eid")String eid,@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,@RequestParam("property")String property,@RequestParam("keyword")String keyword,ModelAndView mv) {
		List<Student> studentlist=studentserviceimpl.searchStudentByEid(pageNum, pageSize, property, keyword,eid);
		PageInfo pageInfo=new PageInfo(studentlist); //分页
		mv.addObject("pageInfo",pageInfo);
		mv.addObject("property",property);
		mv.addObject("keyword",keyword);
		mv.setViewName("coachStudentSearch");
		return mv;
	}
	
	
}
