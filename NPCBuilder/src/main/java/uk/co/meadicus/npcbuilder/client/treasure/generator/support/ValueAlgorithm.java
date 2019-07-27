package uk.co.meadicus.npcbuilder.client.treasure.generator.support;

import java.util.List;

import uk.co.meadicus.npcbuilder.client.treasure.generator.TreasureGenSpec;

public interface ValueAlgorithm {

	int getValue(TreasureGenSpec spec, List<TableRoll> diceRolls);
}
