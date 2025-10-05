package com.revivestyle.cart.pricing;

import com.revivestyle.cart.model.ShoppingCart;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ShippingPricingEngine implements PricingEngine{
    @Override
    public PricingAdjustment calculatePrice(ShoppingCart shoppingCart) {
        PricingAdjustment shippingLevelAdjustment = new PricingAdjustment();
        shippingLevelAdjustment.setPriceAdjustmentType(PricingAdjustmentType.SHIPPING);
        // if cart total is > 25 then apply 5% charges on the total
        // if cart total is > 50 then apply $5 charges on the total
        // if Cart total is >100 then apply 15% charges on the total
        // If cart total is >200 then flat of 20% discount

        if(shoppingCart.getCartTotal()>200){
            shippingLevelAdjustment.setAdjustedPrice(shoppingCart.getCartTotal()*0.2);
        }else
        if(shoppingCart.getCartTotal()>100){
            shippingLevelAdjustment.setAdjustedPrice(shoppingCart.getCartTotal()*0.15);
        }else
        if(shoppingCart.getCartTotal() > 50){
            shippingLevelAdjustment.setAdjustedPrice(shoppingCart.getCartTotal()*0.1);
        } else
        if (shoppingCart.getCartTotal() >25) {
            shippingLevelAdjustment.setAdjustedPrice(5);
        }

        return shippingLevelAdjustment;
    }
}
