package com.goeuro.hireme;

import java.io.Reader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * Parses a json in a Reader using Gson and export to csv file
 * 
 * @author israel.lacerra
 *
 */
public final class GoEuroParser {

	private static final String DEFAULT_CSV_SEPARATOR = ",";

	private static final String NEW_LINE = "\n";

	private GoEuroParser() {
		// use just statically
	}

	public static Location[] loadJson(Reader reader) {
		Gson gson = new GsonBuilder().create();
		return gson.fromJson(reader, Location[].class);
	}

	public static String exportCsvs(Location[] locations) {
		StringBuilder sb = new StringBuilder();
		for (Location l : locations) {
			sb.append(l.get_id());
			sb.append(DEFAULT_CSV_SEPARATOR);
			sb.append(l.getName());
			sb.append(DEFAULT_CSV_SEPARATOR);
			sb.append(l.getType());
			sb.append(DEFAULT_CSV_SEPARATOR);
			sb.append(l.getGeo_position().getLatitude());
			sb.append(DEFAULT_CSV_SEPARATOR);
			sb.append(l.getGeo_position().getLongitude());
			sb.append(NEW_LINE);
		}
		return sb.toString();
	}

}
