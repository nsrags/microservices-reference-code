package com.revivestyle.order.repository;

import com.revivestyle.order.model.Order;
import com.revivestyle.order.model.OrderStatus;
import com.revivestyle.order.model.PaymentInstrument;
import io.agroal.api.AgroalDataSource;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.sql.*;
import java.time.LocalDate;

@ApplicationScoped
public class OrderRepository {

    @Inject
    private AgroalDataSource orderDataSource;

    public Order cancelOrder(long orderId){
        String sql = "UPDATE order_t SET order_status = ? WHERE order_id = ?";
        try (Connection conn = orderDataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, OrderStatus.CANCELLED.name());
            preparedStatement.setLong(2, orderId);
            int count = preparedStatement.executeUpdate();
            Log.infof("Order Cancelled Successfully for OrderID %s ",orderId);
            if(count>0)
                return this.findOrder(orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Order findOrder(long orderID) {
        Order fetchedOrder = null;
        String cartSQL = "SELECT * from order_t where order_id=?";
        try (Connection connection = orderDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(cartSQL)) {
            preparedStatement.setLong(1, orderID); // Set the parameter for the placeholder
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    fetchedOrder = new Order(resultSet.getLong("order_id"));
                    fetchedOrder.setOrderTotal(resultSet.getDouble("order_total"));
                    fetchedOrder.setShippingTotal(resultSet.getDouble("shipping_total"));
                    fetchedOrder.setTotalTax(resultSet.getDouble("tax_total"));
                    fetchedOrder.setGuest(resultSet.getBoolean("is_guest"));
                    fetchedOrder.setOrderStatus(OrderStatus.valueOf(resultSet.getString("order_status")));
                    fetchedOrder.setCustomerId(resultSet.getLong("customer_id"));

                    Log.infof("OrderRepository:: findOrderbyId returns Order  with Id '%s'", orderID);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fetchedOrder;
    }

        @Transactional
    public Order createOrder(Order orderToBeCreated){
        return null;
    }

    public Order addPayment(PaymentInstrument paymentToAdd,long orderId){
        Long generatedId=null;
        try (Connection connection = orderDataSource.getConnection()) {
            String sql = "INSERT INTO payment_t (payment_method_type, expiration_year,expiration_month,issue_number,card_type,order_id) VALUES (?, ?,?,?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1,paymentToAdd.getPaymentInstrumentType().name());
                preparedStatement.setString(2,paymentToAdd.getExpirationYear());
                preparedStatement.setString(3,paymentToAdd.getExpirationMonth());
                preparedStatement.setString(4,paymentToAdd.getIssueNumber());
                preparedStatement.setString(5,paymentToAdd.getCardType());
                preparedStatement.setLong(6,orderId);
                int affectedRows =  preparedStatement.executeUpdate();
                if(affectedRows>0){
                    try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                        if (rs.next()) {
                            generatedId = rs.getLong(1);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.findOrder(orderId);
    }
}
