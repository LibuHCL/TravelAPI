/**
 * 
 */
package com.assessment.lunatechlabs.processors;

import org.springframework.batch.item.ItemProcessor;
import com.assessment.lunatechlabs.domain.Runways;


/**
 * @author Libu
 *
 */
public class RunwayItemProcessor implements  ItemProcessor<Runways, Runways> {
	@Override
	public Runways process(Runways runway) throws Exception {
		return runway;
	}

}
