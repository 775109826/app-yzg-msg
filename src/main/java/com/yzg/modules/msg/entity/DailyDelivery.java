package com.yzg.modules.msg.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class DailyDelivery implements Serializable {

    private String cinvccode;

    private String cinvcname;  //品名

    private String cinvstd;    // 规格

    private BigDecimal drfh;   // 当日发货

    private BigDecimal ylfh;   // 月已发数量

    private BigDecimal drbh;   // 当日报货

    private BigDecimal wf;     // 未发数量

    private BigDecimal kc;     // 仓库库存

    private String fhRatio;    // 发货占比
}
