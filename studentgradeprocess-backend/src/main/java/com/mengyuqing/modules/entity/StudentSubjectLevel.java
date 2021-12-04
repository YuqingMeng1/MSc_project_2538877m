package com.mengyuqing.modules.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
public class StudentSubjectLevel {
	private static final long serialVersionUID = 1L;

    private Long id;

    private Long gradeId;

    private String dicDataCode;

    private String credit;

    private Date createTime;

    private Date updateTime;
}
