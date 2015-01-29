package uk.co.meadicus.npcbuilder.client.skill;

import uk.co.meadicus.npcbuilder.client.npc.Config;
import uk.co.meadicus.npcbuilder.client.util.NPCUtils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class SkillEditWidget extends Composite {

	private static SkillEditWidgetUiBinder uiBinder = GWT
			.create(SkillEditWidgetUiBinder.class);

	interface SkillEditWidgetUiBinder extends UiBinder<Widget, SkillEditWidget> {
	}
	
	@UiField ListBox skillNameListBox;
	@UiField ListBox skillRatingListBox;

	public SkillEditWidget(Config config, Skill skill, ChangeHandler changeHandler) {
		initWidget(uiBinder.createAndBindUi(this));
		
		// set the options
		NPCUtils.addListOptions(skillNameListBox, true, config.getAvailableSkills(), true);
		NPCUtils.selectByValue(skillNameListBox, skill.getName());
		NPCUtils.addListOptions(skillRatingListBox, true, 1, 11, true, false);
		NPCUtils.selectByValue(skillRatingListBox, skill.getRating());
		
		// set the on change events
		skillNameListBox.addChangeHandler(changeHandler);
		skillRatingListBox.addChangeHandler(changeHandler);
	}
	
	/**
	 * Get the skill specified by this widget, or null if none selected.
	 * 
	 * @return the skill or null
	 */
	public Skill getSkill() {
		Skill skill = null;
		String skillName = skillNameListBox.getValue(skillNameListBox.getSelectedIndex());
		if (!skillName.isEmpty()) {
			int skillRating = Integer.parseInt(skillRatingListBox.getValue(skillRatingListBox.getSelectedIndex()));
			skill = new Skill(skillName, skillRating);
		}
		return skill;
	}
	

}
