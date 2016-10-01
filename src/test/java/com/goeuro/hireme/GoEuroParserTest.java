package com.goeuro.hireme;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class GoEuroParserTest extends TestCase {

	public GoEuroParserTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(GoEuroParserTest.class);
	}

	public void testGoEuro() throws IOException {
		InputStream testFile = this.getClass().getResourceAsStream("/berlin.json");
		BufferedReader reader = new BufferedReader(new InputStreamReader(testFile));
		Location[] locations = GoEuroParser.loadJson(reader);
		assertTrue(locations.length == 8);

		String expectedCsv = getExpectedCsv();
		String csv = GoEuroParser.exportCsvs(locations);
		assertTrue(csv.equals(expectedCsv));
	}

	private String getExpectedCsv() throws IOException {
		URL url = this.getClass().getResource("/berlin.csv");
		StringBuilder sb = new StringBuilder();
		Files.lines(Paths.get(url.getPath())).forEach(line -> sb.append(line).append("\n"));
		String expectedCsv = sb.toString();
		return expectedCsv;
	}
}
