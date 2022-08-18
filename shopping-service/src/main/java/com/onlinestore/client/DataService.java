package com.onlinestore.client;


import com.onlinestore.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Component
@FeignClient(name = "data-service", url = "localhost:8081")
public interface DataService {

    @GetMapping("/item")
    public List<ItemDTO> getItemList();

    @PostMapping("/item")
    public ItemDTO addItem(@RequestBody ItemDTO itemDTO);

    @PostMapping("/item/cart/{cartId}")
    public ResponseEntity<?> addItemToCart(@RequestBody ItemDTO itemDTO, @PathVariable int cartId);

    @PutMapping("/item/{itemId}")
    public ResponseEntity<?> updateItemInCart(@RequestBody ItemDTO itemDTO, @PathVariable int itemId);

    @DeleteMapping("item/{itemId}/remove")
    public ResponseEntity<?> removeItemFromCart(@PathVariable int itemId);

    @DeleteMapping("/item/{itemId}")
    public ResponseEntity<?> deleteItemById(@PathVariable int itemId);

    @PostMapping("/cart")
    public ShoppingCartDTO addCart(@RequestBody ShoppingCartDTO shoppingCartDTO);

    @GetMapping("/cart")
    public List<ShoppingCartDTO> viewCartList();

    @DeleteMapping("/cart/{cartId}/empty")
    public ShoppingCartDTO removeAllItemsFromCart(@PathVariable int cartId);

    @DeleteMapping("/cart/{cartId}")
    public ResponseEntity<?> deleteShoppingCartById(@PathVariable int cartId);


}
