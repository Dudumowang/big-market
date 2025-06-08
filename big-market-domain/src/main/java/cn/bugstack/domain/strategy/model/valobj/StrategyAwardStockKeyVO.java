package cn.bugstack.domain.strategy.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 杨文彬
 * @Description: 策略奖品库存Key标识值对象
 * @CreateTime: 2025-06-08
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyAwardStockKeyVO {
	private Long strategyId;
	private Integer awardId;
}
