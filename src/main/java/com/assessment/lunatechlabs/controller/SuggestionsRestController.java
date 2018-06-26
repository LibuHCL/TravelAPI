/**
 * 
 */
package com.assessment.lunatechlabs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.lunatechlabs.assessment.services.impl.CountryService;

import java.util.List;

/**
 * This is the controller for smart search functionality.
 * 
 * @author Libu
 *
 */
@RestController("/suggestions")
public class SuggestionsRestController {
	
	@Autowired
    private CountryService countryService;
	
	@RequestMapping("/country/{countryParam}")
    public List<String> countrySuggestions(@PathVariable String countryParam) {
        List<String> suggestions = countryService.countryNamesWithPrefix(countryParam);
        return suggestions;
    }

}
