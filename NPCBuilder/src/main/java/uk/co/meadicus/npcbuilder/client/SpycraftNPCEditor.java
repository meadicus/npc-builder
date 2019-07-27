package uk.co.meadicus.npcbuilder.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

import uk.co.meadicus.npcbuilder.client.gear.GearPicksDialogBox;
import uk.co.meadicus.npcbuilder.client.npc.SpycraftNPC;
import uk.co.meadicus.npcbuilder.client.npc.SpycraftNPCImpl;
import uk.co.meadicus.npcbuilder.client.quality.QualityFactory;
import uk.co.meadicus.npcbuilder.client.render.SpycraftNPCRenderer;
import uk.co.meadicus.npcbuilder.client.stats.SpycraftMinimalStatter;
import uk.co.meadicus.npcbuilder.client.util.NPCUtils;

public class SpycraftNPCEditor extends NPCEditor {

	private static final String COOKIE_NAME = "spycraft_npc_builder";

	private static SpycraftNPCEditorUiBinder uiBinder = GWT.create(SpycraftNPCEditorUiBinder.class);

	interface SpycraftNPCEditorUiBinder extends UiBinder<Widget, SpycraftNPCEditor> {
	}

	@UiField
	ListBox wealthListBox;
	@UiField
	Button gearButton;
	@UiField
	ListBox typeListBox;

	final GearPicksDialogBox gearDialog = new GearPicksDialogBox(this, getSpycraftNpc());

	public SpycraftNPCEditor(final SpycraftNPCImpl npc, final QualityFactory qualityFactory) {
		super(npc);
		initWidget(uiBinder.createAndBindUi(this));

		SpycraftNPCRenderer renderer = new SpycraftNPCRenderer();
		super.setRenderer(renderer);
		super.loadSavedNPC();

		initCommonNPCWidgets();

		super.setStatter(new SpycraftMinimalStatter(renderer));
		super.updateStatBlock();

		// type selector
		NPCUtils.addListOptions(typeListBox, true, SpycraftNPC.SCNPCType.displayValues(), false);
		NPCUtils.selectByValueIgnoreCase(typeListBox, npc.getType());
		typeListBox.addChangeHandler(event -> {
			npc.setType(
					SpycraftNPC.SCNPCType.valueOf(typeListBox.getValue(typeListBox.getSelectedIndex()).toUpperCase()));
			updateStatBlock();
		});

		// wealth selector
		NPCUtils.addListOptions(wealthListBox, true, 0, 11, true, false);
		NPCUtils.selectByValue(wealthListBox, npc.getWealth());
		wealthListBox.addChangeHandler(event -> {
			npc.setWealth(Integer.parseInt(wealthListBox.getValue(wealthListBox.getSelectedIndex())));
			updateStatBlock();
		});

		// setup buttons
		gearButton.addClickHandler(event -> {
			gearDialog.init();
			gearDialog.show();
		});

	}

	private SpycraftNPC getSpycraftNpc() {
		return (SpycraftNPC) getNpc();
	}

	protected void updateWidgetsFromNPC() {
		super.updateWidgetsFromNPC();
		NPCUtils.selectByValueIgnoreCase(typeListBox, getSpycraftNpc().getType());
		NPCUtils.selectByValue(wealthListBox, getSpycraftNpc().getWealth());
	}

	protected String getCookieName() {
		return COOKIE_NAME;
	}
}
