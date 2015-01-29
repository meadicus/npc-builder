package uk.co.meadicus.npcbuilder.client.attack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class AttacksConfig {

	public static final List<String> naturalAttacks;
	static {
		List<String> natAttacks = new ArrayList<String>();
		natAttacks.add("bite");
		natAttacks.add("claw");
		natAttacks.add("kick");
		natAttacks.add("slam");
		natAttacks.add("talon");
		natAttacks.add("gore");
		natAttacks.add("squeeze");
		natAttacks.add("swallow");
		natAttacks.add("tail slap");
		natAttacks.add("tentacle slap");
		natAttacks.add("trample");
		naturalAttacks = Collections.unmodifiableList(natAttacks);
	}
	public static final List<String> damageTypes;
	static {
		List<String> dmgTypes = new ArrayList<String>();
		dmgTypes.add("acid");
		dmgTypes.add("bang");
		dmgTypes.add("cold");
		dmgTypes.add("divine");
		dmgTypes.add("electrical");
		dmgTypes.add("explosive");
		dmgTypes.add("fire");
		dmgTypes.add("flash");
		dmgTypes.add("force");
		dmgTypes.add("heat");
		dmgTypes.add("lethal");
		dmgTypes.add("sneak attack");
		dmgTypes.add("sonic");
		dmgTypes.add("stress");
		dmgTypes.add("subdual");
		damageTypes = Collections.unmodifiableList(dmgTypes);
	}
	public static final Map<String, Integer> damageAttacks;
	static {
		Map<String, Integer> dmgAttacks = new LinkedHashMap<String, Integer>();
		dmgAttacks.put("damage", 2);
		dmgAttacks.put("rotting", 2);
		dmgAttacks.put("rusting", 2);
		damageAttacks = Collections.unmodifiableMap(dmgAttacks);
	}
	public static final String DEFAULT_DAMAGE_ATTACK = "damage";
	public static final int keenMultiplier = 4;
	public static final int APMultiplier = 2;
	public static final Map<String, Integer> saveAttacks;
	static {
		Map<String, Integer> svAttacks = new LinkedHashMap<String, Integer>();
		svAttacks.put("baffling", 3);
		svAttacks.put("blinding", 2);
		svAttacks.put("deafening", 2);
		svAttacks.put("draining, attribute", 4);
		svAttacks.put("draining, life", 3);
		svAttacks.put("draining, soul", 5);
		svAttacks.put("enraging", 2);
		svAttacks.put("entangling", 2);
		svAttacks.put("fatiguing", 3);
		svAttacks.put("frightening", 2);
		svAttacks.put("paralyzing", 4);
		svAttacks.put("petrifying", 5);
		svAttacks.put("shaking", 3);
		svAttacks.put("sickening", 2);
		svAttacks.put("slowing", 3);
		svAttacks.put("sprawling", 2);
		svAttacks.put("stunning", 3);
		svAttacks.put("wounding", 2);
		saveAttacks = Collections.unmodifiableMap(svAttacks);
	}
	public static final String DEFAULT_SAVE_ATTACK = "baffling";
	public static final Map<String, AreaUpgrade> areaUpgrades;
	static {
		Map<String, AreaUpgrade> arUpgrades = new LinkedHashMap<String, AreaUpgrade>();
		arUpgrades.put("aura", new AreaUpgrade(3, 10, "ft. radius"));
		arUpgrades.put("beam", new AreaUpgrade(1, 10, "ft. range"));
		arUpgrades.put("blast", new AreaUpgrade(3, 5, "ft blast increment"));
		arUpgrades.put("cone", new AreaUpgrade(2, 10, "ft range"));
		arUpgrades.put("gaze", new AreaUpgrade(3, 0, ""));
		arUpgrades.put("ray", new AreaUpgrade(1, 20, "ft. range"));
		areaUpgrades = Collections.unmodifiableMap(arUpgrades);
	}
	
	public static class AreaUpgrade {
		private final int xpMultiplier;
		private final int distanceUnit;
		private final String distanceDescriptor;
		
		private AreaUpgrade(int xpMultiplier, int distanceUnit, String distanceDescriptor) {
			super();
			this.xpMultiplier = xpMultiplier;
			this.distanceUnit = distanceUnit;
			this.distanceDescriptor = distanceDescriptor;
		}

		public int getXpMultiplier() {
			return xpMultiplier;
		}

		public int getDistanceUnit() {
			return distanceUnit;
		}

		public String getDistanceDescriptor() {
			return distanceDescriptor;
		}
		
	}
}
