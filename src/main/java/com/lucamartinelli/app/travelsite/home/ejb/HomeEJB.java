package com.lucamartinelli.app.travelsite.home.ejb;

import com.lucamartinelli.app.travelsite.home.vo.OffersVO;
import com.lucamartinelli.app.travelsite.home.vo.ShowcaseVO;

public interface HomeEJB {
	
	
	public ShowcaseVO getShowcase();
	
	public OffersVO getOffers();
	
}
