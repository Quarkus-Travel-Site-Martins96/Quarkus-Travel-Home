package com.lucamartinelli.app.travelsite.home.ejb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.jboss.logging.Logger;

import com.lucamartinelli.app.travelsite.home.vo.OfferProductVO;
import com.lucamartinelli.app.travelsite.home.vo.OffersVO;
import com.lucamartinelli.app.travelsite.home.vo.ShowcaseProductVO;
import com.lucamartinelli.app.travelsite.home.vo.ShowcaseVO;

import io.agroal.api.AgroalDataSource;

@RequestScoped
public class HomeDBEJB implements HomeEJB {
	
	@Inject
	Logger log;
	

    private final String SQL_QUERY_SHOWCASE =  "SELECT h.*, o.* FROM hotels AS h INNER JOIN showcase_products AS o ON h.HOTEL_ID = o.HOTEL_ID;";
    private final String SQL_QUERY_OFFERS =  "SELECT h.*, o.* FROM hotels AS h INNER JOIN offer_products AS o ON h.HOTEL_ID = o.HOTEL_ID;";
	
	@Override
	@Fallback(fallbackMethod = "showcaseFallback")
	@Retry(maxRetries = 3, delay = 2, delayUnit = ChronoUnit.SECONDS)
	public ShowcaseVO getShowcase() {
		
		final AgroalDataSource dataSource = 
				CDI.current().select(AgroalDataSource.class).get();
		
		final List<ShowcaseProductVO> products = new ArrayList<>();
		
		log.debug(dataSource.getConfiguration().connectionPoolConfiguration()
				.connectionFactoryConfiguration().jdbcUrl());
		
		log.debug("Connecting to database...");
		try(final Connection conn = dataSource.getConnection();
				final Statement stmt = conn.createStatement();) {

	        log.debug("Executing query: " + SQL_QUERY_SHOWCASE);
	        final ResultSet rs = stmt.executeQuery(SQL_QUERY_SHOWCASE);
	        log.debug("Query executed success");
	        while (rs.next()) {
	        	ShowcaseProductVO p = new ShowcaseProductVO();
	        	p.setHotelID(rs.getString("HOTEL_ID"));
	        	p.setTitle(rs.getString("TITLE"));
	        	p.setDescription(rs.getString("SHORT_DESCRIPTION"));
	        	p.setLocation(rs.getString("LOCATION"));
	        	p.setImage(rs.getString("PREVIEW_IMAGE"));
	        	p.setBasicPrice(rs.getDouble("BASIC_PRICE"));
	        	
	        	products.add(p);
	        }
	        rs.close();
		} catch (SQLException e) {
			log.error("Failed get data from database", e);
			throw new RuntimeException(e);
		}
        
		return new ShowcaseVO(products);
	}
	
	
	/**
	 * Fallback method, if the showcase main method fails this will be called 
	 * and returned an empty object
	 */
	@SuppressWarnings("unused")
	private ShowcaseVO showcaseFallback() {
		log.debug("Showcase fallback called");
		return new ShowcaseVO();
	}
	
	@Override
	@Fallback(fallbackMethod = "offersFallback")
	@Retry(maxRetries = 3, delay = 2, delayUnit = ChronoUnit.SECONDS)
	public OffersVO getOffers() {

		final AgroalDataSource dataSource = 
				CDI.current().select(AgroalDataSource.class).get();
		
		final List<OfferProductVO> products = new ArrayList<>();
		
		log.debug("Connecting to database...");
		try(final Connection conn = dataSource.getConnection();
				final Statement stmt = conn.createStatement();) {

	        log.debug("Executing query: " + SQL_QUERY_OFFERS);
	        final ResultSet rs = stmt.executeQuery(SQL_QUERY_OFFERS);
	        log.debug("Query executed success");
	        while (rs.next()) {
	        	OfferProductVO p = new OfferProductVO();
	        	p.setHotelID(rs.getString("HOTEL_ID"));
	        	p.setTitle(rs.getString("TITLE"));
	        	p.setDescription(rs.getString("SHORT_DESCRIPTION"));
	        	p.setLocation(rs.getString("LOCATION"));
	        	p.setImage(rs.getString("PREVIEW_IMAGE"));
	        	p.setBasicPrice(rs.getDouble("BASIC_PRICE"));
	        	p.setOfferPrice(rs.getDouble("OFFER_PRICE"));
	        	
	        	products.add(p);
	        }
	        rs.close();
		} catch (SQLException e) {
			log.error("Failed get data from database", e);
			throw new RuntimeException(e);
		}
		
		return new OffersVO(products);
	}
	
	/**
	 * Fallback method, if the offers main method fails this will be called 
	 * and returned an empty object
	 */
	@SuppressWarnings("unused")
	private OffersVO offersFallback() {
		log.debug("Offers fallback called");
		return new OffersVO();
	}
	
}
