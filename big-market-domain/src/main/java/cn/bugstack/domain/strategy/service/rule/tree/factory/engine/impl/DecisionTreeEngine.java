package cn.bugstack.domain.strategy.service.rule.tree.factory.engine.impl;



import cn.bugstack.domain.strategy.model.valobj.RuleLogicCheckTypeVO;
import cn.bugstack.domain.strategy.model.valobj.RuleTreeNodeLineVO;
import cn.bugstack.domain.strategy.model.valobj.RuleTreeNodeVO;
import cn.bugstack.domain.strategy.model.valobj.RuleTreeVO;
import cn.bugstack.domain.strategy.service.rule.tree.ILogicTreeNode;
import cn.bugstack.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;
import cn.bugstack.domain.strategy.service.rule.tree.factory.engine.IDecisionTreeEngine;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;


/**
 * @Author: 杨文彬
 * @Description: 决策树引擎
 * @CreateTime: 2025-06-05
 */
@Slf4j
public class DecisionTreeEngine implements IDecisionTreeEngine {

	private final Map<String, ILogicTreeNode> logicTreeNodeGroup;

	private final RuleTreeVO ruleTreeVO;

	public DecisionTreeEngine(Map<String, ILogicTreeNode> logicTreeNodeGroup, RuleTreeVO ruleTreeVO) {
		this.logicTreeNodeGroup = logicTreeNodeGroup;
		this.ruleTreeVO = ruleTreeVO;
	}

	@Override
	public DefaultTreeFactory.StrategyAwardVO process(String userId, Long strategyId, Integer awardId) {

		DefaultTreeFactory.StrategyAwardVO strategyAwardData = null;

		// 获取基础信息
		String nextNode = ruleTreeVO.getTreeRootRuleNode();
		Map<String, RuleTreeNodeVO> treeNodeMap  = ruleTreeVO.getTreeNodeMap();

		RuleTreeNodeVO ruleTreeNode = treeNodeMap.get(nextNode);

		while(null != nextNode) {
			ILogicTreeNode logicTreeNode = logicTreeNodeGroup.get(ruleTreeNode.getRuleKey());
			String ruleValue = ruleTreeNode.getRuleValue();

			DefaultTreeFactory.TreeActionEntity logicEntity = logicTreeNode.logic(userId, strategyId, awardId,ruleValue);
			RuleLogicCheckTypeVO ruleLogicCheckTypeVO = logicEntity.getRuleLogicCheckType();
			strategyAwardData = logicEntity.getStrategyAwardVO();
			log.info("决策树引擎【{}】treeId:{} node:{} code:{}", ruleTreeVO.getTreeName(), ruleTreeVO.getTreeId(), nextNode, ruleLogicCheckTypeVO.getCode());

			nextNode = nextNode(ruleLogicCheckTypeVO.getCode(),ruleTreeNode.getTreeNodeLineVOList());
			ruleTreeNode = treeNodeMap.get(nextNode);


		}

		// 返回最终结果
		return strategyAwardData;
	}

	private String nextNode(String matterValue, List<RuleTreeNodeLineVO> ruleTreeNodeVOList) {
		if(null == ruleTreeNodeVOList || ruleTreeNodeVOList.isEmpty()) {
			return null;
		}

		for(RuleTreeNodeLineVO nodeLine: ruleTreeNodeVOList){
			if(decisionLogic(matterValue, nodeLine)) {
				return nodeLine.getRuleNodeTo();
			}
		}
		throw new RuntimeException("决策树引擎，nextNode未找到下一个节点");


	}


	public boolean decisionLogic(String matterValue, RuleTreeNodeLineVO nodeLine) {
		switch (nodeLine.getRuleLimitType()) {
			case EQUAL:
				return matterValue.equals(nodeLine.getRuleLimitValue().getCode());
			// 以下规则暂时不需要实现
			case GT:
			case LT:
			case GE:
			case LE:
			default:
				return false;
		}
	}

}
