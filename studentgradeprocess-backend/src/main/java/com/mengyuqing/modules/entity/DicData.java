package com.mengyuqing.modules.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
public class DicData {

     private Long id;

     private String dictValue;

     private String code;

     private String createTime;

     private String updateTime;
}
