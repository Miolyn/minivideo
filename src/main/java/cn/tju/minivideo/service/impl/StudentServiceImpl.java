package cn.tju.minivideo.service.impl;

import cn.tju.minivideo.entity.Student;
import cn.tju.minivideo.dao.StudentMapper;
import cn.tju.minivideo.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;



    @Override
    public PageInfo<Student> queryByPage(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        PageInfo<Student> pageInfo = new PageInfo<>(studentMapper.queryStudentByPage());
        return pageInfo;
    }
}
