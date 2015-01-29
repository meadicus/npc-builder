package uk.co.meadicus.npcbuilder.client.treasure.generator.support;

import java.util.List;

import uk.co.meadicus.npcbuilder.client.treasure.generator.TreasureGenSpec;


public abstract class MultValueAlgorithm implements ValueAlgorithm{

	private final double multiplier;
	
	public MultValueAlgorithm(double multiplier) {
		this.multiplier = multiplier;
	}

	protected double getMultiplier() {
		return multiplier;
	}
	
	abstract double getMultiplicationBase(TreasureGenSpec spec, List<TableRoll> diceRolls);
	
	@Override
	public int getValue(TreasureGenSpec spec, List<TableRoll> diceRolls) {
		return (int)Math.floor(getMultiplicationBase(spec, diceRolls) * this.multiplier);
	}
	
}
