package cn.bugstack.domain.strategy.service.armory;

/**
 * @Author: 杨文彬
 * @Description: 策略抽奖调度
 * @CreateTime: 2025-03-23
 */

public interface IStrategyDispatch {
    Integer getRandomAwardId(Long strategyId);

    Integer getRandomAwardId(Long strategyId,String ruleWeight);
}
