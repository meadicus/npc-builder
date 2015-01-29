package uk.co.meadicus.npcbuilder.client.quality;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import uk.co.meadicus.npcbuilder.client.npc.FantasyCraftConfig;
import uk.co.meadicus.npcbuilder.client.quality.ConfiguredClassAbilityQuality.ClassAbility;

public class FantasyCraftQualityFactory extends QualityFactory {
		
	private static FantasyCraftQualityFactory singleton;
	private FantasyCraftQualityFactory() {
		super();
	}

	public static FantasyCraftQualityFactory getInstance() {
		if (singleton == null) {
			singleton = new FantasyCraftQualityFactory();
		}
		return singleton;
	}
	
	private final String[] achillesHeelTypes = {"acid",
												"bang",
												"cold",
												"divine",
												"electrical",
												"explosive",
												"fire",
												"flash",
												"force",
												"heat",
												"sneak attack",
												"sonic",
												"stress",
												"subdual",
												"blackpowder",
												"blunt",
												"bows",
												"edged",
												"animal",
												"beast",
												"construct",
												"elemental",
												"fey",
												"folk",
												"horror",
												"ooze",
												"outsider",
												"plant",
												"spirit",
												"undead"};
	private static final String[] terraintypes = {"aquatic",
												  "arctic",
												  "caverns/mountains",
												  "desert",
												  "forest/jungle",
												  "indoors/settled",
												  "plains",
												  "swamp"};
	private static final String[] conditions = {"baffled",
												"bleeding",
												"blinded",
												"deafened",
												"enraged",
												"entagled",
												"fatigued",
												"fixated",
												"flanked",
												"flat-footed",
												"frightened",
												"held",
												"helpless",
												"hidden",
												"incorporeal",
												"invisible",
												"paralyzed",
												"pinned",
												"prone",
												"shaken",
												"sickened",
												"slowed",
												"sprawled",
												"stunned"};
	private static final Map<String, Integer> scoredDamageTypes = new LinkedHashMap<String, Integer>();
	static {
		scoredDamageTypes.put("acid",7);
		scoredDamageTypes.put("bang",7);
		scoredDamageTypes.put("cold",7);
		scoredDamageTypes.put("divine",7);
		scoredDamageTypes.put("electrical",7);
		scoredDamageTypes.put("explosive",7);
		scoredDamageTypes.put("fire",7);
		scoredDamageTypes.put("flash",7);
		scoredDamageTypes.put("force",7);
		scoredDamageTypes.put("heat",7);
		scoredDamageTypes.put("lethal",5);
		scoredDamageTypes.put("sneak attack",7);
		scoredDamageTypes.put("sonic",7);
		scoredDamageTypes.put("stress",7);
		scoredDamageTypes.put("subdual",7);
	}
	private static final String[] senses = {"sight",
											"hearing",
											"smell"};
	
