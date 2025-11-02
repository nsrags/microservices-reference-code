package com.revivestyle.order.model;

import lombok.Data;

@Data
public class OrderLineItem {
    private long orderLineItemId;
    private String skuId;
    private String productId;
    private int quantity;
    private double lineItemPrice;
}
