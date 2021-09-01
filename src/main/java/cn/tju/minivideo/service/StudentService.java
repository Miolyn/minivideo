package cn.tju.minivideo.service;

import cn.tju.minivideo.entity.Student;
import com.github.pagehelper.PageInfo;

public interface StudentService{



    PageInfo<Student> queryByPage(Integer page, Integer pageSize);
}
