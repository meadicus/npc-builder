package uk.co.meadicus.npcbuilder.client.template;

import java.util.List;
import java.util.Set;

import uk.co.meadicus.npcbuilder.client.attack.Attack;
import uk.co.meadicus.npcbuilder.client.npc.FantasyCraftConfig.FCNPCType;
import uk.co.meadicus.npcbuilder.client.npc.Size;
import uk.co.meadicus.npcbuilder.client.npc.form.Mobility;
import uk.co.meadicus.npcbuilder.client.quality.Quality;
import uk.co.meadicus.npcbuilder.client.skill.Skill;
import uk.co.meadicus.npcbuilder.client.xp.HasXP;
import uk.co.meadicus.npcbuilder.client.xp.SimpleXP;
import uk.co.meadicus.npcbuilder.client.xp.XP;

public class FantasyCraftTemplate implements HasXP {

	private final String name;
	private final int[] attrMods; // str, dex, con, int, wis, cha
	private final int[] statMods; // init, atk, def, res, hlth, comp
	private final int sizeMod;
	private final Size size;
	private final int reachMod;
	private final List<Skill> skills;
	private final List<Quality> qualities;
	private final List<Attack> attacks;
	private final Set<FCNPCType> types;
	private final FCNPCType type;
	private final int attacksMod;
	private final int speedMod;
	private final Mobility mobility;
	private final int xp;
	
	public FantasyCraftTemplate(String name, int[] attrMods, int[] statMods,
								List<Skill> skills, List<Quality> qualities, 
								List<Attack> attacks, Set<FCNPCType> types,
								int attacksMod, int sizeMod, int reachMod, int xp) {
		this(name, attrMods, statMods, skills, qualities, attacks,
				types, null, attacksMod, sizeMod, null, reachMod, 0, null, xp);
	}
	
	public FantasyCraftTemplate(String name, int[] attrMods, int[] statMods,
								List<Skill> skills, List<Quality> qualities, 
								List<Attack> attacks, Set<FCNPCType> types,
								int attacksMod, Size size, int reachMod, int xp) {
		this(name, attrMods, statMods, skills, qualities, attacks,
				types, null, attacksMod, 0, size, reachMod, 0, null, xp);
	}

	protected FantasyCraftTemplate(String name, int[] attrMods, int[] statMods,
								   List<Skill> skills, List<Quality> qualities, List<Attack> attacks,
								   Set<FCNPCType> types, int attacksMod, int sizeMod, Size size,
								   int reachMod, int xp) {
		this(name, attrMods, statMods, skills, qualities, attacks,
				types, null, attacksMod, sizeMod, size, reachMod,
				0, null, xp);
	}

	protected FantasyCraftTemplate(String name, int[] attrMods, int[] statMods,
								   List<Skill> skills, List<Quality> qualities, List<Attack> attacks,
								   Set<FCNPCType> types, int attacksMod, int sizeMod, Size size,
								   int reachMod, int speedMod, int xp) {
		this(name, attrMods, statMods, skills, qualities, attacks,
				types, null, attacksMod, sizeMod, size, reachMod,
				speedMod, null, xp);
	}

	protected FantasyCraftTemplate(String name, int[] attrMods, int[] statMods,
								   List<Quality> qualities, List<Attack> attacks,
								   FCNPCType type, int attacksMod, int sizeMod, Size size,
								   int reachMod, int speedMod, int xp) {
		this(name, attrMods, statMods, null, qualities, attacks,
				null, type, attacksMod, sizeMod, size, reachMod,
				speedMod, null, xp);
	}

	protected FantasyCraftTemplate(String name, int[] attrMods, int[] statMods,
								   List<Quality> qualities, List<Attack> attacks,
								   FCNPCType type, int attacksMod, int sizeMod, Size size, 
								   int reachMod, int speedMod, Mobility mobility, int xp) {
		this(name, attrMods, statMods, null, qualities, attacks,
				null, type, attacksMod, sizeMod, size, reachMod,
				speedMod, mobility, xp);
	}

	protected FantasyCraftTemplate(String name, int[] attrMods, int[] statMods,
			List<Skill> skills, List<Quality> qualities, List<Attack> attacks, Set<FCNPCType> types,
			FCNPCType type, int attacksMod, int sizeMod, Size size, int reachMod, int speedMod, Mobility mobility, int xp) {
		super();
		this.name = name;
		if (attrMods == null) {
			this.attrMods = new int[6];
		} else {
			this.attrMods = attrMods;
		}
		if (statMods == null) {
			this.statMods = new int[6];
		} else {
			this.statMods = statMods;
		}
		this.skills = skills;
		this.qualities = qualities;
		this.attacks = attacks;		
		this.attacksMod = attacksMod;
		this.types = types;
		this.type = type;
		this.sizeMod = sizeMod;
		this.size = size;
		this.reachMod = reachMod;
		this.speedMod = speedMod;
		this.mobility = mobility;
		this.xp = xp;
	}

	public String getName() {
		return name;
	}

	public int[] getAttrMods() {
		return attrMods;
	}

	public int[] getStatMods() {
		return statMods;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public List<Quality> getQualities() {
		return qualities;
	}

	public List<Attack> getAttacks() {
		return attacks;
	}

	public int getSizeMod() {
		return sizeMod;
	}

	public Size getSize() {
		return size;
	}
	
	public int getReachMod() {
		return reachMod;
	}

	public int getAttacksMod() {
		return attacksMod;
	}
	
	public int getSpeedMod() {
		return speedMod;
	}

	public Mobility getMobility() {
		return mobility;
	}

	public Set<FCNPCType> getTypes() {
		return types;
	}

	public FCNPCType getType() {
		return type;
	}

	@Override
	public XP getXp() {
		return new SimpleXP(this.xp);
	}
}
