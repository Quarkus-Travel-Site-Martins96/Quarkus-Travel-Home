package com.lucamartinelli.app.travelsite.home.vo;

import java.io.Serializable;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ShowcaseProductVO implements Serializable {
	
	private static final long serialVersionUID = -5561222322423035083L;
	
	private String hotelID;
	private String title;
	private String location;
	private double basicPrice;
	private String description;
	private String image;
	
	public ShowcaseProductVO() {
	}

	public ShowcaseProductVO(String hotelID, String title, String location, double basicPrice, String description,
			String image) {
		super();
		this.hotelID = hotelID;
		this.title = title;
		this.location = location;
		this.basicPrice = basicPrice;
		this.description = description;
		this.image = image;
	}

	public String getHotelID() {
		return hotelID;
	}

	public void setHotelID(String hotelID) {
		this.hotelID = hotelID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public double getBasicPrice() {
		return basicPrice;
	}

	public void setBasicPrice(double basicPrice) {
		this.basicPrice = basicPrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(basicPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((hotelID == null) ? 0 : hotelID.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShowcaseProductVO other = (ShowcaseProductVO) obj;
		if (Double.doubleToLongBits(basicPrice) != Double.doubleToLongBits(other.basicPrice))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (hotelID == null) {
			if (other.hotelID != null)
				return false;
		} else if (!hotelID.equals(other.hotelID))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ShowcaseProductVO [hotelID=" + hotelID + ", title=" + title + ", location=" + location + ", basicPrice="
				+ basicPrice + ", description=" + description + ", image=" + image + "]";
	}

	public JsonObject toJSON() {
		final JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("hotelID", hotelID);
		job.add("title", title);
		job.add("location", location);
		job.add("basicPrice", basicPrice);
		job.add("description", description);
		job.add("image", image);
		return job.build();
	}
}
