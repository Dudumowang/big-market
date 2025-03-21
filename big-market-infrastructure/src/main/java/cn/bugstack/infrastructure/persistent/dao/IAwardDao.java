package cn.bugstack.infrastructure.persistent.dao;

import cn.bugstack.infrastructure.persistent.po.Award;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: 杨文彬
 * @Description: 奖品表DAO
 * @CreateTime: 2025-03-21
 */


@Mapper
public interface IAwardDao {
    List<Award> queryAwardList();
}
