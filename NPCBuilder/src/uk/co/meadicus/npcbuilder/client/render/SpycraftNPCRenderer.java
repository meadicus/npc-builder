package uk.co.meadicus.npcbuilder.client.render;

import java.util.ArrayList;
import java.util.List;

import uk.co.meadicus.npcbuilder.client.gear.GearPick;
import uk.co.meadicus.npcbuilder.client.npc.SpycraftNPC;
import uk.co.meadicus.npcbuilder.client.npc.SpycraftNPC.SCNPCType;
import uk.co.meadicus.npcbuilder.client.util.NPCUtils;
import uk.co.meadicus.npcbuilder.client.util.Roman;

public class SpycraftNPCRenderer extends NPCRenderer<SpycraftNPC> {

	@Override
	public String renderStatBlock(SpycraftNPC npc) {
		String block = "<strong>" + renderTitle(npc) + "</strong>";
				
		// traits
		block += renderTraits(npc);
		
		// skills
		block += " " + renderSkills(npc);
		
		// size and speed
		block += " SZ " + npc.getSize().toString().toUpperCase().charAt(0) + ";";
		block += " Spd " + npc.getSpeed() + " ft;";
		
		// wealth
		if(npc.getType() != SCNPCType.ANIMAL) {
			block += " Wealth: " + Roman.toRoman(npc.getWealth()) + ";";
		}
		
		// weapons
		block += " Weapons:";
		block += this.renderGearPicks(npc.getWeapons());
		block += ";";
		//gear
		block += " Gear:";
		block += this.renderGearPicks(npc.getGear());
		block += ";";
		//vehicle
		block += " Vehicle:";
		block += this.renderGearPicks(npc.getVehicles());
		block += ";";	
		
		// qualities
		block += " " + renderQualities(npc);
				
		return block;
	}

	public String renderTitle(SpycraftNPC npc) {
		return npc.getName() + " (" + npc.getType() + " " + npc.getXp() + " XP):";
	}
	
	public String renderTraits(SpycraftNPC npc) {
		String output = "";
		output += " Init " + Roman.toRoman(npc.getInit()) + ";";
		output += " Atk " + Roman.toRoman(npc.getAtk()) + ";";
		output += " Def " + Roman.toRoman(npc.getDef()) + ";";
		output += " Resilience " + Roman.toRoman(npc.getRes()) + ";";
		if (npc.getType() == SCNPCType.SPECIAL) {
			output += " v/wp: " + Roman.toRoman(npc.getHlth()) + ";";
		} else {
			output += " Damage Save: " + Roman.toRoman(npc.getHlth()) + ";";
		}
		if (npc.getType() != SCNPCType.ANIMAL) {			
			output += " Competence: " + Roman.toRoman(npc.getComp()) + ";";
		}
		
		return output;
	}
	
	public String renderGearPicks(List<GearPick> picks) {
		String output = "";
		
		if (picks.isEmpty()) {
			output += " None";
		} else {
			// Picks of the same calibre are outputted together
			
			String delimeter = " ";
			
			List<GearPick> calibreList = new ArrayList<GearPick>();
			int calibreQuantity = 0;
			
			for (int i = 0; i < picks.size(); ++i) {
				GearPick pick = picks.get(i);
				
				// Add this pick to the totals for the current calibre
				calibreList.add(pick);
				calibreQuantity += pick.getQuantity();
				
				// If the next item is of the same calibre, skip to is
				if (i < (picks.size() - 1) && picks.get(i+1).getCalibre() == pick.getCalibre()) {
					continue;
				}
								
				output += delimeter;
				output += calibreQuantity + " × Caliber " + Roman.toRoman(pick.getCalibre());
				
				if (calibreList.size() > 1) {
					output += " (";
					String descriptionDelimiter = "";
					for (GearPick descriptionPick : calibreList) {
						output += descriptionDelimiter;
						String description = descriptionPick.getDescription();
						if (description.isEmpty()) {
							description = "?";
						}
						output += description += "(×" + descriptionPick.getQuantity() + ")";
						descriptionDelimiter = ", ";
					}
					output += ")";
				} else {
					// just one pick type
					if (!pick.getDescription().isEmpty()) {
						output += " (" + pick.getDescription() + ")";
					}
				}
				
				// reset counters for next calibre grouping
				calibreList.clear();
				calibreQuantity = 0;
				
				delimeter = ", ";
			}
		}
		
		return output;
	}
	
