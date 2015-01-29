package uk.co.meadicus.npcbuilder.client.quality;

import java.util.ArrayList;
import java.util.List;

import uk.co.meadicus.npcbuilder.client.npc.Config;
import uk.co.meadicus.npcbuilder.client.util.NPCUtils;

import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;

public class ConfiguredClassAbilityQualityUserInterface implements QualityUserInterface<ConfiguredClassAbilityQuality> {

	private final List<ListBox> optionBoxes = new ArrayList<ListBox>();

	@Override
	public void setupInterface(Panel panel, ConfiguredClassAbilityQuality quality,
			ChangeHandler changeHandler, Config config) {

		int maxuses = config.getDefaultMaxUses();
		
		for (int i = 0; i < maxuses; ++i) {
			FlowPanel editWrapper = new FlowPanel();
			
			ListBox optionBox = new ListBox();
			optionBoxes.add(optionBox);
			editWrapper.add(optionBox);
			optionBox.addItem("");
			NPCUtils.addListOptionValuedGroups(optionBox, false, quality.getAbilityNamesByClass());
			if (quality.getUses().size() > i) {
				NPCUtils.selectByValueIgnoreCase(optionBox, quality.getUses().get(i));
			}
			optionBox.addChangeHandler(changeHandler);
			
			panel.add(editWrapper);
		}
			
	}
	
	@Override
	public void populateFromInterface(Panel panel, ConfiguredClassAbilityQuality quality) {
		quality.getUses().clear();
		for (ListBox optionBox : optionBoxes) {
			String use = NPCUtils.getSelectedItemValue(optionBox);
			if (!use.isEmpty()) {
				quality.getUses().add(use);
			}
		}
	}
}
