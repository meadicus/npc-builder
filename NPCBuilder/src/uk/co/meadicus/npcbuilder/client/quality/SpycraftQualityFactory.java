package uk.co.meadicus.npcbuilder.client.quality;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import uk.co.meadicus.npcbuilder.client.npc.Attributes;
import uk.co.meadicus.npcbuilder.client.npc.SpycraftConfig;

public class SpycraftQualityFactory extends QualityFactory {

	private static SpycraftQualityFactory singleton;
	private SpycraftQualityFactory() {
		super();
	}
	public static SpycraftQualityFactory getInstance() {
		if (singleton == null) {
			singleton = new SpycraftQualityFactory();
		}
		return singleton;
	}
	
	private static final String[] achillesHeelTypes = {"acid",
													   "bang",
													   "cold",
													   "collision",
													   "contagon",
													   "electrical",
													   "explosive",
													   "falling",
													   "fire",
													   "flash",
													   "heat",
													   "laser",
													   "sneak attack",
													   "stress",
													   "subdual",
													   "vacuum",
													   "called shot",
														"threat"};
	private static final String[] terraintypes = {"aquatic",
												  "arctic",
												  "desert",
												  "forest",
												  "jungle",
												  "mountain",
												  "plains",
												  "swamp"};
	private static final String[] damageTypes = {"acid",
												 "bang",
												 "cold",
												 "collision",
												 "contagon",
												 "electrical",
												 "explosive",
												 "falling",
												 "fire",
												 "flash",
												 "heat",
												 "laser",
												 "sneak attack",
												 "stress",
												 "subdual",
												 "vacuum"};
	private static final String[] naturalAttacks = {"bite",
													"claw",
													"kick",
													"slam",
													"talon",
													"gore",
													"poison",
													"squeeze",
													"sting",
													"swallow",
													"tail slap",
													"tentacle slap",
													"trample"};
	private static final Map<String, List<String>> qualityGroups  = new LinkedHashMap<String, List<String>>();
	static {
		List<String> coreNames = new ArrayList<String>();
		coreNames.add("achilles heel");
		coreNames.add("all thumbs");
		coreNames.add("alternate identity/form");
		coreNames.add("aquatic");
		coreNames.add("attach on command");
		coreNames.add("augmented");
		coreNames.add("burrow");
		coreNames.add("camouflaged");
		coreNames.add("captivating");
		coreNames.add("carry on command");
		coreNames.add("class ability");
		coreNames.add("clumsy");
		coreNames.add("commander");
		coreNames.add("construct");
		coreNames.add("damage reduction");
		coreNames.add("damage resistance");
		coreNames.add("disarm on command");
		coreNames.add("diurnal");
		coreNames.add("diving");
		coreNames.add("entertain on command");
		coreNames.add("extended training");
		coreNames.add("fast healing");
		coreNames.add("fearless");
		coreNames.add("fearsome");
		coreNames.add("feat");
		coreNames.add("feral");
		coreNames.add("fleet");
		coreNames.add("flight");
		coreNames.add("foil");
		coreNames.add("guard on command");
		coreNames.add("henchman");
		coreNames.add("horde");
		coreNames.add("horrific");
		coreNames.add("hulking");
		coreNames.add("improved hearing");
		coreNames.add("improved scent");
		coreNames.add("improved vision");
		coreNames.add("inferior attribute");
		coreNames.add("low-light vision");
		coreNames.add("mastermind");
		coreNames.add("meek");
		coreNames.add("minion");
		coreNames.add("mount");
		coreNames.add("natural attack");
		coreNames.add("nocturnal");
		coreNames.add("non-combatant");
		coreNames.add("open doors");
		coreNames.add("prodigy");
		coreNames.add("rescue");
		coreNames.add("retrieve on command");
		coreNames.add("seductive");
		coreNames.add("seek on command");
		coreNames.add("sluggish");
		coreNames.add("specialty");
		coreNames.add("squad item");
		coreNames.add("squad weapon");
		coreNames.add("steadfast");
		coreNames.add("story-critical");
		coreNames.add("strong-minded");
		coreNames.add("subdue on command");
		coreNames.add("superior attribute");
		coreNames.add("swarm");
		coreNames.add("synchronized");
		coreNames.add("talented");
		coreNames.add("tough");
		coreNames.add("treacherous");
		coreNames.add("uncoordinated");
		coreNames.add("undersized");
		coreNames.add("unnerving");
		qualityGroups.put("Core Spycraft 2.0", coreNames);
		
		List<String> fmNames = new ArrayList<String>();
		fmNames.add("beast");
		fmNames.add("blindsight");
		fmNames.add("from beyond");
		fmNames.add("hideous");
		fmNames.add("horror");
		fmNames.add("mystic spark");
		fmNames.add("ooze");
		fmNames.add("puppeteer");
		fmNames.add("winged flight");
		qualityGroups.put("Fragile Minds", fmNames);
		
		List<String> pmpNames = new ArrayList<String>();
		pmpNames.add("advanced combat");
		qualityGroups.put("Practive Makes Perfect", pmpNames);
		
		List<String> ootsNames = new ArrayList<String>();
		ootsNames.add("banned check");
		ootsNames.add("beguiling");
		ootsNames.add("burden of ages");
		ootsNames.add("dramatic entrance");
		ootsNames.add("dread");
		ootsNames.add("extra stability");
		ootsNames.add("fatal falls");
		ootsNames.add("fey");
		ootsNames.add("grueling combatant");
		ootsNames.add("hollow bones");
		ootsNames.add("horror");
		ootsNames.add("light sensitivity");
		ootsNames.add("light sleeper");
		ootsNames.add("lumbering");
		ootsNames.add("monsterous attack");
		ootsNames.add("ostracized");
		ootsNames.add("paralyzing gaze");
		ootsNames.add("petrifying gaze");
		ootsNames.add("reviled");
		ootsNames.add("shadow-vision");
		ootsNames.add("spark");
		ootsNames.add("thick hide");
		ootsNames.add("venemous");
		ootsNames.add("winged flight");
		qualityGroups.put("Origin of the Species", ootsNames);
		
		List<String> sbNames = new ArrayList<String>();
		sbNames.add("fey");
		sbNames.add("outsider");
		sbNames.add("spell points");
		sbNames.add("spellbook");
		sbNames.add("undead");
		qualityGroups.put("Spellbound", sbNames);
		
		List<String> otherNames = new ArrayList<String>();
		otherNames.add("custom");
		qualityGroups.put("Other", otherNames);
	}
	
