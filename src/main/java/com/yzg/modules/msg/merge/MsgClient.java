package com.yzg.modules.msg.merge;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.yzg.common.dingTaik.config.RobotConfig;
import com.yzg.common.dingTaik.util.Constant;
import com.yzg.common.dingTaik.util.DingTalkUtil;
import com.yzg.common.freemarketools.FreemarkeTools;
import com.yzg.common.util.FileUtils;
import com.yzg.modules.msg.entity.DailyDelivery;
import com.yzg.modules.msg.entity.FundDaily;
import com.yzg.modules.msg.service.MsgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description:描述 拼接机器人发送消息
 * @author: 空想的闲暇时光  wall·e
 * @createDate: 2022/11/3 16:26
 **/
@Component
public class MsgClient {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 默认行数
     */
    private final int rowNum = 5;
    /**
     * 默认行高
     */
    private final int rowHeight = 40;
    /**
     * 默认图片高度
     */
    private final int imageWidth = 600;

    @Autowired
    private MsgService msgService;
    /**
     * 获取生成图片路径
     */
    @Value("${file.uploadpath.image}")
    private String filePath;
    /**
     * 换行(建议\n前后各添加两个空格)
     */
    public String hh = "  \n  ";


    /**
     * @param templateName 模板名称
     * @param modelMap     模板数据
     * @param beforeName   图片名前缀名称
     * @param robotTypeUrl 机器人类型路径
     * @param rowNum       行数(计算高度,默认5行)
     * @param rowHeight    行高(表格每行高度.默认40)
     * @param imageWidth   生成图片宽度(默认600)
     */
    public String createImage(String templateName, ModelMap modelMap, String beforeName, RobotConfig.RobotTypeUrl robotTypeUrl, int rowNum, int rowHeight, int imageWidth) {
        // 默认行数
        if (NumberUtil.isNumber(Convert.toStr(rowNum)) && rowNum == 0) {
            rowNum = this.rowNum;
        }
        // 默认行高
        if (NumberUtil.isNumber(Convert.toStr(rowHeight)) && rowHeight == 0) {
            rowHeight = this.rowHeight;
        }
        // 默认图片高度
        if (NumberUtil.isNumber(Convert.toStr(imageWidth)) && imageWidth == 0) {
            imageWidth = this.imageWidth;
        }

        // 日期和时间戳获取
        DateTime date = DateUtil.date();
        String outDate = DateUtil.format(date, "yyyyMMdd");
        String timestamp = DateUtil.format(date, "yyyyMMddHHmmss");
        // 生成 磁盘跟路径 + 日期 + 机器人类型 路径
        String path = filePath.concat(outDate).concat("/").concat(robotTypeUrl.getValue()).concat("/");
        // 生成 nginx配置路径
        String imageUrl = Constant.nginxImageUrl.concat(outDate).concat("/").concat(robotTypeUrl.getValue()).concat("/");
        // 创建路径
        FileUtils.mkdirsFilePath(path);
        // 生成 图片名称 html模板路径和image路径
        String imageFileName = beforeName + timestamp + ".png";
        String htmlFilePath = path + beforeName + timestamp + ".html";
        String imageFilePath = path + imageFileName;
        // 设置图片高度
        int height = rowNum * rowHeight;
        try {
            //获取模板填充数据
            String html = FreemarkeTools.getTemplate(modelMap, templateName);
            //生成图片
            FreemarkeTools.turnImage(html, htmlFilePath, imageFilePath, imageWidth, height);
            //拼接返回路径 nginx配置路径+生成图片名称
            return imageUrl.concat(imageFileName);
        } catch (Exception e) {
            logger.error(templateName + ":模板生成图片失败,错误原因:" + e);
        }
        return null;
    }
    /**
     * 消息拼接-发货日报
     *
     * @return
     */
    public void mergeDeliverGoods(Date currentDate) {
        //获取指定机器人
        DingTalkUtil ding = DingTalkUtil.of(RobotConfig.Robot.DailyDeliveryReportRoboot.getWebhook(), RobotConfig.Robot.DailyDeliveryReportRoboot.getSecret());
        StringBuffer markDownStr = new StringBuffer();
        ModelMap modelMap = new ModelMap();
        // 机器人模板和生成图片前缀配置
        String templateName = "sale_day_report";
        String beforeName = "SDR";
        try {
            // 参数填充
            Map<String, Object> resultMap = msgService.queryDailyDeliveryReport(currentDate);
            List<DailyDelivery> dailyDeliveryList = (List<DailyDelivery>) resultMap.get("dataList");
            modelMap.put("entityList", dailyDeliveryList);
            modelMap.put("sumItem", resultMap.get("dataItem"));
            modelMap.put("dataDate", DateUtil.format(DateUtil.date(), "yyyy年MM月dd日"));
            //生成图片
            String imageUrl = createImage(templateName, modelMap, beforeName, RobotConfig.RobotTypeUrl.goodsImage, dailyDeliveryList.size(), 0, 0);
            //拼接markDown文本发送图片
            markDownStr.append("> ![screenshot](").append(imageUrl).append(")").append(hh);
            ding.sendMessageByMarkdown("发货日报", Convert.toStr(markDownStr), null, false);
        } catch (Exception e) {
            logger.error("机器人-发货日报-消息发送失败,错误原因:" + e);
        }
    }

    /**
     * 消息拼接-发货日报
     *
     * @return
     */
    public void mergeFundDailyReport(Date currentDate) {
        //获取指定机器人
        DingTalkUtil ding = DingTalkUtil.of(RobotConfig.Robot.testRobot.getWebhook(), RobotConfig.Robot.testRobot.getSecret());
        StringBuffer markDownStr = new StringBuffer();
        ModelMap modelMap = new ModelMap();
        // 机器人模板和生成图片前缀配置
        String templateName = "fund_day_report";
        String beforeName = "FDR";
        try {
            // 参数填充
            Map<String, Object> resultMap = msgService.queryFundDailyReport(currentDate);
            modelMap.put("kcCash", resultMap.get("kcCash"));
            modelMap.put("amountInstance", resultMap.get("amountInstance"));
            List<FundDaily> fundDailyList = (List<FundDaily>)resultMap.get("amountList");
            modelMap.put("amountList", resultMap.get("amountList"));
            modelMap.put("sumItem", resultMap.get("sumItem"));
            modelMap.put("dataDate", DateUtil.format(DateUtil.date(), "yyyy年MM月dd日"));
            //生成图片
            String imageUrl = createImage(templateName, modelMap, beforeName, RobotConfig.RobotTypeUrl.goodsImage, fundDailyList.size(), 0, 450);
            //拼接markDown文本发送图片
            markDownStr.append("> ![screenshot](").append(imageUrl).append(")").append(hh);
            ding.sendMessageByMarkdown("资金日报", Convert.toStr(markDownStr), null, false);
        } catch (Exception e) {
            logger.error("机器人-发货日报-消息发送失败,错误原因:" + e);
        }
    }
}
