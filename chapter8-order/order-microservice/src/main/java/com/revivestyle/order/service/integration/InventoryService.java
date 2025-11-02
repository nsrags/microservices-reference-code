package com.revivestyle.order.service.integration;

import com.revivestyle.order.service.integration.model.ShoppingCart;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/inventory")
@RegisterRestClient(configKey = "inventory-service")
public interface InventoryService {
    @GET
    @Path("/{skuId}")
    @Produces(MediaType.APPLICATION_JSON)
    boolean isAvailable(@PathParam("skuId") long skuId);
}
