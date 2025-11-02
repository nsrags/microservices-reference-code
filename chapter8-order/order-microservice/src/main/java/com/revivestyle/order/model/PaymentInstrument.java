package com.revivestyle.order.model;

import lombok.Data;

@Data
public class PaymentInstrument {
    private double amount;
    private PaymentMethodType paymentInstrumentType;
    private String expirationYear;
    private String expirationMonth;
    private String issueNumber;
    private String cardType;
}
