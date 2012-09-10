package controllers;

import java.io.IOException;
import java.util.List;

import models.Derp;
import models.Herp;
import play.db.ebean.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Application extends Controller {

	static final int MAX_COOLNESS = 10000;
	static final int MAX_NAME_SUFFIX = 200;

	static final int MAX_HERP_SUFFIX = 20;

	public static Result index() {
		return ok(index.render("Your new application is ready."));
	}

	public static Result myDerperCreate() {
		addRandomDerp();

		List<Derp> allDerps = findAllDerps();

		return ok("derper OK: " + allDerps);
	}

	public static Result myHerperCreate() {
		addRandomHerp();

		List<Herp> allHerps = findAllHerps();

		return ok("derper OK: " + allHerps);
	}

	public static Result myDerperJson() {
		List<Derp> allDerps = findAllDerps();

		ObjectMapper mapper = new ObjectMapper();
		try {
			return ok(mapper.writeValueAsString(allDerps));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Result myHerperJson() {
		List<Herp> allHerps = findAllHerps();

		ObjectMapper mapper = new ObjectMapper();

		try {
			return ok(mapper.writeValueAsString(allHerps));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
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

	public static Result myDerperAssign(String idDerp, String idHerp) {
		Derp derp = Derp.find.byId(new Long(idDerp));
		Herp herp = Herp.find.byId(new Long(idHerp));

		derp.herp = herp;

		derp.update();

		return ok("Assigned derp " + derp);
	}

	@Transactional
	public static List<Derp> findAllDerps() {
		return Derp.find.all();
	}

	@Transactional
	public static List<Herp> findAllHerps() {
		return Herp.find.all();
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
	public static void addRandomHerp() {
		Herp herp = new Herp();

		int nameSuffix = ((new java.util.Random().nextInt() % MAX_HERP_SUFFIX) + MAX_HERP_SUFFIX) / 2;

		herp.name = "Herp " + nameSuffix;

		herp.save();
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
