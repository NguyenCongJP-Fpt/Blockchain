package com.t1708e.coin.dto;


import com.t1708e.coin.entity.Coin;
import com.t1708e.coin.entity.Market;
import com.t1708e.coin.util.DateTimeUtil;
import com.t1708e.coin.util.ObjectUtil;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class CoinDTO {
    private int id;
    private String name;
    private String baseAsset;
    private String quoteAsset;
    private String lastPrice;
    private String volumn24h;
    private String marketName;
    private String createdAt;
    private String updateddAt;
    private String deleteddAt;
    private String status;

    public CoinDTO() {
    }

    public CoinDTO(int id, String name, String baseAsset, String quoteAsset, String lastPrice, String volumn24h, String marketName, String createdAt, String updateddAt, String deleteddAt, String status) {
        this.id = id;
        this.name = name;
        this.baseAsset = baseAsset;
        this.quoteAsset = quoteAsset;
        this.lastPrice = lastPrice;
        this.volumn24h = volumn24h;
        this.marketName = marketName;
        this.createdAt = createdAt;
        this.updateddAt = updateddAt;
        this.deleteddAt = deleteddAt;
        this.status = status;
    }

    public CoinDTO(Coin coin) {
        this.id = coin.getId();
        this.name = coin.getName();
        this.baseAsset = coin.getBaseAsset();
        this.quoteAsset = coin.getQuoteAsset();
        this.lastPrice = coin.getLastPrice();
        this.volumn24h = coin.getVolumn24h();
        ObjectUtil.cloneObject(this, coin);
        this.marketName = coin.getMarket().getName();
        this.createdAt = DateTimeUtil.formatDateFromLong(coin.getCreatedAt());
        this.updateddAt = DateTimeUtil.formatDateFromLong(coin.getUpdateddAt());
        this.status = coin.getStatus() == 1 ? "Active" : "Deactive";
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

    public String getBaseAsset() {
        return baseAsset;
    }

    public void setBaseAsset(String baseAsset) {
        this.baseAsset = baseAsset;
    }

    public String getQuoteAsset() {
        return quoteAsset;
    }

    public void setQuoteAsset(String quoteAsset) {
        this.quoteAsset = quoteAsset;
    }

    public String getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(String lastPrice) {
        this.lastPrice = lastPrice;
    }

    public String getVolumn24h() {
        return volumn24h;
    }

    public void setVolumn24h(String volumn24h) {
        this.volumn24h = volumn24h;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdateddAt() {
        return updateddAt;
    }

    public void setUpdateddAt(String updateddAt) {
        this.updateddAt = updateddAt;
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
