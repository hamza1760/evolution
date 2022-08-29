package com.onlinestore.client;

import com.onlinestore.dto.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Component
@FeignClient(name = "data-service", url = "localhost:8181")
public interface DataService {

    @GetMapping("/item")
    List<ItemDTO> getItemList();

    @PostMapping("/item")
    ItemDTO saveItem(@RequestBody ItemDTO itemDTO);

    @PostMapping("/item/{cartId}")
    ItemDTO addItemToCart(@RequestBody ItemDTO itemDTO, @PathVariable int cartId);

    @GetMapping("/item/{itemId}")
    ItemDTO findItem(@PathVariable int itemId);

    @DeleteMapping("/item/{itemId}")
    ResponseEntity<?> deleteItem(@PathVariable int itemId);

    @PostMapping("/cart")
    ShoppingCartDTO saveCart(@RequestBody ShoppingCartDTO shoppingCartDTO);

    @GetMapping("/cart")
    List<ShoppingCartDTO> viewCartList();

    @GetMapping("/cart/{cartId}")
    ShoppingCartDTO findShoppingCart(@PathVariable int cartId);

    @DeleteMapping("/cart/{cartId}")
    ResponseEntity<?> deleteShoppingCart(@PathVariable int cartId);
}
