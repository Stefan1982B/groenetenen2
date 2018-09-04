package be.vdab.groenetenen.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.groenetenen.entities.Filiaal;
import be.vdab.groenetenen.services.FiliaalService;

@RequestMapping(path = "filialen", produces = MediaType.TEXT_HTML_VALUE)
@Controller
class FiliaalController {
	private static final String VAN_TOT_POSTCODE_VIEW = "filialen/vantotpostcode";
	private final FiliaalService filiaalService;

	FiliaalController(FiliaalService filiaalService) {
		this.filiaalService = filiaalService;
	}

	@GetMapping("vantotpostcode")
	ModelAndView vanTotPostcode() {
		return new ModelAndView(VAN_TOT_POSTCODE_VIEW).addObject(new VanTotPostcodeForm());
	}

	@GetMapping(params = { "van", "tot" })
	ModelAndView vanTotPostcode(@Valid VanTotPostcodeForm form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView(VAN_TOT_POSTCODE_VIEW);
		}
		return new ModelAndView(VAN_TOT_POSTCODE_VIEW, "filialen",
				filiaalService.findByAdresPostcode(form.getVan(), form.getTot()));
	}

	private static final String FILIAAL_VIEW = "filialen/filiaal";
	private static final String REDIRECT_FILIAAL_NIET_GEVONDEN = "redirect:/";

	@GetMapping("{filiaal}")
	ModelAndView read(@PathVariable Optional<Filiaal> filiaal, RedirectAttributes redirectAttributes) {
		int aantalFilialen = filiaalService.findAll().size();
		if (filiaal.isPresent()) {
			return new ModelAndView(FILIAAL_VIEW, "filiaal", filiaal.get()).addObject("aantalFilialen", aantalFilialen);
		}
		redirectAttributes.addAttribute("fout", "Filiaal niet gevonden");
		return new ModelAndView(REDIRECT_FILIAAL_NIET_GEVONDEN);
	}

	private static final String PER_ID_VIEW = "filialen/perid";

	@GetMapping("perid")
	String findById() {
		return PER_ID_VIEW;
	}
}
