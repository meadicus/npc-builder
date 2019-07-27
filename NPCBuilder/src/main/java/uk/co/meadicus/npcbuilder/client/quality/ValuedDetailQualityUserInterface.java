package uk.co.meadicus.npcbuilder.client.quality;

import java.util.ArrayList;
import java.util.List;

import uk.co.meadicus.npcbuilder.client.npc.Config;
import uk.co.meadicus.npcbuilder.client.util.NPCUtils;

import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;

public class ValuedDetailQualityUserInterface implements
		QualityUserInterface<ValuedDetailQuality> {

	private final List<ListBox> optionBoxes = new ArrayList<ListBox>();
	private final List<TextBox> textBoxes = new ArrayList<TextBox>();
	private final List<ListBox> valueBoxes = new ArrayList<ListBox>();

	
	public void setupInterface(Panel panel, ValuedDetailQuality quality,
			ChangeHandler changeHandler, Config config) {
		int maxuses = quality.getMaxUses();
		if (maxuses <= 0) {
			maxuses = config.getDefaultMaxUses();
		}
		List<String> useNames = quality.getSortedUseNames();
		boolean hasOptions = (quality.getOptions() != null && quality.getOptions().length > 0);
		for (int i = 0; i < maxuses; ++i) {
			FlowPanel hPanel = new FlowPanel();
			if (hasOptions) {
				ListBox optionBox = new ListBox();
				optionBoxes.add(optionBox);
				hPanel.add(optionBox);
				NPCUtils.addListOptions(optionBox, true, quality.getOptions(), true);
				if (useNames.size() > i) {
					NPCUtils.selectByValueIgnoreCase(optionBox, useNames.get(i));
				}
				optionBox.addChangeHandler(changeHandler);
			} else {
				TextBox textBox = new TextBox();
				textBoxes.add(textBox);
				hPanel.add(textBox);
				if (useNames.size() > i) {
					textBox.setValue(useNames.get(i));
				}
				textBox.addChangeHandler(changeHandler);
			}
			ListBox valueBox = new ListBox();
			valueBoxes.add(valueBox);
			hPanel.add(valueBox);
			NPCUtils.addListOptions(valueBox, true, quality.getValues(), false);
			if (useNames.size() > i) {
				NPCUtils.selectByValueIgnoreCase(valueBox, quality.getUses().get(useNames.get(i)));
			}
			valueBox.addChangeHandler(changeHandler);
			panel.add(hPanel);
		}
	}

	
	public void populateFromInterface(Panel panel, ValuedDetailQuality quality) {
		quality.getUses().clear();
		for (int i = 0; i < valueBoxes.size(); ++i) {
			String value = NPCUtils.getSelectedItemValue(valueBoxes.get(i));
			String name = null;
			if (!optionBoxes.isEmpty()) {
				name = NPCUtils.getSelectedItemValue(optionBoxes.get(i)).trim();
			} else {
				name = textBoxes.get(i).getText().trim();
			}
			if (name != null && !name.isEmpty()) {
				quality.getUses().put(name, value);
			}
		}
	}
}
