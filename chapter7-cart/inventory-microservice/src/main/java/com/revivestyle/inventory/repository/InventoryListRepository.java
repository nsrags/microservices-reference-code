package com.revivestyle.inventory.repository;

import com.revivestyle.inventory.model.InventoryList;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InventoryListRepository implements PanacheRepository<InventoryList> {

}
