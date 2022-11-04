package com.yzg.modules.msg.service;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.Map;

/**
 * @description:描述
 * @author: 空想的闲暇时光  wall·e
 * @createDate: 2022/10/29 10:14
 **/
public interface MsgService extends IService<Map> {

    Map<String, Object> queryDailyDeliveryReport(Date currentDate) throws Exception;

    Map<String, Object> queryFundDailyReport(Date currentDate) throws Exception;
}