	private static final Map<String, List<String>> qualityGroups  = new LinkedHashMap<String, List<String>>();
	static {
		List<String> coreNames = new ArrayList<String>();
		coreNames.add("achilles heel");
		coreNames.add("always ready");
		coreNames.add("aquatic");
		coreNames.add("attractive");
		coreNames.add("banned action");
		coreNames.add("battering");
		coreNames.add("beguiling");
		coreNames.add("blindsight");
		coreNames.add("bright");
		coreNames.add("burden of ages");
		coreNames.add("cagey");
		coreNames.add("chameleon");
		coreNames.add("charge attack");
		coreNames.add("class ability");
		coreNames.add("clumsy");
		coreNames.add("cold-blooded");
		coreNames.add("condition immunity");
		coreNames.add("contagion immunity");
		coreNames.add("conversion");
		coreNames.add("critical hesitation");
		coreNames.add("critical surge");
		coreNames.add("damage defiance");
		coreNames.add("damage immunity");
		coreNames.add("damage reduction");
		coreNames.add("darkvision");
		coreNames.add("death throes");
		coreNames.add("devoted");
		coreNames.add("devour");
		coreNames.add("diurnal");
		coreNames.add("dramatic entrance");
		coreNames.add("dread");
		coreNames.add("everlasting");
		coreNames.add("expanded spellbook");
		coreNames.add("expertise");
		coreNames.add("fast healing");
		coreNames.add("fatal falls");
		coreNames.add("favoured foes");
		coreNames.add("fearless");
		coreNames.add("fearsome");
		coreNames.add("feat");
		coreNames.add("feral");
		coreNames.add("ferocity");
		coreNames.add("frenzy");
		coreNames.add("grappler");
		coreNames.add("grueling combatant");
		coreNames.add("hive mind");
		coreNames.add("honorable");
		coreNames.add("impaired sense");
		coreNames.add("improved carrying capacity");
		coreNames.add("improved sense");
		coreNames.add("improved stability");
		coreNames.add("interests");
		coreNames.add("invisibility");
		coreNames.add("knockback");
		coreNames.add("light-sensitive");
		coreNames.add("light sleeper");
		coreNames.add("lumbering");
		coreNames.add("meek");
		coreNames.add("menacing threat");
		coreNames.add("mook");
		coreNames.add("monsterous attack");
		coreNames.add("monsterous defense");
		coreNames.add("natural defense");
		coreNames.add("natural spell");
		coreNames.add("never outnumbered");
		coreNames.add("night-blind");
		coreNames.add("nocturnal");
		coreNames.add("regeneration");
		coreNames.add("rend");
		coreNames.add("repulsive");
		coreNames.add("shambling");
		coreNames.add("shapeshifter");
		coreNames.add("spell defense");
		coreNames.add("spell reflection");
		coreNames.add("splitter");
		coreNames.add("stench");
		coreNames.add("sterner stuff");
		coreNames.add("story-critical");
		coreNames.add("superior climber");
		coreNames.add("superior jumper");
		coreNames.add("superior runner");
		coreNames.add("superior swimmer");
		coreNames.add("superior traveler");
		coreNames.add("swarm");
		coreNames.add("swift attack");
		coreNames.add("telepathic");
		coreNames.add("tough");
		coreNames.add("treacherous");
		coreNames.add("tricky");
		coreNames.add("turning");
		coreNames.add("turn immunity");
		coreNames.add("unlimited spell points");
		coreNames.add("unnerving");
		coreNames.add("veteran");
		qualityGroups.put("Core Fantasy Craft", coreNames);
		
		List<String> sbNames = new ArrayList<String>();
		sbNames.add("luminous");
		qualityGroups.put("Spellbound", sbNames);
		
		List<String> otherNames = new ArrayList<String>();
		otherNames.add("custom");
		qualityGroups.put("Other", otherNames);
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
			quality = new DetailQuality(name, -2, null, achillesHeelTypes);
		} else if (name.equals("always ready")) {
			quality = new SimpleQuality(name, 2, null, null);
		} else if (name.equals("aquatic")) {
			Map<String, Integer> values = new LinkedHashMap<String, Integer>();
			values.put("I", 1);
			values.put("II", 3);
			quality = new ScoredSimpleQuality(name, 0, null, values);
		} else if (name.equals("attractive")) {
			String[] options = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
			quality = new SimpleQuality(name, 1, null, options);
		} else if (name.equals("banned action")) {
			quality = new DetailQuality(name, 0, null, null);
		} else if (name.equals("battering")) {
			quality = new SimpleQuality(name, 2, null, null);
		} else if (name.equals("beguiling")) {
			quality = new SimpleQuality(name, 2, null, null);
		} else if (name.equals("blindsight")) {
			quality = new SimpleQuality(name, 4, null, null);
		} else if (name.equals("bright")) {
			String[] options = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
			quality = new SimpleQuality(name, 1, null, options);
		} else if (name.equals("burden of ages")) {
			quality = new SimpleQuality(name, -4, null, null);
		} else if (name.equals("cagey")) {
			String[] options = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
			quality = new SimpleQuality(name, 1, null, options);
		} else if (name.equals("chameleon")) {
			String[] values = {"I", "II"};
			quality = new ValuedDetailQuality(name, 2, null, terraintypes, values, 8);
		} else if (name.equals("charge attack")) {
			quality = new SimpleQuality(name, 2, null, null);
		} else if (name.equals("class ability")) {
			quality = new ConfiguredClassAbilityQuality(name, classAbilities);
		} else if (name.equals("clumsy")) {
			quality = new SimpleQuality(name, -2, null, null);
		} else if (name.equals("cold-blooded")) {
			quality = new SimpleQuality(name, -2, null, null);
		} else if (name.equals("condition immunity")) {
			quality = new DetailQuality(name, 4, null, conditions, 5);
		} else if (name.equals("contagion immunity")) {
			quality = new SimpleQuality(name, 4, null, null);
		} else if (name.equals("conversion")) {
			Map<String, Integer> values = new LinkedHashMap<String, Integer>();
			values.put("infectious", 5);
			values.put("killing", 5);
			quality = new ScoredSimpleQuality(name, 0, null, values);
		} else if (name.equals("critical hesitation")) {
			quality = new SimpleQuality(name, -2, null, null);
		} else if (name.equals("critical surge")) {
			quality = new SimpleQuality(name, 2, null, null);
		} else if (name.equals("damage defiance")) {
			quality = new DetailQuality(name, 2, null, achillesHeelTypes);
		} else if (name.equals("damage immunity")) {
			quality = new DetailQuality(name, 5, null, achillesHeelTypes);
		} else if (name.equals("damage reduction")) {
			String[] oneToTwenty = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
			quality = new SimpleQuality(name, 3, null, oneToTwenty);
		} else if (name.equals("darkvision")) {
			Map<String, Integer> values = new LinkedHashMap<String, Integer>();
			values.put("I", 1);
			values.put("II", 3);
			quality = new ScoredSimpleQuality(name, 0, null, values);
		} else if (name.equals("death throes")) {
			quality = new ScoredSimpleQuality(name, 0, null, scoredDamageTypes);
		} else if (name.equals("devoted")) {
			String[] options = {"I","II","III","IV","V"};
			quality = new ValuedDetailQuality(name, 2, null, null, options, 5);
		} else if (name.equals("devour")) {
			quality = new SimpleQuality(name, 3, null, null);
		} else if (name.equals("diurnal")) {
			quality = new SimpleQuality(name, -1, null, null);
		} else if (name.equals("dramatic entrance")) {
			quality = new SimpleQuality(name, 8, null, null);
		} else if (name.equals("dread")) {
			quality = new SimpleQuality(name, 2, null, null);
		} else if (name.equals("everlasting")) {
			quality = new SimpleQuality(name, 0, null, null);
		} else if (name.equals("expanded spellbook")) {
			quality = new DetailQuality(name, 1, null, null);
		} else if (name.equals("expertise")) {
			List<String> options = FantasyCraftConfig.getInstance().getAvailableSkills();
			quality = new DetailQuality(name, 2, null, options.toArray(new String[options.size()]));
		} else if (name.equals("fast healing")) {
			quality = new SimpleQuality(name, 1, null, null);
		} else if (name.equals("fatal falls")) {
			quality = new SimpleQuality(name, -1, null, null);
		} else if (name.equals("favoured foes")) {
			quality = new DetailQuality(name, 1, null, FantasyCraftConfig.FCNPCType.displayValues());
		} else if (name.equals("fearless")) {
			String[] options = {"I", "II"};
			quality = new SimpleQuality(name, 2, null, options);
		} else if (name.equals("fearsome")) {
			quality = new SimpleQuality(name, 2, null, null);
		} else if (name.equals("feat")) {
			quality = new DetailQuality(name, 2, null, null);
		} else if (name.equals("feral")) {
			quality = new SimpleQuality(name, 1, null, null);
		} else if (name.equals("ferocity")) {
			quality = new SimpleQuality(name, 3, null, null);
		} else if (name.equals("frenzy")) {
			String[] options = {"I", "II", "III"};
			quality = new SimpleQuality(name, 4, null, options);
		} else if (name.equals("grappler")) {
			quality = new SimpleQuality(name, 2, null, null);
		} else if (name.equals("grueling combatant")) {
			quality = new SimpleQuality(name, 2, null, null);
		} else if (name.equals("hive mind")) {
			quality = new SimpleQuality(name, 2, null, null);
		} else if (name.equals("honorable")) {
			quality = new SimpleQuality(name, 3, null, null);
		} else if (name.equals("impaired sense")) {
			quality = new DetailQuality(name, -1, null, senses);
		} else if (name.equals("improved carrying capacity")) {
			quality = new SimpleQuality(name, 0, null, null);
		} else if (name.equals("improved sense")) {
			quality = new DetailQuality(name, 1, null, senses);
		} else if (name.equals("improved stability")) {
			quality = new SimpleQuality(name, 2, null, null);
		} else if (name.equals("interests")) {
			quality = new DetailQuality(name, 0, null, null);
		} else if (name.equals("invisibility")) {
			quality = new SimpleQuality(name, 8, null, null);
		} else if (name.equals("knockback")) {
			quality = new SimpleQuality(name, 2, null, null);
		} else if (name.equals("light-sensitive")) {
			quality = new SimpleQuality(name, -2, null, null);
		} else if (name.equals("light sleeper")) {
			quality = new SimpleQuality(name, 1, null, null);
		} else if (name.equals("lumbering")) {
			quality = new SimpleQuality(name, -2, null, null);
		} else if (name.equals("meek")) {
			quality = new SimpleQuality(name, -2, null, null);
		} else if (name.equals("menacing threat")) {
			quality = new SimpleQuality(name, 2, null, null);
		} else if (name.equals("mook")) {
			quality = new SimpleQuality(name, -4, null, null);
		} else if (name.equals("monsterous attack")) {
			String[] options = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
			quality = new SimpleQuality(name, 3, null, options);
		} else if (name.equals("monsterous defense")) {
			String[] options = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
			quality = new SimpleQuality(name, 3, null, options);
		} else if (name.equals("natural defense")) {
			quality = new ScoredSimpleQuality(name, 0, null, scoredDamageTypes);
		} else if (name.equals("natural spell")) {
			Map<String, Integer> values = new LinkedHashMap<String, Integer>();
			for (int v = 0; v < 10; ++v) {
				values.put(Integer.toString(v), v);
			}
			quality = new ScoredValuedDetailQuality(name, 0, null, null, values);
		} else if (name.equals("never outnumbered")) {
			quality = new SimpleQuality(name, 2, null, null);
		} else if (name.equals("night-blind")) {
			quality = new SimpleQuality(name, 0, null, null);
		} else if (name.equals("nocturnal")) {
			quality = new SimpleQuality(name, -1, null, null);
		} else if (name.equals("regeneration")) {
			String[] oneToTwenty = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
			quality = new SimpleQuality(name, 3, null, oneToTwenty);
		} else if (name.equals("rend")) {
			quality = new SimpleQuality(name, 3, null, null);
		} else if (name.equals("repulsive")) {
			String[] options = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
			quality = new SimpleQuality(name, -2, null, options);
		} else if (name.equals("shambling")) {
			quality = new SimpleQuality(name, -4, null, null);
		} else if (name.equals("shapeshifter")) {
			String[] options = {"I", "II", "III"};
			quality = new SimpleQuality(name, 4, null, options);
		} else if (name.equals("spell defense")) {
			String[] options = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
			quality = new SimpleQuality(name, 2, null, options);
		} else if (name.equals("spell reflection")) {
			quality = new SimpleQuality(name, 3, null, null);
		} else if (name.equals("splitter")) {
			Map<String, Integer> values = new LinkedHashMap<String, Integer>();
			values.put("damage", 0);
			values.put("damage-mature instnatly", 5);
			values.put("killing", 0);
			values.put("killing-mature instnatly", 5);
			quality = new ScoredSimpleQuality(name, 0, null, values);
		} else if (name.equals("stench")) {
			quality = new SimpleQuality(name, 2, null, null);
		} else if (name.equals("sterner stuff")) {
			quality = new SimpleQuality(name, 1, null, null);
		} else if (name.equals("story-critical")) {
			quality = new SimpleQuality(name, 0, null, null);
		} else if (name.equals("superior climber")) {
			String[] options = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
			quality = new SimpleQuality(name, 1, null, options);
		} else if (name.equals("superior jumper")) {
			String[] options = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
			quality = new SimpleQuality(name, 1, null, options);
		} else if (name.equals("superior runner")) {
			String[] options = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
			quality = new SimpleQuality(name, 1, null, options);
		} else if (name.equals("superior swimmer")) {
			String[] options = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
			quality = new SimpleQuality(name, 1, null, options);
		} else if (name.equals("superior traveler")) {
			String[] options = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
			quality = new SimpleQuality(name, 1, null, options);
		} else if (name.equals("swarm")) {
			quality = new SimpleQuality(name, 2, null, null);
		} else if (name.equals("swift attack")) {
			String[] options = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
			quality = new SimpleQuality(name, 3, null, options);
		} else if (name.equals("telepathic")) {
			quality = new SimpleQuality(name, 1, null, null);
		} else if (name.equals("tough")) {
			String[] options = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
			quality = new SimpleQuality(name, 5, null, options);
		} else if (name.equals("treacherous")) {
			quality = new SimpleQuality(name, 5, null, null);
		} else if (name.equals("tricky")) {
			quality = new DetailQuality(name, 2, null, null);
		} else if (name.equals("turning")) {
			quality = new DetailQuality(name, 4, null, FantasyCraftConfig.FCNPCType.displayValues());
		} else if (name.equals("turn immunity")) {
			quality = new SimpleQuality(name,2, null, null);
		} else if (name.equals("unlimited spell points")) {
			quality = new SimpleQuality(name, 5, null, null);
		} else if (name.equals("unnerving")) {
			quality = new SimpleQuality(name, 2, null, null);
		} else if (name.equals("veteran")) {
			String[] options = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
			quality = new SimpleQuality(name, 2, null, options);
		} else if (name.equals("luminous")) {
			Map<String, Integer> values = new LinkedHashMap<String, Integer>();
			values.put("I", 3);
			values.put("II", 12);
			values.put("II", 20);
			quality = new ScoredSimpleQuality(name, 0, null, values);
		} else {
			quality = new CustomQuality();
		}
		
