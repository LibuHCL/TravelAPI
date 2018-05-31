/**
 * 
 */
package com.assessment.lunatechlabs.processors;

import org.springframework.batch.item.ItemProcessor;
import com.assessment.lunatechlabs.domain.Airport;


/**
 * @author Libu
 *
 */
public class AirportItemProcessor implements  ItemProcessor<Airport, Airport> {
	@Override
	public Airport process(Airport airport) throws Exception {
		return airport;
	}

}
