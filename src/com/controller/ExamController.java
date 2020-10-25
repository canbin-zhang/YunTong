package com.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.pagehelper.PageInfo;
import com.pojo.Employee;
import com.pojo.Exam;
import com.pojo.License;
import com.pojo.Student;
import com.pojo.Exam;
import com.service.ExamService;
import com.service.StudentService;


@Controller
@RequiresAuthentication
@RequiresRoles(value={"教练"})
@RequestMapping("/Exam")
public class ExamController {
	@Autowired
	private ExamService examserviceimpl;
	
	@Autowired
	private StudentService studentServiceImpl;
	
	@RequestMapping("/toExamList")
	public ModelAndView toExamList(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,String eid,ModelAndView mv){
		List<Exam> examlist=examserviceimpl.queryExamByEid(pageNum, pageSize, eid);
		PageInfo pageInfo=new PageInfo(examlist); //分页
		mv.addObject("pageInfo",pageInfo);
		mv.setViewName("examList");
		return mv;
	}
	@RequestMapping("/toUpdateExam")
	public ModelAndView toUpdateExam(@RequestParam("id")String id,ModelAndView mv) {
		Exam exam=examserviceimpl.queryExamById(id);
		mv.addObject("exam",exam);
		mv.setViewName("updateExam");
		return  mv;
	}
	
	@RequestMapping("/updateExam")
	public String updateExam(@ModelAttribute("exam")Exam exam,RedirectAttributes attributes) {	
		Subject subject=SecurityUtils.getSubject();
		Session session=subject.getSession();
		Employee employee =(Employee) session.getAttribute("employee");
		String eid=employee.getEid();
		if(examserviceimpl.updateExam(exam)) {
			attributes.addFlashAttribute("updateMessage","修改成功!!!");
			return "redirect:/Exam/toExamList?pageNum=1&pageSize=8&eid="+eid;
		}else {
			attributes.addFlashAttribute("updateMessage","修改失败，请再次操作！");
			return "redirect:/Exam/toExamList?pageNum=1&pageSize=8&eid="+eid;
		}
	}
	
	@RequestMapping("/toAddExam")
	public ModelAndView toAddExam(@RequestParam("sid")String sid,ModelAndView mv) {
		Student student=studentServiceImpl.queryStudentBySid(sid);
		mv.addObject("student", student);
		mv.setViewName("addExam");
		return mv;
	}
	
	@RequestMapping("/addExam")
	public String addExam(@ModelAttribute("Exam") Exam exam,String exam_date, RedirectAttributes attributes,HttpServletRequest request) throws ParseException {
		Subject subject=SecurityUtils.getSubject();
		Session session=subject.getSession();
		Employee employee =(Employee) session.getAttribute("employee");
		String eid=employee.getEid();
		//将表单的String转为date
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date date=simpleDateFormat.parse(exam_date);
		Exam e=examserviceimpl.addExam(exam);		
		if(e==null) {
			attributes.addFlashAttribute("failedMessage","操作失败，请重新再试一次");
			return "redirect:/Exam/toaddExam";
		}else {
			attributes.addFlashAttribute("successMessage",e);
			return "redirect:/Exam/toExamList?pageNum=1&pageSize=8&eid="+eid;
		}
	}
}
