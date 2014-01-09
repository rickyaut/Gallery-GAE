package com.rickyaut.gallery;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SearchController {
	private static final Logger logger = Logger.getLogger(SearchController.class.getName());
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public ModelAndView requestContactUs(){
		ModelAndView mv = new ModelAndView("search");
		return mv;
	}
	
}
