package cn.bugstack.domain.strategy.service.rule.tree.impl;

import cn.bugstack.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import cn.bugstack.domain.strategy.model.valobj.StrategyAwardStockKeyVO;
import cn.bugstack.domain.strategy.repository.IStrategyRepository;
import cn.bugstack.domain.strategy.service.armory.IStrategyDispatch;
import cn.bugstack.domain.strategy.service.rule.tree.ILogicTreeNode;
import cn.bugstack.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: 杨文彬
 * @Description:
 * @CreateTime: 2025-06-05
 */
@Slf4j
@Component("rule_stock")
public class RuleStockLogicTreeNode implements ILogicTreeNode {

	@Resource
	private IStrategyRepository strategyRepository;

	@Resource
	private IStrategyDispatch strategyDispatch;

	@Override
	public DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId,String ruleValue) {


		log.info("规则过滤-库存扣减 userId:{}, strategyId:{}, awardId:{}", userId, strategyId, awardId);

		//扣减库存
		Boolean status = strategyDispatch.subtractionAwardStock(strategyId, awardId);

		if(status){
			strategyRepository.awardStockConsumeSendQueue(StrategyAwardStockKeyVO.builder()
					.strategyId(strategyId)
					.awardId(awardId)
					.build());

			return DefaultTreeFactory.TreeActionEntity.builder()
					.ruleLogicCheckType(RuleLogicCheckTypeVO.TAKE_OVER)
					.strategyAwardVO(DefaultTreeFactory.StrategyAwardVO.builder()
							.awardId(awardId)
							.awardRuleValue("")
							.build())
					.build();
		}
		return DefaultTreeFactory.TreeActionEntity.builder()
				.ruleLogicCheckType(RuleLogicCheckTypeVO.ALLOW)
				.build();
	}

}
