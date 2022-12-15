package com.service.users.controller;

import com.service.users.entity.Checkout;
import com.service.users.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    CheckoutService service;

    @PostMapping
    public ResponseEntity<Checkout> saveCheckout(@RequestBody Checkout request) {
        Checkout checkout = service.saveCheckout(request);
        return new ResponseEntity<Checkout>(checkout, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getCheckout(@PathVariable("id") int checkoutId) {
        Optional<Checkout> checkout = service.getData(checkoutId);
        if(!checkout.isPresent()) {
            return new ResponseEntity("Not Found!", HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity(checkout, HttpStatus.OK);
        }
    }
}
