package controllers;

import java.util.List;

import com.mysql.jdbc.log.Log;

import play.mvc.*;

import views.html.*;

import models.*;
import play.db.ebean.Transactional;
import static play.libs.Json.toJson;

public class Application extends Controller {

	static final int MAX_COOLNESS = 10000;
	static final int MAX_NAME_SUFFIX = 200;

	public static Result index() {
		return ok(index.render("Your new application is ready."));
	}

	public static Result myDerper() {
		addRandomDerp();

		List<Derp> allDerps = findAllDerps();

		return ok("derper OK: " + allDerps);
	}

	public static Result myDerperJson() {
		List<Derp> allDerps = findAllDerps();

		return ok(toJson(allDerps));
	}

	public static Result myDerperDelete(String id) {
		Long derpID = new Long(id);
		if (deleteDerpWithID(derpID)) {
			return ok("Deleted derp " + id);
		} else {
			return ok("Couldn't delete derp " + id);
		}
	}

	public static Result myDerperUpdate(String id, String name, String coolness) {
		boolean status = updateDerp(new Long(id), name, new Integer(coolness));

		if (status) {
			Derp derp = Derp.find.byId(new Long(id));
			return ok("Updated derp to: " + derp);
		} else {
			return ok("Couldn't update derp");
		}
	}

	@Transactional
	public static List<Derp> findAllDerps() {
		return Derp.find.all();
	}

	@Transactional
	public static void addRandomDerp() {
		Derp derp = new Derp();

		int nameSuffix = ((new java.util.Random().nextInt() % MAX_NAME_SUFFIX) + MAX_NAME_SUFFIX) / 2;
		int coolness = ((new java.util.Random().nextInt() % MAX_COOLNESS) + MAX_COOLNESS) / 2;

		derp.name = "Derper " + nameSuffix;
		derp.coolness = coolness;

		derp.save();
	}

	@Transactional
	public static boolean deleteDerpWithID(Long derpID) {
		Derp derp = Derp.find.byId(derpID);

		if (derp == null) {
			System.out.println("Derp not found size: ");
		} else {
			derp.delete();

			return true;
		}

		return false;
	}

	@Transactional
	public static boolean updateDerp(Long id, String name, Integer coolness) {
		Derp derp = Derp.find.byId(id);

		if (derp == null) {
			System.out.println("Derp not found");
		} else {
			derp.name = name;
			derp.coolness = coolness;

			derp.update();

			return true;
		}

		return false;
	}

}
