package com.goeuro.hireme;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

/**
 * This app searchs for a json file in GoEuro Location JSON API
 * and returns a csv file with the the form: _id, name, type, latitude, longitude
 * 
 * Usage example:
 * java -jar GoEuro.jar berlin
 * 
 * The csv file will be printed in the console. If the user wants to put it in a file,
 * he can redirect using ">"
 * 
 * java -jar GoEuro.jar berlin > berlin.csv
 * 
 * @author israel.lacerra
 *
 */
public class GoEuro {

	private static final String GOEURO_REST_URL = "http://api.goeuro.com/api/v2/position/suggest/en/";

	public static void main(String[] args) {
		if (args.length != 1) {
			printUsageInstructions();
			return;
		}

		String url = getUrlString(args[0]);

		Location[] locations;
		try {
			Reader reader = readUrl(url);
			locations = GoEuroParser.loadJson(reader);
		} catch (IOException e) {
			System.out.println("I could not connect to " + url);
			return;
		} catch (JsonSyntaxException | JsonIOException e) {
			System.out.println(
					"Unexpected JSON file format. Maybe there is a temporary error or the file format did change.");
			return;
		}

		String csv = GoEuroParser.exportCsvs(locations);
		System.out.println(csv);
	}

	private static void printUsageInstructions() {
		System.out.println("== GoEuro hire me app ==");
		System.out.println("Usage:");
		System.out.println("java -jar goeuro.jar \"location\"");
		System.out.println();
		System.out.println("Example: ");
		System.out.println("java -jar goeuro.jar berlin ");
		System.out.println("The csv file will be printed in the console. If you want it in a file, you can do this:");
		System.out.println("java -jar goeuro.jar berlin > berlin.csv");
		System.out.println("=======================");
	}

	private static BufferedReader readUrl(String stringUrl) throws IOException {
		URL url = new URL(stringUrl);
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		return reader;
	}

	private static String getUrlString(String location) {
		return GOEURO_REST_URL + location;
	}

}
