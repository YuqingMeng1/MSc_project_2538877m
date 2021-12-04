package com.mengyuqing.modules.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
@Builder
public class StudentInfo {

	private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;
    private String lastName;
    private String name;
    private String studentId;
    private String fileName;
    private String overallGrade;

    @TableField(fill = FieldFill.INSERT)
    private String createTime;
    private String updateTime;
}
