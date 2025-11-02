package com.revivestyle.inventory.service;

import com.revivestyle.inventory.model.InventoryListItem;
import com.revivestyle.inventory.repository.InventoryItemRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class InventoryService {
    @Inject
    private InventoryItemRepository inventoryItemRepository;

    public InventoryListItem getAvailability(String skuId,String inventoryListId){
        return inventoryItemRepository.findBySkUIdAndListId(inventoryListId,skuId,Boolean.TRUE.booleanValue());
    }

    public List<InventoryListItem> getAvailabilityForAllSKUs(String inventoryListId) {
       return  inventoryItemRepository.findAllSKUs(inventoryListId);
    }


}
