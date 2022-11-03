package com.yzg.modules.msg.service.Impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.yzg.modules.msg.dao.MsgDao;
import com.yzg.modules.msg.entity.DailyDelivery;
import com.yzg.modules.msg.service.MsgService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description:描述
 * @author: 空想的闲暇时光  wall·e
 * @createDate: 2022/10/29 10:15
 **/
@Service("msgService")
public class MsgServiceImpl extends ServiceImpl<MsgDao, Map> implements MsgService {

    @Override
    public List<DailyDelivery> queryDailyDeliveryReport(Date currentDate) throws Exception{
        String dataDate = DateUtil.format(currentDate, "yyyy-MM-dd");
        String dataMonth = DateUtil.format(currentDate, "yyyyMM");
        Map<String, Object> params = Maps.newHashMap();
        params.put("dataDate", dataDate);
        params.put("dataMonth", dataMonth);
        return this.baseMapper.queryDailyDeliveryReport(params);
    }
}
