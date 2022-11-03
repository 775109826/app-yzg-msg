package com.yzg.modules.msg.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class DailyDelivery implements Serializable {

    private String cinvccode;

    private String cinvcname;

    private String cinvstd;

    private BigDecimal drfh;

    private BigDecimal ylfh;

    private BigDecimal drbh;

    private BigDecimal wf;

    private BigDecimal kc;
}
