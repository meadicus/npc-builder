package uk.co.meadicus.npcbuilder.client.quality;

import java.util.ArrayList;
import java.util.List;

import uk.co.meadicus.npcbuilder.client.npc.Config;
import uk.co.meadicus.npcbuilder.client.util.NPCUtils;

import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;

public class SimpleQualityUserInterface implements QualityUserInterface<SimpleQuality> {

	private final List<ListBox> optionBoxes = new ArrayList<ListBox>();
	
	
	public void populateFromInterface(Panel panel, SimpleQuality quality) {	
		if (!optionBoxes.isEmpty()) {
			String optionUsed = NPCUtils.getSelectedItemValue(optionBoxes.get(0));
			quality.setUse(optionUsed);
		}		
	}

	
	public void setupInterface(Panel panel, SimpleQuality quality,
			ChangeHandler changeHandler, Config config) {
		if (quality.getOptions() != null && quality.getOptions().length > 0) {
			ListBox optionBox = new ListBox();
			optionBoxes.add(optionBox);
			panel.add(optionBox);
			boolean includeBlank = (quality.getBaseXp() != 0);
			NPCUtils.addListOptions(optionBox, true, quality.getOptions(), includeBlank);
			NPCUtils.selectByValueIgnoreCase(optionBox, quality.getUse());
			optionBox.addChangeHandler(changeHandler);
		}		
	}
}
