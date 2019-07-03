package ru.smartsoft.shop.model.dto;

import ru.smartsoft.shop.model.entity.BuyItem;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderDto {

    private Long id;

    private Timestamp purchaseDate;

    private UserDto user;

    private double amount;

    private List<BuyItem> buyItems = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public List<BuyItem> getBuyItems() {
        return buyItems;
    }

    public void setBuyItems(List<BuyItem> buyItems) {
        this.buyItems = buyItems;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
