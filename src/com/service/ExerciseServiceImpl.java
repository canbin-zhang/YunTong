package com.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ExerciseDao;
import com.github.pagehelper.PageHelper;
import com.pojo.Exercise;
@Service
public class ExerciseServiceImpl implements ExerciseService{
	@Autowired
	private ExerciseDao exercisedao;
	
	@Override
	public boolean updateExercise(Exercise exercise) {
		if(exercisedao.updateExercise(exercise)>0) {
			return true;
		}else {
			return false;
		}
	}

	@Transactional
	@Override
	public Exercise addExercise(Exercise exercise, Date exercise_date, HttpServletRequest request) {
		if(exercisedao.addExercise(exercise)>0) {
			return exercise;
		}else {
			return null;
		}
	}

	@Override
	public List<Exercise> queryAllExercise(int pageNum, int pageSize) {
		// TODO 自动生成的方法存根
		return exercisedao.queryAllExercise();
	}

	public Exercise queryExerciseById(String id) {
		// TODO 自动生成的方法存根
		return exercisedao.queryExerciseById(id);
	}

	@Override
	public List<Exercise> queryExerciseByEid(int pageNum, int pageSize, String eid) {
		PageHelper.startPage(pageNum,pageSize);
		return exercisedao.queryExerciseByEid(eid);
	}


}
