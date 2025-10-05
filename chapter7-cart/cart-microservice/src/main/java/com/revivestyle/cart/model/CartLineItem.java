package com.revivestyle.cart.model;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartLineItem  implements Serializable {
    @Setter(AccessLevel.NONE)
    @NonNull
    private Long lineItemId;
    private String productId;
    private String productName;
    private String skuId;
    private long quantity;
    private double salePrice;
    private double listPrice;
}
