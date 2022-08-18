package com.backendtest.shoppingservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ItemDTO {

    private UUID itemId;

    private String name;

    private Integer quantity;

    private String color;

    private Long upc;

    private Double price;

    private Double discount;
}
