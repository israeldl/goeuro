package com.goeuro.hireme;

/**
 * 
 * Entity to map json results of GoEuro Location JSON API: 
 * 
 * http://api.goeuro.com/api/v2/position/suggest/en/CITY_NAME
 * 
 * This entity is loaded using Gson. Please, do not change the fields names, as they
 * need to be the same as the ones founds in the JSON API.
 * 
 * @author israel.lacerra
 *
 */
public class Location {

	private int _id;

	private String name;

	private String fullName;

	private String type;

	private String country;

	private LocationGeo geo_position;

	public int get_id() {
		return _id;
	}

	public String getName() {
		return name;
	}

	public String getFullName() {
		return fullName;
	}

	public String getType() {
		return type;
	}

	public String getCountry() {
		return country;
	}

	public LocationGeo getGeo_position() {
		return geo_position;
	}

}