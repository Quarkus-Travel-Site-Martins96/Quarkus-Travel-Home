package com.lucamartinelli.app.travelsite.home.inmemory;


import java.util.List;

import com.lucamartinelli.app.travelsite.home.vo.ShowcaseProductVO;
import com.lucamartinelli.app.travelsite.home.vo.ShowcaseVO;

public class MockedDataShowcase {
	
	private static final ShowcaseVO SHOWCASE_1 = new ShowcaseVO(List.of(
		new ShowcaseProductVO(
				"1",
				"title1",
				"location1", 
				111.11D,
				"description1",
				"https://cdn.iconscout.com/icon/free/png-256/hotel-512-453740.png"),
		new ShowcaseProductVO(
				"2",
				"title2",
				"location2", 
				222.22D,
				"description2",
				"https://cdn.iconscout.com/icon/free/png-256/hotel-512-453740.png"),
		new ShowcaseProductVO(
				"3",
				"title3",
				"location3", 
				333.33D,
				"description3",
				"https://cdn.iconscout.com/icon/free/png-256/hotel-512-453740.png")
	));
	
	
	public static ShowcaseVO getShowcase() {
		return SHOWCASE_1;
	}
	
}
