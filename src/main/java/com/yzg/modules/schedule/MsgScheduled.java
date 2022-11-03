package com.yzg.modules.schedule;

import cn.hutool.core.date.DateUtil;
import com.yzg.modules.msg.merge.MsgClient;
import com.yzg.modules.msg.service.MsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;

/**
 * @description:描述 计划任务
 * @author: 空想的闲暇时光  wall·e
 * @createDate: 2022/11/3 15:31
 **/

@Component("msgScheduled")
public class MsgScheduled {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MsgService msgService;
    @Resource
    private MsgClient msgClient;
    /**
     * 任务计划-1
     */
    // @Scheduled(cron = "0 0 9 * * ?")
    @PostConstruct
    public void test01() {
        try {
            Map<String, Object> resultMap = msgService.queryDailyDeliveryReport(DateUtil.date());
            msgClient.mergeDeliverGoods(resultMap);
        } catch (Exception e) {
            logger.error("执行计划-失败.错误原因:" + e);
        }

    }

}
