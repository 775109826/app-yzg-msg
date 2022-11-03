package com.yzg.modules.msg.merge;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import com.yzg.common.dingTaik.config.RobotConfig;
import com.yzg.common.dingTaik.util.DingTalkUtil;
import com.yzg.common.freemarketools.FreemarkeTools;
import com.yzg.common.util.FileUtils;
import com.yzg.modules.msg.entity.DailyDelivery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;

import java.util.List;

/**
 * @description:描述 拼接机器人发送消息
 * @author: 空想的闲暇时光  wall·e
 * @createDate: 2022/11/3 16:26
 **/
@Slf4j
public class MsgMerge {
    /**
     * 获取生成图片路径
     */
    @Value("${file.uploadpath.image}")
    private String filePath;
    /**
     * 换行(建议\n前后各添加两个空格)
     */
    public String hh = "  \n  ";

    public MsgMerge() {
    }

    public static  MsgMerge of() {
        return new MsgMerge();
    }

    /**
     * 消息拼接-发货日报
     *
     * @return
     */
    public  void mergeDeliverGoods(List<DailyDelivery> entityList) {
        //获取指定机器人
        DingTalkUtil ding = DingTalkUtil.of(RobotConfig.testRobot.getWebhook(), RobotConfig.testRobot.getSecret());
        StringBuffer markDownStr = new StringBuffer();
        String templateName = "sale_day_report";
        ModelMap modelMap = new ModelMap();
        try {
            modelMap.put("entityList", entityList);
            String html = FreemarkeTools.getTemplate(modelMap, templateName);
            String path = filePath.concat(DateUtil.format(DateUtil.date(), "yyyyMMdd")).concat("/");
            FileUtils.mkdirsFilePath(path);
            String timestamp = DateUtil.format(DateUtil.date(), "yyyyMMddHHmmss");
            String htmlFilePath = path + "sdr" + timestamp + ".html";
            String imageFileName = "sdr" + timestamp + ".png";
            String imageFilePath = path + imageFileName;
            int height = 200 + entityList.size() * 35;
            FreemarkeTools.turnImage(html, htmlFilePath, imageFilePath, 800, height);
            //拼接图片
            markDownStr.append("> ![screenshot](").append(imageFileName).append(")").append(hh);
            ding.sendMessageByMarkdown("发货日报", Convert.toStr(markDownStr), null, false);
        } catch (Exception e) {
            log.error("机器人-发货日报-消息发送失败,错误原因:" + e);
        }
    }

}
