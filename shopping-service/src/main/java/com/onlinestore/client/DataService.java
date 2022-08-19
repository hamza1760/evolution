package com.onlinestore.client;

import com.onlinestore.dto.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Component
@FeignClient(name ="data-service", url = "localhost:8181")
public interface DataService {

    @GetMapping("/item")
    List<ItemDTO> getItemList();

    @PostMapping("/item")
    ItemDTO addItem(@RequestBody ItemDTO itemDTO);


    @GetMapping("/item/{itemId}")
    ItemDTO findItem(@PathVariable int itemId);


    @DeleteMapping("/item/{itemId}")
    ResponseEntity<?> deleteItem(@PathVariable int itemId);

    @PostMapping("/cart")
    ShoppingCartDTO addCart(@RequestBody ShoppingCartDTO shoppingCartDTO);

    @GetMapping("/cart")
    List<ShoppingCartDTO> viewCartList();

    @GetMapping("/cart/{cartId}")
    ShoppingCartDTO findShoppingCart(@PathVariable int cartId);


    @DeleteMapping("/cart/{cartId}")
    ResponseEntity<?> deleteShoppingCart(@PathVariable int cartId);



}
