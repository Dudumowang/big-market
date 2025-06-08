package cn.bugstack.domain.strategy.service.rule.tree;

import cn.bugstack.domain.strategy.service.rule.tree.factory.DefaultTreeFactory;

/**
 * @Author: 杨文彬
 * @Description: 规则树接口
 * @CreateTime: 2025-06-05
 */
public interface ILogicTreeNode {

	DefaultTreeFactory.TreeActionEntity logic(String userId, Long strategyId, Integer awardId,String ruleValue);

}
