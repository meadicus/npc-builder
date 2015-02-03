package uk.co.meadicus.npcbuilder.client.render;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import uk.co.meadicus.npcbuilder.client.attack.Attack;
import uk.co.meadicus.npcbuilder.client.npc.FantasyCraftConfig.FCNPCType;
import uk.co.meadicus.npcbuilder.client.npc.FantasyCraftNPC;
import uk.co.meadicus.npcbuilder.client.npc.Size;
import uk.co.meadicus.npcbuilder.client.npc.SpycraftNPC.SCNPCType;
import uk.co.meadicus.npcbuilder.client.npc.form.Mobility;
import uk.co.meadicus.npcbuilder.client.npc.form.Mobility.MotionType;
import uk.co.meadicus.npcbuilder.client.quality.Quality;
import uk.co.meadicus.npcbuilder.client.skill.Skill;
import uk.co.meadicus.npcbuilder.client.spellcasting.Spellcasting;
import uk.co.meadicus.npcbuilder.client.template.FantasyCraftTemplate;
import uk.co.meadicus.npcbuilder.client.template.FantasyCraftTemplateFactory;
import uk.co.meadicus.npcbuilder.client.template.TemplateApplicator;
import uk.co.meadicus.npcbuilder.client.treasure.Treasure.TreasureType;
import uk.co.meadicus.npcbuilder.client.util.NPCUtils;
import uk.co.meadicus.npcbuilder.client.util.Roman;

public class FantasyCraftNPCRenderer extends NPCRenderer<FantasyCraftNPC> {

	@Override
	public String renderStatBlock(FantasyCraftNPC npc) {
		String block = "<strong>" + renderTitle(npc) + "</strong>";
		
		// attributes
		block += renderAttributes(npc);
		
		// size (footprint, reach)
		block += " SZ " + npc.getSize().toString().charAt(0);
		block += " (" + npc.getFootprintX() + "×" + npc.getFootprintY() + ",";
		block += " Reach " + npc.getReach() + ");";
		
		// mobility
		block += renderSpeed(npc, " Spd");

		// traits
		block += renderTraits(npc);
		
		// skills
		if (npc.getSpellcasting().getGrade() > 0) {
			List<Skill> skills = new ArrayList<Skill>();
			skills.addAll(npc.getSkills());
			Skill spellcastingSkill = new Skill("Spellcasting", npc.getSpellcasting().getGrade());
			skills.add(spellcastingSkill);
			Collections.sort(skills);
			block += " " + renderSkills(skills);
			
			block += renderSpells(npc);
		} else {
			block += " " + renderSkills(npc);
		}
		
		// qualities
		block += " " + renderQualities(npc);
		
		// attacks/weapons
		if (!npc.getAttacks().isEmpty()) {
			block += "<br/>";
			block += "<em class='blockstart'>Attacks/Weapons:</em> ";
			String delim = "";
			for (Attack attack : npc.getAttacks()) {
				block += delim;
				block += attack.render();
				delim = ", ";
			}
		}
		
		// gear
		if (!npc.getMountsAndVehicles().isEmpty()) {
			block += "<br/>";
			block += "<em class='blockstart'>Mounts and Vehicles:</em> ";
			block += npc.getMountsAndVehicles();
		}
		if (!npc.getGear().isEmpty()) {
			block += "<br/>";
			block += "<em class='blockstart'>Gear:</em> ";
			block += npc.getGear();
		}
		
		// treasure
		if (!npc.getTreasure().isEmpty()) {
			block += "<br/>";
			block += "<em class='blockstart'>Treasure:</em> ";
			block += renderTreasure(npc);
		}
		
		return block;
	}


	public String renderTitle(FantasyCraftNPC npc) {
		String title = npc.getName() + " (" + npc.getSize();
		
		// type
		for (FCNPCType type : npc.getType()) {
			title += " " + type;
		}
		
		// mobility
		List<MotionType> motionTypes = npc.getMobility().getOrderedMotionTypes();
		String delim = " ";
		for (MotionType motion : motionTypes) {
			title += delim;
			title += motion;
			delim = "/";
		}
		
		title += " — " + npc.getXp() + " XP):";
		
		return title;
	}
	
	public String renderTreasure(FantasyCraftNPC npc) {
		String output = "";
		String delim = "";
		for (TreasureType ttype : TreasureType.values()) {
			int val = npc.getTreasure().get(ttype);
			if (val > 0) {
				output += delim;
				output += val;
				output += ttype.toString().charAt(0);
				delim = ", ";
			}
		}
		return output;
	}

