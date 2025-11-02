package com.revivestyle.order.model;

import lombok.Data;

@Data
public class Payment {
    private double amount;
    private String paymentInstrumentId;
    private String paymentMethodId;
}
