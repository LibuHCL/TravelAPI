/**
 * 
 */
package com.assessment.lunatechlabs.assessment.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.lunatechlabs.assessment.services.IQueryService;
import com.assessment.lunatechlabs.domain.Airport;
import com.assessment.lunatechlabs.repository.QueryRepository;

/**
 * @author Libu
 *
 */
@Service
public class QueryServiceImpl implements IQueryService {
	
	@Autowired
	private QueryRepository queryRepository;

	@Override
	public List<Airport> getAirportByCode(String code) {
		
		List<Airport> airportByCode = new ArrayList<>();
		queryRepository.findByCountry(code,null).forEach(e -> airportByCode.add(e));
		return airportByCode;
	}

	@Override
	public List<Airport> getAirportByName(String name) {
		List<Airport> airportByName = new ArrayList<>();
		queryRepository.findByCountry(null,name).forEach(e -> airportByName.add(e));
		return airportByName;
	}

}
