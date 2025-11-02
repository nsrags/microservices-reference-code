package com.revivestyle.inventory.repository;

import com.revivestyle.inventory.model.InventoryListItem;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class InventoryItemRepository implements PanacheRepository<InventoryListItem> {

    public InventoryListItem findBySkUIdAndListId(String listId,String skuId ,boolean isActive){
        return find("listId = ?1 AND skuId = ?2 AND isActive=?3", listId, skuId,isActive).singleResult();
    }

    public List<InventoryListItem> findAllSKUs(String listId){
        return find("listId = ?1" , listId).stream().toList();
    }
}
