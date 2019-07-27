package uk.co.meadicus.npcbuilder.client.render;

import java.util.ArrayList;
import java.util.List;

import uk.co.meadicus.npcbuilder.client.npc.NPC;
import uk.co.meadicus.npcbuilder.client.quality.Quality;
import uk.co.meadicus.npcbuilder.client.skill.Skill;
import uk.co.meadicus.npcbuilder.client.util.NPCUtils;
import uk.co.meadicus.npcbuilder.client.util.Roman;

public abstract class NPCRenderer<NPCType extends NPC> {

	public abstract String renderStatBlock(NPCType npc);

	public abstract void parseStatBlock(NPCType npc, String statBlock);
	
	public abstract String renderTitle(NPCType npc);
	
	public String renderSkills(NPCType npc) {
		return renderSkills(npc.getSkills());
	}
	
	protected String renderSkills(List<Skill> skills) {
		String output = "";
		if (!skills.isEmpty()) {
			String prefix = "Skills: ";
			for (Skill skill : skills) {
				output += prefix;
				output += skill.getName() + " " + Roman.toRoman(skill.getRating());
				prefix = ", ";
			}
			output += ";";
		}
		return output;
	}

	public String renderQualities(NPCType npc) {
		String output = "";
		
		if (!npc.getQualities().isEmpty()) {
			String delimiter = "Qualities: ";
			for (String qualityName : npc.getQualities().keySet()) {
				Quality quality = npc.getQualities().get(qualityName);
				output += delimiter;
				output += quality.render();
				delimiter = ", ";
			}
			output += ".";
		}
		
		return output;
	}

	protected void parseSkills(NPCType npc, String text) {

		npc.getSkills().clear();

		if (!text.trim().equalsIgnoreCase("none")) {
			
			List<String> listings;
			if (text.indexOf(',') > 0) {
				listings = NPCUtils.bracketAwareSplit(text, ',');
			} else {
				listings = new ArrayList<String>(1);
				listings.add(text);
			}
			
			for (String listing : listings) {
				String[] breakdown = listing.trim().split("\\s");
				// separate values
				String skillname = breakdown[0];
				String skillrating = breakdown[1];
				if (breakdown.length > 2) {
					skillrating = breakdown[breakdown.length-1];
					for (int j = 1; j < breakdown.length-1; ++j) {
						skillname += " " + breakdown[j];
					}
				}
				// make skill
				Skill skill = new Skill(skillname, Roman.toInt(skillrating));
				// add to array
				npc.getSkills().add(skill);
			}
		}
	}
	
	protected void parseQualities(NPCType npc, String text) {
		npc.getQualities().clear();
		
		if (!text.trim().equalsIgnoreCase("none")) {
			List<String> qualities = NPCUtils.bracketAwareSplit(text, ',');
			
			for (String qualityText : qualities) {
				// parse the quality
				Quality thequality = npc.getConfig().getQualityFactory().parseQuality(qualityText.trim());
				// add to array
				if (thequality != null) {
					npc.getQualities().put(thequality.getIdentifyingName(), thequality);
				}
			}
		}
	}
}
