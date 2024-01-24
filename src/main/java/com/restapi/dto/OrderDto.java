package com.restapi.dto;


import com.restapi.model.Order;
import com.restapi.model.OrderedGadget;
import com.restapi.repository.OrderedGadgetRepository;
import com.restapi.request.OrderRequest;
import com.restapi.response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDto {

    @Autowired
    private OrderedGadgetRepository orderedGadgetRepository;

//    public List<OrderResponse> mapToOrderResponse(List<Order> orderList) {
//        List<OrderResponse> orderResponseList = new ArrayList<>();
//        for (Order order : orderList) {
//            OrderResponse orderResponse = new OrderResponse();
//            orderResponse.setId(order.getId());
//            orderResponse.setUserId(order.getAppUser().getId());
//            orderResponse.setName(order.getAppUser().getName());
//            orderResponse.setUsername(order.getAppUser().getUsername());
//            orderResponse.setOrderStatus(order.getOrderStatus().getStatus());
//            orderResponse.setAddress(order.getAddress().getAddress());
//            orderResponse.setCity(order.getAddress().getCity());
//            orderResponse.setPincode(order.getAddress().getPincode());
//
//
//
//            System.out.println(order.getAddress().getAddress());
////            List<OrderedGadget> orderedGadgets=orderedGadgetRepository.findByOrderId(order.getId());
////            orderResponse.setGadgetList(orderedGadgets);
//            orderResponse.setGadgetList(order.getOrderedGadget());
//            orderResponseList.add(orderResponse);
//        }
//        return orderResponseList;
//    }

    public List<OrderResponse> mapToOrderResponse(List<Order> orderList) {
        List<OrderResponse> orderResponseList = new ArrayList<>();
        for (Order order : orderList) {
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setId(order.getId());
            orderResponse.setUserId(order.getAppUser().getId());
            orderResponse.setName(order.getAppUser().getName());
            orderResponse.setUsername(order.getAppUser().getUsername());
            orderResponse.setOrderStatusId(order.getOrderStatus().getId());
            orderResponse.setOrderStatus(order.getOrderStatus().getStatus());
            orderResponse.setAddress(order.getAddress());
//            List<OrderedGadget> orderedGadgets=orderedGadgetRepository.findByOrderId(order.getId());
//            orderResponse.setGadgetList(orderedGadgets);
            orderResponse.setGadgetList(order.getOrderedGadget());
            orderResponseList.add(orderResponse);
        }
        return orderResponseList;
    }



    public List<OrderResponse> mapToOrder(List<Order> orderList,Long userId) {
        List<OrderResponse> orderResponseList = new ArrayList<>();

        for (Order order : orderList) {
            if (order.getAppUser().getId().equals(userId)) {
                OrderResponse orderResponse = new OrderResponse();
                orderResponse.setId(order.getId());
                orderResponse.setUserId(order.getAppUser().getId());
                orderResponse.setName(order.getAppUser().getName());
                orderResponse.setUsername(order.getAppUser().getUsername());
                orderResponse.setOrderStatus(order.getOrderStatus().getStatus());
    //                orderResponse.setAddress(order.getAddress());
                List<OrderedGadget> orderedGadgets=orderedGadgetRepository.findByOrderId(order.getId());
                orderResponse.setGadgetList(orderedGadgets);
                orderResponseList.add(orderResponse);
            }
        }

        return orderResponseList;
    }
}
;