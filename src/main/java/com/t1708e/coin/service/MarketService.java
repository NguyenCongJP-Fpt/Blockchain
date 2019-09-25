package com.t1708e.coin.service;

import com.t1708e.coin.entity.Coin;
import com.t1708e.coin.entity.Market;
import com.t1708e.coin.repository.CoinRepository;
import com.t1708e.coin.repository.MarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class MarketService {

    @Autowired
    MarketRepository marketRepository;

    public List<Market> markets() {
        marketRepository.findAll(PageRequest.of(1, 3));
        return marketRepository.findActiveHero(1);
    }

    public Page<Market> marketWithPaginate(Specification specification, int page, int limit) {
        return marketRepository.findAll(specification, PageRequest.of(page - 1, limit));
    }

    public Market getById(int id) {
        return marketRepository.findById(id).orElse(null);
    }

    public Market create(Market market) {
        market.setStatus(1);
        market.setCreatedAt(Calendar.getInstance().getTimeInMillis());
        market.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        return marketRepository.save(market);
    }

    public Market update(Market market) {
        market.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
        return marketRepository.save(market);
    }

    public boolean delete(Market market) {
        market.setDeleteddAt(Calendar.getInstance().getTimeInMillis());
        market.setStatus(-1);
        marketRepository.save(market);
        return true;
    }
}
