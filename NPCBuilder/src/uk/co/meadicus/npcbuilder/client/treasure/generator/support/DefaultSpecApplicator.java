package uk.co.meadicus.npcbuilder.client.treasure.generator.support;

import uk.co.meadicus.npcbuilder.client.treasure.generator.TreasureGenSpec;

public class DefaultSpecApplicator implements SpecApplicator {

	@Override
	public int getRollMod(TreasureGenSpec spec) {
		return spec.getTl() + spec.getMod();
	}

}
