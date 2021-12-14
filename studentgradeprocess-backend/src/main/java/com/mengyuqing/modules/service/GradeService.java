package com.mengyuqing.modules.service;

import com.mengyuqing.modules.dto.GradeDto;
import com.mengyuqing.modules.util.page.PageData;

import java.util.List;
import java.util.Map;

/**
 * @author Yuqing
 * @date 2021/10/25 10:25
 */
public interface GradeService {
    void importCSV(List<String> grade, String fileName);

    PageData<GradeDto> page(Map<String, Object> params);

}
