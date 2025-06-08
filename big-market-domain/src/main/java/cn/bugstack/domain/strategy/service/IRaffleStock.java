package cn.bugstack.domain.strategy.service;

import cn.bugstack.domain.strategy.model.valobj.StrategyAwardStockKeyVO;

/**
 * @Author: 杨文彬
 * @Description: 抽奖库存相关服务
 * @CreateTime: 2025-06-08
 */
public interface IRaffleStock {

	//获取奖品库存消耗队列
	StrategyAwardStockKeyVO takeQueueValue() throws InterruptedException;

	//更新奖品库存消耗记录
	void updateStrategyAwardStock(Long strategyId, Integer awardId);
}
