package uk.co.meadicus.npcbuilder.client.treasure.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.co.meadicus.npcbuilder.client.treasure.generator.support.TableRoll;
import uk.co.meadicus.npcbuilder.client.util.NPCUtils;

public class RandomTable {

	private int lowerLimit = 999;
	private int upperLimit = -1;
	private final String name;
	private final List<RandomTableRow> items = new ArrayList<RandomTableRow>();
	private final Map<Integer, Integer> table = new HashMap<Integer, Integer>();
	private final boolean visibleRoll;
	
	public enum RollType {
		D20, D40, EVEN
	}
	private final RollType rollType;
	
	public RandomTable(String name) {
		this(name, RollType.D20, true);
	}

	public RandomTable(String name, RollType rollType) {
		this(name, rollType, true);
	}
	public RandomTable(String name, RollType rollType, boolean visibleRoll) {
		this.name = name;
		this.rollType = rollType;
		this.visibleRoll = visibleRoll;
	}
	
	public RandomTableRow getItem(int index) {
		int listIndex;
		if (index <= lowerLimit) {
			listIndex = table.get(lowerLimit);
		} else if (index >= upperLimit) {
			listIndex = table.get(upperLimit);
		} else {
			try {
				listIndex = table.get(index);
			} catch (NullPointerException ex) {
				//Window.alert("Table " + this.name + " is missing an entry for " + index);
				listIndex = lowerLimit;
			}
		}
		return items.get(listIndex);
	}
	public RandomTableRow getRandomItem(List<TableRoll> diceRolls) {
		int itemIndex = rollDice();
		if (this.visibleRoll) {
			diceRolls.add(new TableRoll(this.name, itemIndex));
		}
		return getItem(itemIndex);
	}

	public RandomTableRow getRandomItem(int mod, List<TableRoll> diceRolls) {
		int itemIndex = rollDice(mod);
		if (this.visibleRoll) {
			diceRolls.add(new TableRoll(this.name, itemIndex));
		}
		return getItem(itemIndex);
	}
	
	public RandomTableItem getRandomItem(int rollMod, TreasureGenSpec spec, List<TableRoll> diceRolls) {
		RandomTableRow row = getRandomItem(rollMod, diceRolls);
		RandomTableItem item = row.getAsItem(spec, diceRolls);
		return item;
	}

	private int rollDice() {
		return rollDice(0);
	}

	private int rollDice(int mod) {
		int itemIndex;
		switch (this.rollType) {
		case D20:
			itemIndex = NPCUtils.rollD20() + mod;
			break;
		case D40:
			itemIndex = NPCUtils.rollD20() + NPCUtils.rollD20() + mod;
			break;
		case EVEN:
		default:
			itemIndex = NPCUtils.randomInRange(lowerLimit, upperLimit);
		}
		return itemIndex;
	}
	
	public void addItem(int randomRangeStart, int randomRangeEnd, RandomTableRow row) {
		items.add(row);
		int listIndex = items.size()-1;
		for (int i = randomRangeStart; i <= randomRangeEnd; ++i) {
			table.put(i, listIndex);
			if (i < lowerLimit) {
				lowerLimit = i;
			}
			if (i > upperLimit) {
				upperLimit = i;
			}
		}
	}

	public void addItem(int key, RandomTableRow row) {
		addItem(key, key, row);
	}
	
	public int getLowerLimit() {
		return lowerLimit;
	}

	public int getUpperLimit() {
		return upperLimit;
	}

	public String getName() {
		return name;
	}
	
}
