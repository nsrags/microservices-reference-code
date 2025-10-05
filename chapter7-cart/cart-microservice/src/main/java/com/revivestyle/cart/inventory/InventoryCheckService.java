package com.revivestyle.cart.inventory;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InventoryCheckService {

    public boolean checkIfItemExists(String skuId,String productId){
        return Boolean.FALSE.booleanValue();
    }
}
