//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.backendgip.imp;

import com.backendgip.exception.ResourceNotFoundException;
import com.backendgip.model.CountryApi;
import com.backendgip.model.CountryResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import com.backendgip.service.CountryApiService;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Arrays;

@Service
public class CountryApiServImp implements CountryApiService {

	private RestTemplate restTemplate;

	public CountryApiServImp(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
public List<CountryApi> getAllCountries() {
    String url = "https://countriesnow.space/api/v0.1/countries";

    HttpHeaders headers = new HttpHeaders();
    headers.set("User-Agent", "Mozilla/5.0");

    HttpEntity<String> entity = new HttpEntity<>(headers);
    ResponseEntity<CountryResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, CountryResponse.class);

    if (response.getBody().isError()) {
        throw new RuntimeException("Error retrieving countries: " + response.getBody().getMsg());
    }

    return response.getBody().getData();
}


}
