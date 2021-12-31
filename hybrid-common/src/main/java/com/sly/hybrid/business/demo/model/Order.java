package com.sly.hybrid.business.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 订单 model
 *
 * @author SLY
 * @date 2021/12/28
 */
@Setter
@Getter
public class Order implements Serializable {
    /** 订单Id */
    private String id;
    /** 订单编号 */
    private String orderSn;
}
