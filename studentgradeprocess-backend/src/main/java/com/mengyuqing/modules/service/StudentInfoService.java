package com.mengyuqing.modules.service;

import com.mengyuqing.modules.dto.StudentInfoDto;
import com.mengyuqing.modules.util.page.PageData;

import java.util.Map;

/**
 * @author Yuqing
 * @date 2021/11/13 16:27
 */
public interface StudentInfoService {
    PageData<StudentInfoDto> page(Map<String, Object> params);

    StudentInfoDto get(Long id);
}
