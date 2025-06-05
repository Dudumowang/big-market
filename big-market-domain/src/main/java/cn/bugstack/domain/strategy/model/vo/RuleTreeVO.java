package cn.bugstack.domain.strategy.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @Author: 杨文彬
 * @Description: 规则树对象
 * @CreateTime: 2025-06-05
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleTreeVO {

	/** 规则树ID */
	private Integer treeId;
	/** 规则树名称 */
	private String treeName;
	/** 规则树描述 */
	private String treeDesc;
	/** 规则树接节点 */
	private String treeRootRuleNode;

	/** 规则节点 */
	private Map<String, RuleTreeNodeVO> TreeNodeMap;
}
