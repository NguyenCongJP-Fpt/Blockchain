package com.t1708e.coin.dto;

import com.t1708e.coin.entity.Coin;
import com.t1708e.coin.entity.Market;
import com.t1708e.coin.util.DateTimeUtil;
import com.t1708e.coin.util.ObjectUtil;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

public class MarketDTO {
    private int id;
    private String name;
    private String description;
    private String createdAt;
    private String updatedAt;
    private String deleteddAt;
    private String status;

    public MarketDTO() {
    }

    public MarketDTO(Market market) {
        this.id = market.getId();
        this.name = market.getName();
        this.description = market.getDescription();
        ObjectUtil.cloneObject(this, market);
        this.createdAt = DateTimeUtil.formatDateFromLong(market.getCreatedAt());
        this.updatedAt = DateTimeUtil.formatDateFromLong(market.getCreatedAt());
        this.status = market.getStatus() == 1 ? "Active" : "Deactive";
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDeleteddAt() {
        return deleteddAt;
    }

    public void setDeleteddAt(String deleteddAt) {
        this.deleteddAt = deleteddAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
