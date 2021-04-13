package com.lucamartinelli.app.travelsite.home.health;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;

import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.health.Readiness;
import org.jboss.logging.Logger;

import io.agroal.api.AgroalDataSource;

@Liveness
@Readiness
@ApplicationScoped
public class HealthHome implements HealthCheck {
	
	@Inject
	Logger log;
	
	/**
	 * Register home-service-check name as check observable.
	 * This method is used to check if the application is up and running
	 */
	@Override
	public HealthCheckResponse call() {
		final String mode = ConfigProvider.getConfig()
				.getOptionalValue("home.mode", String.class).orElse("IN_MEMORY");
		final boolean configuredDone = mode != null &&
				(mode.equalsIgnoreCase("IN_MEMORY") || mode.equalsIgnoreCase("DB"));
		final String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		
		HealthCheckResponseBuilder bldr = HealthCheckResponse.named("home-service-check")
				.withData("Date", date)
				.withData("Home mode", mode);
		
		
		boolean db = true;
		// Check if DB is enabled
		if (mode.equalsIgnoreCase("DB")) {
			db = checkDBHealth();
			bldr.withData("Datasouce Health", db);
		}
		
		
		return bldr.state(configuredDone && db)
				.build();
	}

	
	private boolean checkDBHealth() {
		try {
			final AgroalDataSource db = CDI.current().select(AgroalDataSource.class).get();
			if (db.getConnection().isValid(2000))
				return true;
		} catch (SQLException | IllegalArgumentException | IllegalStateException e) {
			log.error("Error during check DB health: ", e);
		}
		return false;
	}
	
}
