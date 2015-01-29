package uk.co.meadicus.npcbuilder.client.stats;

import uk.co.meadicus.npcbuilder.client.attack.Attack;
import uk.co.meadicus.npcbuilder.client.attack.DamageExtraordinaryAttack;
import uk.co.meadicus.npcbuilder.client.attack.NaturalAttack;
import uk.co.meadicus.npcbuilder.client.attack.SaveExtraordinaryAttack;
import uk.co.meadicus.npcbuilder.client.npc.Attributes.Attribute;
import uk.co.meadicus.npcbuilder.client.npc.FantasyCraftConfig;
import uk.co.meadicus.npcbuilder.client.npc.FantasyCraftNPC;
import uk.co.meadicus.npcbuilder.client.npc.NPC;
import uk.co.meadicus.npcbuilder.client.render.FantasyCraftNPCRenderer;
import uk.co.meadicus.npcbuilder.client.util.NPCUtils;

public class FantasyCraftMinimalStatter extends NPCStatter<FantasyCraftNPC> {

	public FantasyCraftMinimalStatter(FantasyCraftNPCRenderer renderer) {
		super(renderer);
	}

	@Override
	public String statNPC(NPC npc, int threatLevel) {
		// render stats
		if (npc instanceof FantasyCraftNPC) {
			return renderStats((FantasyCraftNPC)npc, threatLevel);
		} else {
			return "";
		}
	}

	public String renderStats(FantasyCraftNPC npc, int threatLevel) {
		FantasyCraftConfig config = npc.getConfig();
		
		// Title
		String output = "<strong>" + getRenderer().renderTitle(npc) + " TL: " + threatLevel + "</strong>";
		output += "<br/>";
		
		// Attributes
		output += "Str: " + renderAttribute(npc.getStr()) + ";";
		output += " Dex: " + renderAttribute(npc.getDex()) + ";";
		output += " Con: " + renderAttribute(npc.getCon()) + ";";
		output += " Int: " + renderAttribute(npc.getInt()) + ";";
		output += " Wis: " + renderAttribute(npc.getWis()) + ";";
		output += " Cha: " + renderAttribute(npc.getCha()) + ";";
		output += "<br/>";
		
		// Stats
		output += renderStats(npc, threatLevel, config);
		
		// size; reach; speed
		output += " Size: " + npc.getSize().toString();
		output += " (" + npc.getFootprintX() + "×" + npc.getFootprintY() + ");";
		output += " Reach: " + npc.getReach() + ";";
		output += getRenderer().renderSpeed(npc, " Speed: ");
		output += "<br/>";
		
		// skills
		if (!npc.getSkills().isEmpty()) {
			output += "<em class='blockstart'>Skills:</em> ";
			output += renderSkills(npc, threatLevel, config);
			output += "<br/>";
		}
		
		// spells
		if (npc.getSpellcasting().getGrade() > 0) {			
			int grade = npc.getSpellcasting().getGrade();
			int ranks = config.computeSkill(grade, threatLevel);
			Attribute[] attrs = {Attribute.INT};
			int[] attrScores = {npc.getInt()};
			
			output += renderSkill("Spellcasting", grade, attrs, attrScores, ranks);
			
			output += " (SP: " + 2 * threatLevel + ")";
			output += getRenderer().renderSpells(npc);
			output += "<br/>";
		}
		
		// qualities
		if (!npc.getQualities().isEmpty()) {
			output += getRenderer().renderQualities(npc);
			output += "<br/>";
		}
		
		// attacks
		if (!npc.getAttacks().isEmpty()) {
			output += "<em class='blockstart'>Attacks:</em> ";
			output += renderAttacks(npc, threatLevel, config);
			output += "<br/>";
		}
		
		// gear
		if (!npc.getMountsAndVehicles().isEmpty()) {
			output += "<em class='blockstart'>Mounts and Vehicles:</em> ";
			output += npc.getMountsAndVehicles();
			output += "<br/>";
		}
		if (!npc.getGear().isEmpty()) {
			output += "<em class='blockstart'>Gear:</em> ";
			output += npc.getGear();
			output += "<br/>";
		}
		
		// treasure
		if (!npc.getTreasure().isEmpty()) {
			output += "<em class='blockstart'>Treasure:</em> ";
			output += getRenderer().renderTreasure(npc);
		}
		
		return output;
	}

