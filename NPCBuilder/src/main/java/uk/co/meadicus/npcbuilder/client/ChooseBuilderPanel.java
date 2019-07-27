package uk.co.meadicus.npcbuilder.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ChooseBuilderPanel extends Composite {

	private static ChooseBuilderPanelUiBinder uiBinder = GWT
			.create(ChooseBuilderPanelUiBinder.class);

	interface ChooseBuilderPanelUiBinder extends
			UiBinder<Widget, ChooseBuilderPanel> {
	}

	@UiField Button scButton;
	@UiField Button fcButton;
	@UiField Button fcNameButton;
	@UiField Button fcTreasureButton;
	
	public ChooseBuilderPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
