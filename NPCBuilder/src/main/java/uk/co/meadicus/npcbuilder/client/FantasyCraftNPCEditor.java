package uk.co.meadicus.npcbuilder.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

import uk.co.meadicus.npcbuilder.client.attack.AttacksDialogBox;
import uk.co.meadicus.npcbuilder.client.gear.FantasyCraftGearDialogBox;
import uk.co.meadicus.npcbuilder.client.generators.NameGeneratorDialogBox;
import uk.co.meadicus.npcbuilder.client.npc.FantasyCraftNPC;
import uk.co.meadicus.npcbuilder.client.npc.FantasyCraftNPCImpl;
import uk.co.meadicus.npcbuilder.client.npc.Size;
import uk.co.meadicus.npcbuilder.client.npc.form.MobilityDialogBox;
import uk.co.meadicus.npcbuilder.client.npc.form.MultiTypeDialogBox;
import uk.co.meadicus.npcbuilder.client.quality.QualityFactory;
import uk.co.meadicus.npcbuilder.client.render.FantasyCraftNPCRenderer;
import uk.co.meadicus.npcbuilder.client.spellcasting.SpellcastingDialogBox;
import uk.co.meadicus.npcbuilder.client.stats.FantasyCraftMinimalStatter;
import uk.co.meadicus.npcbuilder.client.template.TemplateDialogBox;
import uk.co.meadicus.npcbuilder.client.treasure.TreasureDialogBox;
import uk.co.meadicus.npcbuilder.client.treasure.TreasureGeneratorWidget;
import uk.co.meadicus.npcbuilder.client.util.NPCUtils;

public class FantasyCraftNPCEditor extends NPCEditor {

	private static final String COOKIE_NAME = "fantasycraft_npc_builder";

	private static FantasyCraftNPCEditorUiBinder uiBinder = GWT.create(FantasyCraftNPCEditorUiBinder.class);

	interface FantasyCraftNPCEditorUiBinder extends UiBinder<Widget, FantasyCraftNPCEditor> {
	}

	// All the fields
	@UiField
	Button randomNameButton;
	@UiField
	ListBox sizeListBox;
	@UiField
	ListBox footprintXListBox;
	@UiField
	ListBox footprintYListBox;
	@UiField
	ListBox reachListBox;
	@UiField
	Button typeButton;
	@UiField
	Button mobilityButton;
	@UiField
	Button templateButton;
	@UiField
	ListBox strListBox;
	@UiField
	ListBox dexListBox;
	@UiField
	ListBox conListBox;
	@UiField
	ListBox intListBox;
	@UiField
	ListBox wisListBox;
	@UiField
	ListBox chaListBox;
	@UiField
	Button spellsButton;
	@UiField
	Button attacksButton;
	@UiField
	Button gearButton;
	@UiField
	Button treasureButton;
	@UiField
	Panel treasureGenPanel;

	private final MultiTypeDialogBox typeDialog = new MultiTypeDialogBox(this, getFantasyCraftNpc());
	private final MobilityDialogBox mobilityDialog = new MobilityDialogBox(this, getFantasyCraftNpc());
	private final SpellcastingDialogBox magicDialog = new SpellcastingDialogBox(this, getFantasyCraftNpc());
	private final FantasyCraftGearDialogBox gearDialog = new FantasyCraftGearDialogBox(this, getFantasyCraftNpc());
	private final TreasureDialogBox treasureDialog = new TreasureDialogBox(this, getFantasyCraftNpc());
	private final AttacksDialogBox attacksDialog = new AttacksDialogBox(this, getFantasyCraftNpc());
	private final NameGeneratorDialogBox randomNameDialog = new NameGeneratorDialogBox(this, getFantasyCraftNpc());
	private final TemplateDialogBox templateDialog = new TemplateDialogBox(this, getFantasyCraftNpc());
	private final Button treasureGenButton = new Button("Random Treasure");
	private final TreasureGeneratorWidget treasureGeneratorWidget;

