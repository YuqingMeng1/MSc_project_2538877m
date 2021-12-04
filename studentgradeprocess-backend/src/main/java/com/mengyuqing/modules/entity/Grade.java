package com.mengyuqing.modules.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
public class Grade {
	private static final long serialVersionUID = 1L;

	private Long id;

    /**
     * 关联学生信息表 related with studentIof table
     */
     private Long studentInfoId;
    /**
     * 关联字典表Code  related with dicData table
     */
     private String subjectName;
    /**
     * 分数 related with source table
     */
     private BigDecimal source;
    /**
     * 创建时间
     */
     @TableField(fill = FieldFill.INSERT)
     private Date createTime;
    /**
     * 更新时间
     */
     private Date updateTime;

     @TableField(exist = false)
     private String level;

     @TableField(exist = false)
     private String credit;
}
