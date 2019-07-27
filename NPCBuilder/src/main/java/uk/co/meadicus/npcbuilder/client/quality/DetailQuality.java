package uk.co.meadicus.npcbuilder.client.quality;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import uk.co.meadicus.npcbuilder.client.util.NPCUtils;
import uk.co.meadicus.npcbuilder.client.xp.ComplexXP;
import uk.co.meadicus.npcbuilder.client.xp.XP;

public class DetailQuality extends OptionsQuality {

	private final int maxUses;
	private final List<String> uses;
	
	public DetailQuality(String name, double baseXp, String restrictions,
			String[] options) {
		super(name, baseXp, restrictions, options);
		this.maxUses = -1;
		this.uses = new ArrayList<String>();
	}
		
	public DetailQuality(String name, double baseXp, String restrictions,
			String[] options, int maxuses) {
		super(name, baseXp, restrictions, options);
		this.maxUses = maxuses;
		this.uses = new ArrayList<String>();
	}

	
	public DetailQuality clone() {
		DetailQuality detailQuality = new DetailQuality(getName(),
														getBaseXp(),
														getRestrictions(),
														getOptions(),
														getMaxUses());
		detailQuality.getUses().addAll(this.getUses());
		return detailQuality;
	}

	public List<String> getUses() {
		return uses;
	}

	protected int getMaxUses() {
		return this.maxUses;
	}

	
	public XP getXp() {
		ComplexXP xp = new ComplexXP();
		for (String use : getUses()) {
			if (use != null && !use.isEmpty()) {
				xp.put(use, getBaseXp());
			}
		}
		return xp;
	}


	
	protected String renderDetails() {
		String details = "";
		Collections.sort(getUses());
		
		String delim = "";
		for (String use : getUses()) {
			details += delim;
			details += use;
			delim = ", ";
		}
		
		return details;
	}
	
	
	protected void reset() {
		getUses().clear();
	}
	
	
	protected void parseDetails(String text) {
		List<String> theUses = NPCUtils.bracketAwareSplit(text, ',');
		getUses().addAll(theUses);
	}

}
