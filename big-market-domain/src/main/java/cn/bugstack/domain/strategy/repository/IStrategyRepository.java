package cn.bugstack.domain.strategy.repository;

import cn.bugstack.domain.strategy.model.entity.StrategyAwardEntity;
import cn.bugstack.domain.strategy.model.entity.StrategyEntity;
import cn.bugstack.domain.strategy.model.entity.StrategyRuleEntity;
import cn.bugstack.domain.strategy.model.vo.StrategyAwardRuleModelVO;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: 杨文彬
 * @Description: 策略仓储接口
 * @CreateTime: 2025-03-22
 */

public interface IStrategyRepository {
    List<StrategyAwardEntity> queryStrategyAwardList(Long strategyId);

    void storeStoreStrategyAwardSearchRateTables(String key, Integer rateRange, HashMap<Integer, Integer> shuffleStrategyAwardSearchRateTables);

    int getRateRange(Long strategyId);

    int getRateRange(String key);

    Integer getStrategyAwardAssemble(String key, Integer rateKey);

    StrategyEntity queryStrategyEntityByStrategyId(Long strategyId);

    StrategyRuleEntity queryStrategyRule(Long strategyId, String ruleModel);


    String queryStrategyRuleValue(Long strategyId, Integer awardId, String ruleModel);

	StrategyAwardRuleModelVO queryStrategyAwardRuleModel(Long strategyId, Integer awardId);
}
