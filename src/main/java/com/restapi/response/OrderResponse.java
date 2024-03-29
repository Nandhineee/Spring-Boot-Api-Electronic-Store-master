package com.restapi.response;


import com.restapi.model.Address;
import com.restapi.model.OrderedGadget;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private Long id;
    private List<OrderedGadget> gadgetList;
    private Long userId;
    private String name;
    private String username;
    private Address address;
    private String city;
    private int pincode;
    private Long orderStatusId;
    private String orderStatus;
}
