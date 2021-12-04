package com.mengyuqing.modules.mapper;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yuqing
 * @date 2021/11/1 22:39
 */
@Repository
public interface DicDataMapper {
    List<String> selectDicValueByCodeNotNull(String code);

}
