package com.t1708e.coin.repository;

import com.t1708e.coin.entity.Coin;
import com.t1708e.coin.entity.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MarketRepository extends JpaRepository<Market, Integer>, JpaSpecificationExecutor<Market> {

    @Query("select h from Market as h where h.status = :status")
    List<Market> findActiveHero(@Param("status") int status);

    List<Market> findAllByStatus(int status);

}
