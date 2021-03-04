package com.lucamartinelli.app.travelsite.home;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import com.lucamartinelli.app.travelsite.home.ejb.HomeDBEJB;
import com.lucamartinelli.app.travelsite.home.ejb.HomeEJB;
import com.lucamartinelli.app.travelsite.home.ejb.HomeInMemoryEJB;
import com.lucamartinelli.app.travelsite.home.vo.OffersVO;
import com.lucamartinelli.app.travelsite.home.vo.ShowcaseVO;

/**
 * @author Luca Martinelli
 * @category REST APIs
 * 
 * This class is the REST APIs manager, exposer and logic caller
 *
 */
@Path("/home")
@ApplicationScoped
public class Home {
	
	@Inject
	@ConfigProperty(name = "home.mode", defaultValue = "IN_MEMORY")
	String homeMode;
	
	@Inject Provider<HomeInMemoryEJB> inMemoryEJBInstance;
	@Inject Provider<HomeDBEJB> dbEJBInstance;
	@Inject Logger log;
	
	@Context
	private HttpServletResponse servletResponse;
	
	/** This EJB will be initialized based property 'home.mode' */
	private HomeEJB ejb = null;
	
	@PostConstruct
	public void init() {
    	log.debug("Get showcase using mode [" + homeMode + "]");
		switch (homeMode.toUpperCase()) {
		case "IN_MEMORY":
			ejb = inMemoryEJBInstance.get();
			return;
			
		case "DB":
			ejb = dbEJBInstance.get();
			return;
			
		default:
			log.error("Configuration for 'home.mode' is not valid, unknow value " + homeMode);
			return;
		}
	}
	

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("showcase")
    public ShowcaseVO getShowcase() {
    	if (ejb == null) {
    		log.error("Error in configuration HOME EJB!");
    		setError(503, "Service Unavailable. Wrong Configurations");
    		return null;
    	}
    	try {
    		return ejb.getShowcase();
    	} catch (RuntimeException e) {
        	log.error("Error during execution getShowcase() ", e);
        	setError(500, "Internal server error");
        	return null;
		}
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("offers")
    public OffersVO getOffers() {
    	if (ejb == null) {
    		log.error("Error in configuration HOME EJB!");
    		setError(503, "Service Unavailable. Wrong Configurations");
    		return null;
    	}
    	try {
    		return ejb.getOffers();
    	} catch (RuntimeException e) {
        	log.error("Error during execution getOffers() ", e);
        	setError(500, "Internal server error");
        	return null;
		}
    }
    
    
    private void setError(int error, String msg) {
    	try {
			servletResponse.sendError(error, msg);
		} catch (IOException e) {
			log.error(e);
			throw new RuntimeException(e);
		}
    }
    
}