	public void parseStatBlock(SpycraftNPC npc, String text) {
		npc.reset();

		// compress white space
		text = text.replaceAll("\\s+", " ");
		
		// get title, split by :
		String npcspec = text.substring(0, text.indexOf(':'));
		String bloc = text.substring(text.indexOf(':')+1);
		
		// get name and type
		String[] title = npcspec.split("\\(", 2);
		
		npc.setName(title[0].trim());

		String[] type = title[1].toLowerCase().split("\\s");
		String typetext = type[0].trim();
		
		if (typetext.equalsIgnoreCase(SCNPCType.ANIMAL.toString())) {
			npc.setType(SCNPCType.ANIMAL);
		} else if (typetext.equalsIgnoreCase(SCNPCType.SPECIAL.toString())) {
			npc.setType(SCNPCType.SPECIAL);
		} else {
			npc.setType(SCNPCType.STANDARD);
		}
				
		// split by ;
		List<String> infoItems = NPCUtils.bracketAwareSplit(bloc, ';');
		for (String info : infoItems) {
			info = info.trim();
			if (!info.isEmpty()) {
				// seperate name from value(s)
				String[] datum;
				if (info.indexOf(':') > 0) {
					datum = info.split(":", 2);
				} else {
					datum = info.split("\\s", 2);
				}
				if (datum.length > 0) {
					datum[1] = datum[1].trim();
					// get quality category, remove any trailing non alphabetic characters
					String qcat = datum[0].trim().replaceAll("^\\W+|\\W+$", "").toLowerCase();
					if (qcat.equals("init")) {
						npc.setInit(Roman.toInt(datum[1]));
					} else if (qcat.equals("atk")) {
						npc.setAtk(Roman.toInt(datum[1]));
					} else if (qcat.equals("def")) {
						npc.setDef(Roman.toInt(datum[1]));
					} else if (qcat.equals("resilience")) {
						npc.setRes(Roman.toInt(datum[1]));
					} else if (qcat.equals("damage save")) {
						npc.setHlth(Roman.toInt(datum[1]));
					} else if (qcat.equals("v/wp")) {
						npc.setHlth(Roman.toInt(datum[1]));
					} else if (qcat.equals("competence")) {
						if (!npc.getType().equals(SCNPCType.ANIMAL)) {
							npc.setComp(Roman.toInt(datum[1]));
						}
					} else if (qcat.equals("wealth")) {
						if (!npc.getType().equals(SCNPCType.ANIMAL)) {
							npc.setWealth(Roman.toInt(datum[1]));
						}
					} else if (qcat.equals("weapons")) {
						this.parseGear(npc.getWeapons(), datum[1]);
					} else if (qcat.equals("gear")) {
						this.parseGear(npc.getGear(), datum[1]);
					} else if (qcat.equals("vehicle")) {
						this.parseGear(npc.getVehicles(), datum[1]);
					} else if (qcat.equals("skills")) {
						parseSkills(npc, datum[1]);
					} else if (qcat.equals("qualities")) {
						parseQualities(npc, datum[1]);
					}
				}
			}
		}
	}
	
	protected void parseGear(List<GearPick> pickList, String text) {
		pickList.clear();
		
		text = text.trim();
		
		if (!text.equalsIgnoreCase("none")) {
			
			// replace commas between calibre listings with an escape sequence
			text = text.replaceAll("(\\d+\\s+×\\s+Caliber\\s+[IV]+),", "$1;~;");
			text = text.replaceAll(",(\\s+\\d+\\s+×\\s+Caliber\\s+[IV]+)", ";~;$1");			
			
			// split on escape sequence
			String[] listings;
			if (text.indexOf(";~;") > 0) {
				listings = text.split(";~;");
			} else {
				listings = new String[1];
				listings[0] = text;
			}
			
			// loop through each listing, each one will be at its own calibre
			for (String listing : listings) {				
				// get the quantity of picks at this calibre
				String quantityAndCalibre = listing.trim();
				String descriptions = "";
				if (listing.indexOf("(") > 0) {
					quantityAndCalibre = listing.substring(0, listing.indexOf("("));
					quantityAndCalibre = quantityAndCalibre.trim();
					descriptions = listing.substring(listing.indexOf("(")+1);
					// remove last closing bracket from descriptions
					descriptions = descriptions.trim();
					descriptions = descriptions.replaceFirst("\\)$", "");
					descriptions = descriptions.trim();
				}						
				
				String[] breakdown = quantityAndCalibre.split("\\s");
				// get quantity
				int quantity = Integer.parseInt(breakdown[0], 10);
				// get caliber
				int calibre = Roman.toInt(breakdown[breakdown.length - 1]);
				// get any descriptions
				if (descriptions.isEmpty()) {// no descriptions
					GearPick pick = new GearPick("", calibre, quantity);
					pickList.add(pick);
				} else { // we have descriptions				
					List<String> desarray = NPCUtils.bracketAwareSplit(descriptions, ',');				
					if (desarray.size() == 1) { // only one description									
						GearPick pick = null;
						pick = new GearPick(desarray.get(0), calibre, quantity);
						pickList.add(pick);
					} else { // more than one description
						for (String description : desarray) {
														
							int pickquantity = 1;
							
							// does the description contain a multiplier
							if (description.indexOf("(") > 0) {
								String desquantity = description.substring(description.indexOf("("));
								desquantity = desquantity.replaceAll("[\\(\\)×]", "");
								pickquantity = Integer.parseInt(desquantity);
								description = description.substring(0, description.indexOf("("));
								description = description.trim();								
							}
							
							// make pick and add to array
							GearPick pick = new GearPick(description, calibre, pickquantity);
							pickList.add(pick);
						}
					}
				}
			}
		}			
	}
	
}
