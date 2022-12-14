package com.yzg.modules.schedule;

import cn.hutool.core.date.DateUtil;
import com.yzg.modules.msg.merge.MsgClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @description:描述 计划任务
 * @author: 空想的闲暇时光  wall·e
 * @createDate: 2022/11/3 15:31
 **/

@Component("msgScheduled")
public class MsgScheduled {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private MsgClient msgClient;

    @Scheduled(cron = "${app.task.pushdailydeliveryreport}")
    public void sendDailyDeliveryReport() {
        try {
            Date currentDate = DateUtil.date();
            msgClient.mergeDeliverGoods(currentDate);
        } catch (Exception e) {
            logger.error("发送日报执行计划-失败.错误原因:" + e);
        }
    }

    @Scheduled(cron = "${app.task.funddailyreportroboot}")
    public void sendFundDailyReportRoboot() {
        try {
            Date currentDate = DateUtil.date();
            msgClient.mergeFundDailyReport(currentDate);
        } catch (Exception e) {
            logger.error("资金日报执行计划-失败.错误原因:" + e);
        }
    }
}
