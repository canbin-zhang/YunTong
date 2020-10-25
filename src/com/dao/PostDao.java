package com.dao;

import com.pojo.Employee;
import com.pojo.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PostDao {
    public List<Post> queryAllPost();
    public Post queryPostByPid(String pid);
    public int addPost(Post post);
    public int updatePostSalary(@Param("pid")String pid,@Param("salary")String salary);
}
