package com.onlinestore.entity;

import com.onlinestore.constant.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cartId;
    private int itemCount;
    private double grandTotal;
    private double totalDiscount;
    private String status = Constants.ACTIVE.getValue();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cart")
    private List<Item> itemList;

    public ShoppingCart() {
    }

    public ShoppingCart(int cartId, int itemCount, double grandTotal, double totalDiscount, String status, List<Item> itemList) {
        this.cartId = cartId;
        this.itemCount = itemCount;
        this.grandTotal = grandTotal;
        this.totalDiscount = totalDiscount;
        this.status = status;
        this.itemList = itemList;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "cartId=" + cartId +
                ", itemCount=" + itemCount +
                ", grandTotal=" + grandTotal +
                ", totalDiscount=" + totalDiscount +
                ", status='" + status + '\'' +
                ", itemList=" + itemList +
                '}';
    }
}