	private String renderAttacks(FantasyCraftNPC npc, int threatLevel,
			FantasyCraftConfig config) {
		
		String output = "";
		
		String delim = "";
		for (Attack attack : npc.getAttacks()) {
			String display = attack.render();
			
			if (attack instanceof NaturalAttack) {
				String title = attack.getTitle();
				int grade = attack.getGrade();
				
				String dmgDice = config.getNaturalAttackDamageDice(title, npc.getSize(), grade);
				String threat = config.getNaturalAttackThreat(title, npc.getSize(), grade);
				
				String match = attack.renderTitle();
				String detail = match + " [" + dmgDice + "/" + threat + "]";
				display = display.replace(match, detail);				
			} else if (attack instanceof DamageExtraordinaryAttack) {
				int grade = attack.getGrade();
				String dmgDice = config.damageAttackDice(grade);
				String match = attack.renderTitle();
				String detail = match + " [" + ((int)Math.ceil(threatLevel/2.0)) + dmgDice + "/20]";
				display = display.replace(match, detail);
			} else if (attack instanceof SaveExtraordinaryAttack) {
				int grade = attack.getGrade();
				int saveDC = 5 + (5 * grade);
				String match = attack.renderTitle();
				String detail = match + " [DC " + saveDC + "]";
				display = display.replace(match, detail);
			}
			
			output += delim;
			output += display;
			delim = ", ";
		}
		
		return output;
	}

	private String renderStats(FantasyCraftNPC npc, int threatLevel,
			FantasyCraftConfig config) {
		
		// init
		StatSum init = new StatSum();
		init.add(config.computeInit(npc.getInit(), threatLevel), null, true);
		init.add(NPCUtils.attributeToBonus(npc.getDex()), "dex", true);
		
		// health
		StatSum dmgSave = new StatSum();
		dmgSave.add(config.computeHlth(npc.getHlth(), threatLevel), null, true);
		dmgSave.add(NPCUtils.attributeToBonus(npc.getCon()), "con", true);
		dmgSave.add(config.computeDSModifier(npc.getSize()), "size", false);
		String vwp = config.computeVitality(npc, threatLevel) + "/" + config.computeWounds(npc);
		
		// defense
		StatSum def = new StatSum();
		def.add(10, null, false);
		def.add(config.computeDef(npc.getDef(), threatLevel), null, true);
		def.setTotalIsModifier(false);
		def.add(NPCUtils.attributeToBonus(npc.getDex()), "dex", true);
		def.add(config.computeDefModifier(npc.getSize()), "size", false);
		// TODO search gear for a defense penalty from armour
		
		// melee
		StatSum melee = new StatSum();
		melee.add(config.computeAtk(npc.getAtk(), threatLevel), null, true);
		melee.add(NPCUtils.attributeToBonus(npc.getStr()), "str", true);
		
		// ranged
		StatSum ranged = new StatSum();
		ranged.add(config.computeAtk(npc.getAtk(), threatLevel), null, true);
		ranged.add(NPCUtils.attributeToBonus(npc.getDex()), "dex", true);
				
		// comp
		StatSum comp = null;
		if (npc.getComp() > 0) {
			comp = new StatSum();
			comp.add(config.computeComp(npc.getComp(), threatLevel), null, true);
		}
		
		// fort
		StatSum fort = new StatSum();
		fort.add(config.computeRes(npc.getRes(), threatLevel), null, true);
		fort.add(NPCUtils.attributeToBonus(npc.getCon()), "con", true);
		
		// ref
		StatSum ref = new StatSum();
		ref.add(config.computeRes(npc.getRes(), threatLevel), null, true);
		ref.add(NPCUtils.attributeToBonus(npc.getDex()), "dex", true);
		
		// will
		StatSum will = new StatSum();
		will.add(config.computeRes(npc.getRes(), threatLevel), null, true);
		will.add(NPCUtils.attributeToBonus(npc.getWis()), "wis", true);
		
		String[][] data = new String[3][3];
		data[0][0] = "Init: " + init.render();
		data[0][1] = "Health: " + dmgSave.render() + " ~ " + vwp;
		data[0][2] = "Def: " + def.render();
		data[1][0] = "Melee: " + melee.render();
		data[1][1] = "Ranged: " + ranged.render();
		data[1][2] = "Comp: " + ((comp != null) ? comp.render() : "None");
		data[2][0] = "Fort: " + fort.render();
		data[2][1] = "Ref: " + ref.render();
		data[2][2] = "Will: " + will.render();
		
		return simpleTablePrint(data);
	}

	@Override
	protected FantasyCraftNPCRenderer getRenderer() {
		return (FantasyCraftNPCRenderer) super.getRenderer();
	}
	
}
