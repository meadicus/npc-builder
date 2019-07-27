package uk.co.meadicus.npcbuilder.client.quality;

import uk.co.meadicus.npcbuilder.client.xp.SimpleXP;
import uk.co.meadicus.npcbuilder.client.xp.XP;

public class SimpleQuality extends OptionsQuality {

	private String use;
	
	protected SimpleQuality(String name, double baseXp, String restrictions,
			String[] options) {
		super(name, baseXp, restrictions, options);
		use = "";
	}

	
	public XP getXp() {
		if (getOptions() != null && getOptions().length > 0) {
			// get index of use in options
			int index = -1;
			for (int i = 0; i < getOptions().length; ++i) {
				if (getUse().equals(getOptions()[i])) {
					index = i;
				}
			}
			return new SimpleXP((index+1)*getBaseXp(), true);
		} else {
			return new SimpleXP(getBaseXp(), true);
		}
	}


	
	protected String renderDetails() {
		return getUse();
	}
	
	
	protected void reset() {
		setUse("");
	}
	
	
	protected void parseDetails(String text) {
		setUse(text);
	}

	public String getUse() {
		return use;
	}

	protected void setUse(String use) {
		this.use = use;
	}

	
	public SimpleQuality clone() {
		SimpleQuality simpleQuality = new SimpleQuality(getName(),
														getBaseXp(),
														getRestrictions(),
														getOptions());
		simpleQuality.setUse(getUse());
		return simpleQuality;
	}
}
