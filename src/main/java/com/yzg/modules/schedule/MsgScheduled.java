package com.yzg.modules.schedule;

import cn.hutool.core.date.DateUtil;
import com.yzg.modules.msg.entity.DailyDelivery;
import com.yzg.modules.msg.merge.MsgMerge;
import com.yzg.modules.msg.service.MsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @description:描述 计划任务
 * @author: 空想的闲暇时光  wall·e
 * @createDate: 2022/11/3 15:31
 **/

@Component("msgScheduled")
public class MsgScheduled {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 获取图片存储路径
     */
    @Value("${file.uploadpath.msg}")
    private String filePath;

    @Autowired
    private MsgService msgService;


    /**
     * 任务计划-1
     */
    // @Scheduled(cron = "0 0 9 * * ?")
    @PostConstruct
    public void test01() {
        try {
            List<DailyDelivery> dailyDeliveries = msgService.queryDailyDeliveryReport(DateUtil.date());
            MsgMerge.of().mergeDeliverGoods(dailyDeliveries);
        } catch (Exception e) {
            logger.error("执行计划-失败.错误原因:" + e);
        }

    }

}
