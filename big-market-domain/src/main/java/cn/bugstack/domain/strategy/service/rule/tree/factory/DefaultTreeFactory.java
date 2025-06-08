package cn.bugstack.domain.strategy.service.rule.tree.factory;

import cn.bugstack.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import cn.bugstack.domain.strategy.model.valobj.RuleTreeVO;
import cn.bugstack.domain.strategy.service.rule.tree.ILogicTreeNode;
import cn.bugstack.domain.strategy.service.rule.tree.factory.engine.IDecisionTreeEngine;
import cn.bugstack.domain.strategy.service.rule.tree.factory.engine.impl.DecisionTreeEngine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: 杨文彬
 * @Description: 规则树工厂
 * @CreateTime: 2025-06-05
 */


@Service
public class DefaultTreeFactory {

	private final Map<String, ILogicTreeNode> logicTreeNodeGroup;

	public DefaultTreeFactory(Map<String, ILogicTreeNode> logicTreeNodeGroup) {
		this.logicTreeNodeGroup = logicTreeNodeGroup;
	}

	public IDecisionTreeEngine openLogicTree(RuleTreeVO ruleTreeVO){
       		return new DecisionTreeEngine(logicTreeNodeGroup, ruleTreeVO);
	}


	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class TreeActionEntity {
		private RuleLogicCheckTypeVO ruleLogicCheckType;
		private StrategyAwardVO strategyAwardVO;
	}


	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class StrategyAwardVO {
		private Integer awardId;
		private String awardRuleValue;
	}



}
