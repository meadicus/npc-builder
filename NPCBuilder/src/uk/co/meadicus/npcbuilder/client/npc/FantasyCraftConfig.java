package uk.co.meadicus.npcbuilder.client.npc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.co.meadicus.npcbuilder.client.npc.Attributes.Attribute;
import uk.co.meadicus.npcbuilder.client.npc.FantasyCraftStatTables.NatAtkStats;
import uk.co.meadicus.npcbuilder.client.npc.FantasyCraftStatTables.SizeMods;
import uk.co.meadicus.npcbuilder.client.quality.FantasyCraftQualityFactory;
import uk.co.meadicus.npcbuilder.client.quality.QualityFactory;
import uk.co.meadicus.npcbuilder.client.skill.Skill;
import uk.co.meadicus.npcbuilder.client.util.NPCUtils;

public class FantasyCraftConfig implements Config {

	public static enum FCNPCType {		
		ANIMAL("Animal", 0),
		BEAST("Beast", 0),
		CONSTRUCT("Construct", 5),
		ELEMENTAL("Elemental", 5),
		FEY("Fey", 0),
		FOLK("Folk", 0),
		HORROR("Horror", 5),
		OOZE("Ooze", 5),
		OUTSIDER("Outsider", 0),
		PLANT("Plant", 5),
		SPIRIT("Spirit", 5),
		UNDEAD("Undead", 5);
	
		final private String displayText;
		final private int xpValue;
				
		private FCNPCType(String displayText, int xp) {
			this.displayText = displayText;
			this.xpValue = xp;
		}
	
		protected String getDisplayText() {
			return displayText;
		}
	
		@Override
		public String toString() {
			return getDisplayText();
		}
		
		public int getXpValue() {
			return this.xpValue;
		}
	
		public static String[] displayValues() {
			List<String> values = new ArrayList<String>();
			for (FCNPCType type : FCNPCType.values()) {
				values.add(type.toString());
			}
			return values.toArray(new String[values.size()]);
		}
		
		public static FCNPCType fromString(String text) {
			try {
				return FCNPCType.valueOf(text.toUpperCase());
			} catch (IllegalArgumentException ex) {
				return null;
			}
		}
	}

	private final QualityFactory qualityFactory;
	
	private static List<String> npcSkills;
	private static Map<String, Attribute[]> skillAttributes;
	static {
		npcSkills = new ArrayList<String>();
		skillAttributes = new HashMap<String, Attribute[]>();
		npcSkills.add("Acrobatics");
		skillAttributes.put("Acrobatics", new Attribute[] {Attribute.DEX});
		npcSkills.add("Athletics");
		skillAttributes.put("Athletics", new Attribute[] {Attribute.STR});
		npcSkills.add("Blend");
		skillAttributes.put("Blend", new Attribute[] {Attribute.CHA});
		npcSkills.add("Bluff");
		skillAttributes.put("Bluff", new Attribute[] {Attribute.CHA});
		npcSkills.add("Crafting");
		skillAttributes.put("Crafting", new Attribute[] {Attribute.INT});
		npcSkills.add("Disguise");
		skillAttributes.put("Disguise", new Attribute[] {Attribute.CHA});
		npcSkills.add("Haggle");
		skillAttributes.put("Haggle", new Attribute[] {Attribute.WIS});
		npcSkills.add("Impress");
		skillAttributes.put("Impress", new Attribute[] {Attribute.CHA});
		npcSkills.add("Intimidate");
		skillAttributes.put("Intimidate", new Attribute[] {Attribute.WIS});
		npcSkills.add("Investigate");
		skillAttributes.put("Investigate", new Attribute[] {Attribute.WIS});
		npcSkills.add("Medicine");
		skillAttributes.put("Medicine", new Attribute[] {Attribute.INT});
		npcSkills.add("Notice");
		skillAttributes.put("Notice", new Attribute[] {Attribute.WIS});
		npcSkills.add("Prestidigitation");
		skillAttributes.put("Prestidigitation", new Attribute[] {Attribute.DEX});
		npcSkills.add("Resolve");
		skillAttributes.put("Resolve", new Attribute[] {Attribute.CON});
		npcSkills.add("Ride");
		skillAttributes.put("Ride", new Attribute[] {Attribute.DEX});
		npcSkills.add("Search");
		skillAttributes.put("Search", new Attribute[] {Attribute.INT});
		npcSkills.add("Sense Motive");
		skillAttributes.put("Sense Motive", new Attribute[] {Attribute.WIS});
		npcSkills.add("Sneak");
		skillAttributes.put("Sneak", new Attribute[] {Attribute.DEX});
		npcSkills.add("Survival");
		skillAttributes.put("Survival", new Attribute[] {Attribute.WIS});
		npcSkills.add("Tactics");
		skillAttributes.put("Tactics", new Attribute[] {Attribute.INT});
	}
	
