package uk.co.meadicus.npcbuilder.client.treasure.generator.support;

import uk.co.meadicus.npcbuilder.client.treasure.generator.TreasureGenSpec;

public class FixedSpecApplicator extends DefaultSpecApplicator {

	private final int fixedMod;
	
	public FixedSpecApplicator(int fixedMod) {
		super();
		this.fixedMod = fixedMod;
	}

	
	public int getRollMod(TreasureGenSpec spec) {
		return super.getRollMod(spec) + fixedMod;
	}

}
