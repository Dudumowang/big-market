package cn.bugstack.infrastructure.persistent.po;

import lombok.Data;

import java.util.Date;

/**
 * @Author: 杨文彬
 * @Description: 规则树
 * @CreateTime: 2025-06-06
 */
@Data
public class RuleTree {

	/* 自增ID */
	private Long id;
	/* 规则树ID */
	private String treeId;
	/* 规则树名称 */
	private String treeName;
	/* 规则树描述 */
	private String treeDesc;
	/* 规则树根节点 */
	private String treeRootRuleKey;
	/* 创建时间 */
	private Date createTime;
	/* 更新时间 */
	private Date updateTime;
}
