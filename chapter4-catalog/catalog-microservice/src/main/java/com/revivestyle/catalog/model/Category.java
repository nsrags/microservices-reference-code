package com.revivestyle.catalog.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(collection="categories")
public class Category {

    @BsonId
    private ObjectId id;
    private String categoryId;
    private String name;
    private String description;
    private boolean isActive;
    private boolean rootCategory;
    private Set<Product> childProducts = new HashSet<Product>();
}
