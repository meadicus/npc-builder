package uk.co.meadicus.npcbuilder.client.gear;

import uk.co.meadicus.npcbuilder.client.util.NPCUtils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class GearPickEditWidget extends Composite {

	private static GearPickEditWidgetUiBinder uiBinder = GWT
			.create(GearPickEditWidgetUiBinder.class);

	interface GearPickEditWidgetUiBinder extends
			UiBinder<Widget, GearPickEditWidget> {
	}

	@UiField TextBox pickDescriptionTextBox;
	@UiField ListBox pickQuantityListBox;
	@UiField ListBox pickCalibreListBox;
	@UiField Button removeButton;
	
	public GearPickEditWidget(GearPick pick, final ChangeHandler gearDialog) {
		initWidget(uiBinder.createAndBindUi(this));
		
		pickDescriptionTextBox.setText(pick.getDescription());
		
		// set the options
		NPCUtils.addListOptions(pickCalibreListBox, true, 1, 6, true, false);
		NPCUtils.selectByValue(pickCalibreListBox, pick.getCalibre());
		NPCUtils.addListOptions(pickQuantityListBox, true, 1, 21, false, false);
		NPCUtils.selectByValue(pickQuantityListBox, pick.getQuantity());
		
		// set the on change events
		pickCalibreListBox.addChangeHandler(gearDialog);
		pickQuantityListBox.addChangeHandler(gearDialog);
		
	}

	public GearPick getGearPick() {
		String description = this.pickDescriptionTextBox.getText().trim();
		int calibre = Integer.parseInt(pickCalibreListBox.getValue(pickCalibreListBox.getSelectedIndex()));
		int quantity = Integer.parseInt(pickQuantityListBox.getValue(pickQuantityListBox.getSelectedIndex()));
		GearPick pick = new GearPick(description, calibre, quantity);
		return pick;
	}
}
