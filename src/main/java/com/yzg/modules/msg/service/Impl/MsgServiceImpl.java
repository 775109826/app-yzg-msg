package com.yzg.modules.msg.service.Impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.base.Optional;
import com.google.common.collect.Maps;
import com.yzg.modules.msg.dao.MsgDao;
import com.yzg.modules.msg.entity.DailyDelivery;
import com.yzg.modules.msg.service.MsgService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    private BigDecimal add(BigDecimal value1, BigDecimal value2) {
        if (!Optional.fromNullable(value1).isPresent())
            value1 = BigDecimal.ZERO;
        if (!Optional.fromNullable(value2).isPresent())
            value2 = BigDecimal.ZERO;
        return value1.add(value2);
    }

    private String percent(BigDecimal value1, BigDecimal value2) {
        if (!Optional.fromNullable(value1).isPresent())
            return "0%";
        if (!Optional.fromNullable(value2).isPresent())
            return "0%";
        if (value2.compareTo(BigDecimal.ZERO) == 0)
            return "0%";
        BigDecimal percentValue = NumberUtil.div(value1, value2, 4);
        return NumberUtil.decimalFormat("##,###.##%", percentValue);
    }

    private Map<String, Object> calcDailyDelivery(List<DailyDelivery> dailyDeliveryList) {
        Map<String, Object> resultMap = Maps.newHashMap();
        if (Optional.fromNullable(dailyDeliveryList).isPresent()) {
            String sumName = "合计";
            String sumCinvccode = "999999";
            BigDecimal sumDrfh = BigDecimal.ZERO;
            BigDecimal sumYlfh = BigDecimal.ZERO;
            BigDecimal sumDrbh = BigDecimal.ZERO;
            BigDecimal sumWf = BigDecimal.ZERO;
            BigDecimal sumKc = BigDecimal.ZERO;
            for (DailyDelivery item : dailyDeliveryList) {
                sumDrfh = add(sumDrfh, item.getDrfh());
                sumYlfh = add(sumYlfh, item.getYlfh());
                sumDrbh = add(sumDrbh, item.getDrbh());
                sumWf = add(sumWf, item.getWf());
                sumKc = add(sumKc, item.getKc());
            }
            DailyDelivery dailyDelivery = null;
            try {
                Class clazz = Class.forName("com.yzg.modules.msg.entity.DailyDelivery");
                try {
                    dailyDelivery = (DailyDelivery) clazz.newInstance();
                    dailyDelivery.setCinvcname(sumName);
                    dailyDelivery.setCinvccode(sumCinvccode);
                    dailyDelivery.setDrfh(sumDrfh);
                    dailyDelivery.setYlfh(sumYlfh);
                    dailyDelivery.setDrbh(sumDrbh);
                    dailyDelivery.setWf(sumWf);
                    dailyDelivery.setKc(sumKc);
                    dailyDelivery.setFhRatio(percent(dailyDelivery.getYlfh(), dailyDelivery.getYlfh()));
                    resultMap.put("dataItem", dailyDelivery);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            for (DailyDelivery item : dailyDeliveryList) {
                item.setFhRatio(percent(item.getYlfh(), dailyDelivery.getYlfh()));
            }
            resultMap.put("dataList", dailyDeliveryList);
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> queryDailyDeliveryReport(Date currentDate) throws Exception {
        String dataDate = DateUtil.format(currentDate, "yyyy-MM-dd");
        String dataMonth = DateUtil.format(currentDate, "yyyyMM");
        Map<String, Object> params = Maps.newHashMap();
        params.put("dataDate", dataDate);
        params.put("dataMonth", dataMonth);
        List<DailyDelivery> dailyDeliveryList = this.baseMapper.queryDailyDeliveryReport(params);
        return this.calcDailyDelivery(dailyDeliveryList);
    }
}
