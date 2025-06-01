package cn.bugstack.domain.strategy.service;

import cn.bugstack.domain.strategy.model.entity.RaffleAwardEntity;
import cn.bugstack.domain.strategy.model.entity.RaffleFactorEntity;
import cn.bugstack.domain.strategy.model.entity.RuleActionEntity;
import cn.bugstack.domain.strategy.model.vo.RuleLogicCheckTypeVO;
import cn.bugstack.domain.strategy.model.vo.StrategyAwardRuleModelVO;
import cn.bugstack.domain.strategy.repository.IStrategyRepository;
import cn.bugstack.domain.strategy.service.armory.IStrategyDispatch;
import cn.bugstack.domain.strategy.service.rule.chain.ILogicChain;
import cn.bugstack.domain.strategy.service.rule.chain.factory.DefaultChainFactory;
import cn.bugstack.types.enums.ResponseCode;
import cn.bugstack.types.exception.AppException;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: 杨文彬
 * @Description: 抽奖策略抽象类
 * @CreateTime: 2025-04-01
 */

@Slf4j
public abstract class AbstractRaffleStrategy implements IRaffleStrategy {


    protected IStrategyRepository repository;

    protected IStrategyDispatch strategyDispatch;


    private DefaultChainFactory defaultChainFactory;

    public AbstractRaffleStrategy(IStrategyRepository repository, IStrategyDispatch strategyDispatch, DefaultChainFactory defaultChainFactory) {
        this.repository = repository;
        this.strategyDispatch = strategyDispatch;
        this.defaultChainFactory = defaultChainFactory;
    }

    @Override
    public RaffleAwardEntity performRaffle(RaffleFactorEntity raffleFactorEntity) {
        // 1. 参数校验
        String userId = raffleFactorEntity.getUserId();
        Long strategyId = raffleFactorEntity.getStrategyId();
        if(null == userId || null == strategyId){
            throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(),
                    ResponseCode.ILLEGAL_PARAMETER.getInfo());
        }

        // 2. 责任链抽奖
        ILogicChain logicChain = defaultChainFactory.openLogicChain(strategyId);
        Integer awardId = logicChain.logic(userId,strategyId);


        // 3. 抽奖中,拿到奖品ID过滤规则；抽奖后，扣减库存抽过滤，拦截和无库存走兜底

        StrategyAwardRuleModelVO strategyAwardRuleModelVO = repository.queryStrategyAwardRuleModel(strategyId,awardId);

        // 4 . 抽奖中-规则过滤
        RuleActionEntity<RuleActionEntity.RaffleCenterEntity> RuleActionCenterEntity = this.doCheckRaffleCenterLogic(RaffleFactorEntity.builder()
                .userId(userId)
                .strategyId(strategyId)
                .awardId(awardId)
                .build(), strategyAwardRuleModelVO.raffleCenterRuleModelList());


        if(RuleLogicCheckTypeVO.TAKE_OVER.getCode().equals(RuleActionCenterEntity.getCode())){
            log.info("[临时日志] 中奖规则拦截，通过抽奖后规则rule_luck_award兜底");
            return RaffleAwardEntity.builder()
                    .awardDesc("中将规则拦截，抽奖后规则兜底")
                    .build();

        }




        return RaffleAwardEntity.builder()
                .awardId(awardId)
                .build();
	}

    protected abstract RuleActionEntity<RuleActionEntity.RaffleBeforeEntity> doCheckRaffleBeforeLogic(RaffleFactorEntity raffleFactorEntity, String... logics);

    protected abstract RuleActionEntity<RuleActionEntity.RaffleCenterEntity> doCheckRaffleCenterLogic(RaffleFactorEntity raffleFactorEntity, String... logics);


}