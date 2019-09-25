package com.t1708e.coin.controller;


import com.t1708e.coin.dto.CoinDTO;
import com.t1708e.coin.dto.MarketDTO;
import com.t1708e.coin.entity.Coin;
import com.t1708e.coin.entity.Market;
import com.t1708e.coin.entity.rest.RESTPagination;
import com.t1708e.coin.entity.rest.RESTResponse;
import com.t1708e.coin.service.MarketService;
import com.t1708e.coin.specification.CoinSpecification;
import com.t1708e.coin.specification.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/vi/market")
public class MarketController {

    @Autowired
    MarketService marketService;

    public ResponseEntity<Object> getCoinList(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "from", required = false) String from,
            @RequestParam(value = "to", required = false) String to,
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int limit) {
        Specification specification = Specification.where(null);
        if (keyword != null && keyword.length() > 0) {
            specification = specification
                    .and(new CoinSpecification(new SearchCriteria("name", ":", keyword)))
                    .or(new CoinSpecification(new SearchCriteria("description", ":", keyword)));
        }

        Page<Market> marketPage = marketService.marketWithPaginate(specification, page, limit);
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Action success!")
                .addData(marketPage.getContent().stream().map(x -> new MarketDTO(x)).collect(Collectors.toList()))
                .setPagination(new RESTPagination(page, limit, marketPage.getTotalPages(), marketPage.getTotalElements()))
                .build(),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Object> getDetail(@PathVariable int id) {
        Market market = marketService.getById(id);
        if (market == null) {
            return new ResponseEntity<>(new RESTResponse.SimpleError()
                    .setCode(HttpStatus.NOT_FOUND.value())
                    .setMessage("Not found")
                    .build(),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Success")
                .addData(new MarketDTO(marketService.getById(id)))
                .build(),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> store(@RequestBody Market market) {
        // validate.
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.CREATED.value())
                .setMessage("Action Success")
                .addData(new MarketDTO(marketService.create(market)))
                .build(),
                HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Market updateMarket) {
        Market existMarket = marketService.getById(id);
        if (existMarket == null) {
            return new ResponseEntity<>(new RESTResponse.SimpleError()
                    .setCode(HttpStatus.NOT_FOUND.value())
                    .setMessage("Not found")
                    .build(),
                    HttpStatus.NOT_FOUND);
        }
        existMarket.setName(updateMarket.getName());
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Success")
                .addData(new MarketDTO(marketService.update(existMarket)))
                .build(),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        Market existMarket = marketService.getById(id);
        if (existMarket == null) {
            return new ResponseEntity<>(new RESTResponse.SimpleError()
                    .setCode(HttpStatus.NOT_FOUND.value())
                    .setMessage("Not found")
                    .build(),
                    HttpStatus.NOT_FOUND);
        }
        marketService.delete(existMarket);
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Simple Success")
                .build(),
                HttpStatus.OK);
    }
}
