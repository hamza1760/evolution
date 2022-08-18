package com.backendtest.dataservice.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;


@Getter
@Setter
public class ItemDTO {

    private UUID itemId;

    private String name;

    private Integer quantity;

    private Long upc;

    private String color;

    private Double price;

    private Double discount;
}
