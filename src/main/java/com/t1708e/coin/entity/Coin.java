package com.t1708e.coin.entity;


import javax.persistence.*;

@Entity
public class Coin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String baseAsset;
    private String quoteAsset;
    private String lastPrice;
    private String volumn24h;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "marketId")
    private Market market;

    private long createdAt;
    private long updateddAt;
    private long deleteddAt;
    private int status;

    public Coin() {
    }

    public Coin(int id, String name, String baseAsset, String quoteAsset, String lastPrice, String volumn24h, String marketName, long createdAt, long updateddAt, long deleteddAt, int status) {
        this.id = id;
        this.name = name;
        this.baseAsset = baseAsset;
        this.quoteAsset = quoteAsset;
        this.lastPrice = lastPrice;
        this.volumn24h = volumn24h;
        this.createdAt = createdAt;
        this.updateddAt = updateddAt;
        this.deleteddAt = deleteddAt;
        this.status = status;
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

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdateddAt() {
        return updateddAt;
    }

    public void setUpdateddAt(long updateddAt) {
        this.updateddAt = updateddAt;
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

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }
}
