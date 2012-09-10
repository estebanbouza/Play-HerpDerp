package controllers;

import java.util.List;

import play.*;
import play.mvc.*;
import scala.util.Random;

import views.html.*;

import models.*;
import play.api.libs.json.*;
import static play.libs.Json.toJson;

public class Application extends Controller {

	public static Result index() {
		return ok(index.render("Your new application is ready."));
	}

	public static Result myDerper() {
		Derp derp = new Derp();

		derp.name = "Derper " + new Random().nextInt() % 200;
		derp.coolness = new Random().nextInt() % 10000;

		derp.save();

		List<Derp> allDerps = findAllDerps();

		return ok("derper OK: " + allDerps);
	}

	public static Result myDerperJson() {
		List<Derp> allDerps = findAllDerps();

		return ok(toJson(allDerps));

	}

	public static List<Derp> findAllDerps() {
		return Derp.find.all();
	}

}