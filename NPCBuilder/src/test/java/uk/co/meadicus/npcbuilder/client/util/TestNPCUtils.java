package uk.co.meadicus.npcbuilder.client.util;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;


public class TestNPCUtils extends TestCase {

	@Test
	public void testBracketAwareSplit() {
		
		String simpleList = "hello, foo, bar";
		String[] simpleExp = {"hello", "foo", "bar"};
		textListParseExample(simpleList, simpleExp, ',');

		String input1 = "hello, foo (yo), bar";
		String[] expected1 = {"hello", "foo (yo)", "bar"};
		textListParseExample(input1, expected1, ',');

		String input2 = "hello, foo (((yo))), bar";
		String[] expected2 = {"hello", "foo (((yo)))", "bar"};
		textListParseExample(input2, expected2, ',');

		String input3 = "hello, foo (yo (go) (fo)), bar";
		String[] expected3 = {"hello", "foo (yo (go) (fo))", "bar"};
		textListParseExample(input3, expected3, ',');

		String input4 = "hello, foo (yo ((go) (fo))), bar";
		String[] expected4 = {"hello", "foo (yo ((go) (fo)))", "bar"};
		textListParseExample(input4, expected4, ',');

		String input5 = "hello, foo (yo ((go) (fo)), bar";
		String[] expected5 = {"hello", "foo (yo ((go) (fo)), bar"};
		textListParseExample(input5, expected5, ',');

		String input6 = "hello, foo (yo) (do,may), bar";
		String[] expected6 = {"hello", "foo (yo) (do,may)", "bar"};
		textListParseExample(input6, expected6, ',');

		String input7 = "hello, foo (yo) [do,m(ay], bar";
		String[] expected7 = {"hello", "foo (yo) [do,m(ay]", "bar"};
		textListParseExample(input7, expected7, ',');
	}

	private void textListParseExample(String input, String[] output, char delim) {
		textListParseExample(input, Arrays.asList(output), delim);
	}
	
	private void textListParseExample(String input, List<String> output, char delim) {
		List<String> items = NPCUtils.bracketAwareSplit(input, delim);
		
		assertEquals(items, output);
	}
}
