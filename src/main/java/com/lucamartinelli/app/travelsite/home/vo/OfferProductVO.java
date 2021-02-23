package com.lucamartinelli.app.travelsite.home.vo;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class OfferProductVO extends ShowcaseProductVO {
	
	private static final long serialVersionUID = 8816341474897310219L;
	
	
	private double offerPrice;
	
	public OfferProductVO() {
	}

	public OfferProductVO(String hotelID, String title, String location, double basicPrice, 
			double offerPrice, String description, String image) {
		super(hotelID, title, location, basicPrice, description, image);
		this.offerPrice = offerPrice;
	}

	public double getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(double offerPrice) {
		this.offerPrice = offerPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(offerPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		OfferProductVO other = (OfferProductVO) obj;
		if (Double.doubleToLongBits(offerPrice) != Double.doubleToLongBits(other.offerPrice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OfferProductVO [offerPrice=" + offerPrice + "]";
	}
	
	public JsonObject toJSON() {
		final JsonObjectBuilder job = Json.createObjectBuilder(super.toJSON());
		job.add("offerPrice", offerPrice);
		return job.build();
	}
	
	
}
