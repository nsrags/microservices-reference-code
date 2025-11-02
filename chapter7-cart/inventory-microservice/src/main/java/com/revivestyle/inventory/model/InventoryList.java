package com.revivestyle.inventory.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "inventory_list_t")
@Data
public class InventoryList {
    @Id @GeneratedValue
    @Column(name="inventory_list_id")
    private Long listId;
    @Column(name="list_n")
    private String listName;
    @Column(name="list_desc_t")
    private String listDescription;
    @Column(name="is_active")
    private boolean isActive;
    @Column(name="start_date")
    private Date startDate;
    @Column(name="end_date")
    private Date endDate;
    @OneToMany(mappedBy = "listId")
    private List<InventoryListItem> inventoryItems = new ArrayList<InventoryListItem>();
}
