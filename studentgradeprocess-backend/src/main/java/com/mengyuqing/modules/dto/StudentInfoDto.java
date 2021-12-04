package com.mengyuqing.modules.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Yuqing
 * @date 2021/11/13 16:28
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class StudentInfoDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String name;
    private String studentId;
    private String fileName;
    private String overallGrade;

    private String createTime;
    private String updateTime;
}
