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
import com.google.gwt.user.client.ui.TextBox;

public class OriginQualityUserInterface implements
		QualityUserInterface<OriginQuality> {

	private TextBox titleTextBox;
	private TextBox extraTextBox;
	private Map<Attribute, ListBox> attributeListBoxes = new HashMap<Attribute, ListBox>();
	
	@Override
	public void setupInterface(Panel panel, OriginQuality quality,
			ChangeHandler changeHandler, Config config) {
		titleTextBox = new TextBox();
		titleTextBox.setValue(quality.getTitle());
		panel.add(titleTextBox);
		titleTextBox.addChangeHandler(changeHandler);
		
		FlowPanel attrPanel = new FlowPanel();
		FlowPanel attrPanel1 = new FlowPanel();
		FlowPanel attrPanel2 = new FlowPanel();
				
		Attribute[] attributes = Attribute.values();
		for (int i=0; i < attributes.length; ++i) {
			Attribute attribute = attributes[i];
			InlineLabel label = new InlineLabel(attribute.toString());
			label.addStyleName("attrLabel");
			ListBox listBox = new ListBox();
			attributeListBoxes.put(attribute, listBox);
			NPCUtils.addListOptions(listBox,
									true,
									-5,
									5,
									false, false);
			NPCUtils.selectByValue(listBox, quality.getAttributeModifiers().getAttribute(attribute));
			listBox.addChangeHandler(changeHandler);
			if (i < 3) {
				attrPanel1.add(label);
				attrPanel1.add(listBox);
			} else {
				attrPanel2.add(label);
				attrPanel2.add(listBox);
			}
		}			
		
		attrPanel.add(attrPanel1);
		attrPanel.add(attrPanel2);
		panel.add(attrPanel);
		
		FlowPanel extraPanel = new FlowPanel();
		InlineLabel label = new InlineLabel("Extra:");
		extraPanel.add(label);
		extraTextBox = new TextBox();
		extraTextBox.setValue(quality.getExtra());
		extraPanel.add(extraTextBox);
		panel.add(extraPanel);
		extraTextBox.addChangeHandler(changeHandler);
	}

	@Override
	public void populateFromInterface(Panel panel, OriginQuality quality) {
		String title = titleTextBox.getValue();
		quality.setTitle(title);
		String extra = extraTextBox.getValue();
		quality.setExtra(extra);
		
		for (Attribute attribute : Attributes.Attribute.values()) {
			ListBox listBox = attributeListBoxes.get(attribute);
			int score = Integer.parseInt(NPCUtils.getSelectedItemValue(listBox));
			quality.getAttributeModifiers().setAttribute(attribute, score);
		}
	}

}
