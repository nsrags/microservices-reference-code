package com.revivestyle.cart.pricing;

import io.quarkus.arc.All;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.sun.java.accessibility.util.EventID.ITEM;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PricingAdjustment {
    private double totalPrice;
    private double adjustedPrice;
    private PricingAdjustmentType priceAdjustmentType;
}
