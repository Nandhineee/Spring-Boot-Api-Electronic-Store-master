package com.restapi.controller.admin;


import com.restapi.model.Gadget;
import com.restapi.model.Role;
import com.restapi.request.GadgetRequest;
import com.restapi.response.common.APIResponse;
import com.restapi.service.GadgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/admin/gadget")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
@RolesAllowed(Role.ADMIN)
public class AdminGadgetController {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private GadgetService gadgetService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllGadgets() {
        List<Gadget> gadgetList = gadgetService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(gadgetList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<APIResponse> createGadget(@RequestBody GadgetRequest gadgetRequest) {
        List<Gadget> gadgetList = gadgetService.createBook(gadgetRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(gadgetList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<APIResponse> updateGadget(@RequestBody GadgetRequest gadgetRequest) {
        List<Gadget> gadgetList =gadgetService.updateBook(gadgetRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(gadgetList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteGadget(@PathVariable Integer id) {
        List<Gadget> gadgetList = gadgetService.deleteById(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(gadgetList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
