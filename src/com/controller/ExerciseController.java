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
import com.pojo.Exercise;
import com.pojo.Student;
import com.pojo.Exercise;
import com.service.ExerciseService;
import com.service.ExerciseServiceImpl;
import com.service.StudentService;
import com.service.ExerciseServiceImpl;

@Controller
@RequiresAuthentication
@RequestMapping("/Exercise")
@RequiresRoles(value={"教练"})
public class ExerciseController {
	@Autowired
	private ExerciseService exerciseserviceimpl;
	
	@Autowired
	private StudentService studentServiceImpl;
	
	@RequestMapping("/toExerciseList")
	public ModelAndView toExerciseList(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize,String eid,ModelAndView mv){
		List<Exercise> exerciselist=exerciseserviceimpl.queryExerciseByEid(pageNum, pageSize, eid);
		PageInfo pageInfo=new PageInfo(exerciselist); //分页
		mv.addObject("pageInfo",pageInfo);
		mv.setViewName("exerciseList");
		return mv;
	}
	@RequestMapping("/toUpdateExercise")
	public ModelAndView toUpdateExercise(@RequestParam("id")String id,ModelAndView mv) {
		Exercise exercise=exerciseserviceimpl.queryExerciseById(id);
		mv.addObject("exercise",exercise);
		mv.setViewName("updateExercise");
		return  mv;
	}
	
	@RequestMapping("/updateExercise")
	public String updateExercise(@ModelAttribute("exercise")Exercise exercise,RedirectAttributes attributes) {	
		Subject subject=SecurityUtils.getSubject();
		Session session=subject.getSession();
		Employee employee =(Employee) session.getAttribute("employee");
		String eid=employee.getEid();
		if(exerciseserviceimpl.updateExercise(exercise)) {
			attributes.addFlashAttribute("updateMessage","修改成功!!!");
		}else {
			
			attributes.addFlashAttribute("updateMessage","修改失败，请再次操作！");
		}
		return "redirect:/Exercise/toExerciseList?pageNum=1&pageSize=8&eid="+eid;
	}
	
	@RequestMapping("/toAddExercise")
	public ModelAndView toAddExercise(@RequestParam("sid")String sid,ModelAndView mv) {
		Student student=studentServiceImpl.queryStudentBySid(sid);
		mv.addObject("student", student);
		mv.setViewName("addExercise");
		return mv;
	}
	
	@RequestMapping("/addExercise")
	public String addStudent(@ModelAttribute("Exercise") Exercise exercise,String exer_date, RedirectAttributes attributes,HttpServletRequest request) throws ParseException {
		Subject subject=SecurityUtils.getSubject();
		Session session=subject.getSession();
		Employee employee =(Employee) session.getAttribute("employee");
		String eid=employee.getEid();
		//将表单的String转为date
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date date=simpleDateFormat.parse(exer_date);
		Exercise e=exerciseserviceimpl.addExercise(exercise,date,request);		
		if(e==null) {
			attributes.addFlashAttribute("failedMessage","操作失败，请重新再试一次");
			return "redirect:/Exercise/toaddExercise";
		}else {
			attributes.addFlashAttribute("successMessage",e);
			return "redirect:/Exercise/toExerciseList?pageNum=1&pageSize=8&eid="+eid;
		}
	}
}
