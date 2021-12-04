package com.mengyuqing.modules.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
public class SourceTemplate {
	private static final long serialVersionUID = 1L;

	private Long id;

    private String rule;
}
