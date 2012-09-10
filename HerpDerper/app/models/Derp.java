package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Derp extends Model {

	@Id
	@Constraints.Min(100)
	public Long id;

	public String name;

	public Integer coolness;

	public static Finder<Long, Derp> find = new Finder<Long, Derp>(Long.class,
			Derp.class);
	
	
	@Override
	public String toString() {
		return "ID: " + this.id + "name: " + this.name + "coolness: " + this.coolness;
	}

}
