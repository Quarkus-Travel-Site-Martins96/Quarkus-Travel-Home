package com.lucamartinelli.app.travelsite.home;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.lucamartinelli.app.travelsite.home.vo.OfferProductVO;
import com.lucamartinelli.app.travelsite.home.vo.OffersVO;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class HomeOfferTest {
	
	@Test
	public void testOfferService() {
		final OffersVO resp = given()
				.when()
					.get("/home/offers")
				.then()
					.statusCode(200)
					.extract().as(OffersVO.class);
			
		// Check if the call with 'IN_MEMORY' data returned the expected data (registered 3 products)
		
		assertNotNull(resp, "Response is null");
		assertNotNull(resp.getProducts());
		assertEquals(3, resp.getProducts().size(), "Mocked products are not 3!");
		
		final OfferProductVO prod = resp.getProducts().get(0);
		assertNotNull(prod, "Offer prod is null");;
		assertTrue(prod.getBasicPrice() > prod.getOfferPrice());
	}

}
