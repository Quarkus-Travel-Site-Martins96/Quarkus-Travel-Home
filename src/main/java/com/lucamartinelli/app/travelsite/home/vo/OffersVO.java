package com.lucamartinelli.app.travelsite.home.vo;

import java.io.Serializable;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OffersVO implements Serializable {
	
	private static final long serialVersionUID = 8373419180266463291L;
	
	private List<OfferProductVO> products;
	
	public OffersVO() {
	}
	
	public OffersVO(List<OfferProductVO> products) {
		super();
		this.products = products;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((products == null) ? 0 : products.hashCode());
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
		OffersVO other = (OffersVO) obj;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		return true;
	}

	public List<OfferProductVO> getProducts() {
		return products;
	}
	public void setProducts(List<OfferProductVO> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "OffersVO [products=" + products + "]";
	}

	public JsonObject toJSON() {
		final JsonObjectBuilder job = Json.createObjectBuilder();
		final JsonArrayBuilder jab = Json.createArrayBuilder();
		for (final OfferProductVO p : products) {
			jab.add(p.toJSON());
		}
		job.add("products", jab.build());
		return job.build();
	}

}
