package be.vdab.groenetenen.web;

import javax.validation.constraints.NotNull;

import be.vdab.groenetenen.constraints.Postcode;
import be.vdab.groenetenen.constraints.VanTotPostCodeFormVanIsKleinerDanTot;

@VanTotPostCodeFormVanIsKleinerDanTot
public class VanTotPostcodeForm {
	@NotNull
	@Postcode
	private Integer van;

	@NotNull
	@Postcode
	private Integer tot;

	public Integer getVan() {
		return van;
	}

	public void setVan(Integer van) {
		this.van = van;
	}

	public Integer getTot() {
		return tot;
	}

	public void setTot(Integer tot) {
		this.tot = tot;
	}
}
