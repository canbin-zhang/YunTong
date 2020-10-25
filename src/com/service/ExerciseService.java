package com.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.pojo.Exercise;

public interface ExerciseService {
	public Exercise queryExerciseById(String Id);
	public boolean updateExercise(Exercise exercise);
	public Exercise addExercise(Exercise exercise,Date exercise_date,HttpServletRequest request);
	public List<Exercise> queryAllExercise(int pageNum,int pageSize);
    public List<Exercise> queryExerciseByEid(int pageNum, int pageSize,String eid);
}
