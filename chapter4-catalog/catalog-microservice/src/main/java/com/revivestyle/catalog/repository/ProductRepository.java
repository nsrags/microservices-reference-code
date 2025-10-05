package com.revivestyle.catalog.repository;

import com.revivestyle.catalog.model.Category;
import com.revivestyle.catalog.model.Product;
import com.revivestyle.catalog.model.Sku;
import io.quarkus.logging.Log;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Set;

@ApplicationScoped
public class ProductRepository implements PanacheMongoRepository<Product> {
    public Product findById(String productId){
        Product productFound = find("_id",new ObjectId(productId)).firstResult();
        Log.info("Product Found in Product Repository :"+productFound);
        return productFound;
    }

    public List<Product> findProductsByCategory(String parentCategory){
        List<Product> childProducts = find("parentCategory",parentCategory).list();
        return  childProducts;
    }

    public Set<Sku> findChildSkus(String productId){
      Product productFound = find("_id",new ObjectId(productId)).firstResult();
     return productFound.getChildSkus();

    }
}
