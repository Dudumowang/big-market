package cn.bugstack.domain.strategy.service.rule.tree.impl;

import cn.bugstack.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import cn.bugstack.domain.strategy.service.rule.tree.ILogicTreeNode;
import cn.bugstack.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: 杨文彬
 * @Description: 兜底奖励节点
 * @CreateTime: 2025-06-05
 */
@Slf4j
@Component("rule_luck_award")
public class RuleLuckAwardLogicTreeNode implements ILogicTreeNode {

	@Override
	public DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId) {
		return DefaultTreeFactory.TreeActionEntity.builder()
				.ruleLogicCheckType(RuleLogicCheckTypeVO.TAKE_OVER)
				.strategyAwardData(DefaultTreeFactory.StrategyAwardVO.builder()
						.awardId(101)
						.awardRuleValue("1,100")
						.build())
				.build();
	}

}
