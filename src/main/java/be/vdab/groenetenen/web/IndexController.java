package be.vdab.groenetenen.web;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/")
@Controller
public class IndexController {

	private static final String VIEW = "index";

	private String begroeting() {
		String begroeting = "";
		int hour = LocalDateTime.now().getHour();
		if (hour < 12) {
			begroeting = "goedeMorgen";
		} else if (hour < 18) {
			begroeting = "goedeMiddag";
		} else {
			begroeting = "goedeAvond";
		}
		return begroeting;
	}

	@GetMapping
	ModelAndView index() {
		return new ModelAndView(VIEW, "begroeting", begroeting());
	}

}
