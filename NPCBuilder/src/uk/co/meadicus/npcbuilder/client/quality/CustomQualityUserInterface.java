package uk.co.meadicus.npcbuilder.client.quality;

import uk.co.meadicus.npcbuilder.client.npc.Config;
import uk.co.meadicus.npcbuilder.client.util.NPCUtils;

import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;

public class CustomQualityUserInterface implements QualityUserInterface<CustomQuality> {

	private TextBox titleTextBox;
	private TextBox detailsTextBox;
	private ListBox xpListBox;
	
	@Override
	public void setupInterface(Panel panel, CustomQuality quality,
			ChangeHandler changeHandler, Config config) {
		
		this.titleTextBox = new TextBox();
		this.titleTextBox.setValue(quality.getTitle());
		this.detailsTextBox = new TextBox();
		this.detailsTextBox.setValue(quality.getDetails());
		this.xpListBox = new ListBox();
		NPCUtils.addListOptions(this.xpListBox, true, -99, 100, false, false);
		NPCUtils.selectByValue(this.xpListBox, (int)quality.getBaseXp());
		
		FlowPanel titlePanel = new FlowPanel();
		titlePanel.add(new InlineLabel("Title:"));
		titlePanel.add(this.titleTextBox);
		panel.add(titlePanel);

		FlowPanel detailsPanel = new FlowPanel();
		detailsPanel.add(new InlineLabel("Details:"));
		detailsPanel.add(this.detailsTextBox);
		panel.add(detailsPanel);

		FlowPanel xpPanel = new FlowPanel();
		xpPanel.add(new InlineLabel("XP:"));
		xpPanel.add(this.xpListBox);
		panel.add(xpPanel);
	}

	@Override
	public void populateFromInterface(Panel panel, CustomQuality quality) {
		String title = this.titleTextBox.getValue();
		String details = this.detailsTextBox.getValue();
		int xp = Integer.parseInt(NPCUtils.getSelectedItemValue(this.xpListBox));
		
		quality.setTitle(title);
		quality.setDetails(details);
		quality.setXp(xp);
	}

}
