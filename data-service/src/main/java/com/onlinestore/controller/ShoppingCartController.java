package com.onlinestore.controller;

import com.onlinestore.apiresponse.*;
import com.onlinestore.dto.*;
import com.onlinestore.service.*;
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

    @GetMapping("/cart/{cartId}")
    public ShoppingCartDTO findShoppingCart(@PathVariable int cartId){
        return shoppingCartService.findShoppingCart(cartId);
    }

    @GetMapping("/cart/save")
    public ShoppingCartDTO saveShoppingCart(@RequestBody ShoppingCartDTO cartDTO){
        return shoppingCartService.saveShoppingCart(cartDTO);
    }

    @DeleteMapping("/cart/{cartId}")
    public ResponseEntity<?> deleteShoppingCart(@PathVariable int cartId){
        shoppingCartService.deleteShoppingCart(cartId);
        return new ResponseEntity<>(new ApiResponse("Cart Deleted",cartId),HttpStatus.OK);
    }
}
