package cn.bugstack.domain.strategy.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: 杨文彬
 * @Description: 规则限定枚举值
 * @CreateTime: 2025-06-05
 */

@Getter
@AllArgsConstructor
public enum RuleLimitTypeVO {

	EQUAL(1, "等于"),
	GT(2, "大于"),
	LT(3, "小于"),
	GE(4, "大于等于"),
	LE(5, "小于等于"),
	ENUM(6, "枚举");

	private final Integer id;
	private final String info;
}
