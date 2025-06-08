package cn.bugstack.trigger.job;

import cn.bugstack.domain.strategy.model.valobj.StrategyAwardStockKeyVO;
import cn.bugstack.domain.strategy.service.IRaffleStock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: 杨文彬
 * @Description: 更新奖品库存，异步队列更新数据库
 * @CreateTime: 2025-06-08
 */

@Slf4j
@Component()
public class UpdateAwardStockJob {
	@Resource
	private IRaffleStock raffleStock;

	@Scheduled(cron = "0/5 * * * * ?")
	public void exec() {
		try{
			log.info("定时任务-更新奖品库存");
			StrategyAwardStockKeyVO strategyAwardStockKeyVO = raffleStock.takeQueueValue();
			if(null == strategyAwardStockKeyVO){
				return;
			}
			log.info("定时任务-更新奖品库存, strategyId：{}, AwardId：{}", strategyAwardStockKeyVO.getStrategyId(), strategyAwardStockKeyVO.getAwardId());
			raffleStock.updateStrategyAwardStock(strategyAwardStockKeyVO.getStrategyId(),  strategyAwardStockKeyVO.getAwardId());

		} catch (InterruptedException e) {
			log.error("定时任务，更新奖品消耗库存失败", e);
		}
	}


}
