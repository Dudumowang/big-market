package cn.bugstack.domain.strategy.service.armory;

import cn.bugstack.domain.strategy.model.entity.StrategyAwardEntity;
import cn.bugstack.domain.strategy.repository.IStrategyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.*;

/**
 * @Author: 杨文彬
 * @Description: 策略装配库，负责初始化策略计算
 * @CreateTime: 2025-03-22
 */
@Slf4j
@Service
public class StrategyArmory implements IStrategyArmory {

    @Resource
    private IStrategyRepository repository;


    @Override
    public void assembleLotteryStrategy(Long strategyId) {
        // 1.查询策略配置
        List<StrategyAwardEntity> strategyAwardEntities= repository.queryStrategyAwardList(strategyId);

        // 2.获取最小概率值
        BigDecimal minAwardRate = strategyAwardEntities.stream()
                .map(StrategyAwardEntity::getAwardRate)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        // 3. 获取概率值总和
        BigDecimal totalAwardRate = strategyAwardEntities.stream()
                .map(StrategyAwardEntity::getAwardRate)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 4.用1 除以0.0001，获取概率范围
        BigDecimal rateRange = totalAwardRate.divide(minAwardRate, 0, RoundingMode.CEILING);

        ArrayList<Integer> strategyAwardSearchRateTables = new ArrayList<>(rateRange.intValue());
        for(StrategyAwardEntity strategyAward : strategyAwardEntities){
            Integer awardId = strategyAward.getAwardId();
            BigDecimal awardRate = strategyAward.getAwardRate();

            //计算每个概率值需要存放到查找表数量，循环填充
            for(int i=0;i<rateRange.multiply(awardRate).setScale(0,RoundingMode.CEILING).intValue();i++){
                strategyAwardSearchRateTables.add(awardId);
            }
        }

        //6.乱序
        Collections.shuffle(strategyAwardSearchRateTables);

        // 7. 生成集合
        HashMap<Integer, Integer> shuffleStrategyAwardSearchRateTables = new HashMap<>();
        for(int i=0;i<strategyAwardSearchRateTables.size();i++){
            shuffleStrategyAwardSearchRateTables.put(i,strategyAwardSearchRateTables.get(i));
        }

        //8.存储到redis
        repository.storeStoreStrategyAwardSearchRateTables(strategyId,
                shuffleStrategyAwardSearchRateTables.size(),
                shuffleStrategyAwardSearchRateTables);
    }

    @Override
    public Integer getRandomAwardId(Long strategyId) {
        int rateRange = repository.getRateRange(strategyId);
        return repository.getStrategyAwardAssemble(strategyId, new SecureRandom().nextInt(rateRange));
    }


}
