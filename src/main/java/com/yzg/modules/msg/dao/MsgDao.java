package com.yzg.modules.msg.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yzg.common.datasource.annotation.DataSource;
import com.yzg.modules.msg.entity.DailyDelivery;
import com.yzg.modules.msg.entity.FundDaily;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @description:描述
 * @author: 空想的闲暇时光  wall·e
 * @createDate: 2022/10/29 10:15
 **/
@Mapper
public interface MsgDao extends BaseMapper<Map> {

    @DataSource("u8base")
    List<DailyDelivery> queryDailyDeliveryReport(Map<String, Object> params);

    @DataSource("u8base")
    Map<String, Object> queryKCCashInstance(@Param("dataMonth") String dataMonth);

    @DataSource("u8base")
    Map<String, Object> queryAmountInstance(@Param("dataMonth") String dataMonth);

    @DataSource("u8base")
    List<FundDaily> queryAmountList(@Param("dataMonth") String dataMonth);

}
