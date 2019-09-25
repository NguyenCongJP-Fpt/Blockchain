package com.t1708e.coin.service;

import com.t1708e.coin.entity.Coin;
import com.t1708e.coin.repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.Calendar;
import java.util.List;

@Service
public class CoinService {

    @Autowired
    CoinRepository coinRepository;

    public List<Coin> coins() {
        coinRepository.findAll(PageRequest.of(1, 3));
        return coinRepository.findActiveHero(1);
    }

    public Page<Coin> coinsWithPaginate(Specification specification, int page, int limit) {
        return coinRepository.findAll(specification, PageRequest.of(page - 1, limit));
    }

    public Coin getById(int id) {
        return coinRepository.findById(id).orElse(null);
    }

    public Coin create(Coin coin) {
        coin.setStatus(1);
        coin.setCreatedAt(Calendar.getInstance().getTimeInMillis());
        coin.setUpdateddAt(Calendar.getInstance().getTimeInMillis());
        return coinRepository.save(coin);
    }

    public Coin update(Coin coin) {
        coin.setUpdateddAt(Calendar.getInstance().getTimeInMillis());
        return coinRepository.save(coin);
    }

    public boolean delete(Coin coin) {
        coin.setDeleteddAt(Calendar.getInstance().getTimeInMillis());
        coin.setStatus(-1);
        coinRepository.save(coin);
        return true;
    }
}