	public String renderSpeed(FantasyCraftNPC npc, String prefix) {
		String output = "";

		List<MotionType> motionTypes = npc.getMobility().getOrderedMotionTypes();
		if (motionTypes.size() > 0 && motionTypes.get(0) != MotionType.IMMOBILE) {
			output += prefix;
			String delim = "";
			for (MotionType motion : motionTypes) {
				output += delim;
				output += " " + npc.getMobility().getSpeedMap().get(motion);
				output += " ft. ";
				switch (motion) {
				case WALKER:
					output += "ground";
					break;
				case BURROWER:
					output += "burrow";
					break;
				case FLYER:
					output += "flight";
					break;
				case SWIMMER:
					output += "swim";
				default:
					break;
				}
				delim = ", ";
			}
			output += ";";
		}
		return output;
	}
	
	public String renderSpells(FantasyCraftNPC npc) {
		String output = "";
		if (npc.getSpellcasting().getGrade() > 0) {
			output = " Spells: ";
			String delim = "";
			for (String spell : npc.getSpellcasting().getSpells()) {
				output += delim;
				output += spell;
				delim = ", ";
			}
			output += ";";
		}
		return output;
	}

	private String renderAttributes(FantasyCraftNPC npc) {
		String output = "";
		output += " Str " + npc.getStr() + ",";
		output += " Dex " + npc.getDex() + ",";
		output += " Con " + npc.getCon() + ",";
		output += " Int " + npc.getInt() + ",";
		output += " Wis " + npc.getWis() + ",";
		output += " Cha " + npc.getCha() + ";";
		return output;
	}

	public String renderTraits(FantasyCraftNPC npc) {
		String output = "";
		output += " Init " + Roman.toRoman(npc.getInit()) + ";";
		output += " Atk " + Roman.toRoman(npc.getAtk()) + ";";
		output += " Def " + Roman.toRoman(npc.getDef()) + ";";
		output += " Resilience " + Roman.toRoman(npc.getRes()) + ";";
		output += " Health " + Roman.toRoman(npc.getHlth()) + ";";
		output += " Comp " + Roman.toRoman(npc.getComp()) + ";";
				
		return output;
	}

