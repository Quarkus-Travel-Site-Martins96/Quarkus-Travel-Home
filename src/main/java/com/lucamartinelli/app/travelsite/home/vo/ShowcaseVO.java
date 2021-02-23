package com.lucamartinelli.app.travelsite.home.vo;

import java.io.Serializable;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ShowcaseVO implements Serializable {

	private static final long serialVersionUID = 8530738809061412110L;
	
	@XmlElement(name="product")
	private List<ShowcaseProductVO> products;

	public ShowcaseVO() {
	}

	public ShowcaseVO(List<ShowcaseProductVO> products) {
		super();
		this.products = products;
	}

	public List<ShowcaseProductVO> getProducts() {
		return products;
	}

	public void setProducts(List<ShowcaseProductVO> products) {
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
		ShowcaseVO other = (ShowcaseVO) obj;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ShowcaseVo [products=" + products + "]";
	}

	public JsonObject toJSON() {
		final JsonObjectBuilder job = Json.createObjectBuilder();
		final JsonArrayBuilder jab = Json.createArrayBuilder();
		for (final ShowcaseProductVO p : products) {
			jab.add(p.toJSON());
		}
		job.add("products", jab.build());
		return job.build();
	}
	
	
	
}
