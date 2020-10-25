package com.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.IncomeDao;
import com.dao.LicenseDao;
import com.dao.StudentDao;
import com.dao.StudentlistDao;
import com.github.pagehelper.PageHelper;
import com.pojo.Income;
import com.pojo.License;
import com.pojo.Student;
import com.pojo.Studentlist;
import com.utils.MyUtils;
@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentDao studentdao;
	@Autowired
	private StudentlistDao studentlistdao;
	
	
	@Autowired
	private LicenseDao licenseDao;
	
	@Autowired
	private IncomeDao incomeDao;
	
	@Override
	public List<Student> queryAllStudent(int pageNum, int pageSize) {
		// TODO 自动生成的方法存根
		return studentdao.queryAllStudent();
	}

	@Override
	public List<Student> queryStudentByLid(String lid, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return studentdao.queryStudentByLid(lid);
	}

	@Transactional
	@Override
	public Student addStudent(Student student,String eid, Date sm_date,HttpServletRequest request) {
		
		String newFileName = "";
		String fileName = student.getSphotofile().getOriginalFilename(); 
		//拿到session中的操作员id
		Subject subject= SecurityUtils.getSubject();
		String operator_id= (String) subject.getPrincipal();
		//封装一个招生记录对象
		Studentlist studentlist=new Studentlist();
		studentlist.setOperator_id(operator_id);
		studentlist.setSid(student.getSid());
		studentlist.setEid(eid);
		studentlist.setSm_date(sm_date);
		//封装一个收入记录对象
		Income income=new Income();
		income.setEid(operator_id);
		income.setIncome_date(sm_date);
		income.setIncome_detail("学员"+student.getSname()+"学费");
		income.setIncome_money(licenseDao.queryLicenseByLid(student.getLid()).getLmoney());
		income.setOperator_id(operator_id);
		//选择文件
		if(fileName.length() > 0){
			String realpath = request.getServletContext().getRealPath("photos");
			//实现文件上传
			String fileType = fileName.substring(fileName.lastIndexOf('.'));
			//用身份证号作为照片文件名
			newFileName = student.getSid() + fileType;
			student.setSphoto(newFileName);
			File targetFile = new File(realpath, newFileName); 
			if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	        } 
			//上传
	        try {   
	        	student.getSphotofile().transferTo(targetFile);
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
		}
		if(studentdao.addStudent(student)>0&&studentlistdao.addStudentlist(studentlist)>0&&incomeDao.addIncome(income)>0) {
			return student;
		}else {
			return null;
		}
		
	}

	@Override
	public Student queryStudentBySid(String sid) {
		// TODO 自动生成的方法存根
		return studentdao.queryStudentBySid(sid);
	}

	@Override
	public License queryLicenseBySid(String sid) {
		// TODO 自动生成的方法存根
		return studentdao.queryLicenseBySid(sid);
	}

	
	@Transactional
	@Override
	public boolean updateStudent( Student student, HttpServletRequest request) {
		if(student.getSphotofile()!=null) {
			String newFileName = "";
			String fileName = student.getSphotofile().getOriginalFilename(); 
			
			//选择文件
			if(fileName.length() > 0){
				String realpath = request.getServletContext().getRealPath("photos");
				//实现文件上传
				String fileType = fileName.substring(fileName.lastIndexOf('.'));
				//用身份证号作为照片文件名
				newFileName = student.getSid() + fileType;
				student.setSphoto(newFileName);
				File targetFile = new File(realpath, newFileName); 
				if(!targetFile.exists()){  
		            targetFile.mkdirs();  
		        } 
				//上传覆盖原来的头像
		        try {   
		        	student.getSphotofile().transferTo(targetFile);
		        } catch (Exception e) {  
		            e.printStackTrace();  
		        }  
			}
		}
		if(studentdao.updateStudent(student)>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<Student> searchStudent(int pageNum, int pageSize, String property, String keyword) {
		PageHelper.startPage(pageNum,pageSize);
		return studentdao.searchStudent(property, keyword);
	}


	@Override
	public List<Student> queryStudentlistByEid(int pageNum, int pageSize, String eid) {
		PageHelper.startPage(pageNum,pageSize);
		return studentdao.queryStudentlistByEid(eid);
	}

	@Override
	public List<Student> searchStudentByEid(int pageNum, int pageSize, String property, String keyword, String eid) {
		PageHelper.startPage(pageNum,pageSize);
		return studentdao.searchStudentByEid(property, keyword,eid);
	}



}
