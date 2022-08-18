package com.onlinestore.controller;

import com.onlinestore.dto.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ShoppingCartController {

    @Autowired
    ShoppingCartServiceImpl shoppingCartService;

    @PostMapping("/cart")
    public ShoppingCartDTO addCart(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        return shoppingCartService.addCart(shoppingCartDTO);
    }

    @GetMapping("/cart")
    public List<ShoppingCartDTO> viewCartList() {
        return shoppingCartService.viewCartList();
    }

    @DeleteMapping("/cart/{cartId}/empty")
    public ShoppingCartDTO removeAllItemsFromCart(@PathVariable int cartId) {
        return shoppingCartService.removeAllItemsFromCart(cartId);
    }

    @DeleteMapping("/cart/{cartId}")
    public ResponseEntity<?> deleteShoppingCartById(@PathVariable int cartId) {
        shoppingCartService.deleteShoppingCartById(cartId);
        return new ResponseEntity<>("ShoppingCart Deleted", HttpStatus.OK);
    }
}
