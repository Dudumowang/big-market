package cn.bugstack.domain.strategy.model.vo;

import cn.bugstack.domain.strategy.service.rule.factory.DefaultLogicFactory;
import cn.bugstack.types.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 杨文彬
 * @Description: 抽奖策略规则值对象，没有唯一ID，仅限于从数据库查询对象
 * @CreateTime: 2025-05-31
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StrategyAwardRuleModelVO {

	private String ruleModels;

	public String[] raffleCenterRuleModelList(){
		List<String> ruleModelList = new ArrayList<>();
		String[] ruleModelValues = ruleModels.split(Constants.SPLIT);
		for (String ruleModelValue:ruleModelValues){
			if(DefaultLogicFactory.LogicModel.isCenter(ruleModelValue)){
				ruleModelList.add(ruleModelValue);
			}

		}
		return ruleModelList.toArray(new String[0]);

	}

	public String[] raffleAfterRuleModelList() {
		List<String> ruleModelList = new ArrayList<>();
		String[] ruleModelValues = ruleModels.split(Constants.SPLIT);
		for (String ruleModelValue : ruleModelValues) {
			if (DefaultLogicFactory.LogicModel.isAfter(ruleModelValue)) {
				ruleModelList.add(ruleModelValue);
			}
		}
		return ruleModelList.toArray(new String[0]);
	}

}