	public void parseStatBlock(FantasyCraftNPC npc, String text) {
		npc.reset();
		
		// compress white space
		text = text.replaceAll("\\s+", " ");
		
		// get title, split by :
		String npcspec = text.substring(0, text.indexOf(':'));
		String bloc = text.substring(text.indexOf(':')+1);
		
		// get name and type
		int lastBracket = npcspec.lastIndexOf('(');
		String name = npcspec.substring(0, lastBracket);
		String spec = npcspec.substring(lastBracket+1);
		
		npc.setName(name);
		
		// parse the type
		List<MotionType> mobilityTypes = parseType(npc, spec);
		
		// split into stat-block, attacks, gear and treasure
		// we do this by cutting them off in order of last to first
		int indexOfTreasure = bloc.toLowerCase().indexOf("treasure:");
		if (indexOfTreasure > 0) {
			String treasure = bloc.substring(indexOfTreasure + 9);
			parseTreasure(npc, treasure);
			bloc = bloc.substring(0, indexOfTreasure);
		}
		int indexOfGear = bloc.toLowerCase().indexOf("gear:");
		if (indexOfGear > 0) {
			String gear = bloc.substring(indexOfGear + 5);
			parseGear(npc, gear);
			bloc = bloc.substring(0, indexOfGear);
		}
		int indexOfMaV = bloc.toLowerCase().indexOf("mounts and vehicles:");
		if (indexOfMaV > 0) {
			String mav = bloc.substring(indexOfMaV + 20);
			parseMaV(npc, mav);
			bloc = bloc.substring(0, indexOfMaV);
		}
		int indexOfAttacks = bloc.toLowerCase().indexOf("attacks/weapons:");
		if (indexOfAttacks > 0) {
			String attacks = bloc.substring(indexOfAttacks + 16);
			parseAttacks(npc, attacks);
			bloc = bloc.substring(0, indexOfAttacks);
		}
		// we are now left with just the stat block
		
		// split by ;
		List<String> infoItems = NPCUtils.bracketAwareSplit(bloc, ';');
		for (String info : infoItems) {
			info = info.trim();
			if (!info.isEmpty()) {
				
				if (startsWithIgnoreCase(info, "str")) {
					parseAttributes(npc, info);
				} else if (startsWithIgnoreCase(info, "init")) {
					npc.setInit(Roman.toInt(readValue(info,"init")));
				} else if (startsWithIgnoreCase(info, "atk")) {
						npc.setAtk(Roman.toInt(readValue(info,"atk")));
				} else if (startsWithIgnoreCase(info, "def")) {
					npc.setDef(Roman.toInt(readValue(info,"def")));
				} else if (startsWithIgnoreCase(info, "resilience") || startsWithIgnoreCase(info, "res")) {
					npc.setRes(Roman.toInt(readValue(info,"resilience","res")));
				} else if (startsWithIgnoreCase(info, "health")) {
					npc.setHlth(Roman.toInt(readValue(info,"health")));
				} else if (startsWithIgnoreCase(info, "competence") || startsWithIgnoreCase(info, "comp")) {
					if (!npc.getType().equals(SCNPCType.ANIMAL)) {
						npc.setComp(Roman.toInt(readValue(info,"competence","comp")));
					}
				} else if (startsWithIgnoreCase(info, "skills")) {
					parseSkills(npc, readValue(info,"skills"));
					// check skills for spellcasting
					checkSpellcastingPostParseSkills(npc);
				} else if (startsWithIgnoreCase(info, "qualities")) {
					parseQualities(npc, readValue(info,"qualities"));
				} else if (startsWithIgnoreCase(info, "spells")) {
					parseSpells(npc, readValue(info,"spells"));
				} else if (startsWithIgnoreCase(info, "size") || startsWithIgnoreCase(info, "sz")) {
					parseSize(npc, readValue(info,"size","sz"));
				} else if (startsWithIgnoreCase(info, "speed") || startsWithIgnoreCase(info, "spd")) {
					parseSpeed(npc, readValue(info,"speed","spd"), mobilityTypes);
				}
			}
		}
		
		// Sort out templates
		while (FantasyCraftTemplateFactory.getInstance().getTemplateNames().contains(firstTemplateWord(npc.getName()))) {
			FantasyCraftTemplate template = FantasyCraftTemplateFactory.getInstance().getTemplate(firstTemplateWord(npc.getName()));
			extractTemplate(npc, template);
		}
	}

	private boolean startsWithIgnoreCase(String info, String string) {
		return info.toLowerCase().startsWith(string.toLowerCase());
	}


	private String readValue(String info, String... prefixes) {
		for (String prefix : prefixes) {
			if (startsWithIgnoreCase(info, prefix)) {
				return readValue(info, prefix);
			}
		}
		return "";
	}
	
	private String readValue(String info, String prefix) {
		String val = info.substring(prefix.length()).trim();
		while(val.startsWith(":")) {
			val = val.substring(1);
		}
		return val;
	}


	private String firstTemplateWord(String text) {
		return text.replaceFirst("([^\\s]*)\\([Tt]\\)\\s.*", "$1").trim();
	}
	
	private void parseSpeed(FantasyCraftNPC npc, String text, List<MotionType> motionTypes) {
		// Create new mobiltiy object with primary type set
		Mobility mobility = new Mobility(motionTypes.get(0));
		
		List<String> speeds = NPCUtils.bracketAwareSplit(text, ',');
		for (int i = 0; i < speeds.size(); ++i) {
			if (motionTypes.size() > i) {
				String speed = speeds.get(i).substring(0,speeds.get(i).indexOf("ft")).trim();
				mobility.setSpeed(motionTypes.get(i), Integer.parseInt(speed));
			}
		}
		npc.setMobility(mobility);
	}

	private void parseSize(FantasyCraftNPC npc, String text) {
		// sz this.size.charAt(0) + " (" + this.footprint[0]+"×"+this.footprint[1]+", Reach "+this.reach+");";
		
		String sizechar = null;
		if (text.indexOf('(') > 0) {
			sizechar = text.substring(0, text.indexOf('(')).trim();
		} else {
			sizechar = text.trim();
		}
		
		// match letter to size
		if (!sizechar.isEmpty()) {
			for (Size size : Size.values()) {
				char sizeChar = size.toString().toLowerCase().charAt(0);
				if (sizechar.toLowerCase().charAt(0) == sizeChar) {
					npc.setSize(size);
				}
			}
		}
		
		// cut size char off the begining
		if (text.indexOf('(') > 0) {
			text = text.substring(text.indexOf("("));
	
			// Split the footprint and reach section into its components
			String parttext = text.replaceFirst("^[^\\d]*((\\d+)[^\\d]*(\\d+))?[^\\d]*(\\d+)[^\\d]*$", "$2,$3,$4");
			String[] parts = parttext.split(",");
			
			try {
				npc.setFootprintX(Integer.parseInt(parts[0]));
				npc.setFootprintY(Integer.parseInt(parts[1]));
			} catch (NumberFormatException nfe) {
				// some NPCS are listed without a footprint
			}
			npc.setReach(Integer.parseInt(parts[2]));
		}
	}

