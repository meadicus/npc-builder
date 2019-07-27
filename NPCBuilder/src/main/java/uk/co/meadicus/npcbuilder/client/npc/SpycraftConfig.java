package uk.co.meadicus.npcbuilder.client.npc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.co.meadicus.npcbuilder.client.npc.Attributes.Attribute;
import uk.co.meadicus.npcbuilder.client.npc.SpycraftNPC.SCNPCType;
import uk.co.meadicus.npcbuilder.client.quality.QualityFactory;
import uk.co.meadicus.npcbuilder.client.quality.SpycraftQualityFactory;
import uk.co.meadicus.npcbuilder.client.skill.Skill;
import uk.co.meadicus.npcbuilder.client.util.NPCUtils;

public class SpycraftConfig implements Config {
	
	private final QualityFactory qualityFactory;
	
	private static List<String> npcSkills;
	private static Map<String, Attribute[]> skillAttributes;
	static {
		npcSkills = new ArrayList<String>();
		skillAttributes = new HashMap<String, Attribute[]>();
		npcSkills.add("Acrobatics");
		skillAttributes.put("Acrobatics", new Attribute[] {Attribute.STR, Attribute.DEX});
		npcSkills.add("Analysis");
		skillAttributes.put("Analysis", new Attribute[] {Attribute.INT, Attribute.WIS});
		npcSkills.add("Athletics");
		skillAttributes.put("Athletics", new Attribute[] {Attribute.STR, Attribute.CON});
		npcSkills.add("Blend");
		skillAttributes.put("Blend", new Attribute[] {Attribute.DEX, Attribute.CHA});
		npcSkills.add("Bluff");
		skillAttributes.put("Bluff", new Attribute[] {Attribute.CHA});
		npcSkills.add("Bureaucracy");
		skillAttributes.put("Bureaucracy", new Attribute[] {Attribute.CHA});
		npcSkills.add("Computers");
		skillAttributes.put("Computers", new Attribute[] {Attribute.INT});
		npcSkills.add("Cultures");
		skillAttributes.put("Cultures", new Attribute[] {Attribute.INT});
		npcSkills.add("Drive");
		skillAttributes.put("Drive", new Attribute[] {Attribute.DEX});
		npcSkills.add("Electronics");
		skillAttributes.put("Electronics", new Attribute[] {Attribute.INT, Attribute.WIS});
		npcSkills.add("Falsify");
		skillAttributes.put("Falsify", new Attribute[] {Attribute.INT, Attribute.WIS});
		npcSkills.add("Impress");
		skillAttributes.put("Impress", new Attribute[] {Attribute.CHA});
		npcSkills.add("Intimidate");
		skillAttributes.put("Intimidate", new Attribute[] {Attribute.STR, Attribute.WIS});
		npcSkills.add("Investigation");
		skillAttributes.put("Investigation", new Attribute[] {Attribute.WIS, Attribute.CHA});
		npcSkills.add("Manipulate");
		skillAttributes.put("Manipulate", new Attribute[] {Attribute.WIS, Attribute.CHA});
		npcSkills.add("Mechanics");
		skillAttributes.put("Mechanics", new Attribute[] {Attribute.INT, Attribute.WIS});
		npcSkills.add("Medicine");
		skillAttributes.put("Medicine", new Attribute[] {Attribute.INT, Attribute.WIS});
		npcSkills.add("Networking");
		skillAttributes.put("Networking", new Attribute[] {Attribute.WIS, Attribute.CHA});
		npcSkills.add("Notice");
		skillAttributes.put("Notice", new Attribute[] {Attribute.WIS});
		npcSkills.add("Profession");
		skillAttributes.put("Profession", new Attribute[] {Attribute.CHA});
		npcSkills.add("Resolve");
		skillAttributes.put("Resolve", new Attribute[] {Attribute.CON, Attribute.WIS});
		npcSkills.add("Science");
		skillAttributes.put("Science", new Attribute[] {Attribute.INT});
		npcSkills.add("Search");
		skillAttributes.put("Search", new Attribute[] {Attribute.INT});
		npcSkills.add("Security");
		skillAttributes.put("Security", new Attribute[] {Attribute.INT});
		npcSkills.add("Sense Motive");
		skillAttributes.put("Sense Motive", new Attribute[] {Attribute.WIS, Attribute.CHA});
		npcSkills.add("Sleight of Hand");
		skillAttributes.put("Sleight of Hand", new Attribute[] {Attribute.DEX});
		npcSkills.add("Sneak");
		skillAttributes.put("Sneak", new Attribute[] {Attribute.DEX, Attribute.CHA});
		npcSkills.add("Streetwise");
		skillAttributes.put("Streetwise", new Attribute[] {Attribute.WIS, Attribute.CHA});
		npcSkills.add("Survival");
		skillAttributes.put("Survival", new Attribute[] {Attribute.WIS, Attribute.CHA});
		npcSkills.add("Tactics");
		skillAttributes.put("Tactics", new Attribute[] {Attribute.WIS, Attribute.CHA});
	}
	
	
	private static SpycraftConfig singleton;
	private SpycraftConfig() {
		this.qualityFactory = SpycraftQualityFactory.getInstance();
	}
	
	public static SpycraftConfig getInstance() {
		if (singleton == null) {
			singleton = new SpycraftConfig();
		}
		return singleton;
	}

	
	public List<String> getAvailableSkills() {
		return npcSkills;
	}

	
	public int getMaxSkills() {
		return 5;
	}

	
	public QualityFactory getQualityFactory() {
		return this.qualityFactory;
	}

	
	public Attribute[] getSkillAttributes(Skill skill) {
		return skillAttributes.get(skill.getName());
	}

	
	public int getDefaultMaxUses() {
		return 10;
	}

	public Attributes getDefaultAttributes() {
		return getDefaultAttributes(SpycraftNPCImpl.getInstance().getType());
	}
	
	public Attributes getDefaultAttributes(SCNPCType type) {
		if (type.equals(SCNPCType.ANIMAL)) {
			return new AttributesImpl(10, 10, 10, 2, 10, 10);
		} else {
			return new AttributesImpl(10, 10, 10, 10, 10, 10);
		}
	}

	
	public int computeAtk(int atk, int TL) {
		return NPCUtils.lookupStat(SpycraftStatTables.ATK_TABLE, atk, TL);
	}

	
	public int computeComp(int comp, int TL) {
		return NPCUtils.lookupStat(SpycraftStatTables.COMP_TABLE, comp, TL);
	}

	
	public int computeDef(int def, int TL) {
		return NPCUtils.lookupStat(SpycraftStatTables.DEF_TABLE, def, TL);
	}

	
	public int computeHlth(int hlth, int TL) {
		return NPCUtils.lookupStat(SpycraftStatTables.DS_TABLE, hlth, TL);
	}

	
	public int computeInit(int init, int TL) {
		return NPCUtils.lookupStat(SpycraftStatTables.INIT_TABLE, init, TL);
	}

	
	public int computeRes(int res, int TL) {
		return NPCUtils.lookupStat(SpycraftStatTables.RES_TABLE, res, TL);
	}

	
	public int computeSkill(int skill, int TL) {
		return NPCUtils.lookupStat(SpycraftStatTables.SKILL_TABLE, skill, TL);
	}
	
	public int computeVit(int hlth, int TL) {
		return NPCUtils.lookupStat(SpycraftStatTables.VIT_TABLE, hlth, TL);
	}
	
	public int computeWP(int hlth, int TL) {
		return NPCUtils.lookupStat(SpycraftStatTables.WND_TABLE, hlth, TL);
	}
	

}
