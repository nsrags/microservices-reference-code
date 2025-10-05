package com.revivestyle.catalog.repository;

import com.revivestyle.catalog.model.Category;
import io.quarkus.logging.Log;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.util.List;

@ApplicationScoped
public class CategoryRepository implements PanacheMongoRepository<Category> {

    public Category findById(String categoryId){
        Category categoryFound = find("_id",new ObjectId(categoryId)).firstResult();
        Log.info("Category Found in Category Repository :"+categoryFound);
        return categoryFound;
    }

    public List<Category> findActiveCategories(){
        List<Category> activeCategories = find("rootCategory",false).list();
        return activeCategories;
    }

}