		return quality;
	}

	@Override
	public Map<String, List<String>> getQualityNames() {
		return qualityGroups;
	}
	
	/**
	 * class ability config, put down here because it is huge
	 */
	private static final List<ClassAbility> classAbilities = new ArrayList<ClassAbility>();
	static {
		classAbilities.add(new ClassAbility("Alchemist", "alchemical harmony", 2));
		classAbilities.add(new ClassAbility("Alchemist", "transmutation", 2));
		classAbilities.add(new ClassAbility("Assassin", "bald-faced lie", 5));
		classAbilities.add(new ClassAbility("Assassin", "black vial", 2));
		classAbilities.add(new ClassAbility("Assassin", "cold read 1/session", 2));
		classAbilities.add(new ClassAbility("Assassin", "cold read 2/session", 4));
		classAbilities.add(new ClassAbility("Assassin", "cold read 3/session", 6));
		classAbilities.add(new ClassAbility("Assassin", "convincing", 2));
		classAbilities.add(new ClassAbility("Assassin", "fake it", 2));
		classAbilities.add(new ClassAbility("Assassin", "finish him!", 8));
		classAbilities.add(new ClassAbility("Assassin", "follow my lead", 2));
		classAbilities.add(new ClassAbility("Assassin", "hand of death", 2));
		classAbilities.add(new ClassAbility("Assassin", "offer they can’t refuse", 2));
		classAbilities.add(new ClassAbility("Assassin", "quick on your feet", 2));
		classAbilities.add(new ClassAbility("Avatar", "Alpha", 4));
		classAbilities.add(new ClassAbility("Avatar", "Omega", 10));
		classAbilities.add(new ClassAbility("Avatar", "Sliver of divinity", 5));
		classAbilities.add(new ClassAbility("Bloodsworn", "blood-bound I", 4));
		classAbilities.add(new ClassAbility("Bloodsworn", "blood-bound II", 8));
		classAbilities.add(new ClassAbility("Bloodsworn", "blood-bound III", 12));
		classAbilities.add(new ClassAbility("Bloodsworn", "harm's way I", 2));
		classAbilities.add(new ClassAbility("Bloodsworn", "harm's way II", 4));
		classAbilities.add(new ClassAbility("Bloodsworn", "harm's way III", 6));
		classAbilities.add(new ClassAbility("Bloodsworn", "watch out! (initiative count)", 2));
		classAbilities.add(new ClassAbility("Bloodsworn", "watch out! (defense)", 2));
		classAbilities.add(new ClassAbility("Burglar", "bloody mess", 2));
		classAbilities.add(new ClassAbility("Burglar", "evasion I", 2));
		classAbilities.add(new ClassAbility("Burglar", "evasion II", 4));
		classAbilities.add(new ClassAbility("Burglar", "evasion III", 6));
		classAbilities.add(new ClassAbility("Burglar", "he did it!", 2));
		classAbilities.add(new ClassAbility("Burglar", "I'll cut you! bleeding", 2));
		classAbilities.add(new ClassAbility("Burglar", "I'll cut you! stunned", 4));
		classAbilities.add(new ClassAbility("Burglar", "look out!", 2));
		classAbilities.add(new ClassAbility("Burglar", "slippery", 2));
		classAbilities.add(new ClassAbility("Burglar", "stash it", 2));
		classAbilities.add(new ClassAbility("Burglar", "stick close and don't make a sound", 2));
		classAbilities.add(new ClassAbility("Burglar", "uncanny dodge I", 2));
		classAbilities.add(new ClassAbility("Burglar", "uncanny dodge II", 4));
		classAbilities.add(new ClassAbility("Burglar", "uncanny dodge III", 6));
		classAbilities.add(new ClassAbility("Burglar", "uncanny dodge IV", 8));
		classAbilities.add(new ClassAbility("Burglar", "uncanny dodge V", 10));
		classAbilities.add(new ClassAbility("Burglar", "very, very sneaky", 2));
		classAbilities.add(new ClassAbility("Captain", "battle planning I", 2));
		classAbilities.add(new ClassAbility("Captain", "battle planning II", 4));
		classAbilities.add(new ClassAbility("Captain", "battle planning III", 6));
		classAbilities.add(new ClassAbility("Captain", "battle planning IV", 8));
		classAbilities.add(new ClassAbility("Captain", "battle planning V", 10));
		classAbilities.add(new ClassAbility("Captain", "take command", 2));
		classAbilities.add(new ClassAbility("Captain", "take measure 1/session", 2));
		classAbilities.add(new ClassAbility("Captain", "take measure 2/session", 4));
		classAbilities.add(new ClassAbility("Captain", "virtues of command", 5));
		classAbilities.add(new ClassAbility("Crusader", "Battle chants I", 2));
		classAbilities.add(new ClassAbility("Crusader", "Battle chants II", 4));
		classAbilities.add(new ClassAbility("Crusader", "Battle chants III", 6));
		classAbilities.add(new ClassAbility("Crusader", "Devine virtue I", 8));
		classAbilities.add(new ClassAbility("Crusader", "Devine virtue II", 16));
		classAbilities.add(new ClassAbility("Crusader", "Purifier", 1));
		classAbilities.add(new ClassAbility("Crusader", "Smite", 2));
		classAbilities.add(new ClassAbility("Crusader", "Sword of faith I", 5));
		classAbilities.add(new ClassAbility("Crusader", "Sword of faith II", 10));
		classAbilities.add(new ClassAbility("Crusader", "Swath of god", 12));
		classAbilities.add(new ClassAbility("Courtier", "master plan I", 2));
		classAbilities.add(new ClassAbility("Courtier", "master plan II", 4));
		classAbilities.add(new ClassAbility("Courtier", "never outdone", 8));
		classAbilities.add(new ClassAbility("Courtier", "only the finest", 2));
		classAbilities.add(new ClassAbility("Courtier", "slanderous", 2));
		classAbilities.add(new ClassAbility("Courtier", "with a word", 2));
		classAbilities.add(new ClassAbility("Deadeye", "chink in their armor", 1));
		classAbilities.add(new ClassAbility("Deadeye", "dead to rights", 8));
		classAbilities.add(new ClassAbility("Deadeye", "every shot counts", 2));
		classAbilities.add(new ClassAbility("Deadeye", "ranged sneak attack (1 die)", 1));
		classAbilities.add(new ClassAbility("Deadeye", "ranged sneak attack (2 dice)", 2));
		classAbilities.add(new ClassAbility("Deadeye", "surprise shot +1", 2));
		classAbilities.add(new ClassAbility("Deadeye", "surprise shot +2", 4));
		classAbilities.add(new ClassAbility("Deadeye", "thousand-yard stare", 2));
		classAbilities.add(new ClassAbility("Dragon Lord", "dragonshape", 10));
		classAbilities.add(new ClassAbility("Edgemaster", "blade dance I", 2));
		classAbilities.add(new ClassAbility("Edgemaster", "blade dance II", 4));
		classAbilities.add(new ClassAbility("Edgemaster", "carve", 2));
		classAbilities.add(new ClassAbility("Edgemaster", "deadly blow", 5));
		classAbilities.add(new ClassAbility("Edgemaster", "display of arms I", 2));
		classAbilities.add(new ClassAbility("Edgemaster", "display of arms II", 4));
		classAbilities.add(new ClassAbility("Edgemaster", "effortless cut", 8));
		classAbilities.add(new ClassAbility("Edgemaster", "master’s touch I", 5));
		classAbilities.add(new ClassAbility("Edgemaster", "study the stance", 2));
		classAbilities.add(new ClassAbility("Emissary", "human nature", 2));
		classAbilities.add(new ClassAbility("Emissary", "intercept message", 2));
		classAbilities.add(new ClassAbility("Emissary", "leverage", 2));
		classAbilities.add(new ClassAbility("Emissary", "man of mystery", 2));
		classAbilities.add(new ClassAbility("Emissary", "master of secrets 1/session", 2));
		classAbilities.add(new ClassAbility("Emissary", "master of secrets 2/session", 4));
		classAbilities.add(new ClassAbility("Emissary", "ready for the worst", 5));
		classAbilities.add(new ClassAbility("Emissary", "status quo I", 5));
		classAbilities.add(new ClassAbility("Emissary", "status quo II", 10));
		classAbilities.add(new ClassAbility("Explorer", "bookworm I", 2));
		classAbilities.add(new ClassAbility("Explorer", "bookworm II", 4));
		classAbilities.add(new ClassAbility("Explorer", "bookworm III", 6));
		classAbilities.add(new ClassAbility("Explorer", "lifeline", 5));
		classAbilities.add(new ClassAbility("Explorer", "notebook 1/session", 2));
		classAbilities.add(new ClassAbility("Explorer", "notebook 2/session", 4));
		classAbilities.add(new ClassAbility("Explorer", "tomb raider", 2));
		classAbilities.add(new ClassAbility("Explorer", "uncanny dodge I", 2));
		classAbilities.add(new ClassAbility("Explorer", "uncanny dodge II", 4));
		classAbilities.add(new ClassAbility("Explorer", "uncanny dodge III", 6));
		classAbilities.add(new ClassAbility("Explorer", "uncanny dodge IV", 8));
		classAbilities.add(new ClassAbility("Explorer", "uncanny dodge V", 10));
		classAbilities.add(new ClassAbility("Gallant", "famed blade", 5));
		classAbilities.add(new ClassAbility("Gallant", "no more games (self)", 4));
		classAbilities.add(new ClassAbility("Gallant", "no more games (teammates)", 4));
		classAbilities.add(new ClassAbility("Gallant", "war, by other means", 2));
		classAbilities.add(new ClassAbility("Infernalist", "fire & brimstone", 2));
		classAbilities.add(new ClassAbility("Infernalist", "from hell", 4));
		classAbilities.add(new ClassAbility("Infernalist", "sympathy for the devil I", 2));
		classAbilities.add(new ClassAbility("Infernalist", "sympathy for the devil II", 2));
		classAbilities.add(new ClassAbility("Inquisitor", "Condemn I", 2));
		classAbilities.add(new ClassAbility("Inquisitor", "Condemn II", 4));
		classAbilities.add(new ClassAbility("Inquisitor", "Disconcerting", 2));
		classAbilities.add(new ClassAbility("Inquisitor", "Incorruptible (1 alignment)", 5));
		classAbilities.add(new ClassAbility("Inquisitor", "Incorruptible (2 alignments)", 10));
		classAbilities.add(new ClassAbility("Inquisitor", "Torches and pitchforks", 10));
		classAbilities.add(new ClassAbility("Keeper", "adaptable toolbox", 1));
		classAbilities.add(new ClassAbility("Keeper", "bright idea 1/session", 1));
		classAbilities.add(new ClassAbility("Keeper", "bright idea 2/session", 2));
		classAbilities.add(new ClassAbility("Keeper", "bright idea 3/session", 3));
		classAbilities.add(new ClassAbility("Keeper", "bright idea 4/session", 4));
		classAbilities.add(new ClassAbility("Keeper", "bright idea 5/session", 5));
		classAbilities.add(new ClassAbility("Keeper", "elbow grease", 2));
		classAbilities.add(new ClassAbility("Keeper", "instant solution", 5));
		classAbilities.add(new ClassAbility("Keeper", "man of reason", 2));
		classAbilities.add(new ClassAbility("Keeper", "stash it", 2));
		classAbilities.add(new ClassAbility("Lancer", "born in the saddle", 2));
		classAbilities.add(new ClassAbility("Lancer", "bred for war I", 1));
		classAbilities.add(new ClassAbility("Lancer", "bred for war II", 2));
		classAbilities.add(new ClassAbility("Lancer", "bred for war III", 3));
		classAbilities.add(new ClassAbility("Lancer", "bred for war IV", 4));
		classAbilities.add(new ClassAbility("Lancer", "bred for war V", 5));
		classAbilities.add(new ClassAbility("Lancer", "last stand", 8));
		classAbilities.add(new ClassAbility("Lancer", "master rider 1/adventure", 5));
		classAbilities.add(new ClassAbility("Lancer", "master rider 2/adventure", 10));
		classAbilities.add(new ClassAbility("Mage", "arcane wellspring I", 4));
		classAbilities.add(new ClassAbility("Mage", "arcane wellspring II", 8));
		classAbilities.add(new ClassAbility("Mage", "master of magic", 8));
		classAbilities.add(new ClassAbility("Mage", "spell secret 1 spell", 1));
		classAbilities.add(new ClassAbility("Mage", "spell secret 2 spells", 2));
		classAbilities.add(new ClassAbility("Mage", "spell secret 3 spells", 3));
		classAbilities.add(new ClassAbility("Mage", "spell secret 4 spells", 4));
		classAbilities.add(new ClassAbility("Mage", "spell secret 5 spells", 5));
		classAbilities.add(new ClassAbility("Martial Artist", "backhand", 1));
		classAbilities.add(new ClassAbility("Martial Artist", "beat down", 1));
		classAbilities.add(new ClassAbility("Martial Artist", "finishing move", 8));
		classAbilities.add(new ClassAbility("Martial Artist", "great sweep", 1));
		classAbilities.add(new ClassAbility("Martial Artist", "life of discipline (body)", 2));
		classAbilities.add(new ClassAbility("Martial Artist", "life of discipline (mind)", 2));
		classAbilities.add(new ClassAbility("Martial Artist", "life of discipline (spirit)", 2));
		classAbilities.add(new ClassAbility("Martial Artist", "master's touch I", 5));
		classAbilities.add(new ClassAbility("Martial Artist", "master's touch II", 10));
		classAbilities.add(new ClassAbility("Martial Artist", "provoke", 1));
		classAbilities.add(new ClassAbility("Martial Artist", "quake strike", 2));
		classAbilities.add(new ClassAbility("Martial Artist", "stare down", 1));
		classAbilities.add(new ClassAbility("Martial Artist", "wuxia I", 2));
		classAbilities.add(new ClassAbility("Martial Artist", "wuxia II", 4));
		classAbilities.add(new ClassAbility("Mist Dancer", "fog's cloak", 2));
		classAbilities.add(new ClassAbility("Mist Dancer", "mist-bourne", 5));
		classAbilities.add(new ClassAbility("Mist Dancer", "night's embrace", 2));
		classAbilities.add(new ClassAbility("Mist Dancer", "shade's step", 5));
		classAbilities.add(new ClassAbility("Mist Dancer", "shadow's hand (conceal action/stash)", 2));
		classAbilities.add(new ClassAbility("Mist Dancer", "shadow's hand (feint/disable)", 2));
		classAbilities.add(new ClassAbility("Mist Dancer", "spellshroud", 4));
		classAbilities.add(new ClassAbility("Monk", "heavenly fist", 4));
		classAbilities.add(new ClassAbility("Monk", "iron soul", 2));
		classAbilities.add(new ClassAbility("Monk", "spirit fist", 2));
		classAbilities.add(new ClassAbility("Monster Slayer", "cut deep and true", 2));
		classAbilities.add(new ClassAbility("Monster Slayer", "getting the scent", 4));
		classAbilities.add(new ClassAbility("Monster Slayer", "ride the avalanche (flurry)", 2));
		classAbilities.add(new ClassAbility("Monster Slayer", "ride the avalanche (gaze/trample)", 2));
		classAbilities.add(new ClassAbility("Monster Slayer", "scalebane", 5));
		classAbilities.add(new ClassAbility("Monster Slayer", "the end is near I", 4));
		classAbilities.add(new ClassAbility("Monster Slayer", "the end is near II", 8));
		classAbilities.add(new ClassAbility("Paladin", "lay on hands", 1));
		classAbilities.add(new ClassAbility("Paladin", "smite the indifferent", 2));
		classAbilities.add(new ClassAbility("Paladin", "stand in judgment I", 2));
		classAbilities.add(new ClassAbility("Paladin", "stand in judgment II", 4));
		classAbilities.add(new ClassAbility("Paladin", "state of grace", 8));
		classAbilities.add(new ClassAbility("Priest", "benediction", 2));
		classAbilities.add(new ClassAbility("Priest", "devout", 5));
		classAbilities.add(new ClassAbility("Priest", "divine intervention", 10));
		classAbilities.add(new ClassAbility("Priest", "rebuke", 2));
		classAbilities.add(new ClassAbility("Priest", "sacred weapon", 2));
		classAbilities.add(new ClassAbility("Priest", "saved! I", 2));
		classAbilities.add(new ClassAbility("Priest", "saved! II", 4));
		classAbilities.add(new ClassAbility("Regent", "true majesty", 8));
		classAbilities.add(new ClassAbility("Rune Knight", "blooding rune", 2));
		classAbilities.add(new ClassAbility("Rune Knight", "rune-carved 2 runes", 2));
		classAbilities.add(new ClassAbility("Rune Knight", "rune-carved 4 runes", 4));
		classAbilities.add(new ClassAbility("Rune Knight", "rune-carved 6 runes", 6));
		classAbilities.add(new ClassAbility("Rune Knight", "rune-carved 8 runes", 8));
		classAbilities.add(new ClassAbility("Rune Knight", "rune-carved 10 runes", 10));
		classAbilities.add(new ClassAbility("Rune Knight", "spell breach", 5));
		classAbilities.add(new ClassAbility("Rune Knight", "spell parry", 2));
		classAbilities.add(new ClassAbility("Rune Knight", "spell strike", 8));
		classAbilities.add(new ClassAbility("Rune Knight", "warcasting I", 2));
		classAbilities.add(new ClassAbility("Rune Knight", "warcasting II", 4));
		classAbilities.add(new ClassAbility("Sage", "assistance I", 1));
		classAbilities.add(new ClassAbility("Sage", "assistance II", 2));
		classAbilities.add(new ClassAbility("Sage", "assistance III", 3));
		classAbilities.add(new ClassAbility("Sage", "best of the best 1/scene", 2));
		classAbilities.add(new ClassAbility("Sage", "best of the best 2/scene", 4));
		classAbilities.add(new ClassAbility("Sage", "best of the best 3/scene", 6));
		classAbilities.add(new ClassAbility("Sage", "best of the best 4/scene", 8));
		classAbilities.add(new ClassAbility("Sage", "best of the best 5/scene", 10));
		classAbilities.add(new ClassAbility("Sage", "best of the best 6/scene", 12));
		classAbilities.add(new ClassAbility("Sage", "best of the best 7/scene", 14));
		classAbilities.add(new ClassAbility("Sage", "best of the best 8/scene", 16));
		classAbilities.add(new ClassAbility("Sage", "best of the best 9/scene", 18));
		classAbilities.add(new ClassAbility("Sage", "serendipity", 5));
		classAbilities.add(new ClassAbility("Sage", "take heart 1/dramatic scene", 5));
		classAbilities.add(new ClassAbility("Sage", "take heart 2/dramatic scene", 10));
		classAbilities.add(new ClassAbility("Scout", "killing blow", 1));
		classAbilities.add(new ClassAbility("Scout", "master tracker 1/session", 2));
		classAbilities.add(new ClassAbility("Scout", "master tracker 2/session", 4));
		classAbilities.add(new ClassAbility("Scout", "overrun", 5));
		classAbilities.add(new ClassAbility("Scout", "rough living I", 2));
		classAbilities.add(new ClassAbility("Scout", "rough living II", 4));
		classAbilities.add(new ClassAbility("Scout", "rough living III", 6));
		classAbilities.add(new ClassAbility("Scout", "rough riding", 1));
		classAbilities.add(new ClassAbility("Scout", "sneak attack 1d6", 2));
		classAbilities.add(new ClassAbility("Scout", "sneak attack 2d6", 4));
		classAbilities.add(new ClassAbility("Scout", "sneak attack 3d6", 6));
		classAbilities.add(new ClassAbility("Scout", "sneak attack 4d6", 8));
		classAbilities.add(new ClassAbility("Scout", "sneak attack 5d6", 10));
		classAbilities.add(new ClassAbility("Scout", "sprint", 2));
		classAbilities.add(new ClassAbility("Scout", "stalker", 2));
		classAbilities.add(new ClassAbility("Scout", "trail signs", 2));
		classAbilities.add(new ClassAbility("Scout", "trophy hunter", 2));
		classAbilities.add(new ClassAbility("Soldier", "armor use I", 2));
		classAbilities.add(new ClassAbility("Soldier", "armor use II", 4));
		classAbilities.add(new ClassAbility("Soldier", "armor use III", 6));
		classAbilities.add(new ClassAbility("Soldier", "armor use IV", 8));
		classAbilities.add(new ClassAbility("Soldier", "armor use V", 10));
		classAbilities.add(new ClassAbility("Soldier", "fortunes of war I", 2));
		classAbilities.add(new ClassAbility("Soldier", "fortunes of war II", 4));
		classAbilities.add(new ClassAbility("Soldier", "fortunes of war III", 6));
		classAbilities.add(new ClassAbility("Soldier", "killer instinct", 1));
		classAbilities.add(new ClassAbility("Soldier", "one in a million", 5));
		classAbilities.add(new ClassAbility("Soldier", "one step ahead", 2));
		classAbilities.add(new ClassAbility("Soldier", "portable cover I", 2));
		classAbilities.add(new ClassAbility("Soldier", "portable cover II", 4));
		classAbilities.add(new ClassAbility("Soldier", "rugged weapons", 2));
		classAbilities.add(new ClassAbility("Spirit-Singer", "song of the future", 8));
		classAbilities.add(new ClassAbility("Spirit-Singer", "song of the past", 5));
		classAbilities.add(new ClassAbility("Swashbuckler", "\"…and one for all!\"", 5));		
		classAbilities.add(new ClassAbility("Swashbuckler", "tally ho!", 2));
		classAbilities.add(new ClassAbility("Wind Knight", "shield of the wind", 4));
	}
}
