package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.format.Formats.DateTime;
import play.db.ebean.Model;

import com.avaje.ebean.annotation.CreatedTimestamp;

@Entity
public class Event extends Model{

	@Id
	public Long id;

	public String title;

	public String text;

	@DateTime(pattern="yyyy/mm/dd")
	public String date;

	public String time;

	@CreatedTimestamp
	public Date post;

	public static Finder<Long,Event> find = new Finder<Long, Event>(Long.class, Event.class);

}
