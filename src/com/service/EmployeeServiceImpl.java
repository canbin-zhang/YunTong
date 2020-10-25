package com.service;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dao.EmployeelistDao;
import com.github.pagehelper.PageHelper;
import com.pojo.Employeelist;
import com.pojo.Post;
import com.utils.MyUtils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.DimissionDao;
import com.dao.EmployeeDao;
import com.pojo.Dimission;
import com.pojo.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeDao employeedao;
	@Autowired
    private JavaMailSender javaMailSender;//在spring中配置的邮件发送的bean
	@Autowired
    private RedisTemplate redisTemplate;  //注入redis对发送的验证码进行存储
	@Autowired
	private EmployeelistDao employeelistdao;
	@Autowired
	private DimissionDao dimissiondao;

	
	@Transactional
	@Override
	public Employee addEmployee(Employee employee, Date em_date,HttpServletRequest request) {
		
		String newFileName = "";
		String fileName = employee.getEphotofile().getOriginalFilename(); 
		//拿到session中的操作员id
		Subject subject= SecurityUtils.getSubject();
		String operator_id= (String) subject.getPrincipal();
		
		//拿到随机字符串作为首次注册的密码
		String password=MyUtils.randomString();
		employee.setEpassword(password);
		
		//封装一个招聘记录对象
		Employeelist employeelist=new Employeelist();
		employeelist.setOperator_id(operator_id);
		employeelist.setEid(employee.getEid());
		employeelist.setEm_date(em_date);

		//选择文件
		if(fileName.length() > 0){
			String realpath = request.getServletContext().getRealPath("photos");
			//实现文件上传
			String fileType = fileName.substring(fileName.lastIndexOf('.'));
			//用身份证号作为照片文件名
			newFileName = employee.getEid() + fileType;
			employee.setEphoto(newFileName);
			File targetFile = new File(realpath, newFileName); 
			if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	        } 
			//上传
	        try {   
	        	employee.getEphotofile().transferTo(targetFile);
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
		}
		if(employeedao.addEmployee(employee)>0&&employeelistdao.addEmploylist(employeelist)>0) {
			return employee;
		}else {
			return null;
		}
		
	}

	@Override
	public String sendEmail(String email) {
		if(employeedao.queryEmployeeByEmail(email)==null) {
    		return "该邮箱未注册";
    	}
        MimeMessage mMessage=javaMailSender.createMimeMessage();//创建邮件对象
        MimeMessageHelper mMessageHelper;
        Properties prop = new Properties();
        String from;
        try {
            //从配置文件中拿到发件人邮箱地址
            prop.load(this.getClass().getResourceAsStream("/mail.properties"));
            from = prop.get("mail.smtp.username")+"";
            mMessageHelper=new MimeMessageHelper(mMessage,true);
            mMessageHelper.setFrom(from);//发件人邮箱
            mMessageHelper.setTo(email);//收件人邮箱
            mMessageHelper.setSubject("验证");
            String validatecode=new Random().nextInt(9999)+"";  //随机生成验证码
            mMessageHelper.setText(validatecode);
            javaMailSender.send(mMessage);//发送邮件
            redisTemplate.opsForValue().set(email, validatecode,200,TimeUnit.SECONDS);//将验证码存在redis中
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "验证码已发送";
    }

	@Override
	public int resetPassword(String email, String password) {
		return employeedao.resetPassword(email, password);
	}

	@Override
	public Employee queryEmployeeByEid(String eid) {
		return employeedao.queryEmployeeByEid(eid);
	}

	@Override
	public Post queryPostByEid(String eid) {
		// TODO Auto-generated method stub
		return employeedao.queryPostByEid(eid);
	}

	@Override
	public List<Employee> queryAllEmployee(int pageNum,int pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		return employeedao.queryAllEmployee();
	}

	@Transactional
	@Override
	public boolean deleteEmployee(String eid, Date dm_date, String reason) {
		//拿到session中的操作员id
		Subject subject= SecurityUtils.getSubject();
		String operator_id= (String) subject.getPrincipal();
		
		//封装一个离职记录对象
		Dimission dimission=new Dimission();
		dimission.setEid(eid);
		dimission.setOperator_id(operator_id);
		dimission.setDm_date(dm_date);
		dimission.setReason(reason);
		
		if(employeedao.deleteEmployeeByEid(eid)>0&&dimissiondao.addDimission(dimission)>0) {
			return true;
		}else {
			return false;
		}
	}

	@Transactional
	@Override
	public boolean updateEmployee(Employee employee, HttpServletRequest request) {
		
		if(employee.getEphotofile()!=null) {
			String newFileName = "";
			String fileName = employee.getEphotofile().getOriginalFilename(); 
			
			//选择文件
			if(fileName.length() > 0){
				String realpath = request.getServletContext().getRealPath("photos");
				//实现文件上传
				String fileType = fileName.substring(fileName.lastIndexOf('.'));
				//用身份证号作为照片文件名
				newFileName = employee.getEid() + fileType;
				employee.setEphoto(newFileName);
				File targetFile = new File(realpath, newFileName); 
				if(!targetFile.exists()){  
		            targetFile.mkdirs();  
		        } 
				//上传覆盖原来的头像
		        try {   
		        	employee.getEphotofile().transferTo(targetFile);
		        } catch (Exception e) {  
		            e.printStackTrace();  
		        }  
			}
		}
		if(employeedao.updateEmployee(employee)>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<Employee> searchEmployee(int pageNum, int pageSize, String property, String keyword) {
		PageHelper.startPage(pageNum,pageSize);
		return employeedao.searchEmployee(property, keyword);
	}

	@Override
	public List<Employee> queryEmployeeByPid(String pid,int pageNum,int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return employeedao.queryEmployeeByPid(pid);
	}

	@Override
	public List<Employee> queryEmployeeByPostName(String name) {
		// TODO 自动生成的方法存根
		return employeedao.queryEmployeeByPostName(name);
	}

	@Override
	public int resetPasswordByEid(String eid, String password) {
		// TODO Auto-generated method stub
		return employeedao.resetPasswordByEid(eid, password);
	}

	@Override
	public int resetPasswordByEmail(String email, String password) {
		
		return employeedao.resetPassword(email, password);
	}


}
	
