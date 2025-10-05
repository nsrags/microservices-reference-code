package com.revivestyle.cart.model;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.Currency;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class ShoppingCart {

    @Setter(AccessLevel.NONE)
    @NonNull
    private Long cartId;
    private Long customerId;
    private String customerEmailAddress;
    private Set<CartLineItem> lineItems = new HashSet<CartLineItem>();
    private Customer customer;
    private String createdBy;
    private  double cartTotal;
    private double shippingTotal;
    private double taxTotal;
    private Currency currency = Currency.getInstance("USD");
}
