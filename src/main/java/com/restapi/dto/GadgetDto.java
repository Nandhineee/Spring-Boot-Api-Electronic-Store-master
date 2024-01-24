package com.restapi.dto;


import com.restapi.model.Gadget;
import com.restapi.request.GadgetRequest;
import com.restapi.response.GadgetResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GadgetDto {

    public GadgetResponse mapToGadgetResponse(List<Gadget> gadgetList) {
        return new GadgetResponse(gadgetList);
    }

    public Gadget mapToGadget(GadgetRequest gadgetRequest) {
        Gadget gadget = new Gadget();
        if (gadgetRequest.getId() != null) {
            gadget.setId(gadgetRequest.getId());
        }
        gadget.setPrice(gadgetRequest.getPrice());
        gadget.setDescription(gadgetRequest.getDescription());
        gadget.setTitle(gadgetRequest.getTitle());
        gadget.setStock(gadgetRequest.getStock());
        gadget.setPhoto(gadgetRequest.getPhoto());
        return gadget;
    }
}
