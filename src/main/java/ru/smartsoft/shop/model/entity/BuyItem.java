package ru.smartsoft.shop.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "Buy_items")
public class BuyItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JacksonXmlProperty(isAttribute = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id", foreignKey = @ForeignKey(name = "Buy_items_fk0"))
    @JacksonXmlProperty(isAttribute = true)
    private Item item;

    @JsonBackReference(value="order")
    @ManyToOne
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "Buy_items_fk1"))
    private Order order;

    @Column
    @JacksonXmlProperty(isAttribute = true)
    private Integer count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuyItem buyItem = (BuyItem) o;
        return item.equals(buyItem.item) &&
                order.equals(buyItem.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, order);
    }

    @Override
    public String toString() {
        return "BuyItem{" +
                "id=" + id +
                ", count=" + count +
                '}';
    }
}
