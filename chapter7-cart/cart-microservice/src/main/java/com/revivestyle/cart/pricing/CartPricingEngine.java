package com.revivestyle.cart.pricing;


import com.revivestyle.cart.model.ShoppingCart;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CartPricingEngine {
    //Item Pricing
    // Shiping Pricing
    // Customer Specific Pricing
    @Inject
    private ItemPricingEngine itemPricingEngine;
    @Inject
    private ShippingPricingEngine shippingPricingEngine;

    public ShoppingCart repriceCart(ShoppingCart cartToBeRepriced){

        // Item level Pricing Calculcation

        PricingAdjustment itemPriceAdjusted  = itemPricingEngine.calculatePrice(cartToBeRepriced);
        cartToBeRepriced.setCartTotal(itemPriceAdjusted.getAdjustedPrice());

        //Shipping Level  Pricing  Calculation

        PricingAdjustment shippingPriceAdjusted = shippingPricingEngine.calculatePrice(cartToBeRepriced);
        cartToBeRepriced.setShippingTotal(shippingPriceAdjusted.getAdjustedPrice());

        //Tax Pricing Engine - based on customer delivery Address and state postal code ?

        return cartToBeRepriced;
    }
}
