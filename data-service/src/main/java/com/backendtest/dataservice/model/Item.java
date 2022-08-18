package com.backendtest.dataservice.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue
    private UUID itemId;


    @NonNull
    private String name;

    @NonNull
    private Integer quantity;

    @NonNull
    @Column(unique = true)
    private Long upc;

    @NonNull
    private String color;

    @NonNull
    private Double price;

    @NonNull
    private Double discount;
}
