package cn.bugstack.domain.strategy.service.rule.chain.impl;

import cn.bugstack.domain.strategy.service.armory.IStrategyArmory;
import cn.bugstack.domain.strategy.service.armory.IStrategyDispatch;
import cn.bugstack.domain.strategy.service.rule.chain.AbstractLogicChain;
import lombok.extern.apachecommons.CommonsLog;
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
public class DefaultLogiChain extends AbstractLogicChain {

	@Resource
	protected IStrategyDispatch strategyDispatch;




	@Override
	public Integer logic(String userId, Long strategyId) {
		Integer awardId = strategyDispatch.getRandomAwardId(strategyId);
		log.info("抽奖责任链-默认处理 userId: {} strategyId: {} ruleModel: {} awardId: {}", userId, strategyId, ruleModel(), awardId);
		return awardId;
	}

	@Override
	protected String ruleModel() {
		return "default";
	}
}
