package com.restapi.controller;


import com.restapi.model.Gadget;
import com.restapi.model.Role;
import com.restapi.response.common.APIResponse;
import com.restapi.service.GadgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/gadget")
//@PreAuthorize("hasRole('ROLE_USER')")
@RolesAllowed(Role.USER)
public class GadgetController {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private GadgetService gadgetService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllBooks() {
        List<Gadget> gadgetList = gadgetService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(gadgetList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


}
