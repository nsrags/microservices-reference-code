package com.revivestyle.catalog.service;

import com.revivestyle.catalog.model.Category;
import com.revivestyle.catalog.model.Product;
import com.revivestyle.catalog.model.Sku;
import com.revivestyle.catalog.repository.CategoryRepository;
import com.revivestyle.catalog.repository.ProductRepository;
import io.quarkus.arc.profile.IfBuildProfile;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Set;
@ApplicationScoped
public class CatalogServiceImpl implements  CatalogService{
    @Inject
    private CategoryRepository categoryRepository;
    @Inject
    private ProductRepository productRepository;
    @Override
    public Category findCategory(String categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public Product findProduct(String productId) {
        return productRepository.findById(productId);
    }


    @Override
    public List<Product> findActiveProductsByCategory(String parentCategoryName) {
        return productRepository.findProductsByCategory(parentCategoryName);

    }

    @Override
    public Set<Sku> findActiveSKus(String productId) {
        return productRepository.findChildSkus(productId);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findActiveCategories();
    }
}
