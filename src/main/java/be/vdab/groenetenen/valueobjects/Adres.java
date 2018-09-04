package be.vdab.groenetenen.valueobjects;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import be.vdab.groenetenen.constraints.Postcode;

@Embeddable
@XmlAccessorType(XmlAccessType.FIELD) 
@JsonAutoDetect(fieldVisibility=Visibility.ANY)
public class Adres implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank
	@SafeHtml
	private String straat;
	@NotBlank
	@SafeHtml
	private String huisNr;
	@NotNull
	@Postcode
	private int postcode;
	@NotBlank
	@SafeHtml
	private String gemeente;

	public String getStraat() {
		return straat;
	}

	public String getHuisNr() {
		return huisNr;
	}

	public int getPostcode() {
		return postcode;
	}

	public String getGemeente() {
		return gemeente;
	}

	public Adres(@NotBlank @SafeHtml String straat, @NotBlank @SafeHtml String huisNr,
			@NotNull @Range(min = 1000, max = 9999) int postcode, @NotBlank @SafeHtml String gemeente) {
		this.straat = straat;
		this.huisNr = huisNr;
		this.postcode = postcode;
		this.gemeente = gemeente;
	}

	protected Adres() {

	}

}
