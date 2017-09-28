package com.libertymutual.goforcode.spark.app;

import com.libertymutual.goforcode.spark.app.controllers.ApartmentApiController;
import com.libertymutual.goforcode.spark.app.controllers.ApartmentController;
import com.libertymutual.goforcode.spark.app.controllers.HomeController;
import com.libertymutual.goforcode.spark.app.controllers.SessionApiController;
import com.libertymutual.goforcode.spark.app.controllers.SessionController;
import com.libertymutual.goforcode.spark.app.controllers.UserController;
import com.libertymutual.goforcode.spark.app.filters.SecurityFilters;
import com.libertymutual.goforcode.spark.app.models.Apartment;
import com.libertymutual.goforcode.spark.app.models.User;
import com.libertymutual.goforcode.spark.app.utilities.AutoCloseableDb;

import static spark.Spark.*;

import org.mindrot.jbcrypt.BCrypt;

public class Application {
	
	public static void main(String[] args) {
		
		String encryptedPassword = BCrypt.hashpw("password", BCrypt.gensalt());
		
		try (AutoCloseableDb db = new AutoCloseableDb()) {
			User.deleteAll();
			User roman = new User("rg@gmail.com", encryptedPassword, "Roman", "Galanti");
			roman.saveIt();
			Apartment.deleteAll();
			Apartment apartment = new Apartment(6000, 1, 0, 350, "123 Main St.", "San Francisco", "CA", "95125", true);
			roman.add(apartment);
			apartment.saveIt();
			apartment = new Apartment(1400, 5, 6, 4000, "123 Cowboy Way", "Houston", "TX", "77006", false);
			roman.add(apartment);
			apartment.saveIt();
		}
		
		enableCORS("http://localhost:4200", "*", "*");
		
		path("/apartments", () -> {
			before("/new", SecurityFilters.isAuthenticated);
			get("/new", ApartmentController.newForm);
			
			before("/mine", SecurityFilters.isAuthenticated);
			get("/mine", ApartmentController.index);
			
			get("/:id", ApartmentController.details);
			post("/:id/activations", ApartmentController.activate);
			post("/:id/deactivations", ApartmentController.deactivate);
			post("/:id/like", ApartmentController.like);
			
			before("", SecurityFilters.isAuthenticated);
			post("", ApartmentController.create);
		});
		
		get("/", HomeController.index);
		get("/login", SessionController.newForm);
		get("/users/new", UserController.newForm);
		post("/logout", SessionController.destroy);
		post("/users", UserController.create);
		post("/login", SessionController.create);
		
		path("/api", () -> {
			get("/apartments", ApartmentApiController.index);
			get("/apartments/mine", ApartmentApiController.mine);
			get("/apartments/:id", ApartmentApiController.details);
			post("/apartments", ApartmentApiController.create);
			post("/apartments/:id/activations", ApartmentApiController.activate);
			post("/apartments/:id/deactivations", ApartmentApiController.deactivate);
			post("/apartments/:id/like", ApartmentApiController.like);
			post("/sessions", SessionApiController.create);
			delete("/sessions/mine", SessionApiController.destroy);
		});
	}
	
	// Enables CORS on requests. This method is an initialization method and should be called once.
	private static void enableCORS(final String origin, final String methods, final String headers) {

	    options("/*", (request, response) -> {

	        String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
	        if (accessControlRequestHeaders != null) {
	            response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
	        }

	        String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
	        if (accessControlRequestMethod != null) {
	            response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
	        }

	        return "OK";
	    });

	    before((request, response) -> {
	        response.header("Access-Control-Allow-Origin", origin);
	        response.header("Access-Control-Request-Method", methods);
	        response.header("Access-Control-Allow-Headers", headers);
	        response.header("Access-Control-Allow-Credentials", "true");
	        // Note: this may or may not be necessary in your particular application
	        response.type("application/json");
	    });
	}

}
