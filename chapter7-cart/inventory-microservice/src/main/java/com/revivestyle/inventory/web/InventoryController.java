package com.revivestyle.inventory.web;

import com.revivestyle.inventory.model.InventoryListItem;
import com.revivestyle.inventory.service.InventoryService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class InventoryController {

    @Inject
    private InventoryService inventoryService;

    @GET
    @Path("{inventoryListId}/availability/{skuId}")
    public Response getAvailability(@PathParam("skuId") String skuId, @PathParam("inventoryListId") String inventoryListId){
        InventoryListItem inventoryItem = inventoryService.getAvailability(skuId,inventoryListId);
        return  Response.ok(inventoryItem).build();
    }

    @GET
    @Path("{inventoryListId}/availability/skus")
    public Response getAvailability(@PathParam("inventoryListId") String inventoryListId){
       List<InventoryListItem> inventoryItems  = inventoryService.getAvailabilityForAllSKUs(inventoryListId);
        return  Response.ok(inventoryItems).build();
    }
}