	private FantasyCraftConfig() {
		this.qualityFactory = FantasyCraftQualityFactory.getInstance();
	}
	
	private static FantasyCraftConfig singleton;
	
	public static FantasyCraftConfig getInstance() {
		if (singleton == null) {
			singleton = new FantasyCraftConfig();
		}
		return singleton;
	}
	
	@Override
	public List<String> getAvailableSkills() {
		return npcSkills;
	}

	@Override
	public int getMaxSkills() {
		return 5;
	}

	@Override
	public QualityFactory getQualityFactory() {
		return this.qualityFactory;
	}

	@Override
	public Attribute[] getSkillAttributes(Skill skill) {
		return skillAttributes.get(skill.getName());
	}

	@Override
	public int getDefaultMaxUses() {
		return 10;
	}

	@Override
	public int computeAtk(int atk, int TL) {
		return NPCUtils.lookupStat(FantasyCraftStatTables.ATK_TABLE, atk, TL);
	}

	@Override
	public int computeComp(int comp, int TL) {
		return NPCUtils.lookupStat(FantasyCraftStatTables.RES_COMP_HLTH_TABLE, comp, TL);
	}

	@Override
	public int computeDef(int def, int TL) {
		return NPCUtils.lookupStat(FantasyCraftStatTables.INIT_DEF_TABLE, def, TL);
	}

	@Override
	public int computeHlth(int hlth, int TL) {
		return NPCUtils.lookupStat(FantasyCraftStatTables.RES_COMP_HLTH_TABLE, hlth, TL);
	}

	@Override
	public int computeInit(int init, int TL) {
		return NPCUtils.lookupStat(FantasyCraftStatTables.INIT_DEF_TABLE, init, TL);
	}

	@Override
	public int computeRes(int res, int TL) {
		return NPCUtils.lookupStat(FantasyCraftStatTables.RES_COMP_HLTH_TABLE, res, TL);
	}

	@Override
	public int computeSkill(int skill, int TL) {
		return NPCUtils.lookupStat(FantasyCraftStatTables.SKILL_TABLE, skill, TL);
	}

	public int computeDSModifier(Size size) {
		SizeMods sizemods = FantasyCraftStatTables.SIZE_MODS.get(size);
		return sizemods.dmgSaveMod;
	}

	public int computeDefModifier(Size size) {
		SizeMods sizemods = FantasyCraftStatTables.SIZE_MODS.get(size);
		return sizemods.defMod;
	}
	
	public int computeVitality(FantasyCraftNPC npc, int TL) {
		return 5 * npc.getHlth() * TL;
	}

	public int computeWounds(FantasyCraftNPC npc) {
		SizeMods sizemods = FantasyCraftStatTables.SIZE_MODS.get(npc.getSize());
		return (int)Math.ceil(((double)npc.getCon()) * sizemods.woundMult);
	}
	
	public String damageAttackDice(int grade) {
		return FantasyCraftStatTables.DAMAGE_EO_DICE[grade];
	}
	
	public String getNaturalAttackDamageDice(String type, Size size, int grade) {
		NatAtkStats atkStats = FantasyCraftStatTables.NAT_ATKS.get(type);
		String dice = atkStats.getDamageDice(size);
		
		if (grade > 2) {
			int ndice = Integer.parseInt(dice.substring(0, dice.indexOf('d')));
			
			if (grade == 3 || grade == 4) {
				// double damage dice
				ndice *= 2;
			} else if (grade == 5) {
				// triple
				ndice *= 3;
			}
			dice = ndice + dice.substring(dice.indexOf('d'));
		}
		
		return dice;
	}
	
	public String getNaturalAttackThreat(String type, Size size, int grade) {
		NatAtkStats atkStats = FantasyCraftStatTables.NAT_ATKS.get(type);
		int threat = atkStats.getBaseThreat();
		
		if (grade > 1 && threat != 0) {
			if (grade == 2 || grade == 3) {
				threat -= 1;
			} else if (grade > 3) {
				threat -= 2;
			}
		}
			
		String threatDesc = "-";
		if (threat != 0) {
			if (threat == 20) {
				threatDesc = "20";
			} else {
				threatDesc = threat + "-20";
			}
		}
		
		return threatDesc;
	}
	
}
