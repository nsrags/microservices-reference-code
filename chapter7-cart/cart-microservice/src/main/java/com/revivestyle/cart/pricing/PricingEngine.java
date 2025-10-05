package com.revivestyle.cart.pricing;

import com.revivestyle.cart.model.ShoppingCart;

public interface PricingEngine {
    public PricingAdjustment calculatePrice(ShoppingCart shoppingCart);
}
