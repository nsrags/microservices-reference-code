package com.revivestyle.catalog.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MongoEntity(collection="products")
public class Product {

    @BsonId
    private ObjectId id;
    private String productId;
    private String name;
    private String description;
    private boolean isActive;
    private String parentCategory;
    private Set<Sku> childSkus = new HashSet<>();
}
