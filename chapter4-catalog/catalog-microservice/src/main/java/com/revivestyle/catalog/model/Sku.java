package com.revivestyle.catalog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sku {

    private ObjectId id;
    private String skuId;
    private String name;
    private Map<String,String> attributes = new HashMap();
    private boolean isActive;
}
