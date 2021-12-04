package com.mengyuqing.modules.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Yuqing
 * @date 2021/10/24 17:22
 */
@Data
/**
 * Data Transfer
 */

public class GradeDto {

    private Long id;

    private Long studentInfoId;

    private Long dicDataId;

    private BigDecimal source;

    private Date createTime;

    private Date updateTime;

    private String subjectName;

    private String gradeName;

    private String credit;
}
