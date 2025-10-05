package com.revivestyle.cart.repository;

import com.revivestyle.cart.model.CartLineItem;
import com.revivestyle.cart.model.Customer;
import com.revivestyle.cart.model.ShoppingCart;
import io.agroal.api.AgroalDataSource;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class ShoppingCartRepository {

    @Inject
    AgroalDataSource cartDatasource;

    public ShoppingCart addLineItemsToCart(Set<CartLineItem> lineItems , Long cartId){
        ShoppingCart existingCart  = findCartById(cartId);
        Long generatedId = null;
        try (Connection connection = cartDatasource.getConnection()) {
            String sql = "INSERT INTO cart_line_item_t (cart_id,sku_id,product_id,qty,product_n,list_price,sale_price) VALUES (?, ?,?,?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
                connection.setAutoCommit(Boolean.FALSE.booleanValue());
                for(CartLineItem eachLineItem:lineItems){
                    statement.setLong(1,cartId);
                    statement.setString(2,eachLineItem.getSkuId());
                    statement.setString(3,eachLineItem.getProductId());
                    statement.setLong(4,eachLineItem.getQuantity());
                    statement.setString(5,eachLineItem.getProductName());
                    statement.setDouble(6,eachLineItem.getListPrice());
                    statement.setDouble(7,eachLineItem.getSalePrice());
                    statement.addBatch();
                }
               int[] affectedRows =  statement.executeBatch();
                Log.info("Affected Rows ..."+affectedRows.length);
                if(affectedRows.length>0){
                    try (ResultSet rs = statement.getGeneratedKeys()) {
                        while (rs.next()) {
                            CartLineItem lineItem = new CartLineItem(
                                    rs.getLong("cart_line_item_id"),
                                    rs.getString("product_id"),
                                            rs.getString("product_n"),
                                            rs.getString("sku_id"),
                                            rs.getLong("qty"),
                                            rs.getDouble("sale_price"),
                                            rs.getDouble("list_price")
                                    );
                            existingCart.getLineItems().add(lineItem);
                        }
                    }
                }
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Log.info("Returning shopping cart .."+existingCart);
        return existingCart;
    }

    @Transactional
    public ShoppingCart addLineItemToCart(CartLineItem lineItem,Long cartId){
        ShoppingCart existingCart  = findCartById(cartId);
        Long generatedId = null;
        try (Connection connection = cartDatasource.getConnection()) {
            String sql = "INSERT INTO cart_line_item_t (cart_id,sku_id,product_id,qty,product_n) VALUES (?, ?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
                statement.setLong(1,cartId);
                statement.setString(2,lineItem.getSkuId());
                statement.setString(3,lineItem.getProductId());
                statement.setLong(4,lineItem.getQuantity());
                statement.setString(5,lineItem.getProductName());
                int affectedRows =  statement.executeUpdate();
                if(affectedRows>0){
                    try (ResultSet rs = statement.getGeneratedKeys()) {
                        if (rs.next()) {
                            generatedId = rs.getLong(1);
                        }
                    }
                }
                CartLineItem newLineItem = new CartLineItem(generatedId,lineItem.getProductId(),lineItem.getProductName(), lineItem.getSkuId(),lineItem.getQuantity(), lineItem.getSalePrice(), lineItem.getListPrice());
                existingCart.getLineItems().add(newLineItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return existingCart;
    }

    @Transactional
    public ShoppingCart createNewCart(Long customerId) {
        ShoppingCart existingCart  = findCartByCustomer(customerId);
        Long generatedId = null ;
        if (null == existingCart){
            Log.info("ShoppingCartRepository:: CreateNewCart:: NO Cart Exists hence created new one ");
            try (Connection connection = cartDatasource.getConnection()) {
                String sql = "INSERT INTO cart_t (cart_created_by, customer_id,cart_created_ts) VALUES (?, ?,?)";
                try (PreparedStatement statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
                    statement.setString(1,String.valueOf(customerId));
                    statement.setLong(2,customerId);
                    statement.setDate(3,Date.valueOf(LocalDate.now()));
                   int affectedRows =  statement.executeUpdate();
                   if(affectedRows>0){
                       try (ResultSet rs = statement.getGeneratedKeys()) {
                           if (rs.next()) {
                               generatedId = rs.getLong(1);
                           }
                       }
                   }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ShoppingCart newCart = new ShoppingCart(generatedId);
            newCart.setCreatedBy(String.valueOf(customerId));
            newCart.setCustomer(new Customer(customerId));
            return newCart;
        }
        Log.info("ShoppingCartRepository:: CreateNewCart:: Cart Already Exists hence return existing one ");

        return existingCart;
    }

    public ShoppingCart findCartById(Long cartId){
        ShoppingCart fetchedCart = null;
        String cartSQL = "SELECT * from cart_t where cart_id=?";
        try (Connection connection = cartDatasource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(cartSQL)) {
            preparedStatement.setLong(1, cartId); // Set the parameter for the placeholder
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    fetchedCart= new ShoppingCart(resultSet.getLong("cart_id"));
                    fetchedCart.setCustomerId(resultSet.getLong("customer_id"));
                    fetchedCart.setCreatedBy(resultSet.getString("cart_created_by"));
                    Log.infof("ShoppingCartRepository:: findByCartId returns cart with Id '%s'",fetchedCart.getCartId());
                }
            }
            //TODO: Need to fetch the Line Items ..
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return fetchedCart;
    }

    public ShoppingCart findCartByCustomer(Long customerId){
        ShoppingCart fetchedCart = null;
        String cartSQL = "SELECT * from cart_t where customer_id=?";
        try (Connection connection = cartDatasource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(cartSQL)) {
            preparedStatement.setLong(1, customerId); // Set the parameter for the placeholder
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    fetchedCart= new ShoppingCart(resultSet.getLong("cart_id"));
                    fetchedCart.setCustomerId(resultSet.getLong("customer_id"));
                    fetchedCart.setCreatedBy(resultSet.getString("cart_created_by"));
                    Log.infof("ShoppingCartRepository:: findCartByCustomer returns cart with Id '%s'",fetchedCart.getCartId());
                }
            }
            //TODO: Need to fetch the Line Items ..
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fetchedCart;
    }

}
