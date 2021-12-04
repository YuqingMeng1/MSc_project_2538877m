package com.mengyuqing.modules.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mengyuqing.modules.entity.StudentSubjectLevel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yuqing
 * @date 2021/11/3 22:30
 */
@Repository
public interface StudentSubjectLevelMapper extends BaseMapper<StudentSubjectLevel> {
    Integer insertList(@Param("list") List<StudentSubjectLevel> studentSubjectLevelList);
}
