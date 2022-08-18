package com.backendtest.dataservice.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.id.GUIDGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class ShoppingCart {

    @Id
    @GeneratedValue
    private UUID cartId;

    @NonNull
    private Integer itemCount;

    @NonNull
    private Double grandTotal;

    @NonNull
    private Double totalDiscount;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(joinColumns = {@JoinColumn(name = "cartId")},
            inverseJoinColumns = {@JoinColumn(name = "itemId")})
    private List<Item> itemList;

}
