package uk.co.meadicus.npcbuilder.client.template.quality;

import java.util.List;

import uk.co.meadicus.npcbuilder.client.quality.DetailQuality;

public class DetailQualityMerger extends QualityMerger<DetailQuality> {

	public DetailQualityMerger(DetailQuality baseQuality) {
		super(baseQuality);
	}

	
	protected DetailQuality qualifiedMergeIn(DetailQuality in) {
		
		DetailQuality quality = baseQuality.clone();
		
		List<String> qualityUses = quality.getUses();
		List<String> inUses = in.getUses();
		
		for (String inUse : inUses) {
			if (!qualityUses.contains(inUse)) {
				qualityUses.add(inUse);
			}
		}
		
		return quality;
	}

	
	protected DetailQuality qualifiedMergeOut(DetailQuality out) {
		
		DetailQuality quality = baseQuality.clone();
		
		List<String> qualityUses = quality.getUses();
		List<String> outUses = out.getUses();
		
		for (String outUse : outUses) {
			qualityUses.remove(outUse);
		}
		
		if (qualityUses.isEmpty()) {
			quality = null;
		}
		
		return quality;
	}

}
