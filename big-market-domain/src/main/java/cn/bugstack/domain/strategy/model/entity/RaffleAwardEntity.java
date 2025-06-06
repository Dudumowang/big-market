package cn.bugstack.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 杨文彬
 * @Description: 抽奖奖品实体
 * @CreateTime: 2025-03-31
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaffleAwardEntity {

	/**
	 * 抽奖策略ID
	 */
	private Long strategyId;
	/**
	 * 抽奖奖品ID
	 */
	private Integer awardId;
	/**
	 * 奖品对接标识 - 每一个都是一个对接的发奖策略
	 */
	private String awardKey;
	/**
	 * 奖品配置信息
	 */
	private String awardConfig;
	/**
	 * 奖品内容描述
	 */
	private String awardDesc;
}
