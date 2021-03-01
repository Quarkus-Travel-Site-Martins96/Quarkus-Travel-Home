package com.lucamartinelli.app.travelsite.home.ejb;

import com.lucamartinelli.app.travelsite.home.vo.OffersVO;
import com.lucamartinelli.app.travelsite.home.vo.ShowcaseVO;

public interface HomeEJB {
	
	/**
	 * This method return the hotels in the showcase section 
	 */
	public ShowcaseVO getShowcase();
	
	/**
	 * This method return all hotels registered as offer
	 */
	public OffersVO getOffers();
	
}
