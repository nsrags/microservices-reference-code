package com.revivestyle.cart.service;

import com.revivestyle.cart.model.CartLineItem;
import com.revivestyle.cart.model.ShoppingCart;
import com.revivestyle.cart.pricing.CartPricingEngine;
import com.revivestyle.cart.repository.ShoppingCartRepository;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@ApplicationScoped
public class ShoppingCartImpl implements  CartService{

    @Inject
    private ShoppingCartRepository shoppingCartRepository;
    @Inject
    private CartPricingEngine cartPricingEngine;

    @Override
    public ShoppingCart getCart(Long cartId) {
        Log.infof("ShoppingCartImpl:: CartId '%s' request received for getCart()",cartId);
        ShoppingCart cartRetrieved = shoppingCartRepository.findCartById(cartId);
        return cartRetrieved;
    }
    @Override
    public ShoppingCart addItemToCart(CartLineItem itemToAdd, Long cartId ){
        ShoppingCart cart = shoppingCartRepository.addLineItemToCart(itemToAdd,cartId);
        return cartPricingEngine.repriceCart(cart);
    }

    @Override
    public ShoppingCart addItemsToCart(Set<CartLineItem> itemsToAdd, Long cartId ){
        ShoppingCart cart =  shoppingCartRepository.addLineItemsToCart(itemsToAdd,cartId);
        return cartPricingEngine.repriceCart(cart);
    }

    @Override
    public ShoppingCart updateCart(Set<CartLineItem> itemsToUpdate, Long cartId) {
        return null;
    }

    @Override
    public ShoppingCart removeItemsFromCart(List<CartLineItem> itemsToRemove, ShoppingCart cart) {
        return null;
    }

    @Override
    public ShoppingCart repriceCart(ShoppingCart cartToReprice) {
        return null;
    }

    @Override
    public ShoppingCart getCartByCustomer(Long customerId) {
        Log.infof("ShoppingCartImpl:: CustomerId '%s' request received for getCartByCustomer()",customerId);
        ShoppingCart cartRetrieved = shoppingCartRepository.findCartByCustomer(customerId);
        return cartRetrieved;
    }

    @Override
    public ShoppingCart createCartForCustomer(Long customerId) {
        return shoppingCartRepository.createNewCart(customerId);
    }
}
