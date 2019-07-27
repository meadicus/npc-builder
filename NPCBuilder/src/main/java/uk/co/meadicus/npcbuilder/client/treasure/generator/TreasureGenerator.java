package uk.co.meadicus.npcbuilder.client.treasure.generator;

import uk.co.meadicus.npcbuilder.client.treasure.Treasure.TreasureType;

public class TreasureGenerator {

	private FantasyCraftTreasureTables tables;
	
	public TreasureGenerator() {
		tables = FantasyCraftTreasureTables.getInstance();
	}
	
	public TreasureItem randomItem(TreasureType type, TreasureGenSpec spec) {
		TreasureTable table = tables.getTreasureTable(type);
		return table.generateItem(spec);
	}
	
}
