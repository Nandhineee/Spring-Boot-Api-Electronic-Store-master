package com.restapi.controller;



import com.restapi.model.Role;
import com.restapi.request.CartRequest;
import com.restapi.response.CartResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.net.CacheResponse;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
//@PreAuthorize("hasRole('ROLE_USER')")
@RolesAllowed(Role.USER)
public class CartController {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<APIResponse> getUsersCart(@PathVariable Long userId) {
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(cartService.findUserCart(userId));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<APIResponse> addToCart(@Valid @RequestBody CartRequest cartRequest) {
        List<CartResponse> cartResponseList=cartService.addToCart(cartRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(cartResponseList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/{gadgetCart}")
    public ResponseEntity<APIResponse> deleteBookFromCart(@PathVariable Long userId,
                                                          @PathVariable Long gadgetCart) {
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(cartService.deleteGadgetFromCart(userId, gadgetCart));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
