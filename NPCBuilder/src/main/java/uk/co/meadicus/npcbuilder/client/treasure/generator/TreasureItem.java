package uk.co.meadicus.npcbuilder.client.treasure.generator;

import java.util.List;

import uk.co.meadicus.npcbuilder.client.treasure.Treasure.TreasureType;
import uk.co.meadicus.npcbuilder.client.treasure.generator.support.TableRoll;

public class TreasureItem extends SimpleRandomTableItem {

	private final TreasureType type;
	private final List<TableRoll> diceRolls;
	
	public TreasureItem(TreasureType type, String description, Integer value, Integer pageRef, List<TableRoll> diceRolls) {
		super(description, value, pageRef);
		this.type = type;
		this.diceRolls = diceRolls;
	}

	public TreasureType getType() {
		return type;
	}

	public boolean hasValue() {
		return (getValue() != null);
	}

	public List<TableRoll> getDiceRolls() {
		return diceRolls;
	}
	
}
