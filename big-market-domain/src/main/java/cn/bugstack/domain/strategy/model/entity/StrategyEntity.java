package cn.bugstack.domain.strategy.model.entity;

import cn.bugstack.types.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: 杨文彬
 * @Description: 策略实体
 * @CreateTime: 2025-03-23
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyEntity {
    private String strategyId;
    /** 抽奖策略描述 **/
    private String strategyDesc;
    /** 抽奖规则模型 **/
    private String ruleModels;

    public String[] ruleModels() {
        if(StringUtils.isBlank(ruleModels)){
            return null;
        }
        return ruleModels.split(Constants.SPLIT);
    }

    public String getRuleWeight(){
        String [] ruleModels = this.ruleModels();
        if(null == ruleModels){
            return null;
        }
        for(String ruleModel : ruleModels){
            if("rule_weight".equals(ruleModel)){
                return ruleModel;
            }
        }
        return null;
    }
}
