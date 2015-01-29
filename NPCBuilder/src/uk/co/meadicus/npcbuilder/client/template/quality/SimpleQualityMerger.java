package uk.co.meadicus.npcbuilder.client.template.quality;

import uk.co.meadicus.npcbuilder.client.quality.SimpleQuality;

/**
 * Simple qualities have just a single use.
 * @author mead
 *
 */
public class SimpleQualityMerger extends QualityMerger<SimpleQuality> {

	public SimpleQualityMerger(SimpleQuality baseQuality) {
		super(baseQuality);
	}

	@Override
	protected SimpleQuality qualifiedMergeIn(SimpleQuality in) {
		SimpleQuality quality = baseQuality.clone();
		
		if (Math.abs(in.getXp().getValue()) > Math.abs(quality.getXp().getValue())) {
			quality = in;
		}
		
		return quality;
	}

	@Override
	protected SimpleQuality qualifiedMergeOut(SimpleQuality out) {
		SimpleQuality quality = baseQuality.clone();
		
		if (Math.abs(out.getXp().getValue()) >= Math.abs(quality.getXp().getValue())) {
			quality = null;
		}
		
		return quality;
	}

}
