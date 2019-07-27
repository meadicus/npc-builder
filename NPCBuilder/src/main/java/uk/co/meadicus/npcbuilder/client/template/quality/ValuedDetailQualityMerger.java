package uk.co.meadicus.npcbuilder.client.template.quality;

import java.util.Map;

import uk.co.meadicus.npcbuilder.client.quality.ValuedDetailQuality;

public class ValuedDetailQualityMerger extends
		QualityMerger<ValuedDetailQuality> {

	public ValuedDetailQualityMerger(ValuedDetailQuality baseQuality) {
		super(baseQuality);
	}

	
	protected ValuedDetailQuality qualifiedMergeIn(ValuedDetailQuality in) {
		
		ValuedDetailQuality quality = baseQuality.clone();
		
		Map<String, String> qualityUses = quality.getUses();
		Map<String, String> inUses = in.getUses();
		
		for (Map.Entry<String, String> inEntry : inUses.entrySet()) {
			
			if (qualityUses.containsKey(inEntry.getKey())) {
				// Merge
				double qualityXp = Math.abs(quality.getXpForValue(qualityUses.get(inEntry.getKey())));
				double inXp = Math.abs(in.getXpForValue(inEntry.getValue()));
				if (inXp > qualityXp) {
					qualityUses.put(inEntry.getKey(), inEntry.getValue());
				}
			} else {
				qualityUses.put(inEntry.getKey(), inEntry.getValue());
			}
		}
		
		return quality;
	}

	
	protected ValuedDetailQuality qualifiedMergeOut(ValuedDetailQuality out) {
		
		ValuedDetailQuality quality = baseQuality.clone();
		
		Map<String, String> qualityUses = quality.getUses();
		Map<String, String> outUses = out.getUses();
		
		for (Map.Entry<String, String> outEntry : outUses.entrySet()) {
			
			if (qualityUses.containsKey(outEntry.getKey())) {
				// Merge
				double qualityXp = Math.abs(quality.getXpForValue(qualityUses.get(outEntry.getKey())));
				double inXp = Math.abs(out.getXpForValue(outEntry.getValue()));
				if (inXp >= qualityXp) {
					qualityUses.remove(outEntry.getKey());
				}
			}
		}
		
		if (qualityUses.isEmpty()) {
			quality = null;
		}
		
		return quality;
	}

}
