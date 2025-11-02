package com.revivestyle.order.model;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Order {
    @Setter(AccessLevel.NONE)
    @NonNull
    private long orderId;
    private String orderDesscription;
    private double orderTotal;
    private double shippingTotal;
    private double totalTax;
    private boolean isGuest;
    private OrderStatus orderStatus;
    private Set<OrderLineItem> lineItems = new HashSet<OrderLineItem>();
    private long customerId;
    private Set<Payment> payments = new HashSet<Payment>();

}
