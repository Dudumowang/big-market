package cn.bugstack.infrastructure.persistent.dao;

import cn.bugstack.infrastructure.persistent.po.RuleTreeNode;
import cn.bugstack.infrastructure.persistent.po.RuleTreeNodeLine;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: 杨文彬
 * @Description: 规则树节点连线表DAO
 * @CreateTime: 2025-06-07
 */
@Mapper
public interface IRuleTreeNodeLineDao {
	List<RuleTreeNodeLine> queryRuleTreeNodeLineListByTreeId(String treeId);
}
