package cn.tju.minivideo.dao;

import cn.tju.minivideo.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Mapper
@Repository
public interface StudentMapper extends BaseMapper<Student> {



    List<Student> queryStudentByPage();

}
