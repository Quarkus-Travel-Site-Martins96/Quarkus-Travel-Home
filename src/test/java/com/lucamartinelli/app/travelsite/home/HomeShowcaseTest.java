package com.lucamartinelli.app.travelsite.home;

import io.quarkus.test.junit.QuarkusTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.lucamartinelli.app.travelsite.home.vo.ShowcaseVO;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class HomeShowcaseTest {

	@Test
	public void testShowcaseService() {
		final ShowcaseVO resp = given()
			.when()
				.get("/home/showcase")
			.then()
				.statusCode(200)
				.extract().as(ShowcaseVO.class);
		
		assertNotNull(resp, "Response is null");
		assertNotNull(resp.getProducts());
		assertEquals(3, resp.getProducts().size(), "Mocked products are not 3!");
	}

}