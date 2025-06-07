package cn.bugstack.domain.strategy.service.rule.chain.impl;

import cn.bugstack.domain.strategy.repository.IStrategyRepository;
import cn.bugstack.domain.strategy.service.armory.IStrategyDispatch;
import cn.bugstack.domain.strategy.service.rule.chain.AbstractLogicChain;
import cn.bugstack.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import cn.bugstack.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author: 杨文彬
 * @Description: 权重
 * @CreateTime: 2025-06-02
 */

@Slf4j
@Component("rule_weight")
public class RuleWeightLogicChain extends AbstractLogicChain {

	@Resource
	private IStrategyRepository repository;

	@Resource
	private IStrategyDispatch strategyDispatch;

	public Long userScore = 0L;


	@Override
	public DefaultChainFactory.StrategyAwardVO logic(String userId, Long strategyId) {
		log.info("抽奖责任链-权重开始 userId: {} strategyId: {} ruleModel: {}", userId, strategyId, ruleModel());


		// 1. 获取用户的分数
		String ruleValue = repository.queryStrategyRuleValue(strategyId, ruleModel());
		Map<Long,String> analyticalValueGroup = getAnalyticalValue(ruleValue);
		if(null == analyticalValueGroup||analyticalValueGroup.isEmpty()) return null;


		// 2. 转换keys值，并且默认排序
		List<Long> analyticalSortedKeys = new ArrayList<>(analyticalValueGroup.keySet());
		Collections.sort(analyticalSortedKeys);

		// 3. 找出最小符合的值
		Long nextValue = analyticalSortedKeys.stream()
				.sorted(Comparator.reverseOrder())
				.filter(analyticalSortedKeyValue -> userScore >= analyticalSortedKeyValue)
				.findFirst()
				.orElse(null);


		// 4. 权重抽奖
		if (null != nextValue) {
			Integer awardId = strategyDispatch.getRandomAwardId(strategyId, analyticalValueGroup.get(nextValue));
			log.info("抽奖责任链-权重接管 userId: {} strategyId: {} ruleModel: {} awardId: {}", userId, strategyId, ruleModel(), awardId);
			return DefaultChainFactory.StrategyAwardVO.builder()
					.awardId(awardId)
					.logicModel(ruleModel())
					.build();
		}

		// 5. 过滤其他责任链
		log.info("抽奖责任链-权重放行 userId: {} strategyId: {} ruleModel: {}", userId, strategyId, ruleModel());
		return next().logic(userId, strategyId);



	}

	private Map<Long, String> getAnalyticalValue(String ruleValue) {
		String[] ruleValueGroups = ruleValue.split(Constants.SPACE);
		Map<Long, String> ruleValueMap = new HashMap<>();
		for (String ruleValueKey : ruleValueGroups) {
			// 检查输入是否为空
			if (ruleValueKey == null || ruleValueKey.isEmpty()) {
				return ruleValueMap;
			}
			// 分割字符串以获取键和值
			String[] parts = ruleValueKey.split(Constants.COLON);
			if (parts.length != 2) {
				throw new IllegalArgumentException("rule_weight rule_rule invalid input format" + ruleValueKey);
			}
			ruleValueMap.put(Long.parseLong(parts[0]), ruleValueKey);
		}
		return ruleValueMap;
	}


	@Override
	protected String ruleModel() {
		return DefaultChainFactory.LogicModel.RULE_WEIGHT.getCode();
	}
}
