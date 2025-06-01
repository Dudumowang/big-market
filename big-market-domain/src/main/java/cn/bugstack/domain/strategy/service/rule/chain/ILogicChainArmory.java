package cn.bugstack.domain.strategy.service.rule.chain;

/**
 * @Author: 杨文彬
 * @Description: 装配接口
 * @CreateTime: 2025-06-02
 */
public interface ILogicChainArmory {
	ILogicChain appendNext(ILogicChain next);

	ILogicChain next();
}
