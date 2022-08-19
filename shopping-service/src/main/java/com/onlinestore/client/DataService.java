package com.onlinestore.client;

import com.onlinestore.dto.*;
import org.springframework.cloud.openfeign.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Component
@FeignClient(name ="data-service", url = "localhost:8081")
public interface DataService {

    @GetMapping("/item")
    public List<ItemDTO> getItemList();

    @PostMapping("/item")
    public ItemDTO addItem(@RequestBody ItemDTO itemDTO);


    @GetMapping("/item/{itemId}")
    public ItemDTO findItem(@PathVariable int itemId);

    @GetMapping("/item/save")
    public ItemDTO saveItem(@RequestBody ItemDTO itemDTO);

    @DeleteMapping("/item/{itemId}")
    public ResponseEntity<?> deleteItem(@PathVariable int itemId);

    @PostMapping("/cart")
    public ShoppingCartDTO addCart(@RequestBody ShoppingCartDTO shoppingCartDTO);

    @GetMapping("/cart")
    public List<ShoppingCartDTO> viewCartList();

    @GetMapping("/cart/{cartId}")
    public ShoppingCartDTO findShoppingCart(@PathVariable int cartId);

    @GetMapping("/cart/save")
    public ShoppingCartDTO saveShoppingCart(@RequestBody ShoppingCartDTO cartDTO);

    @DeleteMapping("/cart/{cartId}")
    public ResponseEntity<?> deleteShoppingCart(@PathVariable int cartId);



}
