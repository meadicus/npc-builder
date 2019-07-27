package uk.co.meadicus.npcbuilder.client.template;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import uk.co.meadicus.npcbuilder.client.attack.Attack;
import uk.co.meadicus.npcbuilder.client.npc.FantasyCraftConfig.FCNPCType;
import uk.co.meadicus.npcbuilder.client.npc.Size;
import uk.co.meadicus.npcbuilder.client.npc.form.Mobility;
import uk.co.meadicus.npcbuilder.client.npc.form.Mobility.MotionType;
import uk.co.meadicus.npcbuilder.client.quality.FantasyCraftQualityFactory;
import uk.co.meadicus.npcbuilder.client.quality.Quality;
import uk.co.meadicus.npcbuilder.client.skill.Skill;
import uk.co.meadicus.npcbuilder.client.util.NPCUtils;

public class FantasyCraftTemplateFactory {

	private static FantasyCraftTemplateFactory singleton;
	
	private final Map<String, List<String>> templateNames = new LinkedHashMap<String, List<String>>();	
	private final Map<String, FantasyCraftTemplate> templates = new LinkedHashMap<String, FantasyCraftTemplate>();
	
	private FantasyCraftTemplateFactory() {
		templateNames.put("Rogue", new ArrayList<String>());
		templateNames.get("Rogue").add("Drake");
		templateNames.get("Rogue").add("Dwarf");
		templateNames.get("Rogue").add("Elf");
		templateNames.get("Rogue").add("Giant");
		templateNames.get("Rogue").add("Goblin");
		templateNames.get("Rogue").add("Ogre");
		templateNames.get("Rogue").add("Orc");
		templateNames.get("Rogue").add("Pech");
		templateNames.get("Rogue").add("Rootwalker");
		templateNames.get("Rogue").add("Saurian");
		templateNames.get("Rogue").add("Unborn");
		templateNames.put("Monster", new ArrayList<String>());
		templateNames.get("Monster").add("Alpha");
		templateNames.get("Monster").add("Ancient");
		templateNames.get("Monster").add("Clockwork");
		templateNames.get("Monster").add("Dire");
		templateNames.get("Monster").add("Ghostly");
		templateNames.get("Monster").add("Heavenly");
		templateNames.get("Monster").add("Infernal");
		templateNames.get("Monster").add("Immature");
		templateNames.get("Monster").add("Kaiju");
		templateNames.get("Monster").add("Lich");
		templateNames.get("Monster").add("Predatory");
		templateNames.get("Monster").add("Risen");
		templateNames.get("Monster").add("Skeletal");
		templateNames.get("Monster").add("Vampiric");
	};
	
	public static FantasyCraftTemplateFactory getInstance() {
		if (singleton == null) {
			singleton = new FantasyCraftTemplateFactory();
		}
		return singleton;
	}
	
	public Map<String, List<String>> getTemplatesMap() {
		return this.templateNames;
	}
	
	public List<String> getTemplateNames() {
		List<String> templateNames = new ArrayList<String>();
		for (List<String> list : this.templateNames.values()) {
			templateNames.addAll(list);
		}
		return templateNames;
	}
	
	
	
	public FantasyCraftTemplate getTemplate(String name) {
		if (!templates.containsKey(name)) {
			templates.put(name, createTemplate(name));
		}
		return templates.get(name);
	}

