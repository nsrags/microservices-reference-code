package com.revivestyle.catalog.service;

import com.revivestyle.catalog.model.Category;
import com.revivestyle.catalog.model.Product;
import com.revivestyle.catalog.model.Sku;

import java.util.List;
import java.util.Set;

public interface CatalogService {

    Category findCategory(String categoryId);
    Product findProduct(String productId);
    List<Product> findActiveProductsByCategory(String parentCategoryName);
    Set<Sku> findActiveSKus(String productId);
    List<Category> getAllCategories();// Only Active

}
