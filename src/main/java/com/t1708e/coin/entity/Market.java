package com.t1708e.coin.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;

@Entity
public class Market {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "market", cascade = CascadeType.ALL)
    private Set<Coin> coins;

    private long createdAt;
    private long updatedAt;
    private long deleteddAt;
    private int status;


    public Market() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public long getDeleteddAt() {
        return deleteddAt;
    }

    public void setDeleteddAt(long deleteddAt) {
        this.deleteddAt = deleteddAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Set<Coin> getCoins() {
        return coins;
    }

    public void setCoins(Set<Coin> coins) {
        this.coins = coins;
    }
}
