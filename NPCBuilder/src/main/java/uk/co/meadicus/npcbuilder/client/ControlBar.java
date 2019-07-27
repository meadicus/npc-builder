package uk.co.meadicus.npcbuilder.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ControlBar extends Composite {

	private static ControlBarUiBinder uiBinder = GWT
			.create(ControlBarUiBinder.class);

	interface ControlBarUiBinder extends UiBinder<Widget, ControlBar> {
	}

	@UiField Button scButton;
	@UiField Button fcButton;
		
	public ControlBar() {
		initWidget(uiBinder.createAndBindUi(this));
	}
}
