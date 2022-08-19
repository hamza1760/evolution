package com.onlinestore.controller;

import com.onlinestore.constant.*;
import com.onlinestore.dto.*;
import com.onlinestore.exception.Exception;
import com.onlinestore.service.*;
import feign.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ItemController {

    @Autowired
    ItemServiceImpl itemService;

    @GetMapping("/item")
    public List<ItemDTO> getItemList() {
        return itemService.getItemList();
    }

    @PostMapping("/item")
    public ItemDTO addItem(@RequestBody ItemDTO itemDTO) {
        return itemService.addItem(itemDTO);
    }

    @PostMapping("/item/cart/{cartId}")
    public ResponseEntity<?> addItemToCart(@RequestBody ItemDTO itemDTO, @PathVariable int cartId) {
        itemService.addItemToCart(itemDTO, cartId);
        return new ResponseEntity<>("Item added to cart with cartId:" + " cartId", HttpStatus.OK);
    }
    @PutMapping("/item/{itemId}")
    public ResponseEntity<?> updateItemInCart(@RequestBody ItemDTO itemDTO, @PathVariable int itemId) {
        itemService.updateItemInCart(itemDTO, itemId);
        return new ResponseEntity<>("Item updated", HttpStatus.OK);
    }

    @DeleteMapping("item/{itemId}/remove")
    public ResponseEntity<?> removeItemFromCart(@PathVariable int itemId) {
        itemService.removeItemFromCart(itemId);
        return new ResponseEntity<>("Item removed from cart", HttpStatus.OK);
    }

    @DeleteMapping("/item/{itemId}")
    public ResponseEntity<?> deleteItemById(@PathVariable int itemId) {
        itemService.deleteItemById(itemId);
        return new ResponseEntity<>("Item Deleted", HttpStatus.OK);
    }
}
