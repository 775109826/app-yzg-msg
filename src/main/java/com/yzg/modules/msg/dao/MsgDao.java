package com.yzg.modules.msg.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzg.modules.msg.entity.DailyDelivery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @description:描述
 * @author: 空想的闲暇时光  wall·e
 * @createDate: 2022/10/29 10:15
 **/
@Mapper
public interface MsgDao extends BaseMapper<Map> {

    List<DailyDelivery> queryDailyDeliveryReport(Map<String, Object> params);
}
