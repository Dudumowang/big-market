package cn.bugstack.infrastructure.persistent.dao;
import cn.bugstack.infrastructure.persistent.po.StrategyAward;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: 杨文彬
 * @Description: 抽奖策略奖品明细配置DAO
 * @CreateTime: 2025-03-21
 */
@Mapper
public interface IStrategyAwardDao {
    List<StrategyAward> queryStrategyAwardList();
    List<StrategyAward> queryStrategyAwardListByStrategyId(Long strategyId);

	String queryStrategyAwardRuleModels(StrategyAward strategyAward);
}
