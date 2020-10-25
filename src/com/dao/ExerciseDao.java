package com.dao;

import java.util.List;

import com.pojo.Exercise;

public interface ExerciseDao {
	public int addExercise(Exercise exercise);
	public int updateExercise(Exercise exercise);
	public List<Exercise> queryExerciseByEid(String eid);
	public List<Exercise> queryAllExercise();
	public Exercise queryExerciseById(String id);
}
