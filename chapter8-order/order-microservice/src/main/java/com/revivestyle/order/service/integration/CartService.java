package com.revivestyle.order.service.integration;

import com.revivestyle.order.service.integration.model.ShoppingCart;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/cart")
@RegisterRestClient(configKey = "cart-service")
public interface CartService {

    @GET
    @Path("/{cartId}")
    @Produces(MediaType.APPLICATION_JSON)
    ShoppingCart getCart(@PathParam("cartId") long cartId);

}