	public FantasyCraftNPCEditor(final FantasyCraftNPCImpl npc, final QualityFactory qualityFactory) {
		super(npc);
		initWidget(uiBinder.createAndBindUi(this));

		FantasyCraftNPCRenderer renderer = new FantasyCraftNPCRenderer();
		super.setRenderer(renderer);
		super.loadSavedNPC();

		initCommonNPCWidgets();

		this.treasureGenPanel.setVisible(false);
		this.treasureGeneratorWidget = new TreasureGeneratorWidget(this, npc);
		this.treasureGeneratorWidget.setVisible(false);
		this.treasureGenButton.addClickHandler(event -> {
			treasureGenButton.setVisible(false);
			treasureGeneratorWidget.setVisible(true);
			treasureGeneratorWidget.generate();
		});
		this.treasureGenPanel.add(this.treasureGenButton);
		this.treasureGenPanel.add(this.treasureGeneratorWidget);

		super.setStatter(new FantasyCraftMinimalStatter(renderer));
		super.updateStatBlock();

		// setup the attribute selectors
		NPCUtils.addListOptions(strListBox, true, 1, 100, false, false);
		NPCUtils.selectByValue(strListBox, npc.getStr());
		strListBox.addChangeHandler(event -> {
			npc.setStr(Integer.parseInt(NPCUtils.getSelectedItemValue(strListBox)));
			updateStatBlock();
		});
		NPCUtils.addListOptions(dexListBox, true, 1, 100, false, false);
		NPCUtils.selectByValue(dexListBox, npc.getDex());
		dexListBox.addChangeHandler(event -> {
			npc.setDex(Integer.parseInt(NPCUtils.getSelectedItemValue(dexListBox)));
			updateStatBlock();

		});
		NPCUtils.addListOptions(conListBox, true, 1, 100, false, false);
		NPCUtils.selectByValue(conListBox, npc.getCon());
		conListBox.addChangeHandler(event -> {
			npc.setCon(Integer.parseInt(NPCUtils.getSelectedItemValue(conListBox)));
			updateStatBlock();
		});
		NPCUtils.addListOptions(intListBox, true, 1, 100, false, false);
		NPCUtils.selectByValue(intListBox, npc.getInt());
		intListBox.addChangeHandler(event -> {
			npc.setInt(Integer.parseInt(NPCUtils.getSelectedItemValue(intListBox)));
			updateStatBlock();
		});
		NPCUtils.addListOptions(wisListBox, true, 1, 100, false, false);
		NPCUtils.selectByValue(wisListBox, npc.getWis());
		wisListBox.addChangeHandler(event -> {
			npc.setWis(Integer.parseInt(NPCUtils.getSelectedItemValue(wisListBox)));
			updateStatBlock();
		});
		NPCUtils.addListOptions(chaListBox, true, 1, 100, false, false);
		NPCUtils.selectByValue(chaListBox, npc.getCha());
		chaListBox.addChangeHandler(event -> {
			npc.setCha(Integer.parseInt(NPCUtils.getSelectedItemValue(chaListBox)));
			updateStatBlock();
		});

		// NPC size,footprint,reach selectors
		NPCUtils.addListOptions(sizeListBox, true, Size.values(), false);
		NPCUtils.selectByValue(sizeListBox, npc.getSize());
		sizeListBox.addChangeHandler(event -> {
			npc.setSize(Size.fromString(NPCUtils.getSelectedItemValue(sizeListBox)));
			updateStatBlock();
		});
		NPCUtils.addListOptions(footprintXListBox, false, 1, 100, false, false);
		NPCUtils.selectByValue(footprintXListBox, npc.getFootprintX());
		footprintXListBox.addChangeHandler(event -> {
			npc.setFootprintX(Integer.parseInt(NPCUtils.getSelectedItemValue(footprintXListBox)));
			updateStatBlock();
		});
		NPCUtils.addListOptions(footprintYListBox, false, 1, 100, false, false);
		NPCUtils.selectByValue(footprintYListBox, npc.getFootprintY());
		footprintYListBox.addChangeHandler(event -> {
			npc.setFootprintY(Integer.parseInt(NPCUtils.getSelectedItemValue(footprintYListBox)));
			updateStatBlock();
		});
		NPCUtils.addListOptions(reachListBox, false, 1, 100, false, false);
		NPCUtils.selectByValue(reachListBox, npc.getReach());
		reachListBox.addChangeHandler(event -> {
			npc.setReach(Integer.parseInt(NPCUtils.getSelectedItemValue(reachListBox)));
			updateStatBlock();
		});

		// setup buttons
		typeButton.addClickHandler(event -> {
			typeDialog.init();
			typeDialog.show();
		});
		mobilityButton.addClickHandler(event -> {
			mobilityDialog.init();
			mobilityDialog.show();
		});
		templateButton.addClickHandler(event -> {
			templateDialog.init();
			templateDialog.show();
		});
		spellsButton.addClickHandler(event -> {
			magicDialog.init();
			magicDialog.show();
		});
		gearButton.addClickHandler(event -> {
			gearDialog.init();
			gearDialog.show();
		});
		treasureButton.addClickHandler(event -> {
			treasureDialog.init();
			treasureDialog.show();
		});
		attacksButton.addClickHandler(event -> {
			attacksDialog.init();
			attacksDialog.show();
		});
		randomNameButton.addClickHandler(event -> {
			randomNameDialog.init();
			randomNameDialog.show();
		});

	}

	private FantasyCraftNPC getFantasyCraftNpc() {
		return (FantasyCraftNPC) getNpc();
	}

	protected String getCookieName() {
		return COOKIE_NAME;
	}

	protected void updateWidgetsFromNPC() {
		super.updateWidgetsFromNPC();

		// update size, footprint and reach selectors
		NPCUtils.selectByValue(sizeListBox, getFantasyCraftNpc().getSize());
		NPCUtils.selectByValue(footprintXListBox, getFantasyCraftNpc().getFootprintX());
		NPCUtils.selectByValue(footprintYListBox, getFantasyCraftNpc().getFootprintY());
		NPCUtils.selectByValue(reachListBox, getFantasyCraftNpc().getReach());

		// update attribute selectors
		NPCUtils.selectByValue(strListBox, getFantasyCraftNpc().getStr());
		NPCUtils.selectByValue(dexListBox, getFantasyCraftNpc().getDex());
		NPCUtils.selectByValue(conListBox, getFantasyCraftNpc().getCon());
		NPCUtils.selectByValue(intListBox, getFantasyCraftNpc().getInt());
		NPCUtils.selectByValue(wisListBox, getFantasyCraftNpc().getWis());
		NPCUtils.selectByValue(chaListBox, getFantasyCraftNpc().getCha());

	}

	protected void updateTLStatBlock() {
		super.updateTLStatBlock();
		if (getThreatLevel() == 0) {
			this.treasureGenPanel.setVisible(false);
		} else {
			this.treasureGenPanel.setVisible(true);
		}
	}

}
