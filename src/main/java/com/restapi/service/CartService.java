package com.restapi.service;


import com.restapi.dto.CartDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;

import com.restapi.model.Cart;

import com.restapi.model.Gadget;
import com.restapi.repository.CartRepository;
import com.restapi.repository.GadgetRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.CartRequest;
import com.restapi.response.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GadgetRepository gadgetRepository;
    @Autowired
    private CartDto cartDto;

    @Transactional
    public List<CartResponse> addToCart(CartRequest cartRequest) {
        AppUser appUser = userRepository.findById(cartRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("userId", "userId", cartRequest.getUserId()));//userid

        Gadget gadget = gadgetRepository.findById(cartRequest.getGadgetId())
                .orElseThrow(() -> new ResourceNotFoundException("gadgetId", "gadgetId", cartRequest.getGadgetId()));//productid

        Optional<Cart> cartOptional = cartRepository.deleteByAppUserAndGadget(appUser.getId(), gadget.getId());//for query

        if (cartOptional.isPresent()) {
            System.out.println("optional Cart Came" + cartOptional);
            Cart cart = new Cart();
            System.out.println(cartOptional.get().getId());
            cart.setId(cartOptional.get().getId());
            cart.setAppUser(appUser);
            cart.setGadget(gadget);
            if (cartRequest.getCount() != null) {
                cart.setCount(cartRequest.getCount());
            } else {
                cart.setCount(cartOptional.get().getCount() + 1);
            }
            cartRepository.save(cart);
        } else {
            System.out.println("good");
            System.out.println("else block came");
            Cart cart = new Cart();
            cart.setAppUser(appUser);
            cart.setGadget(gadget);
            cart.setCount(1);
            cartRepository.save(cart);
        }
        //checking cart present
//        if (cartOptional.isPresent()) {
//            boolean ispresent = false;
//            for (Cart cart : cartOptional.get()) {
//                if ((cart.getGadget().getId()) == cartRequest.getGadgetId()) {
//                    cart.setCount(cartRequest.getCount());//set count
//                    cartRepository.save(cart);
//                    ispresent = true;
//
//                }
//            }
//            //cart not present
//            if (!ispresent) {
//                Cart cart = new Cart();
//                cart.setAppUser(appUser);
//                cart.setGadget(gadget);
//                cart.setCount(cartRequest.getCount());
//                cartRepository.save(cart);
//            }
//        } else {
//            Cart cart = new Cart();
//            cart.setAppUser(appUser);
//            cart.setGadget(gadget);
//            cart.setCount(cartRequest.getCount());
//            cartRepository.save(cart);
//        }
        return findUserCart(cartRequest.getUserId());
    }

    public List<CartResponse> findUserCart(Long userId) {
        List<Cart> cart = cartRepository.findUserCart(userId).orElseThrow(() -> new ResourceNotFoundException("userId", "userId", userId));
        return cartDto.mapToCart(cart);
    }

    public List<CartResponse> deleteGadgetFromCart(Long userId, Long gadgetId) {
        Optional<Cart> optionalCart = cartRepository.deleteByAppUserAndGadget(userId, gadgetId);
        cartRepository.deleteById(optionalCart.get().getId());
        return findUserCart(userId);
    }
}
