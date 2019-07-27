package uk.co.meadicus.npcbuilder.client.treasure.generator;

import uk.co.meadicus.npcbuilder.client.treasure.generator.RandomTable.RollType;

public abstract class RandomTableBuilder {

	/**
	 * Builds a rando mtable from the given text whichmust contain
	 * comma delimited entries each is "start-end: detail" or "roll: detail".
	 * 
	 * @param details
	 * @return
	 */
	public static RandomTable buildSimpleTable(String name, String details, RollType rollType) {
		
		RandomTable table;
		if (rollType != null) {
			table = new RandomTable(name, rollType);
		} else {
			table = new RandomTable(name);
		}
		
		populateRandomTable(table, details);
		
		return table;
	}

	public static void populateRandomTable(RandomTable table, String details) {
		String[] rows = details.split(",");
		
		for (String row : rows) {
			int colon = row.indexOf(':');
			String numbers = row.substring(0, colon).trim();
			String desc = row.substring(colon+1).trim();
			RandomTableRow tableRow = new RandomTableRow(desc);
			if (numbers.matches("\\d+[-–]\\d+")) {
				String[] rolls = numbers.split("[-–]");
				int start = Integer.parseInt(rolls[0]);
				int end = Integer.parseInt(rolls[1]);
				table.addItem(start, end, tableRow);
			} else if (numbers.matches("\\d+")) {
				int roll = Integer.parseInt(numbers);
				table.addItem(roll, tableRow);
			} else {
				// something's wrong, ignore for now
			}
			
		}
	}

	public static RandomTable buildSimpleTable(String name, String details) {
		return buildSimpleTable(name, details, null);
	}
	
	public static RandomTable buildEvenTable(String name, String[] items) {
		return buildEvenTable(name, items, true);
	}

	public static RandomTable buildEvenTable(String name, String[] items, boolean visibleRoll) {
		RandomTable table = new RandomTable(name, RollType.EVEN, visibleRoll);
		
		for (int i = 1; i < items.length; ++i) {
			table.addItem(i, new RandomTableRow(items[i]));
		}
		
		return table;
	}
	
	public static RandomTable buildEvenTable(String name, String list, String delim) {
		return buildEvenTable(name, list, delim, true);
	}

	public static RandomTable buildEvenTable(String name, String list, String delim, boolean visibleRoll) {
		String[] items = list.split(delim);
		return buildEvenTable(name, items, visibleRoll);
	}
}
