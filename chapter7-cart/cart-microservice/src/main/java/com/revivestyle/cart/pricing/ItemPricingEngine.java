package com.revivestyle.cart.pricing;

import com.revivestyle.cart.model.CartLineItem;
import com.revivestyle.cart.model.ShoppingCart;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ItemPricingEngine implements PricingEngine{
    @Override
    public PricingAdjustment calculatePrice(ShoppingCart shoppingCart) {
        PricingAdjustment itemLevelAdjustment = new PricingAdjustment();
        itemLevelAdjustment.setPriceAdjustmentType(PricingAdjustmentType.ITEM);
        double adjustmentTotal  = 0.0 ;
        for (CartLineItem cartLineItem : shoppingCart.getLineItems()){
            adjustmentTotal += cartLineItem.getSalePrice();
        }
        // Assuming 10% discount to be applied at Item-Level promotions
        double adjustmentCalculated = adjustmentTotal*0.1;
        adjustmentTotal -= adjustmentCalculated;
        itemLevelAdjustment.setAdjustedPrice(adjustmentTotal);
        return itemLevelAdjustment;
    }
}
