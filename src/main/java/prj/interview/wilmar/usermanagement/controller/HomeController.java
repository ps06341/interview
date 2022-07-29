package prj.interview.wilmar.usermanagement.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {

	@GetMapping(value = "/")
	public ModelAndView test(HttpServletResponse response) {
		try {
			log.debug("HomeController start");
		} catch (Exception e) {
			log.debug(getExceptionStackTrace(e));
		}

		return new ModelAndView("home");
	}
}
