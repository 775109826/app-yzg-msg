package com.yzg.common.dingTaik.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description:描述
 * @author: 空想的闲暇时光  wall·e
 * @createDate: 2022/10/25 15:59
 **/

@Data
@Component
@ConfigurationProperties(prefix ="dd")
public class DingTalkEntity {
    /**
     * 企业id
     */
    private String corpId;
    /**
     * 企业内部应用的AppKey
     */
    private String appKey;
    /**
     *企业内部应用的AppSecret
     */
    private String appSecret;
}
