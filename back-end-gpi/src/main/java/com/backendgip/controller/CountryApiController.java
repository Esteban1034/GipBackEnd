//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.controller;

import com.backendgip.model.CountryApi;
import com.backendgip.service.CountryApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CountryApiController {

    private final CountryApiService countryApiService;

    @Autowired
    public CountryApiController(CountryApiService countryApiService) {
        this.countryApiService = countryApiService;
    }

    @GetMapping({ "/countries" })
    public ResponseEntity<List<CountryApi>> getAllCountries() {
        List<CountryApi> countries = countryApiService.getAllCountries();
        return ResponseEntity.ok(countries);
    }
}
