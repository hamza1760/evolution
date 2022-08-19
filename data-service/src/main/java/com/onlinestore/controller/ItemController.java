package com.onlinestore.controller;

import com.onlinestore.apiresponse.*;
import com.onlinestore.dto.*;
import com.onlinestore.entity.*;
import com.onlinestore.service.*;
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

   @GetMapping("/item/{itemId}")
   public ItemDTO findItem(@PathVariable int itemId){
        return itemService.findItem(itemId);
   }

   @DeleteMapping("/item/{itemId}")
    public ResponseEntity<?> deleteItem(@PathVariable int itemId){
        itemService.deleteItem(itemId);
        return new ResponseEntity<>(new ApiResponse("Item Deleted",itemId),HttpStatus.OK);
   }
}
