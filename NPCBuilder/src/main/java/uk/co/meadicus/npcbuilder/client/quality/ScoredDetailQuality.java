package uk.co.meadicus.npcbuilder.client.quality;

import java.util.Map;

import uk.co.meadicus.npcbuilder.client.xp.ComplexXP;
import uk.co.meadicus.npcbuilder.client.xp.XP;

public class ScoredDetailQuality extends DetailQuality {

	private final Map<String, Integer> values;
	
	public ScoredDetailQuality(String name, double baseXp, String restrictions,
			Map<String, Integer> values) {
		super(name, baseXp, restrictions, values.keySet().toArray(new String[values.size()]));
		this.values = values;
	}

	public ScoredDetailQuality(String name, double baseXp, String restrictions,
			Map<String, Integer> values, int maxuses) {
		super(name, baseXp, restrictions, values.keySet().toArray(new String[values.size()]), maxuses);
		this.values = values;
	}

	protected Map<String, Integer> getValues() {
		return values;
	}

	
	public DetailQuality clone() {
		ScoredDetailQuality detailQuality = new ScoredDetailQuality(getName(),
																	getBaseXp(),
																	getRestrictions(),
																	getValues(),
																	getMaxUses());
		detailQuality.getUses().addAll(this.getUses());
		return detailQuality;
	}

	
	public XP getXp() {
		ComplexXP xp = new ComplexXP();
		for (String use : getUses()) {
			if (getValues().containsKey(use)) {
				xp.put(use, getValues().get(use));
			}
		}
		return xp;
	}
}
