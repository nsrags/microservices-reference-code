package com.revivestyle.order.web;

import com.revivestyle.order.model.Order;
import com.revivestyle.order.model.Payment;
import com.revivestyle.order.model.PaymentInstrument;
import com.revivestyle.order.service.OrderService;
import com.revivestyle.order.service.integration.CartService;
import com.revivestyle.order.service.integration.model.ShoppingCart;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class OrderController {
    @Inject
    private OrderService orderService;
    @Inject
    @RestClient
    private CartService cartService;

    @GET
    @Path("/{orderId}")
    public Response retreiveOrder(@PathParam("orderId") long orderId){
        Order orderFound = orderService.getOrder(orderId);
        return Response.ok(orderFound).build();
    }

    @POST
    @Path("/cancel/{orderId}")
    public Response cancelOrder(@PathParam("orderId") long orderId){
        Order orderFound = orderService.cancelOrder(orderId);
        return Response.ok(orderFound).build();
    }

    @POST
    @Path("/{cartId}")
    public Response createOrder(@PathParam("cartId") long cartId){
        // TODO: implementation of Saga pattern across inventory / payment /and finally Creating order
        ShoppingCart cartFound = cartService.getCart(cartId);
        if (null == cartFound){
            return Response.status(Response.Status.BAD_REQUEST).
                    entity("No Suitable Cart found for the CartID Passed ["+cartId+" ]").build();
        }
        Order orderCreated = orderService.createOrder(null);
        return Response.ok(orderCreated).build();
    }

    @POST
    @Path("/{orderId}/payment-instruments")
    public Response addPaymentInstrumentToOrder(PaymentInstrument paymentInstrumentToAdd, @PathParam("orderId") long orderId){
       Order updatedOrder = orderService.addPaymentInstrumentToOrder(paymentInstrumentToAdd,orderId);
        return Response.ok(updatedOrder).build();
    }

}
