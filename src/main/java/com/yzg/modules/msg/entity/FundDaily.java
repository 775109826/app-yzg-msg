package com.yzg.modules.msg.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class FundDaily implements Serializable {

    private String code;

    private String name;

    private BigDecimal rkAmount;

    private BigDecimal zfAmount;

    private BigDecimal yeAmount;
}
