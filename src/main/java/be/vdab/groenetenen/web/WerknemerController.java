package be.vdab.groenetenen.web;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.groenetenen.services.WerknemerService;

@RequestMapping("werknemers")
@Controller
class WerknemerController {
	private static final String WERKNEMERS_VIEW = "werknemers/werknemers";
	private final WerknemerService werknemerService;

	WerknemerController(WerknemerService werknemerService) {
		this.werknemerService = werknemerService;
	}

	@GetMapping()
	ModelAndView lijst(Pageable pageable) {
		return new ModelAndView(WERKNEMERS_VIEW, "page", werknemerService.findAll(pageable));
	}

}
