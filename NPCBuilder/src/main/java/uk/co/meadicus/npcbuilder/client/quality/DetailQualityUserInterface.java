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

public class DetailQualityUserInterface implements QualityUserInterface<DetailQuality> {

	private final List<ListBox> optionBoxes = new ArrayList<ListBox>();
	private final List<TextBox> textBoxes = new ArrayList<TextBox>();

	
	public void setupInterface(Panel panel, DetailQuality quality,
			ChangeHandler changeHandler, Config config) {
		int maxuses = quality.getMaxUses();
		if (maxuses <= 0) {
			maxuses = config.getDefaultMaxUses();
		}
		boolean hasOptions = (quality.getOptions() != null && quality.getOptions().length > 0);
		for (int i = 0; i < maxuses; ++i) {
			FlowPanel editWrapper = new FlowPanel();
			if (hasOptions) {
				ListBox optionBox = new ListBox();
				optionBoxes.add(optionBox);
				editWrapper.add(optionBox);
				NPCUtils.addListOptions(optionBox, true, quality.getOptions(), true);
				if (quality.getUses().size() > i) {
					NPCUtils.selectByValueIgnoreCase(optionBox, quality.getUses().get(i));
				}
				optionBox.addChangeHandler(changeHandler);
			} else {
				TextBox textBox = new TextBox();
				textBoxes.add(textBox);
				editWrapper.add(textBox);
				if (quality.getUses().size() > i) {
					textBox.setValue(quality.getUses().get(i));
				}
				textBox.addChangeHandler(changeHandler);
			}
			panel.add(editWrapper);
		}
	}
	
	
	public void populateFromInterface(Panel panel, DetailQuality quality) {
		quality.getUses().clear();
		if (!optionBoxes.isEmpty()) {
			for (ListBox optionBox : optionBoxes) {
				String use = NPCUtils.getSelectedItemValue(optionBox);
				if (!use.isEmpty()) {
					quality.getUses().add(use);
				}
			}
		} else {
			for (TextBox textBox : textBoxes) {
				String use = textBox.getText();
				if (!use.trim().isEmpty()) {
					quality.getUses().add(use);
				}
			}
		}
	}

}
