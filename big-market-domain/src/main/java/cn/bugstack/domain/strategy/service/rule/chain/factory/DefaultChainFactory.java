package cn.bugstack.domain.strategy.service.rule.chain.factory;

import cn.bugstack.domain.strategy.model.entity.StrategyEntity;
import cn.bugstack.domain.strategy.repository.IStrategyRepository;
import cn.bugstack.domain.strategy.service.rule.chain.ILogicChain;
import lombok.*;
import org.checkerframework.checker.units.qual.N;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: 杨文彬
 * @Description: 工厂
 * @CreateTime: 2025-06-02
 */
@Service
public class DefaultChainFactory {

	private final Map<String, ILogicChain> logicChainGroup;

	private final IStrategyRepository repository;

	public DefaultChainFactory(IStrategyRepository repository, Map<String, ILogicChain> logicChainGroup) {
		this.repository = repository;
		this.logicChainGroup = logicChainGroup;
	}

	public ILogicChain openLogicChain(Long strategyId) {

		StrategyEntity strategy = repository.queryStrategyEntityByStrategyId(strategyId);
		String[] ruleModels = strategy.ruleModels();

		if(null == ruleModels || ruleModels.length == 0) {
			return logicChainGroup.get("default");
		}

		ILogicChain logicChain = logicChainGroup.get(ruleModels[0]);
		ILogicChain current = logicChain;
		for(int i =1;i<ruleModels.length;i++) {
			ILogicChain nextChain = logicChainGroup.get(ruleModels[i]);
			current = current.appendNext(nextChain);

		}

		current.appendNext(logicChainGroup.get("default"));

		return logicChain;



	}


	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class StrategyAwardVO {
		private Integer awardId;
		private String logicModel;
	}

	@Getter
	@AllArgsConstructor
	public enum LogicModel{
		RULE_DEFAULT("rule_default", "默认抽奖"),
		RULE_BLACKLIST("rule_blacklist", "黑名单抽奖"),
		RULE_WEIGHT("rule_weight", "权重规则"),
		;
		private final String code;
		private final String info;
	}
}
