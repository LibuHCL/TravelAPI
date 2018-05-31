/**
 * 
 */
package com.assessment.lunatechlabs.processors;

import org.springframework.batch.item.ItemProcessor;
import com.assessment.lunatechlabs.domain.Countries;

/**
 * @author Libu
 *
 */
public class CountryItemProcessor implements ItemProcessor<Countries, Countries> {
	
	@Override
	 public Countries process(Countries country) throws Exception {
	  return country;
	 }
}
