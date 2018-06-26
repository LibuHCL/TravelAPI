/**
 * 
 */
package com.assessment.lunatechlabs.assessment.services;

import java.util.List;
import java.util.Optional;

import com.assessment.lunatechlabs.domain.Country;

/**
 * @author Libu
 *
 */

public interface ICountryService {
	
	List<String> countryNamesWithPrefix(String prefix);
	Optional<Country> findCountry(String countryParam);
	List<Country> topTenCountriesWithMostNumberOfAirports();
	List<Country> topTenCountriesWithLeastNumberOfAirports();

}
