package be.vdab.groenetenen.web;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.groenetenen.entities.Offerte;
import be.vdab.groenetenen.services.OfferteService;

@Controller
@RequestMapping("offertes")
@SessionAttributes("offerte")
public class OfferteController {
	private final OfferteService offerteService;

	public OfferteController(OfferteService offerteService) {
		this.offerteService = offerteService;
	}

	private static final String STAP_1_VIEW = "offertes/stap1";

	@GetMapping("toevoegen")
	ModelAndView stap1() {
		return new ModelAndView(STAP_1_VIEW).addObject(new Offerte());
	}

	private static final String STAP_2_VIEW = "offertes/stap2";

	@PostMapping(value = "toevoegen", params = "stap2")
	String stap1NaarStap2(@Validated(Offerte.Stap1.class) Offerte offerte, BindingResult bindingResult) {
		return bindingResult.hasErrors() ? STAP_1_VIEW : STAP_2_VIEW;
	}

	@PostMapping(value = "toevoegen", params = "stap1")
	String stap2NaarStap1(Offerte offerte) {
		return STAP_1_VIEW;
	}

	private static final String REDIRECT_URL_NA_TOEVOEGEN = "redirect:/";
	
	@PostMapping(value = "toevoegen", params = "opslaan")
	String create(@Validated(Offerte.Stap2.class) Offerte offerte, BindingResult bindingResult,
			SessionStatus sessionStatus) {
		if (bindingResult.hasErrors()) {
			return STAP_2_VIEW;
		}
		offerteService.create(offerte);
		sessionStatus.setComplete();
		return REDIRECT_URL_NA_TOEVOEGEN;
	}
}
