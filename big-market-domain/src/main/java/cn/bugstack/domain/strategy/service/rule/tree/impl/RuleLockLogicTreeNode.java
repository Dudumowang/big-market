package cn.bugstack.domain.strategy.service.rule.tree.impl;

import cn.bugstack.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import cn.bugstack.domain.strategy.model.valobj.StrategyAwardStockKeyVO;
import cn.bugstack.domain.strategy.repository.IStrategyRepository;
import cn.bugstack.domain.strategy.service.armory.IStrategyDispatch;
import cn.bugstack.domain.strategy.service.rule.tree.ILogicTreeNode;
import cn.bugstack.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @Author: 杨文彬
 * @Description: 次数锁节点
 * @CreateTime: 2025-06-05
 */

@Slf4j
@Component("rule_lock")
public class RuleLockLogicTreeNode implements ILogicTreeNode {

	private Long userRaffleCount = 10L;

	@Override
	public DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId,String ruleValue) {

		log.info("规则过滤-次数锁 userId:{}, strategyId:{}, awardId:{}, ruleValue:{}", userId, strategyId, awardId, ruleValue);

		long raffleCount = 0L;
		try{
			raffleCount = Long.parseLong(ruleValue);
		} catch(Exception e){
			throw new RuntimeException("规则过滤-次数锁参数错误, ruleValue: " + ruleValue, e);
		}

		//抽奖次数大于规则值，放行
		if(userRaffleCount>= raffleCount){
			return DefaultTreeFactory.TreeActionEntity.builder()
					.ruleLogicCheckType(RuleLogicCheckTypeVO.ALLOW)
					.build();
		}

		return DefaultTreeFactory.TreeActionEntity.builder()
				.ruleLogicCheckType(RuleLogicCheckTypeVO.TAKE_OVER)
				.build();
	}

}
