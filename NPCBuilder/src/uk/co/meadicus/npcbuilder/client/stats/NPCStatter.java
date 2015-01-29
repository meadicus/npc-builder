package uk.co.meadicus.npcbuilder.client.stats;

import uk.co.meadicus.npcbuilder.client.npc.Config;
import uk.co.meadicus.npcbuilder.client.npc.NPC;
import uk.co.meadicus.npcbuilder.client.npc.Attributes.Attribute;
import uk.co.meadicus.npcbuilder.client.render.NPCRenderer;
import uk.co.meadicus.npcbuilder.client.skill.Skill;
import uk.co.meadicus.npcbuilder.client.util.NPCUtils;
import uk.co.meadicus.npcbuilder.client.util.Roman;

public abstract class NPCStatter<NPCType extends NPC> {

	private final NPCRenderer<NPCType> renderer;
	
	protected NPCStatter(NPCRenderer<NPCType> renderer) {
		this.renderer = renderer;
	}
	
	public abstract String statNPC(NPC npc, int threatLevel);
	
	protected String statText(int rating, int score) {
		return renderStat(rating, NPCUtils.renderModifier(score));
	}
	
	protected String renderStat(int rating, String stat) {
		String output = Roman.toRoman(rating);
		output += "(";
		output += stat;
		output += ")";
		return output;		
	}
	
	protected String renderAttribute(int score) {
		String output = Integer.toString(score);
		output += "/";
		output += NPCUtils.renderModifier(NPCUtils.attributeToBonus(score));
		return output;
	}
	
	protected String simpleTablePrint(String[][] data) {
		return simpleTablePrint(data, 3);
	}
	
	/**
	 * Data [col][row]
	 * 
	 * If the data isn't square this will probably throw a wobbly.
	 * 
	 * @param data
	 * @param padding
	 * @return
	 */
	protected String simpleTablePrint(String[][] data, int padding) {
		String output = "";
		
		// compute the widths of the columns, including padding
		int ncols = data.length;
		int maxnrows = 0;
		int[] colwidths = new int[ncols];
		for (int col = 0; col < ncols; ++col) {
			String[] column = data[col];
			int nrows = column.length;
			maxnrows = Math.max(maxnrows, nrows);
			for (int row = 0; row < nrows; ++row) {
				String cell = column[row];
				int size = cell.length() + padding;
				if (size > colwidths[col]) {
					colwidths[col] = size;
				}
			}
		}
		
		// output the data
		for (int row = 0; row < maxnrows; ++row) {
			for (int col = 0; col < ncols; ++col) {
				String datum = data[col][row];
				if (col != (ncols - 1)) {
					datum = makeColumn(datum, colwidths[col]);
				}
				output += datum;
			}
			output += "<br/>";
		}
		
		return output;
	}
	
	private String makeColumn(String content, int width) {
		String padd = "";
		for (int i = 0; i < (width - content.length()); ++i) {
			padd += "&nbsp;";
		}
		return content + padd;
	}

	protected String renderSkills(NPC npc, int threatLevel,
									Config config) {
		String output = "";
		String delim = "";
		for (Skill skill : npc.getSkills()) {
			output += delim;
			Attribute[] attrs = config.getSkillAttributes(skill);
			
			int[] attrScores = new int[attrs.length];
			for (int i = 0; i < attrs.length; ++i) {
				attrScores[i] = npc.getAttribute(attrs[i]);
			}
			int ranks = config.computeSkill(skill.getRating(), threatLevel);
			
			output += renderSkill(skill.getName(), skill.getRating(), attrs, attrScores, ranks);
			
			delim = "; ";
		}
		return output;
	}
	
	protected String renderSkill(String name, int rating, Attribute[] attrs, int[] attrScores, int ranks) {
		String output = name;
		output += " " + Roman.toRoman(rating);
		
		output += " (";
		String adelim = "";
		for (Attribute attr : attrs) {
			output += adelim;
			output += attr.toString();
			adelim="/";
		}
		output += ") ";
		
		if (attrs.length > 1) {
			int[] totals = new int[attrs.length];
			int[] mods = new int[attrs.length];
			for (int i = 0; i < attrs.length; ++i) {
				mods[i] = NPCUtils.attributeToBonus(attrScores[i]);
				totals[i] = ranks + mods[i];
			}
			adelim = "";
			for (int i = 0; i < attrs.length; ++i) {
				output += adelim + totals[i];
				adelim = "/";
			}
			output += " = " + ranks;
			adelim = "";
			for (int i = 0; i < attrs.length; ++i) {
				output += adelim + NPCUtils.renderModifier(mods[i]);
				adelim = "/";
			}
		} else {
			int mod = NPCUtils.attributeToBonus(attrScores[0]);
			int bonus = ranks + mod;
			output += NPCUtils.renderModifier(bonus);
			output += " = " + ranks + NPCUtils.renderModifier(mod);
		}
		
		return output;
	}

	protected NPCRenderer<NPCType> getRenderer() {
		return renderer;
	}
}
