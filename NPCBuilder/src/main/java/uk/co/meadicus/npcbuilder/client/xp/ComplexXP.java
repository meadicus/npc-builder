package uk.co.meadicus.npcbuilder.client.xp;

import java.util.LinkedHashMap;
import java.util.Map;

public class ComplexXP extends XP {
	
	private final Map<String, XP> components = new LinkedHashMap<String, XP>();

	public ComplexXP() {
		super();
	}

	public ComplexXP(boolean autofix) {
		super(autofix);
	}

	public double getValue() {
		int value = 0;
		
		for (XP xp : this.components.values()) {
			value += xp.getValue();
		}
		
		return value;
	}

	public Map<String, XP> getComponents() {
		return components;
	}
	
	public void put(String name, int val) {
		getComponents().put(name, new SimpleXP(val));
	}
	
	public void put(String name, double val) {
		getComponents().put(name, new SimpleXP(val));
	}
	
	public void put(String name, XP xp) {
		getComponents().put(name, xp);
	}

}
