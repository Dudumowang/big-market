package cn.bugstack.infrastructure.persistent.dao;

import cn.bugstack.infrastructure.persistent.po.StrategyRule;
import org.apache.ibatis.annotations.Mapper;


/**
 * @Author: 杨文彬
 * @Description: 策略规则DAO
 * @CreateTime: 2025-03-21
 */

@Mapper
public interface IStrategyRuleDao {

    StrategyRule queryStrategyRule(StrategyRule strategyRuleReq);

    String queryStrategyRuleValue(StrategyRule strategyRule);
}
