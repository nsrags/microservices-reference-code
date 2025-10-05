package com.revivestyle.cart.web;

import com.revivestyle.cart.model.CartLineItem;
import com.revivestyle.cart.model.ShoppingCart;
import com.revivestyle.cart.service.CartService;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Set;


@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class CartController {

    @Inject
    private CartService shoppingCartService;

    @POST
    @Path("/{cartId}/item")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addItemToBasket(CartLineItem lineItem, @PathParam("cartId") String cartId){
        ShoppingCart cart =  shoppingCartService.addItemToCart(lineItem , Long.parseLong(cartId));
        Log.info("Item received to add to Cart .."+lineItem);
        return Response.ok(cart).build();
    }

    @POST
    @Path("/{cartId}/items")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addItemsToBasket(Set<CartLineItem> lineItems, @PathParam("cartId") String cartId){
        ShoppingCart cart =  shoppingCartService.addItemsToCart(lineItems , Long.parseLong(cartId));
        Log.info("Items received to add to Cart .."+lineItems);
       return Response.ok(cart).build();
    }

    @GET
    @Path("/{cartId}")
    public Response findCartByCartId(@PathParam("cartId") String cartId){
        Log.infof("Cart Controller : CartId '%s' received for request ",cartId);
        ShoppingCart cart = shoppingCartService.getCart(Long.parseLong(cartId));
        return Response.ok(cart).build();
    }

    @GET
    @Path("/customer/{customerId}")
    public Response getCartByCustomer(@PathParam("customerId") String customerId){
        Log.infof("Cart Controller : CustomerId '%s' received for request ",customerId);
        ShoppingCart cart = shoppingCartService.getCartByCustomer(Long.parseLong(customerId));
        if(null == cart){
            return Response.status(Response.Status.NOT_FOUND).entity("No Cart Found for the Customer "+customerId).build();
        }
        return Response.ok(cart).build();
    }

    @POST
    @Path("/customer/{customerId}")
    public Response createNewCart(@PathParam("customerId") String customerId){
        ShoppingCart cart = shoppingCartService.createCartForCustomer(Long.parseLong(customerId));
        return Response.status(Response.Status.CREATED).entity(cart).build();
    }

}
