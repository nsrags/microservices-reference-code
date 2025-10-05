package com.revivestyle.catalog.web;

import com.revivestyle.catalog.model.Category;
import com.revivestyle.catalog.model.Product;
import com.revivestyle.catalog.model.Sku;
import com.revivestyle.catalog.service.CatalogService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.xml.catalog.Catalog;
import java.util.List;
import java.util.Set;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class CatalogController {

    @Inject
    private CatalogService catalogService;

    @GET
    @Path("/categories/all")
    public Response getAllCategories(){
        List<Category> activeCategories = catalogService.getAllCategories();
        return Response.ok(activeCategories).build();
    }

    @GET
    @Path("/category/{categoryId}")
    public Response getCategory(@PathParam("categoryId") String categoryId){
        Category category = catalogService.findCategory(categoryId);
        return Response.ok(category).build();
    }
    @GET
    @Path("/products/{parentCategory}")
    public Response getProducts(@PathParam("parentCategory") String parentCategoryName){
       List<Product> childProducts = catalogService.findActiveProductsByCategory(parentCategoryName);
        return Response.ok(childProducts).build();
    }
    @GET
    @Path("/skus/{productId}")
    public Response getSkus(@PathParam("productId") String productId){
        Set<Sku> childSkus  = catalogService.findActiveSKus(productId);
        return Response.ok(childSkus).build();
    }
}
