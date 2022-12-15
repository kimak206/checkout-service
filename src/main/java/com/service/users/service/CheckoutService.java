package com.service.users.service;

import com.service.users.entity.Checkout;
import com.service.users.repository.CheckoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CheckoutService {
    @Autowired
    CheckoutRepository repository;

    public Checkout saveCheckout(Checkout checkout){
        return repository.save(checkout);
    }

    public Optional<Checkout> getData(int id) {
        return repository.findById(id);
    }
}
