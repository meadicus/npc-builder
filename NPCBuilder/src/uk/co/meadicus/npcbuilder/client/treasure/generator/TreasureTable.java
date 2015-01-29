package uk.co.meadicus.npcbuilder.client.treasure.generator;

import java.util.ArrayList;
import java.util.List;

import uk.co.meadicus.npcbuilder.client.treasure.Treasure.TreasureType;
import uk.co.meadicus.npcbuilder.client.treasure.generator.support.DefaultSpecApplicator;
import uk.co.meadicus.npcbuilder.client.treasure.generator.support.SpecApplicator;
import uk.co.meadicus.npcbuilder.client.treasure.generator.support.TableRoll;
import uk.co.meadicus.npcbuilder.client.util.NPCUtils;

public class TreasureTable extends RandomTable {

	private final TreasureType type;
	private final SpecApplicator specApplicator; 
	
	public TreasureTable(String name, TreasureType type) {
		super(name);
		this.type = type;
		this.specApplicator = new DefaultSpecApplicator();
	}
	
	public TreasureTable(String name, RollType rollType, TreasureType type) {
		super(name, rollType);
		this.type = type;
		this.specApplicator = new DefaultSpecApplicator();
	}

	public TreasureTable(String name, RollType rollType, boolean visibleRoll, TreasureType type) {
		super(name, rollType, visibleRoll);
		this.type = type;
		this.specApplicator = new DefaultSpecApplicator();
	}

	@Override
	public RandomTableItem getRandomItem(int rollMod, TreasureGenSpec spec,
			List<TableRoll> diceRolls) {
		RandomTableItem item = super.getRandomItem(rollMod, spec, diceRolls);
		return randomItemToTreasure(item, diceRolls);
	}

	public TreasureItem generateItem(TreasureGenSpec spec) {
		
		List<TableRoll> diceRolls = new ArrayList<TableRoll>();
		
		RandomTableRow row = getRandomItem(this.specApplicator.getRollMod(spec), diceRolls);
		
		RandomTableItem item = row.getAsItem(spec, diceRolls);
		
		return randomItemToTreasure(item, diceRolls);
	}
	
	public TreasureItem randomItemToTreasure(RandomTableItem item, List<TableRoll> diceRolls) {
		TreasureItem result;
		if (item instanceof TreasureItem) {
			result = (TreasureItem) item;
		} else {
			result = new TreasureItem(type, 
									  NPCUtils.evalDiceText(item.getDescription()),
									  item.getValue(),
									  item.getPageRef(),
									  diceRolls);
			result.getChildItems().addAll(item.getChildItems());
		}
		return result;
	}
	
}
