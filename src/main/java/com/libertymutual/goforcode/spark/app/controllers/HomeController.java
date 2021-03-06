package com.libertymutual.goforcode.spark.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.libertymutual.goforcode.spark.app.models.Apartment;
import com.libertymutual.goforcode.spark.app.utilities.AutoCloseableDb;
import com.libertymutual.goforcode.spark.app.utilities.JTwigRenderer;
import com.libertymutual.goforcode.spark.app.utilities.MustacheRenderer;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;

public class HomeController {
	
//	public static final Route index = (Request req, Response res) -> {
//		List<Apartment> apartments = Apartment.findAll();
//		Map<String, Object> model = new HashMap<String, Object>();
//		model.put("apartments", apartments);
//		model.put("currentUser", req.session().attribute("currentUser"));
////		model.put("noUser", req.session().attribute("currentUser") == null);
//		ModelAndView mv = new ModelAndView(model, "index.html");
////		return MustacheRenderer.getInstance().render("home/index.html", model);
//		JTwigRenderer twiggy = new JTwigRenderer();
//		return twiggy.render(mv);
//	};
	
	public static final Route index = (Request req, Response res) -> {
		try (AutoCloseableDb db = new AutoCloseableDb()) {
		List<Apartment> apartments = Apartment.findAll();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("apartments", apartments);
		model.put("currentUser", req.session().attribute("currentUser"));
		model.put("noUser", req.session().attribute("currentUser") == null);
		return MustacheRenderer.getInstance().render("home/index.html", model);
		}
	};

}
