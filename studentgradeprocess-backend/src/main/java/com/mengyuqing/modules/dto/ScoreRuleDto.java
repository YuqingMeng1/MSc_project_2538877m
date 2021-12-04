package com.mengyuqing.modules.dto;

import lombok.Data;


@Data
public class ScoreRuleDto {
    private Long openScore;
    private Long closeScore;
    private Long rate;
    private String grade;
}
