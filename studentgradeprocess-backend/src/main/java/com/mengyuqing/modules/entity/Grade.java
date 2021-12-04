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
     *  related with studentIof table
     */
     private Long studentInfoId;
    /**
     * Code  related with dicData table
     */
     private String subjectName;
    /**
     *  related with source table
     */
     private BigDecimal source;
    /**
     *
     */
     @TableField(fill = FieldFill.INSERT)
     private Date createTime;
    /**
     *
     */
     private Date updateTime;

     @TableField(exist = false)
     private String level;

     @TableField(exist = false)
     private String credit;
}
