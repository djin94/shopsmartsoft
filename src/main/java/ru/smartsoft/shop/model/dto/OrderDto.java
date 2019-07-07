package ru.smartsoft.shop.model.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import ru.smartsoft.shop.model.entity.BuyItem;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@JacksonXmlRootElement(localName = "Order")
public class OrderDto {

    private Long id;

    private Timestamp purchaseDate;

    private UserDto user;

    private double amount;

    @JacksonXmlElementWrapper(localName = "BuyItems")
    @JacksonXmlProperty(localName = "BuyItem")
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
