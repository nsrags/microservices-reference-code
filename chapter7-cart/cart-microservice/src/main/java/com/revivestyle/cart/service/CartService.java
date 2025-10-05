package com.revivestyle.cart.service;

import com.revivestyle.cart.model.CartLineItem;
import com.revivestyle.cart.model.ShoppingCart;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface CartService {

    public ShoppingCart getCart( Long cartId);
    public ShoppingCart addItemToCart(CartLineItem itemToAdd,Long cartId);
    public ShoppingCart addItemsToCart(Set<CartLineItem> itemsToAdd,Long cartId);
    // CartLineItemId is MUST
    public ShoppingCart updateCart(Set<CartLineItem> itemsToUpdate,Long cartId);
    public ShoppingCart removeItemsFromCart(List<CartLineItem> itemsToRemove, ShoppingCart cart);
    public ShoppingCart repriceCart(ShoppingCart cartToReprice);
    public ShoppingCart getCartByCustomer(Long customerId);
    public ShoppingCart createCartForCustomer(Long customerId);

}
