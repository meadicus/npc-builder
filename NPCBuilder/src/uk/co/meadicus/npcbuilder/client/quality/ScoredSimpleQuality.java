package uk.co.meadicus.npcbuilder.client.quality;

import java.util.Map;

import uk.co.meadicus.npcbuilder.client.xp.SimpleXP;
import uk.co.meadicus.npcbuilder.client.xp.XP;

public class ScoredSimpleQuality extends SimpleQuality {

	private final Map<String, Integer> values;	
	
	protected ScoredSimpleQuality(String name, double baseXp,
			String restrictions, Map<String, Integer> values) {
		super(name, baseXp, restrictions, values.keySet().toArray(new String[values.size()]));
		this.values = values;
	}
	
	protected ScoredSimpleQuality(String name, double baseXp,
			String restrictions, Map<String, Integer> values, int maxUses) {
		super(name, baseXp, restrictions, values.keySet().toArray(new String[values.size()]));
		this.values = values;
	}

	protected Map<String, Integer> getValues() {
		return values;
	}


	@Override
	public ScoredSimpleQuality clone() {
		ScoredSimpleQuality cloneQuality = new ScoredSimpleQuality(getName(),
																   getBaseXp(),
																   getRestrictions(),
																   getValues());
		cloneQuality.setUse(getUse());
		return cloneQuality;
	}

	@Override
	public XP getXp() {
		if (getUse() != null && getValues().containsKey(getUse())) {
			return new SimpleXP(getValues().get(getUse()), true);
		} else {
			return new SimpleXP(0);
		}
	}


}
