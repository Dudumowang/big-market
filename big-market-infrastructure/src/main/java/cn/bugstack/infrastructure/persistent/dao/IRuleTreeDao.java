package cn.bugstack.infrastructure.persistent.dao;

import cn.bugstack.infrastructure.persistent.po.RuleTree;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: 杨文彬
 * @Description: 规则树表DAO
 * @CreateTime: 2025-06-06
 */
@Mapper
public interface IRuleTreeDao {
	RuleTree queryRuleTreeByTreeId(String treeId);
}
