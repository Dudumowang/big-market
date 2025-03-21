package cn.bugstack.domain.strategy.service.armory;

import org.springframework.boot.autoconfigure.web.WebProperties;

/**
 * @Author: 杨文彬
 * @Description: 策略装配库，负责初始化策略计算
 * @CreateTime: 2025-03-22
 */

public interface IStrategyArmory {
    void assembleLotteryStrategy(Long strategyId);
}
