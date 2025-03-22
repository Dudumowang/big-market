package cn.bugstack.test.domain;

import cn.bugstack.domain.strategy.service.armory.StrategyArmory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author: 杨文彬
 * @Description: 装配测试
 * @CreateTime: 2025-03-23
 */

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StrategyArmoryTest {

    @Resource
    private StrategyArmory strategyArmory;

    @Test
    public void test_strategyArmory() {
        strategyArmory.assembleLotteryStrategy(100002L);
    }

    @Test
    public void test_getAssembleRandomVal() {
        log.info("测试结果：{} - 奖品ID值", strategyArmory.getRandomAwardId(100002L));
        log.info("测试结果：{} - 奖品ID值", strategyArmory.getRandomAwardId(100002L));
        log.info("测试结果：{} - 奖品ID值", strategyArmory.getRandomAwardId(100002L));
    }
}
