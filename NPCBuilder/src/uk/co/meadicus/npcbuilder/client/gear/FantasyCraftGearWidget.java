package uk.co.meadicus.npcbuilder.client.gear;

import uk.co.meadicus.npcbuilder.client.npc.FantasyCraftNPC;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class FantasyCraftGearWidget extends Composite {

	private static FantasyCraftGearWidgetUiBinder uiBinder = GWT
			.create(FantasyCraftGearWidgetUiBinder.class);

	interface FantasyCraftGearWidgetUiBinder extends
			UiBinder<Widget, FantasyCraftGearWidget> {
	}
	
	@UiField TextArea mountsAndVehicles;
	@UiField TextArea gear;

	public FantasyCraftGearWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void init(FantasyCraftNPC npc) {
		mountsAndVehicles.setText(npc.getMountsAndVehicles());
		gear.setText(npc.getGear());		
	}
	
	protected String getMountsAndVehicles() {
		return mountsAndVehicles.getText();
	}

	protected String getGear() {
		return gear.getText();
	}

}
