package uk.co.meadicus.npcbuilder.client.treasure;

import uk.co.meadicus.npcbuilder.client.NPCEditor;
import uk.co.meadicus.npcbuilder.client.npc.FantasyCraftNPC;
import uk.co.meadicus.npcbuilder.client.treasure.Treasure.TreasureType;
import uk.co.meadicus.npcbuilder.client.treasure.generator.RandomTableItem;
import uk.co.meadicus.npcbuilder.client.treasure.generator.TreasureGenSpec;
import uk.co.meadicus.npcbuilder.client.treasure.generator.TreasureGenerator;
import uk.co.meadicus.npcbuilder.client.treasure.generator.TreasureItem;
import uk.co.meadicus.npcbuilder.client.treasure.generator.support.TableRoll;
import uk.co.meadicus.npcbuilder.client.util.NPCUtils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class TreasureGeneratorWidget extends TreasureWidget {

	private static TreasureGeneratorWidgetUiBinder uiBinder = GWT
			.create(TreasureGeneratorWidgetUiBinder.class);

	interface TreasureGeneratorWidgetUiBinder extends
			UiBinder<Widget, TreasureGeneratorWidget> {
	}
	
	@UiField Button generate;
	@UiField ListBox tlListBox;
	@UiField TextBox xpTextBox;
	@UiField ListBox modListBox;
	@UiField Panel results;
	@UiField Panel npcSpec;
	@UiField Panel treasureCount;
	@UiField Widget tlhintbox;
	@UiField Element tlhint;
	
	private TreasureGenerator generator;
	private FantasyCraftNPC npc;
	private NPCEditor npcEditor;
	
	public TreasureGeneratorWidget() {
		this(null, null);
	}

	public TreasureGeneratorWidget(NPCEditor npcEditor, FantasyCraftNPC npc) {
		initWidget(uiBinder.createAndBindUi(this));
		this.npc = npc;
		this.npcEditor = npcEditor;
		Treasure treasure;
		if (npc == null) {
			treasure = new Treasure();
			tlhintbox.setVisible(false);
		} else {
			treasure = npc.getTreasure();
			npcSpec.setVisible(false);
			treasureCount.setVisible(false);
			npcEditor.addTLListener(new TLChangeHandler());
		}
		setupWidget(treasure);
		generator = new TreasureGenerator();
	}

	private class TLChangeHandler implements ChangeHandler {
		
		@Override
		public void onChange(ChangeEvent event) {
			tlhint.setInnerText(Integer.toString(npcEditor.getThreatLevel()));
		}
		
	}
	public void setupWidget(Treasure treasure) {
		super.setupWidget(treasure);
		
		generate.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				generate();
			}
		});
		if (npc == null) {
			NPCUtils.addListOptions(tlListBox, true, 1, 21, false, false);
		}
		NPCUtils.addListOptions(modListBox, true, -25, 30, 5, false, false);
		NPCUtils.selectByValue(modListBox, 0);
	}
	
	public void generate() {
		// clear output
		results.clear();
		UListElement list = results.getElement().getOwnerDocument().createULElement();
		results.getElement().appendChild(list);
		// read spec		
		TreasureGenSpec spec = getNPCSpec();
		// loop through treasure types and produce treasure
		Treasure treasure = (npc != null) ? npc.getTreasure() : getTreasureFromForm();
		for (TreasureType type : TreasureType.values()) {
			int quantity = treasure.get(type);
			for (int i = 0; i < quantity; ++i) {
				TreasureItem item = generator.randomItem(type, spec);
				LIElement li = list.getOwnerDocument().createLIElement();
				renderTreasureItem(item, li);
				list.appendChild(li);
			}
		}
	}

	private TreasureGenSpec getNPCSpec() {
		TreasureGenSpec spec;
		int mod = Integer.parseInt(NPCUtils.getSelectedItemValue(modListBox));
		if (npcEditor != null && npc != null) {
			spec = new TreasureGenSpec((int)npc.getXp().getValue(), this.npcEditor.getThreatLevel(), mod);
		} else {
			int tl = Integer.parseInt(NPCUtils.getSelectedItemValue(tlListBox));
			int xp = 0;
			try {
				xp = Integer.parseInt(xpTextBox.getValue().replaceAll("[a-zA-Z]", ""));
			} catch (NumberFormatException ex) {
				Window.alert("XP '" + xpTextBox.getValue() + "' isn't a number, using zero");
			}
			spec = new TreasureGenSpec(xp, tl, mod);
		}
		return spec;
	}

	private void renderTreasureItem(TreasureItem item, LIElement li) {
		String description = item.getType().toString() + " : ";
		description += item.getDescription();
		if (item.getValue() != null) {
			description += " (" + item.getValue() + "sp)";
		}
		if (item.getPageRef() != null) {
			description += " (page ref: " + item.getPageRef() + ")";
		}
		description += " <span class='treasureroll'>[";
		String delim = "";
		for (TableRoll roll : item.getDiceRolls()) {
			description += delim + roll.getTableName() + " : " + roll.getRoll();
			delim = ", ";
		}
		description += "]</span>";
		li.setInnerHTML(description);
		
		if (!item.getChildItems().isEmpty()) {
			UListElement childList = li.getOwnerDocument().createULElement();
			for (RandomTableItem childItem : item.getChildItems()) {
				if (childItem instanceof TreasureItem) {
					TreasureItem childTreasure = (TreasureItem)childItem;
					LIElement childLi = childList.getOwnerDocument().createLIElement();
					renderTreasureItem(childTreasure, childLi);
					childList.appendChild(childLi);
				}
			}
			li.appendChild(childList);
		}
		
	}

}
