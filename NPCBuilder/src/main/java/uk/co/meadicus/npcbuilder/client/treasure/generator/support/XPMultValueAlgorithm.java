package uk.co.meadicus.npcbuilder.client.treasure.generator.support;

import java.util.List;

import uk.co.meadicus.npcbuilder.client.treasure.generator.TreasureGenSpec;

public class XPMultValueAlgorithm extends MultValueAlgorithm {

	public XPMultValueAlgorithm(double multiplier) {
		super(multiplier);
	}

	
	double getMultiplicationBase(TreasureGenSpec spec, List<TableRoll> diceRolls) {
		return spec.getXp();
	}


}
