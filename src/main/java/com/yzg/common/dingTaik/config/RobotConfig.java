package com.yzg.common.dingTaik.config;

/**
 * @description:描述
 * @author: 空想的闲暇时光  wall·e
 * @createDate: 2022/10/29 10:36
 **/
public enum RobotConfig {

    //发货日报
    DailyDeliveryReportRoboot("001","url",""),
    //资金日报
    FundDailyReportRoboot("002","url",""),

    /**
     * 测试机器人
     */
    testRobot("1",
            "https://oapi.dingtalk.com/robot/send?access_token=2d50109dedeae983f7f99be5a2029bf437a302181909282d6531e3d506f66134",
            "SEC10af169fa90b888cdc15d6463b0820858b3084a1dadeb4bab9c626182c15ac79");
    /**
     * 机器人名称
     */
    private String robotCode;
    /**
     * 地址
     */
    private String webhook;
    /**
     * 签名
     */
    private String secret;

    public String getRobotCode() {
        return robotCode;
    }

    public void setRobotCode(String robotCode) {
        this.robotCode = robotCode;
    }

    public String getWebhook() {
        return webhook;
    }

    public void setWebhook(String webhook) {
        this.webhook = webhook;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }


    /**
     * 构造方法
     *
     * @param robotCode
     * @param webhook
     * @param secret
     */
    RobotConfig(String robotCode, String webhook, String secret) {
        this.robotCode = robotCode;
        this.webhook = webhook;
        this.secret = secret;
    }

    /**
     * 根据code 获取机器人
     *
     * @param code
     * @return
     */
    public static RobotConfig getByRobotCode(String code) {
        RobotConfig robot = null;
        for (int i = 0; i < RobotConfig.values().length; i++) {
            robot = RobotConfig.values()[i];
            if (robot.getRobotCode().equals(code)) {
                return robot;
            }
        }
        return null;
    }

}

