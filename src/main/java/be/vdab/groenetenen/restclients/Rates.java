package be.vdab.groenetenen.restclients;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rates {

	@JsonProperty("USD")
	private BigDecimal usd;

	public BigDecimal getUsd() {
		return usd;
	}

	public void setUsd(BigDecimal usd) {
		this.usd = usd;
	}
	
	
	
}
