package com.revivestyle.inventory.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "inventory_list_item_t")
@Data
public class InventoryListItem {
    @Id @GeneratedValue
    @Column(name="list_item_i")
    private Long listItemId;
    @Column(name="product_id")
    private String productId;
    @Column(name="sku_id")
    private String skuId;
    @Column(name="stock_level")
    private int stock_level;
    @Column(name="back_order_level")
    private int back_order_level;
    @Column(name="pre_order_level")
    private int pre_order_level;
    @Column(name="is_active")
    private boolean isActive;
    @Column(name="list_id")
    private long listId;

}