	private void parseAttributes(FantasyCraftNPC npc, String text) {
		//Str 10, Dex 12, Con 10, Int 10, Wis 5, Cha 14
		
		List<String> attrs = NPCUtils.bracketAwareSplit(text, ',');
		for (String attrstat : attrs) {
			String attr = attrstat.substring(0,3).toLowerCase();
			int score = Integer.parseInt(attrstat.substring(3).trim());
			if (attr.equals("str")) {
				npc.setStr(score);
			} else if (attr.equals("dex")) {
				npc.setDex(score);
			} else if (attr.equals("con")) {
				npc.setCon(score);
			} else if (attr.equals("int")) {
				npc.setInt(score);
			} else if (attr.equals("wis")) {
				npc.setWis(score);
			} else if (attr.equals("cha")) {
				npc.setCha(score);
			}
		}
	}

	private void parseSpells(FantasyCraftNPC npc, String text) {		
		// split by commas
		
		List<String> spells = NPCUtils.bracketAwareSplit(text, ',');
		if (npc.getSpellcasting() == null) {
			Spellcasting spellcasting = new Spellcasting();
			spellcasting.setGrade(spells.size());
			npc.setSpellcasting(spellcasting);
		}
		for (String spell : spells) {			
			npc.getSpellcasting().getSpells().add(spell);
		}
	}

	private void parseAttacks(FantasyCraftNPC npc, String attacks) {
		// split into seperate attacks where each fits the pattern
		// title (description).
		List<String> parts = NPCUtils.bracketAwareSplit(attacks, ',');
		for (String part : parts) {			
			Attack attack = Attack.parse(part);
			npc.getAttacks().add(attack);
		}
	}

	private void parseMaV(FantasyCraftNPC npc, String mav) {
		if (!mav.trim().equalsIgnoreCase("none")) {
			npc.setMountsAndVehicles(mav);
		}
	}

	private void parseGear(FantasyCraftNPC npc, String gear) {
		if (!gear.trim().equalsIgnoreCase("none")) {
			npc.setGear(gear);
		}
	}

	private void parseTreasure(FantasyCraftNPC npc, String treasure) {
		// 4C, 2L, 4T
		
		// some NPCs list 'Treasure: None'
		if (treasure.trim().equalsIgnoreCase("none")) {
			return;
		}
		
		List<String> rewards = NPCUtils.bracketAwareSplit(treasure.toLowerCase(), ',');
		for (String reward : rewards) {
			// split the number and code
			char code = reward.charAt(reward.length()-1);
			int number = Integer.parseInt(reward.substring(0, reward.length()-1).trim());
			
			switch (code) {
				//any
				case 'a':
					npc.getTreasure().set(TreasureType.ANY, number);
					break;
				//coin
				case 'c':
					npc.getTreasure().set(TreasureType.COIN, number);
					break;
				//gear
				case 'g':
					npc.getTreasure().set(TreasureType.GEAR, number);
					break;
				//loot
				case 'l':
					npc.getTreasure().set(TreasureType.LOOT, number);
					break;
				//magic
				case 'm':
					npc.getTreasure().set(TreasureType.MAGIC, number);
					break;
				//trophies
				case 't':
					npc.getTreasure().set(TreasureType.TROPHIES, number);
					break;
			}			
		}
	}

	protected List<MotionType> parseType(FantasyCraftNPC npc, String text) {

		// remove xp from text
		text = text.replace("[-—].*$","");
		
		// split by white space
		String[] words = text.split("\\s+");		
		
		// first word is size
		npc.setSize(Size.fromString(words[0]));
		
		// second or more could be type
		npc.getType().clear();
		int wordIndex = 1;
		String word = words[wordIndex];
		while (FCNPCType.fromString(word) != null) {
			npc.getType().add(FCNPCType.fromString(word));
			++wordIndex;
			word = words[wordIndex];
		}	
		
		// third word is mobility type, potentially split by '/'
		String[] mobilityTypes = word.split("/");
		
		List<MotionType> motionTypes = new ArrayList<MotionType>();
		for (String mobilityType : mobilityTypes) {
			MotionType motionType = MotionType.fromString(mobilityType.trim());
			if (motionType != null) {
				motionTypes.add(motionType);
			}
		}
		return motionTypes;
	}

