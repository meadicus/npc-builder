package uk.co.meadicus.npcbuilder.client.quality;

import uk.co.meadicus.npcbuilder.client.xp.SimpleXP;
import uk.co.meadicus.npcbuilder.client.xp.XP;

public class FixedXpDetailQuality extends DetailQuality {

	protected FixedXpDetailQuality(String name, double baseXp,
			String restrictions, String[] options, int maxuses) {
		super(name, baseXp, restrictions, options, maxuses);
	}

	protected FixedXpDetailQuality(String name, double baseXp,
			String restrictions, String[] options) {
		super(name, baseXp, restrictions, options);
	}

	@Override
	public FixedXpDetailQuality clone() {
		FixedXpDetailQuality cloneQuality = new FixedXpDetailQuality(getName(),
																	 getBaseXp(),
																	 getRestrictions(),
																	 getOptions(),
																	 getMaxUses());
		cloneQuality.getUses().addAll(getUses());
		return cloneQuality;
	}

	@Override
	public XP getXp() {
		XP xp = new SimpleXP(0);
		for (String use : getUses()) {
			if (use != null && !use.isEmpty()) {
				xp = new SimpleXP(getBaseXp(), true);
				break;
			}
		}
		return xp;
	}

}
