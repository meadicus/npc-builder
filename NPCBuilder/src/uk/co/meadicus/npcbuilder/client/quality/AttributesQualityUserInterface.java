package uk.co.meadicus.npcbuilder.client.quality;

import java.util.HashMap;
import java.util.Map;

import uk.co.meadicus.npcbuilder.client.npc.Attributes;
import uk.co.meadicus.npcbuilder.client.npc.Config;
import uk.co.meadicus.npcbuilder.client.npc.Attributes.Attribute;
import uk.co.meadicus.npcbuilder.client.util.NPCUtils;

import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;

public class AttributesQualityUserInterface implements QualityUserInterface<AttributesQuality> {

	private Map<Attribute, ListBox> attributeListBoxes = new HashMap<Attribute, ListBox>();

	@Override
	public void setupInterface(Panel panel, AttributesQuality quality,
			ChangeHandler changeHandler, Config config) {
		
		for (Attribute attribute : Attributes.Attribute.values()) {
			FlowPanel hPanel = new FlowPanel();
			InlineLabel label = new InlineLabel(attribute.toString());
			label.addStyleName("attrLabel");
			hPanel.add(label);
			ListBox listBox = new ListBox();
			attributeListBoxes.put(attribute, listBox);
			NPCUtils.addListOptions(listBox,
									true,
									quality.getDefaultAttributes().getAttribute(attribute),
									quality.getLimit(),
									false, false);
			NPCUtils.selectByValue(listBox, quality.getAttributes().getAttribute(attribute));
			hPanel.add(listBox);
			listBox.addChangeHandler(changeHandler);
			panel.add(hPanel);
		}
	}
	
	@Override
	public void populateFromInterface(Panel panel, AttributesQuality quality) {

		for (Attribute attribute : Attributes.Attribute.values()) {
			ListBox listBox = attributeListBoxes.get(attribute);
			int score = Integer.parseInt(NPCUtils.getSelectedItemValue(listBox));
			int scoreDiff = score - quality.getDefaultAttributes().getAttribute(attribute);
			quality.getAttributeModifiers().setAttribute(attribute, scoreDiff);
		}
	}
}