	private FantasyCraftTemplate createTemplate(String name) {
		FantasyCraftTemplate template = null;
		
		if (name.equals("Alpha")) {
			int[] attMods = {2, 2, 0, 0, 0, 0}; // str, dex, con, int, wis, cha
			int[] statMods = {1, 1, 1, 0, 1, 1}; // init, atk, def, res, hlth, comp
			List<Quality> qualities = createQualities("class ability (Captain: battle planning I), treacherous, veteran II");
			template = new FantasyCraftTemplate(name, attMods, statMods, null, qualities, null, null, 0, 0, null, 0, 0, 20);
		} else if (name.equals("Ancient")) {
			int[] attMods = {-2, -2, -1, 4, 4, 0}; // str, dex, con, int, wis, cha
			int[] statMods = {0, 0, 0, 0, 0, 3}; // init, atk, def, res, hlth, comp
			List<Quality> qualities = createQualities("burden of ages, cagey II, class ability (Courtier: master plan I, never outdone; Keeper: bright idea II), fatal falls");
			template = new FantasyCraftTemplate(name, attMods, statMods, null, qualities, null, null, 0, 0, null, 0, 0, 20);
		} else if (name.equals("Clockwork")) {
			Set<FCNPCType> types = new HashSet<FCNPCType>();
			types.add(FCNPCType.CONSTRUCT);
			List<Quality> qualities = createQualities("damage reduction 3");
			List<Attack> attacks = createAttacks("Slam III");
			template = new FantasyCraftTemplate(name, null, null, null, qualities, attacks, types, 0, 0, null, 0, 0, 20);
		} else if (name.equals("Dire")) {
			int[] attMods = {4, 0, 4, 0, 0, 0}; // str, dex, con, int, wis, cha
			List<Quality> qualities = createQualities("tough II, unnerving");
			template = new FantasyCraftTemplate(name, attMods, null, null, qualities, null, null, 0, 2, null, 0, 0, 20);
		} else if (name.equals("Ghostly")) {
			Set<FCNPCType> types = new HashSet<FCNPCType>();
			types.add(FCNPCType.SPIRIT);
			types.add(FCNPCType.UNDEAD);
			List<Quality> qualities = createQualities("everlasting");
			List<Attack> attacks = createAttacks("Ghostly Wail (shaking III: 40 ft. aura), Shadow of Death (draining, soul III)");
			template = new FantasyCraftTemplate(name, null, null, null, qualities, attacks, types, 0, 0, null, 0, 0, 40);
		} else if (name.equals("Heavenly")) {
			Set<FCNPCType> types = new HashSet<FCNPCType>();
			types.add(FCNPCType.OUTSIDER);
			int[] attMods = {0, 0, 0, 0, 2, 2}; // str, dex, con, int, wis, cha
			List<Quality> qualities = createQualities("devoted (Good II), natural spell (Bless, Divine Favor)");
			template = new FantasyCraftTemplate(name, attMods, null, null, qualities, null, types, 0, 0, null, 0, 0, 10);
		} else if (name.equals("Infernal")) {
			Set<FCNPCType> types = new HashSet<FCNPCType>();
			types.add(FCNPCType.OUTSIDER);
			int[] attMods = {2, 0, 0, 2, 0, 0}; // str, dex, con, int, wis, cha
			List<Quality> qualities = createQualities("devoted (Evil II), natural spell (Command I, Disguise Self )");
			template = new FantasyCraftTemplate(name, attMods, null, null, qualities, null, types, 0, 0, null, 0, 0, 10);
		} else if (name.equals("Immature")) {
			int[] statMods = {0, -2, -2, -2, -2, -2}; // init, atk, def, res, hlth, comp
			template = new FantasyCraftTemplate(name, null, statMods, null, null, null, null, -2, -2, null, -2, 0, -20);
		} else if (name.equals("Kaiju")) {
			int[] attMods = {5, 0, 5, 0, 0, 0}; // str, dex, con, int, wis, cha
			List<Quality> qualities = createQualities("clumsy, condition immunity (frightened), contagion immunity, damage immunity (stress, subdual), damage reduction 5, fearsome, frenzy II, knockback, lumbering, menacing threat, monsterous defense III, never outnumbered, tough V, unnerving, veteran V");
			List<Attack> attacks = createAttacks("Trample V (armour piercing 10)");
			template = new FantasyCraftTemplate(name, attMods, null, null, qualities, attacks, null, 0, 0, Size.ENORMOUS, 4, 0, 125);
		} else if (name.equals("Lich")) {
			Set<FCNPCType> types = new HashSet<FCNPCType>();
			types.add(FCNPCType.UNDEAD);
			int[] attMods = {0, 0, 0, 2, 2, 2}; // str, dex, con, int, wis, cha
			int[] statMods = {0, 0, 0, 0, 2, 0}; // init, atk, def, res, hlth, comp
			List<Quality> qualities = createQualities("condition immunity (enraged, fatigued, frightened, shaken), damage immunity (cold, electrical), damage reduction 5, dread, everlasting, turn immunity.");
			List<Attack> attacks = createAttacks("Lich Touch (paralyzing III)");
			template = new FantasyCraftTemplate(name, attMods, statMods, null, qualities, attacks, types, 0, 0, null, 0, 0, 70);
		} else if (name.equals("Predatory")) {
			List<Skill> skills = new ArrayList<Skill>();
			skills.add(new Skill("Tactics", 5));
			List<Quality> qualities = createQualities("feat (Coordinated Attack, Misdirection Basics, Wolf Pack Basics, Wolf Pack Mastery), swarm.");
			template = new FantasyCraftTemplate(name, null, null, skills, qualities, null, null, 0, 0, null, 0, 0, 15);
		} else if (name.equals("Risen")) {
			Set<FCNPCType> types = new HashSet<FCNPCType>();
			types.add(FCNPCType.UNDEAD);
			int[] statMods = {-2, 0, 0, 2, 0, -2}; // init, atk, def, res, hlth, comp
			List<Quality> qualities = createQualities("devour, monsterous defense I, shambling");
			template = new FantasyCraftTemplate(name, null, statMods, null, qualities, null, types, 0, 0, null, 0, 0, 5);
		} else if (name.equals("Skeletal")) {
			Set<FCNPCType> types = new HashSet<FCNPCType>();
			types.add(FCNPCType.UNDEAD);
			List<Quality> qualities = createQualities("achilles heel (blunt), damage defiance (edged), damage immunity (bows), ferocity, tricky (Relentless Attack)");
			template = new FantasyCraftTemplate(name, null, null, null, qualities, null, types, 0, 0, null, 0, 0, 15);
		} else if (name.equals("Vampiric")) {
			Set<FCNPCType> types = new HashSet<FCNPCType>();
			types.add(FCNPCType.UNDEAD);
			int[] attMods = {2, 0, 0, 0, 2, 2}; // str, dex, con, int, wis, cha
			int[] statMods = {0, 0,2, 0, 2, 0}; // init, atk, def, res, hlth, comp
			List<Quality> qualities = createQualities("achilles heel (divine, fire, flash), beguiling, damage defiance (cold), damage reduction 3, darkvision II, fast healing, killing conversion, light-sensitive, nocturnal, shapeshifter I, superior climber II");
			List<Attack> attacks = createAttacks("Bite I, Drain Blood (draining, life I: linked to attack bite)");
			template = new FantasyCraftTemplate(name, attMods, statMods, null, qualities, attacks, types, 0, 0, null, 0, 0, 45);
		} else if (name.equals("Drake")) {
			Mobility mobility = new Mobility(MotionType.FLYER, 40);
			List<Quality> qualities = createQualities("cold-blooded");
			List<Attack> attacks = createAttacks("Fire Breath (damage II: beam 20ft. range; damage type: fire), Bite II, Claw II");
			template = new FantasyCraftTemplate(name, null, null, qualities, attacks, FCNPCType.BEAST, 0, 0, Size.LARGE, 0, 0, mobility, 14);			
		} else if (name.equals("Dwarf")) {
			int[] attMods = {0, 0, 2, 0, 0, 0}; // str, dex, con, int, wis, cha
			List<Quality> qualities = createQualities("damage reduction 2, darkvision I, improved stability");
			template = new FantasyCraftTemplate(name, attMods, null, null, qualities, null, null, 0, 0, null, 0, -10, 11);
		} else if (name.equals("Elf")) {
			int[] attMods = {0, 0, 0, 0, 2, 0}; // str, dex, con, int, wis, cha
			List<Quality> qualities = createQualities("attractive I, burden of ages, improved sense (hearing, sight)");
			template = new FantasyCraftTemplate(name, attMods, null, qualities, null, FCNPCType.FEY, 0, 0, null, 0, 10, 2);
		} else if (name.equals("Giant")) {
			List<Quality> qualities = createQualities("improved stability");
			List<Attack> attacks = createAttacks("Trample I");
			template = new FantasyCraftTemplate(name, null, null, null, qualities, attacks, null, 0, 0, Size.LARGE, 0, 20, 7);
		} else if (name.equals("Goblin")) {
			int[] statMods = {0, 0, 0, 0, 1, 0}; // init, atk, def, res, hlth, comp
			List<Quality> qualities = createQualities("dark vision I, feat (Ambush Basics). light-sensitive");
			template = new FantasyCraftTemplate(name, null, statMods, null, qualities, null, null, 0, 0, Size.SMALL, 0, 0, 2);
		} else if (name.equals("Ogre")) {
			int[] attMods = {0, 0, 2, 0, 0, 0}; // str, dex, con, int, wis, cha
			List<Quality> qualities = createQualities("tough I");
			template = new FantasyCraftTemplate(name, attMods, null, null, qualities, null, null, 0, 0, Size.LARGE, 0, 0, 7);
		} else if (name.equals("Orc")) {
			int[] attMods = {2, 0, 0, 0, 0, 0}; // str, dex, con, int, wis, cha
			List<Quality> qualities = createQualities("always ready, grueling combatant, light-sensitive");
			template = new FantasyCraftTemplate(name, attMods, null, null, qualities, null, null, 0, 0, null, 0, 0, 4);
		} else if (name.equals("Pech")) {
			int[] attMods = {0, 2, 0, 0, 0, 0}; // str, dex, con, int, wis, cha
			template = new FantasyCraftTemplate(name, attMods, null, null, null, null, null, 0, 0, Size.SMALL, 0, 0, 2);
		} else if (name.equals("Rootwalker")) {
			List<Quality> qualities = createQualities("achilles heel (fire), chameleon I (forest/jungle), condition immunity (bleeding), damage reduction I, light sleeper, lumbering");
			template = new FantasyCraftTemplate(name, null, null, qualities, null, FCNPCType.PLANT, 0, 0, Size.LARGE, 0, 0, 11);
		} else if (name.equals("Saurian")) {
			int[] attMods = {0, 2, 0, 0, 0, 0}; // str, dex, con, int, wis, cha
			List<Quality> qualities = createQualities("aquatic I, cold-blooded, darkvision I");
			List<Attack> attacks = createAttacks("Tail slap I (reach 1), Bite I");
			template = new FantasyCraftTemplate(name, attMods, null, null, qualities, attacks, null, 0, 0, null, 0, 0, 6);
		} else if (name.equals("Unborn")) {
			int[] statMods = {0, 0, 1, 1, 1, 0}; // init, atk, def, res, hlth, comp
			int[] attMods = {0, 0, 0, 0, 0, -2}; // str, dex, con, int, wis, cha
			List<Quality> qualities = createQualities("achilles heel (electrical), lumbering");
			template = new FantasyCraftTemplate(name, attMods, statMods, qualities, null, FCNPCType.CONSTRUCT, 0, 0, null, 0, -10, 4);
		}
		
		return template;
	}
	
	private List<Quality> createQualities(String quals) {
		List<Quality> qualities = new ArrayList<Quality>();
		// split qualities by bracket aware comma
		List<String> items = NPCUtils.bracketAwareSplit(quals, ',');
		// parse each quality one by one
		for (String item : items) {
			Quality quality = FantasyCraftQualityFactory.getInstance().parseQuality(item);
			qualities.add(quality);
		}
		return qualities;
	}
	
	private List<Attack> createAttacks(String atks) {
		List<Attack> attacks = new ArrayList<Attack>();
		List<String> parts = NPCUtils.bracketAwareSplit(atks, ',');
		for (String part : parts) {			
			Attack attack = Attack.parse(part);
			attacks.add(attack);
		}
		return attacks;
	}
}
