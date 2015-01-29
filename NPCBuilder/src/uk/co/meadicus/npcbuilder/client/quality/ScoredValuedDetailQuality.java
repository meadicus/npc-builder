package uk.co.meadicus.npcbuilder.client.quality;

import java.util.Map;

import uk.co.meadicus.npcbuilder.client.xp.ComplexXP;
import uk.co.meadicus.npcbuilder.client.xp.XP;

public class ScoredValuedDetailQuality extends ValuedDetailQuality {

	private final Map<String, Integer> values;
	
	public ScoredValuedDetailQuality(String name, double baseXp,
			String restrictions, String[] options, Map<String, Integer>  values) {
		super(name, baseXp, restrictions, options, values.keySet().toArray(new String[values.size()]));
		this.values = values;
	}

	protected Map<String, Integer> getValuesMap() {
		return values;
	}

	@Override
	public ScoredValuedDetailQuality clone() {
		ScoredValuedDetailQuality cloneQuality = new ScoredValuedDetailQuality(getName(),
																				getBaseXp(),
																				getRestrictions(),
																				getOptions(),
																				getValuesMap());
		cloneQuality.getUses().putAll(getUses());
		return cloneQuality;
	}

	@Override
	public XP getXp() {
		ComplexXP xp = new ComplexXP(true);
		for (String use : getUses().keySet()) {
			if (!use.isEmpty()) {
				String value = getUses().get(use);
				if (getValuesMap().containsKey(value)) {
					xp.put(use + "(" + value + ")", getValuesMap().get(value));
				}
			}
		}
		return xp;
	}

	public double getXpForValue(String value) {
		return getValuesMap().get(value);
	}

}
