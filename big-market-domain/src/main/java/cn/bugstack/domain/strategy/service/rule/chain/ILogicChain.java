package cn.bugstack.domain.strategy.service.rule.chain;

import cn.bugstack.domain.strategy.service.rule.chain.factory.DefaultChainFactory;

/**
 * @Author: 杨文彬
 * @Description: 责任链接口
 * @CreateTime: 2025-06-02
 */
public interface ILogicChain extends ILogicChainArmory{

	DefaultChainFactory.StrategyAwardVO logic(String userId, Long strategyId);




}
