package cn.bugstack.domain.strategy.service.rule.tree.impl;

import cn.bugstack.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import cn.bugstack.domain.strategy.service.rule.tree.ILogicTreeNode;
import cn.bugstack.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import cn.bugstack.types.common.Constants;
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
	public DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId,String ruleValue) {

		log.info("规则过滤-兜底奖励 userId:{}, strategyId:{}, awardId:{}, ruleValue:{}", userId, strategyId, awardId, ruleValue);
		String[] split = ruleValue.split(Constants.COLON);
		if(split.length==0){
			log.error("规则过滤，兜底未配置 userId:{} awardId:{} ruleValue:{}", userId, awardId, ruleValue);
			throw new RuntimeException("兜底奖品未配置" + ruleValue);
		}
		//兜底奖励配置
		Integer luckAwardId = Integer.parseInt(split[0]);
		String awardRuleValue = split.length > 1 ? split[1] : null;

		log.info("规则过滤-兜底奖励配置 userId:{}, strategyId:{}, awardId:{}, luckAwardId:{}, awardRuleValue:{}",
				userId, strategyId, awardId, luckAwardId, awardRuleValue);

		return DefaultTreeFactory.TreeActionEntity.builder()
				.ruleLogicCheckType(RuleLogicCheckTypeVO.TAKE_OVER)
				.strategyAwardVO(DefaultTreeFactory.StrategyAwardVO.builder()
						.awardId(luckAwardId)
						.awardRuleValue(awardRuleValue)
						.build())
				.build();




	}

}