	/**
	 * After the skills have been parsed, this checks to a skill
	 * called Spellcasting. If it finds one it removes it and
	 * sets the spellcasting quality.
	 */
	private void checkSpellcastingPostParseSkills(FantasyCraftNPC npc) {
		Iterator<Skill> ittr = npc.getSkills().iterator();
		while (ittr.hasNext()) {
			Skill skill = ittr.next();
			if (skill.getName().equalsIgnoreCase("spellcasting")) {
				int rating = skill.getRating();
				if (npc.getSpellcasting() == null) {
					npc.setSpellcasting(new Spellcasting());				
				}
				npc.getSpellcasting().setGrade(rating);
				ittr.remove();
			}
		}		
	}
	
	/**
	 * Reverses the effects of the template on the NPC. Also adds the template to the NPCs list
	 * of templates.
	 * 
	 * @param npc
	 * @param template
	 */
	private void extractTemplate(FantasyCraftNPC npc, FantasyCraftTemplate template) {
		
		// name
		npc.setName(npc.getName().replaceFirst(template.getName() + "\\([Tt]\\)","").trim());
		// attributes
		if (template.getAttrMods() != null) {
			npc.setStr(npc.getStr() - template.getAttrMods()[0]);
			npc.setDex(npc.getDex() - template.getAttrMods()[1]);
			npc.setCon(npc.getCon() - template.getAttrMods()[2]);
			npc.setInt(npc.getInt() - template.getAttrMods()[3]);
			npc.setWis(npc.getWis() - template.getAttrMods()[4]);
			npc.setCha(npc.getCha() - template.getAttrMods()[5]);
		}
		// stats
		if (template.getStatMods() != null) { // init, atk, def, res, hlth, comp
			npc.setInit(npc.getInit() - template.getStatMods()[0]);
			npc.setAtk(npc.getAtk() - template.getStatMods()[1]);
			npc.setDef(npc.getDef() - template.getStatMods()[2]);
			npc.setRes(npc.getRes() - template.getStatMods()[3]);
			npc.setHlth(npc.getHlth() - template.getStatMods()[4]);
			npc.setComp(npc.getComp() - template.getStatMods()[5]);
		}
		// size
		if (template.getSizeMod() != 0) {
			Size preSize = npc.getSize();
			npc.setSize(npc.getSize().resize(-template.getSizeMod()));
			// footprint
			npc.setFootprintX(TemplateApplicator.updatefootprint(preSize, npc.getFootprintX(), npc.getSize()));
			npc.setFootprintY(TemplateApplicator.updatefootprint(preSize, npc.getFootprintY(), npc.getSize()));
		}
		// reach
		npc.setReach(npc.getReach() - template.getReachMod());
		// skills
		if (template.getSkills() != null) {
			List<Skill> skills = TemplateApplicator.removeSkills(npc.getSkills(), template.getSkills());
			npc.getSkills().clear();
			npc.getSkills().addAll(skills);
		}
		// qualities
		if (template.getQualities() != null) {
			Map<String, Quality> qualities = TemplateApplicator.removeQualities(npc.getQualities(), template.getQualities());
			npc.getQualities().clear();
			npc.getQualities().putAll(qualities);
		}
		// types
		if (template.getTypes() != null) {
			Set<FCNPCType> types = TemplateApplicator.removeTypes(npc.getType(), template.getTypes());
			npc.getType().clear();
			npc.getType().addAll(types);
		}
		// attacks
		if (template.getAttacks() != null || template.getAttacksMod() != 0) {
			List<Attack> attacks = TemplateApplicator.removeAttacks(npc.getAttacks(), template.getAttacks(), template.getAttacksMod());
			npc.getAttacks().clear();
			npc.getAttacks().addAll(attacks);
		}
		// mobility
		if (template.getMobility() != null || template.getSpeedMod() != 0) {
			Mobility mobility = TemplateApplicator.removeMobility(npc.getMobility(), template.getMobility(), template.getSpeedMod());
			npc.setMobility(mobility);
		}
		
		npc.getTemplates().add(template);
	}
}
