package com.yzg.modules.msg.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yzg.modules.msg.entity.DailyDelivery;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description:描述
 * @author: 空想的闲暇时光  wall·e
 * @createDate: 2022/10/29 10:14
 **/
public interface MsgService extends IService<Map> {

    List<DailyDelivery> queryDailyDeliveryReport(Date currentDate) throws Exception;
}
