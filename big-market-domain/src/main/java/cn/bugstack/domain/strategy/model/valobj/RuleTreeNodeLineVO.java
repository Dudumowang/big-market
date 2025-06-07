package cn.bugstack.domain.strategy.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 杨文彬
 * @Description: 规则树节点指向对象，from->to 的关系
 * @CreateTime: 2025-06-05
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleTreeNodeLineVO {
	/** 规则树ID */
    private String treeId;
    /** 规则Key节点From */
    private String ruleNodeFrom;
    /** 规则Key节点To */
    private String ruleNodeTo;
    /** 限定类型 1:=;2:>;3:<;4:>=;5:<=;6:enum[枚举范围] */
	private RuleLimitTypeVO ruleLimitType;
    /** 限定值 */
    private RuleLogicCheckTypeVO ruleLimitValue;

}
