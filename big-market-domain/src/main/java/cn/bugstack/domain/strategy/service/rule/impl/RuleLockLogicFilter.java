package cn.bugstack.domain.strategy.service.rule.impl;

import cn.bugstack.domain.strategy.model.entity.RuleActionEntity;
import cn.bugstack.domain.strategy.model.entity.RuleMatterEntity;
import cn.bugstack.domain.strategy.model.vo.RuleLogicCheckTypeVO;
import cn.bugstack.domain.strategy.repository.IStrategyRepository;
import cn.bugstack.domain.strategy.service.annotation.LogicStrategy;
import cn.bugstack.domain.strategy.service.rule.ILogicFilter;
import cn.bugstack.domain.strategy.service.rule.factory.DefaultLogicFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: 杨文彬
 * @Description: 用户抽奖N次后对应奖品可解锁抽奖
 * @CreateTime: 2025-05-31
 */

@Slf4j
@Component
@LogicStrategy(logicMode = DefaultLogicFactory.LogicModel.RULE_LOCK)
public class RuleLockLogicFilter implements ILogicFilter<RuleActionEntity.RaffleCenterEntity> {

	@Resource
	private IStrategyRepository repository;

	private Long userRaffleCount = 0L; // 模拟用户抽奖次数，实际应用中应从用户数据中获取

	@Override
	public RuleActionEntity<RuleActionEntity.RaffleCenterEntity> filter(RuleMatterEntity ruleMatterEntity) {
        log.info("规则过滤-次数锁 userId:{} strategyId:{} ruleModel:{}",
				ruleMatterEntity.getUserId(), ruleMatterEntity.getStrategyId(), ruleMatterEntity.getRuleModel());

		String  ruleValue = repository.queryStrategyRuleValue(ruleMatterEntity.getStrategyId(),ruleMatterEntity.getAwardId(),ruleMatterEntity.getRuleModel());

		long raffleCount = Long.parseLong(ruleValue);

		if (userRaffleCount >= raffleCount) {
			// 用户抽奖次数已达到或超过锁定次数，允许抽奖
			return RuleActionEntity.<RuleActionEntity.RaffleCenterEntity>builder()
							.code(RuleLogicCheckTypeVO.ALLOW.getCode())
							.info(RuleLogicCheckTypeVO.ALLOW.getInfo())
							.build();
		}

		return RuleActionEntity.<RuleActionEntity.RaffleCenterEntity>builder()
				.code(RuleLogicCheckTypeVO.TAKE_OVER.getCode())
				.info(RuleLogicCheckTypeVO.TAKE_OVER.getInfo())
				.build();
    }
}
