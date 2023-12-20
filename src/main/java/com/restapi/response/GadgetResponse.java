package com.restapi.response;


import com.restapi.model.Gadget;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
 public class GadgetResponse {

    private List<Gadget> gadgetList;
}
