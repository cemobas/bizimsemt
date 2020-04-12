package com.krakus.bizimsemt.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;


@Document("orders")
@TypeAlias("order")
public class Order {

    @Id
    @Field("_id") private ObjectId id;
    @DBRef
    private Buyer buyer;
    @DBRef
    private Seller seller;
    @Field
    private List<Item> items;
    @Field
    private Date lastModified;

    public Order(ObjectId id, Buyer buyer, Seller seller, List<Item> items, Date lastModified) {
        this.id = id;
        this.buyer = buyer;
        this.seller = seller;
        this.items = items;
        this.lastModified = lastModified;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", buyer=" + buyer.toString() +
                ", seller=" + seller.toString() +
                ", items=" + items.toString() +
                ", lastModified=" + lastModified +
                '}';
    }
}
