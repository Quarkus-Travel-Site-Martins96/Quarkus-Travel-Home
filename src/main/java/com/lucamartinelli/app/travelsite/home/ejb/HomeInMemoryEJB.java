package com.lucamartinelli.app.travelsite.home.ejb;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.lucamartinelli.app.travelsite.home.inmemory.MockedDataOffers;
import com.lucamartinelli.app.travelsite.home.inmemory.MockedDataShowcase;
import com.lucamartinelli.app.travelsite.home.vo.OffersVO;
import com.lucamartinelli.app.travelsite.home.vo.ShowcaseVO;

@SessionScoped
public class HomeInMemoryEJB implements HomeEJB {
	
	@Inject
	Logger log;
	
	
	@Override
	public ShowcaseVO getShowcase() {
		log.debug("START get showcase with mode: [IN_MEMORY]");
		
		try {
			return MockedDataShowcase.getShowcase();
		} finally {
			log.debug("END get showcase with mode: [IN_MEMORY]");
		}
	}
	
	@Override
	public OffersVO getOffers() {
		log.debug("START get offers with mode: [IN_MEMORY]");
		
		try {
			return MockedDataOffers.getOffers();
		} finally {
			log.debug("END get offers with mode: [IN_MEMORY]");
		}
	}
	
	
}
