package uk.co.meadicus.npcbuilder.client.treasure.generator.support;

import java.util.List;

import uk.co.meadicus.npcbuilder.client.treasure.generator.TreasureGenSpec;
import uk.co.meadicus.npcbuilder.client.util.NPCUtils;

public class D20MultValueAlgorithm extends MultValueAlgorithm {

	public D20MultValueAlgorithm(double multiplier) {
		super(multiplier);
	}

	
	double getMultiplicationBase(TreasureGenSpec spec, List<TableRoll> diceRolls) {
		int roll = NPCUtils.rollD20();
		diceRolls.add(new TableRoll("Value", roll));
		return roll;
	}


}
