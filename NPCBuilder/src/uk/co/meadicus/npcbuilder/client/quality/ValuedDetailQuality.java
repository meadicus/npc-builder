package uk.co.meadicus.npcbuilder.client.quality;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Arrays;

import uk.co.meadicus.npcbuilder.client.util.NPCUtils;
import uk.co.meadicus.npcbuilder.client.xp.ComplexXP;
import uk.co.meadicus.npcbuilder.client.xp.XP;

public class ValuedDetailQuality extends OptionsQuality {

	private final String[] values;
	private final Map<String, String> uses;
	private final int maxUses;
	
	public ValuedDetailQuality(String name, double baseXp, String restrictions,
			String[] options, String[] values) {
		super(name, baseXp, restrictions, options);
		this.values = values;
		this.maxUses = -1; 
		this.uses = new HashMap<String, String>();
	}
	
	public ValuedDetailQuality(String name, double baseXp, String restrictions,
			String[] options, String[] values, int maxuses) {
		super(name, baseXp, restrictions, options);
		this.values = values;
		this.maxUses = maxuses; 
		this.uses = new HashMap<String, String>();
	}

	protected String[] getValues() {
		return values;
	}

	public Map<String, String> getUses() {
		return this.uses;
	}

	protected int getMaxUses() {
		return this.maxUses;
	}
	
	protected List<String> getSortedUseNames() {
		List<String> names = new ArrayList<String>();
		names.addAll(getUses().keySet());
		Collections.sort(names);
		return names;
	}

	@Override
	public ValuedDetailQuality clone() {
		ValuedDetailQuality cloneQuality = new ValuedDetailQuality(getName(),
																		getBaseXp(),
																		getRestrictions(),
																		getOptions(),
																		getValues(),
																		getMaxUses());
		cloneQuality.getUses().putAll(getUses());
		return cloneQuality;
	}

	@Override
	public XP getXp() {
		ComplexXP xp = new ComplexXP(true);
		for (String use : getUses().keySet()) {
			if (!use.isEmpty()) {
				String value = getUses().get(use);
				xp.put(use + "(" + value + ")", getXpForValue(value));
			}
		}
		return xp;
	}

	public double getXpForValue(String value) {
		// get index of use in values
		int index = -1;
		for (int i = 0; i < getValues().length; ++i) {
			if (value.equals(getValues()[i])) {
				index = i;
			}
		}
		return (index+1)*getBaseXp();
	}
	
	@Override
	protected String renderDetails() {
		// Get the list of uses and sort them
		List<String> useNames = getSortedUseNames();
		
		String details = "";
		
		String delim = "";
		for (String name : useNames) {
			details += delim;
			String value = getUses().get(name);
			
			details += name + " " + value;
			delim = ", ";
		}
		
		return details;
	}
	
	@Override
	protected void reset() {
		getUses().clear();
	}

	@Override
	protected void parseDetails(String text) {
		List<String> theUses = NPCUtils.bracketAwareSplit(text, ',');
		for (String use : theUses) {
			use = use.trim();
			int splitpoint = use.lastIndexOf(" ");
			if (splitpoint > 0) {
				String useName = use.substring(0, splitpoint).trim();
				String useValue = use.substring(splitpoint).trim();
				if (Arrays.binarySearch(getValues(), useValue) > 0) {
					getUses().put(useName, useValue);
				} else {
					getUses().put(use, "");
				}
			} else {
				getUses().put(use, "");
			}
		}
	}

}
