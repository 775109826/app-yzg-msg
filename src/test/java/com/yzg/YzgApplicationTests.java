package com.yzg;

import cn.hutool.core.date.DateUtil;
import com.google.common.collect.Maps;
import com.yzg.common.dingTaik.DingTalkUtil;
import com.yzg.common.freemarketools.FreemarkeTools;
import com.yzg.common.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ModelMap;

import java.util.Date;
import java.util.Map;

@Slf4j
@SpringBootTest
class YzgApplicationTests {
    @Value("${file.uploadpath.msg}")
    private String filePath;

    @Test
    void contextLoads() {
        try {
            String imgPath = null;
            String token = "https://oapi.dingtalk.com/robot/send?access_token=2d50109dedeae983f7f99be5a2029bf437a302181909282d6531e3d506f66134";
            String secret = "SEC10af169fa90b888cdc15d6463b0820858b3084a1dadeb4bab9c626182c15ac79";
            DingTalkUtil dingTalkUtil = new DingTalkUtil(token, secret);
            // dingTalkUtil.sendMessageByText("asddas",null,false);
            String markdownstr="#### 测试杭州天气 @156xxxx8827\n" +
                    "> 9度，西北风1级，空气良89，相对温度73%\n\n" +
                    "> ![screenshot](https://gw.alicdn.com/tfs/TB1ut3xxbsrBKNjSZFpXXcXhFXa-846-786.png)\n"  +
                    "> ###### 10点20分发布 [天气](http://www.thinkpage.cn/) \n";
            // dingTalkUtil.sendMessageByMarkdown("asddas",markdownstr,null,false);
            ModelMap modelMap = new ModelMap();
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
            String templateName = "not-accounts";
            String html = FreemarkeTools.getTemplate(modelMap, templateName);
            String path = filePath.concat(DateUtil.format(DateUtil.date(), "yyyyMMdd")).concat("/");
            FileUtils.mkdirsFilePath(path);
            String timestamp = DateUtil.format(DateUtil.date(), "yyyyMMddHHmmss");
            String htmlFilePath = path + "sna" + timestamp + ".html";
            String imageFilePath = path + "sna" + timestamp + ".png";
            int height = 185 + 1 * 35;
            imgPath = FreemarkeTools.turnImage(html, htmlFilePath, imageFilePath, 800, height);
            System.out.println(imgPath);
        } catch (Exception e) {
            log.error(String.valueOf(e));
        }
    }

}
