package cn.bugstack.domain.strategy.service.rule.chain.impl;

import cn.bugstack.domain.strategy.service.armory.IStrategyDispatch;
import cn.bugstack.domain.strategy.service.rule.chain.AbstractLogicChain;
import cn.bugstack.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: 杨文彬
 * @Description: 兜底
 * @CreateTime: 2025-06-02
 */

@Slf4j
@Component("default")
public class DefaultLogicChain extends AbstractLogicChain {

	@Resource
	protected IStrategyDispatch strategyDispatch;




	@Override
	public DefaultChainFactory.StrategyAwardVO logic(String userId, Long strategyId) {
		Integer awardId = strategyDispatch.getRandomAwardId(strategyId);
		log.info("抽奖责任链-默认处理 userId: {} strategyId: {} ruleModel: {} awardId: {}", userId, strategyId, ruleModel(), awardId);
		return DefaultChainFactory.StrategyAwardVO.builder()
				.awardId(awardId)
				.logicModel(ruleModel())
				.build();
	}

	@Override
	protected String ruleModel() {
		return DefaultChainFactory.LogicModel.RULE_DEFAULT.getCode();
	}
}
