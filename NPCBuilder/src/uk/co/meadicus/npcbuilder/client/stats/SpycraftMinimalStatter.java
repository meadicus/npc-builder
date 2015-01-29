package uk.co.meadicus.npcbuilder.client.stats;

import java.util.List;

import uk.co.meadicus.npcbuilder.client.gear.GearPick;
import uk.co.meadicus.npcbuilder.client.npc.NPC;
import uk.co.meadicus.npcbuilder.client.npc.SpycraftConfig;
import uk.co.meadicus.npcbuilder.client.npc.SpycraftNPC;
import uk.co.meadicus.npcbuilder.client.npc.SpycraftNPC.SCNPCType;
import uk.co.meadicus.npcbuilder.client.render.SpycraftNPCRenderer;
import uk.co.meadicus.npcbuilder.client.util.Roman;

public class SpycraftMinimalStatter extends NPCStatter<SpycraftNPC> {

	public SpycraftMinimalStatter(SpycraftNPCRenderer renderer) {
		super(renderer);
	}

	@Override
	public String statNPC(NPC npc, int threatLevel) {
		// render stats
		if (npc instanceof SpycraftNPC) {
			return renderStats((SpycraftNPC)npc, threatLevel);
		} else {
			return "";
		}
	}
	
	public String renderStats(SpycraftNPC npc, int threatLevel) {
		SpycraftConfig config = npc.getConfig();
		
		// Title
		String output = "<strong>" + getRenderer().renderTitle(npc) + " TL: " + threatLevel + "</strong>";
		output += "<br/>";
		
		// Stats
		output += renderStats(npc, threatLevel, config);
		output += "<br/>";			
		
		// Attributes
		output += "Str: " + renderAttribute(npc.getStr()) + ";";
		output += " Dex: " + renderAttribute(npc.getDex()) + ";";
		output += " Con: " + renderAttribute(npc.getCon()) + ";";
		output += " Int: " + renderAttribute(npc.getInt()) + ";";
		output += " Wis: " + renderAttribute(npc.getWis()) + ";";
		output += " Cha: " + renderAttribute(npc.getCha()) + ";";
		// size and speed
		output += " SZ " + npc.getSize().toString().toUpperCase().charAt(0) + ";";
		output += " Spd " + npc.getSpeed() + " ft;";
		output += "<br/>";
		
		// Skills
		if (!npc.getSkills().isEmpty()) {
			output += "<em class='blockstart'>Skills:</em> ";
			output += renderSkills(npc, threatLevel, config);
			output += "<br/>";
		}
		
		// Qualities
		if (!npc.getQualities().isEmpty()) {
			output += getRenderer().renderQualities(npc);
			output += "<br/>";
		}
		
		// Weapons
		output += renderPicks(npc.getWeapons(), "Weapon");
		// Vehicle
		output += renderPicks(npc.getVehicles(), "Vehicle");
		// Gear
		output += renderPicks(npc.getGear(), "Gear");
		
		// Commander
		if (npc.getQualities().containsKey("commander")) {
			int comTL = Math.min(threatLevel + 2, 20);
			output += "<strong>Commander TL: " + comTL + "</strong>";
			output += " " + renderStats(npc, comTL, config);
			output += "<br/>";
			output += renderSkills(npc, comTL, config);
		}
		
		return output;
	}

	private String renderPicks(List<GearPick> picks, String type) {
		String output = "";
		for (GearPick pick : picks) {
			output += type;
			output += ", caliber ";
			output += Roman.toRoman(pick.getCalibre()) + ": ";
			if (pick.getQuantity() > 1) {
				output += pick.getQuantity() + " × ";
			}
			output += pick.getDescription();
			output += "<br/>";
		}
		return output;
	}

	private String renderStats(SpycraftNPC npc, int threatLevel,
			SpycraftConfig config) {
		String output = "Init " + statText(npc.getInit(), config.computeInit(npc.getInit(), threatLevel));		
		output += " Atk " + statText(npc.getAtk(), config.computeAtk(npc.getAtk(), threatLevel));
		output += " Def " + statText(npc.getDef(), config.computeDef(npc.getDef(), threatLevel));
		output += " Res " + statText(npc.getRes(), config.computeRes(npc.getRes(), threatLevel));
		if (npc.getType() == SCNPCType.SPECIAL) {
			output += " v/wp ";
			output += Integer.toString(config.computeVit(npc.getHlth(), threatLevel));
			output += "/";
			output += Integer.toString(config.computeWP(npc.getHlth(), threatLevel));
		} else {
			output += " DS " + statText(npc.getHlth(), config.computeHlth(npc.getHlth(), threatLevel));
		}
		output += " Comp " + statText(npc.getComp(), config.computeComp(npc.getComp(), threatLevel));
		output += " Wlth " + Roman.toRoman(npc.getWealth());
		return output;
	}
	
}
