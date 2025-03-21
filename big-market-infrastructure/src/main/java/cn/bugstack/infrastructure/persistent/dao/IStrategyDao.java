package cn.bugstack.infrastructure.persistent.dao;

import cn.bugstack.infrastructure.persistent.po.Strategy;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: 杨文彬
 * @Description: 抽奖策略DAO
 * @CreateTime: 2025-03-21
 */
@Mapper
public interface IStrategyDao {
    List<Strategy> queryStrategyList();
}
