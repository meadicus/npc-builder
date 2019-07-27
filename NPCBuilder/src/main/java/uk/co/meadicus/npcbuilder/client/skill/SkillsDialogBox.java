package uk.co.meadicus.npcbuilder.client.skill;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import uk.co.meadicus.npcbuilder.client.NPCEditor;
import uk.co.meadicus.npcbuilder.client.npc.NPC;
import uk.co.meadicus.npcbuilder.client.util.XPDialogBox;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class SkillsDialogBox extends XPDialogBox {
	
	private final List<SkillEditWidget> skillEditWidgets = new ArrayList<SkillEditWidget>();

	VerticalPanel panel;
	
	public SkillsDialogBox(NPCEditor npcEditor, NPC npc) {
		super("Skills", npcEditor, npc, true);
		this.panel = new VerticalPanel();
		setContent();
	}
	
	
	protected Widget getContent() {
		return this.panel;
	}
	
	
	public void init() {
		
		this.panel.clear();
		this.skillEditWidgets.clear();
		
		// Add a skill edit widget for each currently allocated skill
		for (Skill skill : getNpc().getSkills()) {
			SkillEditWidget skillEditWidget = new SkillEditWidget(getNpc().getConfig(), skill, this);
			this.skillEditWidgets.add(skillEditWidget);
			panel.add(skillEditWidget);
		}
		
		// If more skills are allowed, create new edit widgets
		while (this.skillEditWidgets.size() < getNpc().getConfig().getMaxSkills()) {
			Skill skill = new Skill("", 1);
			SkillEditWidget skillEditWidget = new SkillEditWidget(getNpc().getConfig(), skill, this);
			this.skillEditWidgets.add(skillEditWidget);		
			panel.add(skillEditWidget);	
		}
		
		super.init();				
	}

	
	protected boolean onOk() {
		getNpc().getSkills().clear();
		// Total the ratings of all the skills
		for (SkillEditWidget skillEditWidget : this.skillEditWidgets) {
			Skill skill = skillEditWidget.getSkill();
			if (skill != null) {
				getNpc().getSkills().add(skill);
			}
		}
		// sort the NPC's skill list
		Collections.sort(getNpc().getSkills());
		return true;
	}

	
	protected int calculateXP() {
		int xp = 0;
		// Total the ratings of all the skills
		for (SkillEditWidget skillEditWidget : this.skillEditWidgets) {
			Skill skill = skillEditWidget.getSkill();
			if (skill != null) {
				xp += skill.getRating();
			}
		}
		return xp;
	}

}
