package cn.bugstack.domain.strategy.service.rule.chain;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: 杨文彬
 * @Description:
 * @CreateTime: 2025-06-02
 */
@Slf4j
public  abstract class AbstractLogicChain implements ILogicChain {

	private ILogicChain next;

	@Override
	public ILogicChain appendNext(ILogicChain next) {
		this.next = next;
		return next;
	}

	@Override
	public ILogicChain next() {
		return null;
	}

	protected abstract String ruleModel();
}
