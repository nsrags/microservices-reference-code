package com.revivestyle.order.service;

import com.revivestyle.order.model.Order;
import com.revivestyle.order.model.PaymentInstrument;
import com.revivestyle.order.repository.OrderRepository;
import io.quarkus.arc.profile.IfBuildProfile;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class OrderService {

    @Inject
    private OrderRepository orderRepository;

    public Order getOrder(long orderId){
        return orderRepository.findOrder(orderId);
    }
    public Order cancelOrder(long orderId){
        return orderRepository.cancelOrder(orderId);
    }

    public Order createOrder(Order orderToBeCreated){
        return null;
    }


    public Order addPaymentInstrumentToOrder(PaymentInstrument paymentInstrument , long orderId){
       return orderRepository.addPayment(paymentInstrument,orderId);
    }

}
