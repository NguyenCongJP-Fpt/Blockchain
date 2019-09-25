package com.t1708e.coin.controller;

import com.t1708e.coin.dto.CoinDTO;
import com.t1708e.coin.entity.Coin;
import com.t1708e.coin.entity.rest.RESTPagination;
import com.t1708e.coin.entity.rest.RESTResponse;
import com.t1708e.coin.service.CoinService;
import com.t1708e.coin.specification.CoinSpecification;
import com.t1708e.coin.specification.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/vi/coins")
public class CoinController {

    CoinService coinService;

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
                    .or(new CoinSpecification(new SearchCriteria("baseAsset", ":", keyword)))
                    .or(new CoinSpecification(new SearchCriteria("quoteAsset", ":", keyword)))
                    .or(new CoinSpecification(new SearchCriteria("lastPrice", ":", keyword)))
                    .or(new CoinSpecification(new SearchCriteria("volumn24h", ":", keyword)));
        }
        Page<Coin> coinPage = coinService.coinsWithPaginate(specification, page, limit);
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Action success!")
                .addData(coinPage.getContent().stream().map(x -> new CoinDTO(x)).collect(Collectors.toList()))
                .setPagination(new RESTPagination(page, limit, coinPage.getTotalPages(), coinPage.getTotalElements()))
                .build(),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Object> getDetail(@PathVariable int id) {
        Coin coin = coinService.getById(id);
        if (coin == null) {
            return new ResponseEntity<>(new RESTResponse.SimpleError()
                    .setCode(HttpStatus.NOT_FOUND.value())
                    .setMessage("Not found")
                    .build(),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Success")
                .addData(new CoinDTO(coinService.getById(id)))
                .build(),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> store(@RequestBody Coin coin) {
        // validate.
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.CREATED.value())
                .setMessage("Action Success")
                .addData(new CoinDTO(coinService.create(coin)))
                .build(),
                HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody Coin updateHero) {
        Coin existCoin = coinService.getById(id);
        if (existCoin == null) {
            return new ResponseEntity<>(new RESTResponse.SimpleError()
                    .setCode(HttpStatus.NOT_FOUND.value())
                    .setMessage("Not found")
                    .build(),
                    HttpStatus.NOT_FOUND);
        }
        existCoin.setName(updateHero.getName());
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Success")
                .addData(new CoinDTO(coinService.update(existCoin)))
                .build(),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        Coin existCoin = coinService.getById(id);
        if (existCoin == null) {
            return new ResponseEntity<>(new RESTResponse.SimpleError()
                    .setCode(HttpStatus.NOT_FOUND.value())
                    .setMessage("Not found")
                    .build(),
                    HttpStatus.NOT_FOUND);
        }
        coinService.delete(existCoin);
        return new ResponseEntity<>(new RESTResponse.Success()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Simple Success")
                .build(),
                HttpStatus.OK);
    }
}
