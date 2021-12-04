package com.mengyuqing.modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mengyuqing.modules.dto.GradeDto;
import com.mengyuqing.modules.dto.StudentInfoDto;
import com.mengyuqing.modules.entity.Grade;
import com.mengyuqing.modules.entity.StudentInfo;
import com.mengyuqing.modules.mapper.StudentInfoMapper;
import com.mengyuqing.modules.service.StudentInfoService;
import com.mengyuqing.modules.util.CommonMethod;
import com.mengyuqing.modules.util.ConvertUtils;
import com.mengyuqing.modules.util.page.PageData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Yuqing
 * @date 2021/11/13 16:27
 */
@Service
public class StudentInfoServiceImpl implements StudentInfoService {

    private final StudentInfoMapper studentInfoMapper;

    public StudentInfoServiceImpl(StudentInfoMapper studentInfoMapper) {
        this.studentInfoMapper = studentInfoMapper;
    }

    private QueryWrapper<StudentInfo> getWrapper(Map<String, Object> params) {

        String lastName = (String) params.get("lastName");
        String studentId = (String) params.get("studentId");

        QueryWrapper<StudentInfo> wrapper = new QueryWrapper<>();

        wrapper.like(StringUtils.isNotBlank(lastName), "last_name", lastName);
        wrapper.like(StringUtils.isNotBlank(studentId), "student_id", studentId);
        wrapper.orderByDesc(true, "id");

        return wrapper;
    }


    @Override
    public PageData<StudentInfoDto> page(Map<String, Object> params) {
        IPage<StudentInfo> page = studentInfoMapper.selectPage(
                CommonMethod.getPage(params, null, false),
                getWrapper(params)
        );
        return CommonMethod.getPageData(page, StudentInfoDto.class);
    }

    @Override
    public StudentInfoDto get(Long id) {
        return ConvertUtils.sourceToTarget(studentInfoMapper.selectById(id), StudentInfoDto.class);
    }
}
