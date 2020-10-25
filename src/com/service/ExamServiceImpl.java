package com.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ExamDao;
import com.dao.StudentDao;
import com.github.pagehelper.PageHelper;
import com.pojo.Exam;
@Service
public class ExamServiceImpl implements ExamService{
	@Autowired
	private ExamDao examdao;
	
	@Autowired
	private StudentDao studentDao;
	
	
	@Override
	public boolean updateExam(Exam exam) {
		//判断是否通过考试，通过的话更新学生科目状态
		String exam_subject="";
		if(exam.getPass()==1) {
			   switch (exam.getExam_subject()) {
			case "科目一":
				exam_subject="科目二";
				break;
			case "科目二":
				exam_subject="科目三";
				break;
			case "科目三":
				exam_subject="科目四";
				break;
			case "科目四":
				exam_subject="已取证";
				break;
			default:
				break;
			}
		   if(examdao.updateExam(exam)>0&&studentDao.updateStudentSubject(exam.getSid(), exam_subject)>0) {
				return true;
			}else {
				return false;
			}
		   //如果本来是没通过记成通过的话，则返回科目状态
		}else {
			
		if(examdao.updateExam(exam)>0&&studentDao.updateStudentSubject(exam.getSid(),exam.getExam_subject())>0){
			return true;
		}else {
			return false;
		}
		}
	}
		
		
		
	public Exam addExam(Exam exam) {
		//判断是否通过考试，通过的话更新学生科目状态
		String exam_subject="";
		if(exam.getPass()==1) {
			   switch (exam.getExam_subject()) {
			case "科目一":
				exam_subject="科目二";
				break;
			case "科目二":
				exam_subject="科目三";
				break;
			case "科目三":
				exam_subject="科目四";
				break;
			case "科目四":
				exam_subject="已取证";
				break;
			default:
				break;
			}
		   if(examdao.addExam(exam)>0&&studentDao.updateStudentSubject(exam.getSid(), exam_subject)>0) {
				return exam;
			}else {
				return null;
			}
		}else if(examdao.addExam(exam)>0) {
			return exam;
		}else {
			return null;
		}
	}

	@Override
	public List<Exam> queryAllExam(int pageNum, int pageSize) {
		// TODO 自动生成的方法存根
		return examdao.queryAllExam();
	}

	public Exam queryExamById(String id) {
		// TODO 自动生成的方法存根
		return examdao.queryExamById(id);
	}

	@Override
	public List<Exam> queryExamByEid(int pageNum, int pageSize, String eid) {
		PageHelper.startPage(pageNum,pageSize);
		return examdao.queryExamByEid(eid);
	}


}
