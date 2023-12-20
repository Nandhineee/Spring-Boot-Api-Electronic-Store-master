package com.restapi.dto;


import com.restapi.model.Gadget;
import com.restapi.request.GadgetRequest;
import com.restapi.response.GadgetResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GadgetDto {

    public GadgetResponse mapToBookResponse(List<Gadget> gadgetList) {
        return new GadgetResponse(gadgetList);
    }

    public Gadget mapToBook(GadgetRequest gadgetRequest) {
        Gadget gadget = new Gadget();
        if (gadgetRequest.getId() != null) {
            gadget.setId(gadgetRequest.getId());
        }
        gadget.setPrice(gadgetRequest.getPrice());
        gadget.setDescription(gadgetRequest.getDescription());
        gadget.setTitle(gadgetRequest.getTitle());
       // gadget.setPhoto(bookRequest.getPhoto());
        return gadget;
    }
}
