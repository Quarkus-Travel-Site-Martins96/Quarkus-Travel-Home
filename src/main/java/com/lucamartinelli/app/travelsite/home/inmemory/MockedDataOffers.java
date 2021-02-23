package com.lucamartinelli.app.travelsite.home.inmemory;

import java.util.List;

import com.lucamartinelli.app.travelsite.home.vo.OfferProductVO;
import com.lucamartinelli.app.travelsite.home.vo.OffersVO;

public class MockedDataOffers {
	
	private static final OffersVO OFFERS_1 = new OffersVO(List.of(
			new OfferProductVO(
					"1",
					"title1",
					"location1", 
					111.11D,
					99.99D,
					"description1",
					"https://cdn.iconscout.com/icon/free/png-256/hotel-512-453740.png"),
			new OfferProductVO(
					"2",
					"title2",
					"location2", 
					222.22D,
					199.99D,
					"description2",
					"https://cdn.iconscout.com/icon/free/png-256/hotel-512-453740.png"),
			new OfferProductVO(
					"3",
					"title3",
					"location3", 
					333.33D,
					299.99D,
					"description3",
					"https://cdn.iconscout.com/icon/free/png-256/hotel-512-453740.png")
	));
	
	public static OffersVO getOffers() {
		return OFFERS_1;
	}
}
