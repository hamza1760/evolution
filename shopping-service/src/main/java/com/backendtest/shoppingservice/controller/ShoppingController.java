package com.backendtest.shoppingservice.controller;

import com.backendtest.shoppingservice.dto.ItemDTO;
import com.backendtest.shoppingservice.dto.ShoppingCartDTO;
import com.backendtest.shoppingservice.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/1.0/shopping")
public class ShoppingController {

    private ShoppingService shoppingService;

    @PostMapping
    ResponseEntity<ShoppingCartDTO> createNewCart(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        return ResponseEntity.ok(shoppingService.createNewCart(shoppingCartDTO));
    }

    @GetMapping
    ResponseEntity<ShoppingCartDTO> getCart(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        return ResponseEntity.ok(shoppingService.getCart(shoppingCartDTO));
    }

    @GetMapping("/{cartId}/items")
    ResponseEntity<List<ItemDTO>> getCartItems(@PathVariable String cartId) {
        return ResponseEntity.ok(shoppingService.getCartItems(cartId));
    }

    @PutMapping("/{cartId}")
    ResponseEntity<ShoppingCartDTO> updateCartItems(@PathVariable String cartId, @RequestBody ItemDTO itemsDTO) {
        return ResponseEntity.ok(shoppingService.updateCartItems(cartId, itemsDTO));
    }

    @PutMapping("/{cartId}/{itemId}")
    ResponseEntity<ShoppingCartDTO> removeCartItems(@PathVariable String cartId, @PathVariable String itemId) {
        return ResponseEntity.ok(shoppingService.removeCartItems(cartId, itemId));
    }

    @Autowired
    public void setShoppingService(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }
}
