package uk.co.meadicus.npcbuilder.client.quality;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import uk.co.meadicus.npcbuilder.client.util.NPCUtils;
import uk.co.meadicus.npcbuilder.client.xp.ComplexXP;
import uk.co.meadicus.npcbuilder.client.xp.XP;

public class ConfiguredClassAbilityQuality extends ScoredDetailQuality {

	private final List<ClassAbility> abilities;
	private final Map<String, ClassAbility> abilityByName = new HashMap<String, ClassAbility>();
	private final Map<String, Map<String, String>> abilityNamesByClass = new LinkedHashMap<String, Map<String, String>>();
	
	public static class ClassAbility {
		
		private final String className;
		private final String classAbility;
		private final int xp;
		
		public ClassAbility(String className, String classAbility, int xp) {
			super();
			this.className = className;
			this.classAbility = classAbility;
			this.xp = xp;
		}
		
		public String toString() {
			return getClassName() + ": " + getClassAbility();
		}

		public final String getClassName() {
			return className;
		}

		public final String getClassAbility() {
			return classAbility;
		}

		public final int getXp() {
			return xp;
		}
		
		public int hashcode() {
			return toString().hashCode();
		}
		
	}
	
	public ConfiguredClassAbilityQuality(String name, List<ClassAbility> abilities) {
		super(name, 0, null, new HashMap<String, Integer>());
		this.abilities = abilities;
		for (ClassAbility ability : abilities) {
			getAbilityByName().put(ability.toString(), ability);
			if (!getAbilityNamesByClass().containsKey(ability.getClassName())) {
				getAbilityNamesByClass().put(ability.getClassName(), new LinkedHashMap<String, String>());
			}
			String description = ability.getClassAbility() + " (" + ability.getXp() + "xp)";
			getAbilityNamesByClass().get(ability.getClassName()).put(description, ability.toString());
		}
	}

	public final List<ClassAbility> getAbilities() {
		return abilities;
	}

	private final Map<String, ClassAbility> getAbilityByName() {
		return abilityByName;
	}

	protected final Map<String, Map<String, String>> getAbilityNamesByClass() {
		return abilityNamesByClass;
	}

	@Override
	public DetailQuality clone() {
		ConfiguredClassAbilityQuality newQuality = new ConfiguredClassAbilityQuality(getName(),
																					 getAbilities());
		newQuality.getUses().addAll(this.getUses());
		return newQuality;
	}

	@Override
	public XP getXp() {
		ComplexXP xp = new ComplexXP(true);
		
		for (String use : getUses()) {
			if (getAbilityByName().containsKey(use)) {
				xp.put(use, getAbilityByName().get(use).getXp());
			}
		}
		return xp;
	}

	@Override
	protected void parseDetails(String text) {
		List<String> theUses = NPCUtils.bracketAwareSplit(text, ',');
		String lastClassname = "";
		for (String use : theUses) {
			if (use.matches("[^\\s]+:.*")) {
				lastClassname = use.substring(0, use.indexOf(':'));
			} else {
				use = lastClassname + ": " + use;
			}
			getUses().add(use);
		}
	}

	
}
