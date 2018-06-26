/**
 * 
 */
package com.assessment.lunatechlabs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.assessment.lunatechlabs.assessment.services.impl.AirportService;
import com.assessment.lunatechlabs.assessment.services.impl.CountryService;
import com.assessment.lunatechlabs.assessment.services.impl.RunwayService;
import com.assessment.lunatechlabs.domain.Airport;
import com.assessment.lunatechlabs.domain.Country;
import com.assessment.lunatechlabs.domain.Runway;

import lombok.NonNull;


/**
 * The controller for handling the Query request.
 * 
 * @author Libu
 *
 */

@Controller
public class QueryController {

	@Autowired
	private CountryService countryService;

	@Autowired
	private AirportService airportService;

	@Autowired
	private RunwayService runwayService;

	@RequestMapping("/query")
	public String query() {
		return "query";
	}

	@RequestMapping("/query/{countryParam}")
	public String queryCountry(@PathVariable String countryParam, Model model) {
		Optional<Country> country = countryService.findCountry(countryParam);
		if (country.isPresent()) {
			populateResults(country.get(), model);
		} else {
			model.addAttribute("error", "Invalid Country " + countryParam);
		}
		return "query";
	}

	protected void populateResults(@NonNull Country country, @NonNull Model model) {
		List<Airport> airports = airportService.findAirports(country);
		Map<Long, Airport> airportMap = new HashMap<>();
		airports.forEach(a -> {
			airportMap.put(a.getId(), a);
			a.setRunways(new ArrayList<>());
		});
		List<Runway> runways = runwayService.fetchRunwaysForAirports(airports);
		runways.forEach(r -> airportMap.get(r.getAirportId()).getRunways().add(r));
		model.addAttribute("country", country);
		model.addAttribute("airports", airports);
	}

}
