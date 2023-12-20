package com.restapi.service;


import com.restapi.dto.OrderDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.*;
import com.restapi.repository.*;
import com.restapi.request.OrderRequest;
import com.restapi.response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderDto orderDto;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderedGadgetRepository orderedGadgetRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Transactional
    public List<OrderResponse> placeOrder(OrderRequest orderRequest) {
        AppUser appUser = userRepository.findById(orderRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("userId", "userId", orderRequest.getUserId()));

        Address address = addressRepository.findById(orderRequest.getAddressId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("addressId", "addressId",orderRequest.getAddressId()));

        OrderStatus orderStatus = orderStatusRepository.findById(1L)
                .orElseThrow(() ->
                        new ResourceNotFoundException("orderStatusId", "orderStatusId", 1));

        List<Cart> cartList = cartRepository.findUserCart(orderRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("userId", "userId", orderRequest.getUserId()));

        Order order = new Order();
        order.setAddress(address);
        order.setOrderStatus(orderStatus);
        order.setAppUser(appUser);

        order = orderRepository.save(order);

        for (Cart cart : cartList) {
            OrderedGadget orderedGadget = new OrderedGadget();
            orderedGadget.setOrder(order);
            orderedGadget.setTitle(cart.getGadget().getTitle());
            orderedGadget.setDescription(cart.getGadget().getDescription());
            orderedGadget.setPrice(cart.getGadget().getPrice());
            orderedGadget.setCount(cart.getCount());
            orderedGadgetRepository.save(orderedGadget);
            cartRepository.deleteById(cart.getId());
        }

        return getUserOrders(appUser.getId());
    }

    public List<OrderResponse> getUserOrders(Long userId) {
        List<Order> orderList = orderRepository.findUserOrder(userId)
                .orElseThrow(() -> new ResourceNotFoundException("userId", "userId", userId));
        return orderDto.mapToOrderResponse(orderList);
    }

    public List<OrderResponse> getAllOrders() {
        List<Order> orderList = orderRepository.findAll();
        return orderDto.mapToOrderResponse(orderList);
    }

    public List<OrderStatus> getAllOrderStatus() {
        return orderStatusRepository.findAll();
    }

    public List<OrderResponse> updateOrderStatus(Long orderId, Long statusId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("orderId", "orderId", orderId));

        OrderStatus orderStatus = orderStatusRepository.findById(statusId)
                .orElseThrow(() -> new ResourceNotFoundException("statusId", "statusId", statusId));

        order.setOrderStatus(orderStatus);

        orderRepository.save(order);

        return getAllOrders();
    }

}
