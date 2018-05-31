/**
 * 
 */
package com.assessment.lunatechlabs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.assessment.lunatechlabs.assessment.services.IQueryService;
import com.assessment.lunatechlabs.domain.Airport;
import com.assessment.lunatechlabs.exceptions.CountryNotFoundException;

/**
 * @author Libu
 *
 */

@Controller("/assessment")
@CrossOrigin(origins = {"http://localhost:4200"})
public class TravelController {

	@Autowired
	private IQueryService queryService;

	//Fetch all airports based on country code
	@GetMapping(value= "/travelapibycode/{code}", produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Airport>> getAirportByCode(@RequestParam("code") String code) {
		List<Airport> responseAirportList = new ArrayList<>();
		List<Airport> articleList = queryService.getAirportByCode(code);
		if(!articleList.isEmpty()) {
			for (int i = 0; i < articleList.size(); i++) {
				Airport ob = new Airport();
				BeanUtils.copyProperties(articleList.get(i), ob);
				responseAirportList.add(ob);    
			}
			return new ResponseEntity<List<Airport>>(responseAirportList, HttpStatus.OK);
		} else {
			 String message = String.format("Country %d not found", code);
			 throw new CountryNotFoundException(message);
		}
	}

	//Fetch all airports based on country name
	@GetMapping(value= "/travelapibyname/{name}", produces= { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Airport>> getAirportByName(@RequestParam("name")String name) {
		List<Airport> responseAirportList = new ArrayList<>();
		List<Airport> articleList = queryService.getAirportByName(name);
		for (int i = 0; i < articleList.size(); i++) {
			Airport ob = new Airport();
			BeanUtils.copyProperties(articleList.get(i), ob);
			responseAirportList.add(ob);    
		}
		return new ResponseEntity<List<Airport>>(responseAirportList, HttpStatus.OK);
	}
}
