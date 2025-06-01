package cn.bugstack.domain.strategy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 杨文彬
 * @Description: 抽奖因子实体
 * @CreateTime: 2025/3/31
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaffleFactorEntity {

    private  String userId;
    private  Long strategyId;
    private  Integer awardId;

}
