package be.vdab.groenetenen.messaging;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import be.vdab.groenetenen.entities.Offerte;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferteEnOffertesURL {
	private Offerte offerte;
	private String offertesURL;

	public Offerte getOfferte() {
		return offerte;
	}

	public void setOfferte(Offerte offerte) {
		this.offerte = offerte;
	}

	public String getOffertesURL() {
		return offertesURL;
	}

	public void setOffertesURL(String offertesURL) {
		this.offertesURL = offertesURL;
	}

	public OfferteEnOffertesURL(Offerte offerte, String offertesURL) {
		super();
		this.offerte = offerte;
		this.offertesURL = offertesURL;
	}

	protected OfferteEnOffertesURL() {

	}
}
