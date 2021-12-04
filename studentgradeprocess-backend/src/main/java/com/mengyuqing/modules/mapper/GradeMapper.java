package com.mengyuqing.modules.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mengyuqing.modules.entity.Grade;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yuqing
 * @date 2021/11/3 22:13
 */
@Repository
public interface GradeMapper extends BaseMapper<Grade> {
    Integer insertList(@Param("list") List<Grade> gradeList);
}
