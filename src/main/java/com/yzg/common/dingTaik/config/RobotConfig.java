package com.yzg.common.dingTaik.config;

/**
 * @description:描述
 * @author: 空想的闲暇时光  wall·e
 * @createDate: 2022/10/29 10:36
 **/
public class RobotConfig {

    /**
     * 机器人配置
     */
    public enum Robot {
        //发货日报
        DailyDeliveryReportRoboot("001", "https://oapi.dingtalk.com/robot/send?access_token=a90af2613aaea2bc412fc4d16d86fd730fee35f28dfcd51b8760cf78e94fd53a", "SECae45371e871a5fbe7b74b015750d7fb057926a7fd0a4ea7f3f45fe3da3fbde62"),
        //资金日报
        FundDailyReportRoboot("002", "url", ""),

        /**
         * 测试机器人
         */
        testRobot("1", "https://oapi.dingtalk.com/robot/send?access_token=2d50109dedeae983f7f99be5a2029bf437a302181909282d6531e3d506f66134",
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
        Robot(String robotCode, String webhook, String secret) {
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
        public static Robot getByRobotCode(String code) {
            Robot robot = null;
            for (int i = 0; i < Robot.values().length; i++) {
                robot = Robot.values()[i];
                if (robot.getRobotCode().equals(code)) {
                    return robot;
                }
            }
            return null;
        }
    }


    /**
     * 机器人类型路径配置
     */
    public enum RobotTypeUrl {
        /**
         * 货品类型
         */
        goodsImage("goods"),
        /**
         * 资金类型
         */
        fundImage("fund");

        private String value;

        RobotTypeUrl(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}

