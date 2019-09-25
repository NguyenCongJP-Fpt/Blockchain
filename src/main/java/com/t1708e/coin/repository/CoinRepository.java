package com.t1708e.coin.repository;

import com.t1708e.coin.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface CoinRepository extends JpaRepository<Coin, Integer>, JpaSpecificationExecutor<Coin> {

    @Query("select h from Coin as h where h.status = :status")
    List<Coin> findActiveHero(@Param("status") int status);

    List<Coin> findAllByStatus(int status);

}
