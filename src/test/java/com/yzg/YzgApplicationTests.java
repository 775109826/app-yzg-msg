package com.yzg;

import com.yzg.common.dingTaik.config.RobotConfig;
import com.yzg.common.dingTaik.util.DingTalkUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class YzgApplicationTests {
    @Value("${file.uploadpath.image}")
    private String filePath;

    @Test
    void contextLoads() {
        try {
            DingTalkUtil ding = DingTalkUtil.of(RobotConfig.testRobot.getWebhook(), RobotConfig.testRobot.getSecret());
            // markDown文本
            String markDownstr = "#### 杭州天气 @156xxxx8827\n" +
                    "> 9度，西北风1级，空气良89，相对温度73%\n\n" +
                    "> ![screenshot](https://gw.alicdn.com/tfs/TB1ut3xxbsrBKNjSZFpXXcXhFXa-846-786.png)\n" +
                    "> ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \n";
            ding.sendMessageByMarkdown("我是标题", markDownstr, null, false);
          /*  ModelMap modelMap = new ModelMap();
            Map<String, Object> mainItem = Maps.newHashMap();
            mainItem.put("title", "reportName");
            mainItem.put("time", DateUtil.format(new Date(), "yyyy年MM月dd日 HH:mm:ss"));
            mainItem.put("remark", "remark");
            mainItem.put("notice", "通知时间");
            mainItem.put("cusno", "店铺编号");
            mainItem.put("shopName", "店铺名称");
            mainItem.put("outDate", "最后结账日");
            mainItem.put("saleName", "店长");
            mainItem.put("areaName", "办事处");
            String templateName = "not-msg";
            String html = FreemarkeTools.getTemplate(modelMap, templateName);
            String path = filePath.concat(DateUtil.format(DateUtil.date(), "yyyyMMdd")).concat("/");
            FileUtils.mkdirsFilePath(path);
            String timestamp = DateUtil.format(DateUtil.date(), "yyyyMMddHHmmss");
            String htmlFilePath = path + "sna" + timestamp + ".html";
            String imageFilePath = path + "sna" + timestamp + ".png";
            int height = 500;
            imgPath = FreemarkeTools.turnImage(html, htmlFilePath, imageFilePath, 800, height);
            System.out.println(imgPath);*/
        } catch (Exception e) {
            log.error(String.valueOf(e));
        }
    }

}
