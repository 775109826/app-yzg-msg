
package com.yzg.common.dingTaik.util;

import com.alibaba.fastjson.JSON;
import com.aliyun.credentials.utils.StringUtils;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;


/**
 * @description:描述 钉钉机器人工具类
 * @author: 空想的闲暇时光  wall·e
 * @createDate: 2022/10/27 15:54
 **/

public class DingTalkUtil {
    protected static Logger logger = LoggerFactory.getLogger(DingTalkUtil.class);

    /**
     * 钉钉群设置 webhook
     */
    private String accessToken;

    /**
     * 签名
     */
    private String secret;


    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }


    public String getAccessToken() {
        return accessToken;
    }

    public String getSecret() {
        return secret;
    }


    /**
     * 消息类型
     */
    private static final String MSG_TYPE_TEXT = "text";
    private static final String MSG_TYPE_LINK = "link";
    private static final String MSG_TYPE_MARKDOWN = "markdown";
    private static final String MSG_TYPE_ACTION_CARD = "actionCard";
    private static final String MSG_TYPE_FEED_CARD = "feedCard";


    /**
     * 客户端实例
     */
    private DingTalkClient client;

    public DingTalkUtil(String accessToken, String secret) {
        this.accessToken = accessToken;
        this.secret = secret;
        try {
            client = new DefaultDingTalkClient(this.accessToken + sign(this.secret));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | InvalidKeyException e) {
            e.printStackTrace();
        }

    }


    /**
     * 发送Markdown 编辑格式的消息
     *
     * @param title        标题
     * @param markdownText 支持markdown 编辑格式的文本信息
     * @param mobileList   消息@ 联系人
     * @param isAtAll      是否@ 全部
     * @return OapiRobotSendResponse
     */

    public OapiRobotSendResponse sendMessageByMarkdown(String title, String markdownText, List<String> mobileList, boolean isAtAll) {
        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(markdownText)) {
            return null;
        }

        //参数	类型	必选	说明
        //msgtype	String	是	此消息类型为固定markdown
        //title	String	是	首屏会话透出的展示内容
        //text	String	是	markdown格式的消息
        //atMobiles	Array	否	被@人的手机号(在text内容里要有@手机号)
        //isAtAll	bool	否	@所有人时：true，否则为：false
        OapiRobotSendRequest.Markdown markdown = new OapiRobotSendRequest.Markdown();
        markdown.setTitle(title);
        markdown.setText(markdownText);

        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype(DingTalkUtil.MSG_TYPE_MARKDOWN);
        request.setMarkdown(markdown);
        if (!CollectionUtils.isEmpty(mobileList)) {
            OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
            at.setIsAtAll(isAtAll);
            at.setAtMobiles(mobileList);
            request.setAt(at);
        }
        OapiRobotSendResponse response = new OapiRobotSendResponse();
        try {
            response = client.execute(request);
            System.out.println("【DingTalkUtils】发送link 响应参数：" + JSON.toJSONString(response));
        } catch (ApiException e) {
            logger.error("[发送link 类型消息]: 发送消息失败, 异常捕获{}", e.getMessage());
        }
        return response;
    }


    /**
     * 整体跳转ActionCard类型的消息发送
     *
     * @param title          消息标题, 会话消息会展示标题
     * @param markdownText   markdown格式的消息
     * @param singleTitle    单个按钮的标题
     * @param singleURL      单个按钮的跳转链接
     * @param btnOrientation 是否横向排列(true 横向排列, false 纵向排列)
     * @param hideAvatar     是否隐藏发消息者头像(true 隐藏头像, false 不隐藏)
     * @return OapiRobotSendResponse
     */

    public OapiRobotSendResponse sendMessageByActionCardSingle(String title, String markdownText, String singleTitle, String singleURL, boolean btnOrientation, boolean hideAvatar) {
        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(markdownText)) {
            return null;
        }
        //参数	类型	必选	说明
        //    msgtype	string	true	此消息类型为固定actionCard
        //    title	string	true	首屏会话透出的展示内容
        //    text	string	true	markdown格式的消息
        //    singleTitle	string	true	单个按钮的方案。(设置此项和singleURL后btns无效)
        //    singleURL	string	true	点击singleTitle按钮触发的URL
        //    btnOrientation	string	false	0-按钮竖直排列，1-按钮横向排列
        //    hideAvatar	string	false	0-正常发消息者头像，1-隐藏发消息者头像
        OapiRobotSendRequest.Actioncard actionCard = new OapiRobotSendRequest.Actioncard();
        actionCard.setTitle(title);
        actionCard.setText(markdownText);
        actionCard.setSingleTitle(singleTitle);
        actionCard.setSingleURL(singleURL);
        // 此处默认为0
        actionCard.setBtnOrientation(btnOrientation ? "1" : "0");
        // 此处默认为0
        actionCard.setHideAvatar(hideAvatar ? "1" : "0");

        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype(DingTalkUtil.MSG_TYPE_ACTION_CARD);
        request.setActionCard(actionCard);
        OapiRobotSendResponse response = new OapiRobotSendResponse();
        try {
            response = client.execute(request);
            System.out.println("【DingTalkUtils】整体跳转ActionCard类型的发送消息 响应参数：" + JSON.toJSONString(response));
        } catch (ApiException e) {
            logger.error("[发送ActionCard 类型消息]: 整体跳转ActionCard类型的发送消息失败, 异常捕获{}", e.getMessage());
        }
        return response;
    }


    /**
     * 独立跳转ActionCard类型 消息发送
     *
     * @param title          标题
     * @param markdownText   文本
     * @param btns           按钮列表
     * @param btnOrientation 是否横向排列(true 横向排列, false 纵向排列)
     * @param hideAvatar     是否隐藏发消息者头像(true 隐藏头像, false 不隐藏)
     * @return OapiRobotSendResponse
     */

    public OapiRobotSendResponse sendMessageByActionCardMulti(String title, String markdownText, List<OapiRobotSendRequest.Btns> btns, boolean btnOrientation, boolean hideAvatar) {
        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(markdownText) || CollectionUtils.isEmpty(btns)) {
            return null;
        }
        //参数	类型	必选	说明
        //msgtype	string	true	此消息类型为固定actionCard
        //title	string	true	首屏会话透出的展示内容
        //text	string	true	markdown格式的消息
        //btns	array	true	按钮的信息：title-按钮方案，actionURL-点击按钮触发的URL
        //btnOrientation	string	false	0-按钮竖直排列，1-按钮横向排列
        //hideAvatar	string	false	0-正常发消息者头像，1-隐藏发消息者头像
        OapiRobotSendRequest.Actioncard actionCard = new OapiRobotSendRequest.Actioncard();
        actionCard.setTitle(title);
        actionCard.setText(markdownText);
        // 此处默认为0
        actionCard.setBtnOrientation(btnOrientation ? "1" : "0");
        // 此处默认为0
        actionCard.setHideAvatar(hideAvatar ? "1" : "0");

        actionCard.setBtns(btns);

        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype(DingTalkUtil.MSG_TYPE_ACTION_CARD);
        request.setActionCard(actionCard);
        OapiRobotSendResponse response = new OapiRobotSendResponse();
        try {
            response = client.execute(request);
            System.out.println("【DingTalkUtils】独立跳转ActionCard类型发送消息 响应参数：" + JSON.toJSONString(response));
        } catch (ApiException e) {
            logger.error("[发送ActionCard 类型消息]: 独立跳转ActionCard类型发送消息失败, 异常捕获{}", e.getMessage());
        }
        return response;
    }


    /**
     * 发送FeedCard类型消息
     *
     * @param links
     * @return OapiRobotSendResponse
     */

    public OapiRobotSendResponse sendMessageByFeedCard(List<OapiRobotSendRequest.Links> links) {
        if (CollectionUtils.isEmpty(links)) {
            return null;
        }

        //msgtype	string	true	  此消息类型为固定feedCard
        //title	string	true	      单条信息文本
        //messageURL	string	true  点击单条信息到跳转链接
        //picURL	string	true	  单条信息后面图片的URL
        OapiRobotSendRequest.Feedcard feedcard = new OapiRobotSendRequest.Feedcard();
        feedcard.setLinks(links);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setMsgtype(DingTalkUtil.MSG_TYPE_FEED_CARD);
        request.setFeedCard(feedcard);
        OapiRobotSendResponse response = new OapiRobotSendResponse();
        try {
            response = client.execute(request);
            System.out.println("【DingTalkUtils】独立跳转ActionCard类型发送消息 响应参数：" + JSON.toJSONString(response));
        } catch (ApiException e) {
            logger.error("[发送ActionCard 类型消息]: 独立跳转ActionCard类型发送消息失败, 异常捕获{}", e.getMessage());
        }
        return response;
    }


    /**
     * 获取签名
     * 把timestamp+"\n"+密钥当做签名字符串，使用HmacSHA256算法计算签名，然后进行Base64 encode，最后再把签名参数再进行urlEncode，得到最终的签名（需要使用UTF-8字符集）。
     * timestamp 当前时间戳，单位是毫秒，与请求调用时间误差不能超过1小时。
     * secret 密钥，机器人安全设置页面，加签一栏下面显示的SEC开头的字符串。
     *
     * @param
     * @return java.lang.String
     */

    private static String sign(String secret) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        Long timestamp = System.currentTimeMillis();
        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
        return "&timestamp=" + timestamp + "&sign=" + sign;
    }


}