	@Override
	public Map<String, List<String>> getQualityNames() {
		return qualityGroups;
	}
		
	@Override
	public Quality getQuality(String name) {
		/*
		 * Simple Quality - name, baseXp, restrictions, options
		 * Detail Quality - name, baseXp, restrictions, options, maxuses(optional)
		 * Valued Detail Quality - name, baseXp, restrictions, options, values, maxuses(optional)
		 * 
		 */
		
		Quality quality = null;
		if (name.equals("achilles heel")) {
			quality = new DetailQuality(name, -3, null, achillesHeelTypes);
		} else if (name.equals("all thumbs")) {
			quality = new SimpleQuality(name, -2, null, null);
		} else if (name.equals("alternate identity/form")) {
			quality = new SimpleQuality(name, 4, null, null);
		} else if (name.equals("aquatic")) {
			quality = new SimpleQuality(name, 3, null, null);
		} else if (name.equals("attach on command")) {
			quality = new SimpleQuality(name, 2, "animals only", null);
		} else if (name.equals("augmented")) {
			String[] values = {"I", "II", "III", "IV", "V"};
			quality = new ValuedDetailQuality(name, 3, null, null, values, 4);
		} else if (name.equals("burrow")) {
			quality = new SimpleQuality(name, 3, "animals only", null);
		} else if (name.equals("camouflaged")) {
			quality = new DetailQuality(name, 3, null, terraintypes, 8);
		} else if (name.equals("captivating")) {
			quality = new SimpleQuality(name, 5, "special only", null);
		} else if (name.equals("carry on command")) {
			quality = new SimpleQuality(name, 1, "animals only - trained", null);
		} else if (name.equals("class ability")) {
			String[] oneToTwenty = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
			quality = new ValuedDetailQuality(name, 2, null, null, oneToTwenty);
		} else if (name.equals("clumsy")) {
			quality = new SimpleQuality(name, -2, null, null);
		} else if (name.equals("commander")) {
			quality = new SimpleQuality(name, 3, "minions only", null);
		} else if (name.equals("construct")) {
			quality = new SimpleQuality(name, 8, null, null);
		} else if (name.equals("damage reduction")) {
			String[] damageReductionOptions = {"1", "2", "3", "4", "5", "6", "7", "8"};
			quality = new SimpleQuality(name, 3, null, damageReductionOptions);
		} else if (name.equals("damage resistance")) {
			String[] resistanceValues = {"1", "2", "3", "4", "5", "6", "7", "8"};
			quality = new ValuedDetailQuality(name, 2, null, damageTypes, resistanceValues);
		} else if (name.equals("disarm on command")) {
			quality = new SimpleQuality(name, 2, "animals only - trained", null);
		} else if (name.equals("diurnal")) {
			quality = new SimpleQuality(name, -1, "animals only", null);
		} else if (name.equals("diving")) {
			quality = new SimpleQuality(name, 1, "animals only", null);
		} else if (name.equals("entertain on command")) {
			quality = new SimpleQuality(name, 1, "animals only - trained", null);
		} else if (name.equals("extended training")) {
			String[] options = {"×1","×2","×3","×4","×5","×6","×7","×8","×9","×10"};
			quality = new SimpleQuality(name, 1, null, options);
		} else if (name.equals("fast healing")) {
			quality = new SimpleQuality(name, 2, null, null);
		} else if (name.equals("fearless")) {
			String[] options = {"+4","+8","+12","+16","+20"};
			quality = new SimpleQuality(name, 1, null, options);
		} else if (name.equals("fearsome")) {
			quality = new SimpleQuality(name, 5, null, null);
		} else if (name.equals("feat")) {
			quality = new DetailQuality(name, 2, null, null);
		} else if (name.equals("feral")) {
			quality = new SimpleQuality(name, 2, null, null);
		} else if (name.equals("fleet")) {
			String[] options = {"+10ft","+20ft","+30ft","+40ft","+50ft","+60ft"};
			quality = new SimpleQuality(name, 3, null, options);
		} else if (name.equals("flight")) {
			String[] options = {"20ft","40ft","60ft","80ft","100ft"};
			quality = new SimpleQuality(name, 4, null, options);
		} else if (name.equals("foil")) {
			Map<String, Integer> values = new LinkedHashMap<String, Integer>();
			for (int v = -5; v < 6; ++v) {
				values.put(Integer.toString(v), v);
			}
			quality = new ScoredSimpleQuality(name, 0, null, values);
		} else if (name.equals("guard on command")) {
			quality = new SimpleQuality(name, 2, "animals only - trained", null);
		} else if (name.equals("henchman")) {
			quality = new SimpleQuality(name, 4, "special only", null);
		} else if (name.equals("horde")) {
			quality = new SimpleQuality(name, 4, "minions only", null);
		} else if (name.equals("horrific")) {
			quality = new SimpleQuality(name, 8, "no standard", null);
		} else if (name.equals("hulking")) {
			String[] options = {"Large", "Huge", "Gargantuan", "Colossal", "Enormous", "Vast"};
			quality = new SimpleQuality(name, 5, null, options);
		} else if (name.equals("improved hearing")) {
			quality = new SimpleQuality(name, 3, null, null);
		} else if (name.equals("improved scent")) {
			quality = new SimpleQuality(name, 3, null, null);
		} else if (name.equals("improved vision")) {
			quality = new SimpleQuality(name, 3, null, null);
		} else if (name.equals("inferior attribute")) {
			Attributes defaultAttributes = SpycraftConfig.getInstance().getDefaultAttributes();
			quality = new AttributesQuality(name, defaultAttributes, 0);
		} else if (name.equals("low-light vision")) {
			quality = new SimpleQuality(name, 2, null, null);
		} else if (name.equals("mastermind")) {
			quality = new SimpleQuality(name, 8, "special only", null);
		} else if (name.equals("meek")) {
			String[] options = {"-4","-8","-12","-16","-20"};
			quality = new SimpleQuality(name, -1, null, options);
		} else if (name.equals("minion")) {
			quality = new SimpleQuality(name, 2, "standard only", null);
		} else if (name.equals("mount")) {
			quality = new SimpleQuality(name, 1, "animals only - trained", null);
		} else if (name.equals("natural attack")) {
			String[] resistanceValues = {"I","II","III","IV","V"};
			quality = new ValuedDetailQuality(name, 2, null, naturalAttacks, resistanceValues, 4);
		} else if (name.equals("nocturnal")) {
			quality = new SimpleQuality(name,-1, null, null);
		} else if (name.equals("non-combatant")) {
			quality = new SimpleQuality(name, -3, null, null);
		} else if (name.equals("open doors")) {
			quality = new SimpleQuality(name, 1, "animals only - trained", null);
		} else if (name.equals("prodigy")) {
			quality = new SimpleQuality(name, 5, "standard only", null);
		} else if (name.equals("rescue")) {
			quality = new SimpleQuality(name, 3, "animals only - trained", null);
		} else if (name.equals("retrieve on command")) {
			quality = new SimpleQuality(name, 1, "animals only - trained", null);
		} else if (name.equals("seductive")) {
			quality = new SimpleQuality(name, 3, "special only", null);
		} else if (name.equals("seek on command")) {
			String[] options = {"animals","chemicals","drugs","explosives","food","people"};
			quality = new SimpleQuality(name, 2, "animals only - trained", options);
		} else if (name.equals("sluggish")) {
			String[] options = {"-10ft","-20ft"};
			quality = new SimpleQuality(name, -2, null, options);
		} else if (name.equals("specialty")) {
			quality = new DetailQuality(name, 5, null, null, 1);
		} else if (name.equals("squad item")) {
			quality = new DetailQuality(name, 8, "groups only", null, 1);
		} else if (name.equals("squad weapon")) {
			quality = new DetailQuality(name, 8, "groups only", null, 1);
		} else if (name.equals("steadfast")) {
			quality = new SimpleQuality(name, 3, "no animals", null);
		} else if (name.equals("story-critical")) {
			String[] options = {"+1 die type","+2 die type","+3 die type","+4 die type"};
			quality = new SimpleQuality(name, 3, null, options);
		} else if (name.equals("strong-minded")) {
			String[] options = {"+4","+8","+12","+16","+20"};
			quality = new SimpleQuality(name, 2, null, options);
		} else if (name.equals("subdue on command")) {
			quality = new SimpleQuality(name, 2, "animals only - trained", null);
		} else if (name.equals("superior attribute")) {
			Attributes defaultAttributes = SpycraftConfig.getInstance().getDefaultAttributes();
			quality = new AttributesQuality(name, defaultAttributes, 41);
		} else if (name.equals("swarm")) {
			quality = new SimpleQuality(name, 3, "animals only", null);
		} else if (name.equals("synchronized")) {
			quality = new SimpleQuality(name, 2, "groups only", null);
		} else if (name.equals("talented")) {
			quality = new OriginQuality(name, 5, null);
		} else if (name.equals("tough")) {
			String[] options = {"+1 save","+2 saves","+3 saves","+4 saves","+5 saves"};
			quality = new SimpleQuality(name, 5, null, options);
		} else if (name.equals("treacherous")) {
			quality = new SimpleQuality(name, 5, "no special", null);
		} else if (name.equals("uncoordinated")) {
			quality = new SimpleQuality(name, -2, "groups only", null);
		} else if (name.equals("undersized")) {
			String[] options = {"Small", "Tiny", "Diminuative", "Fine", "Nuisance"};
			quality = new SimpleQuality(name, -3, null, options);
		} else if (name.equals("unnerving")) {
			String[] options = {"1d4","2d4","3d4"};
			quality = new SimpleQuality(name, 3, null, options);
		} else if (name.equals("beast")) {
			quality = new SimpleQuality(name, 0, null, null);
		} else if (name.equals("blindsight")) {
			quality = new SimpleQuality(name, 6, "animals only", null);
		} else if (name.equals("from beyond")) {
			String[] options = {"1 grade", "2 grades", "3 grades", "4 grades", "5 grades"};
			quality = new SimpleQuality(name, 5, null, options);
		} else if (name.equals("hideous")) {
			String[] options = {"1 grade", "2 grades", "3 grades", "4 grades", "5 grades"};
			quality = new SimpleQuality(name, -1, null, options);
		} else if (name.equals("horror")) {
			quality = new SimpleQuality(name, 5, null, null);
		} else if (name.equals("mystic spark")) {
			quality = new SimpleQuality(name, 4, "high magic", null);
		} else if (name.equals("ooze")) {
			quality = new SimpleQuality(name, 5, null, null);
		} else if (name.equals("puppeteer")) {
			quality = new SimpleQuality(name, 10, null, null);
		} else if (name.equals("winged flight")) {
			Map<String, Integer> values = new LinkedHashMap<String, Integer>();
			int basexp = 3;
			for (int v = 0; v < 8; ++v) {
				String speed = (40 + (10 * v)) + "ft";
				values.put(speed, basexp + v);
			}
			quality = new ScoredSimpleQuality(name, 0, null, values);
		} else if (name.equals("advanced combat")) {
			quality = new DetailQuality(name, 1, null, null);
		} else if (name.equals("banned check")) {
			quality = new DetailQuality(name, -0.5, null, null);
		} else if (name.equals("beguiling")) {
			quality = new SimpleQuality(name, 2, null, null);
		} else if (name.equals("burden of ages")) {
			quality = new SimpleQuality(name, -4, null, null);
		} else if (name.equals("dramatic entrance")) {
			quality = new SimpleQuality(name, 8, null, null);
		} else if (name.equals("dread")) {
			String[] options = {"1 grade", "2 grades", "3 grades", "4 grades"};
			quality = new SimpleQuality(name, 2, null, options);
		} else if (name.equals("extra stability")) {
			quality = new SimpleQuality(name, 3, null, null);
		} else if (name.equals("fatal falls")) {
			quality = new SimpleQuality(name, -1, null, null);
		} else if (name.equals("fey")) {
			quality = new SimpleQuality(name, 3, null, null);
		} else if (name.equals("grueling combatant")) {
			String[] options = {"1 grade", "2 grades", "3 grades", "4 grades", "5 grades"};
			quality = new SimpleQuality(name, 2, null, options);
		} else if (name.equals("hollow bones")) {
			quality = new SimpleQuality(name, -1, null, null);
		} else if (name.equals("horror")) {
			quality = new SimpleQuality(name, 5, null, null);
		} else if (name.equals("light sensitivity")) {
			quality = new SimpleQuality(name, -2, null, null);
		} else if (name.equals("light sleeper")) {
			quality = new SimpleQuality(name, 1, null, null);
		} else if (name.equals("lumbering")) {
			quality = new SimpleQuality(name, -2, null, null);
		} else if (name.equals("monsterous attack")) {
			quality = new SimpleQuality(name, 1, null, null);
		} else if (name.equals("ostracized")) {
			quality = new SimpleQuality(name, -1, null, null);
		} else if (name.equals("paralyzing gaze")) {
			quality = new SimpleQuality(name, 10, null, null);
		} else if (name.equals("petrifying gaze")) {
			quality = new SimpleQuality(name, 15, null, null);
		} else if (name.equals("reviled")) {
			quality = new SimpleQuality(name, -2, null, null);
		} else if (name.equals("shadow-vision")) {
			quality = new SimpleQuality(name, 4, null, null);
		} else if (name.equals("spark")) {
			String[] options = {"1","2","3","4","5","6","7","8","9","10"};
			quality = new SimpleQuality(name, 1, null, options);
		} else if (name.equals("thick hide")) {
			String[] options = {"1","2","3","4","5","6","7","8","9","10"};
			quality = new SimpleQuality(name, 2, null, options);
		} else if (name.equals("venemous")) {
			Map<String, Integer> values = new LinkedHashMap<String, Integer>();
			values.put("I", 1);
			values.put("II", 2);
			values.put("III", 4);
			quality = new ScoredSimpleQuality(name, 0, null, values);
		} else if (name.equals("outsider")) {
			quality = new SimpleQuality(name, 5, null, null);
		} else if (name.equals("spell points")) {
			Map<String, Integer> values = new LinkedHashMap<String, Integer>();
			values.put("I", 3);
			values.put("II", 6);
			values.put("III", 9);
			values.put("X", 15);
			quality = new ScoredSimpleQuality(name, 0, null, values);
		} else if (name.equals("spellbook")) {
			quality = new FixedXpDetailQuality(name, 5, null, null);
		} else if (name.equals("undead")) {
			quality = new SimpleQuality(name, 5, null, null);
		} else {
			quality = new CustomQuality();
		}
		
		return quality;
	}
}
