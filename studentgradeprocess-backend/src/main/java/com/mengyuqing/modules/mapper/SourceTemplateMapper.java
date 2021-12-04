package com.mengyuqing.modules.mapper;

import com.mengyuqing.modules.entity.SourceTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Yuqing
 * @date 2021/11/1 22:57
 */
@Repository
public interface SourceTemplateMapper {
    SourceTemplate selectRule();

}
