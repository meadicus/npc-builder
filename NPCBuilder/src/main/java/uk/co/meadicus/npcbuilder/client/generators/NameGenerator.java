package uk.co.meadicus.npcbuilder.client.generators;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

import uk.co.meadicus.npcbuilder.client.util.NPCUtils;

public class NameGenerator extends Composite {

	private static NameGeneratorUiBinder uiBinder = GWT.create(NameGeneratorUiBinder.class);

	interface NameGeneratorUiBinder extends UiBinder<Widget, NameGenerator> {
	}

	@UiField
	ListBox speciesListBox;
	@UiField
	ListBox genderListBox;
	@UiField
	Button generateButton;
	@UiField
	Label randomName;

	public NameGenerator() {
		initWidget(uiBinder.createAndBindUi(this));

		// setup lists
		NPCUtils.addListOptions(speciesListBox, true, RandomNameGenerator.getSpeciesList(), false);
		NPCUtils.addListOptions(genderListBox, true, RandomNameGenerator.getGenderList(), false);

		generateButton.addClickHandler(event -> generateName());
	}

	protected String getName() {
		return randomName.getText();
	}

	private void generateName() {
		String species = NPCUtils.getSelectedItemValue(speciesListBox);
		String gender = NPCUtils.getSelectedItemValue(genderListBox);
		String name = RandomNameGenerator.getName(species, gender);
		randomName.setText(" " + name);
	}
}
