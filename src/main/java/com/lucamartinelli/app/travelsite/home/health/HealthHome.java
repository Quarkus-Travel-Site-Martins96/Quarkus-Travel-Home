package com.lucamartinelli.app.travelsite.home.health;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.health.Readiness;

@Liveness
@Readiness
public class HealthHome implements HealthCheck{
	
	@Override
	public HealthCheckResponse call() {
		final String mode = ConfigProvider.getConfig()
				.getOptionalValue("home.mode", String.class).orElse("IN_MEMORY");
		final boolean configuredDone = mode != null &&
				(mode.equalsIgnoreCase("IN_MEMORY") || mode.equalsIgnoreCase("DB"));
		final String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		
		return HealthCheckResponse.named("home-service-check")
				.withData("Date", date)
				.withData("Home mode", mode)
				.state(configuredDone)
				.build();
	}
	
}
