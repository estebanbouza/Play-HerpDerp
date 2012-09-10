package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Herp extends Model {

	@Id
	public Long id;

	@Constraints.Required
	public String name;

	@OneToMany
	@JsonManagedReference
	public List<Derp> derps;

	public static Finder<Long, Herp> find = new Finder<Long, Herp>(Long.class,
			Herp.class);

	@Override
	public String toString() {
		return "ID: " + this.id + ", name: " + this.name;
	}
}
