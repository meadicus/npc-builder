package uk.co.meadicus.npcbuilder.client.treasure;

import uk.co.meadicus.npcbuilder.client.treasure.Treasure.TreasureType;
import uk.co.meadicus.npcbuilder.client.util.NPCUtils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class TreasureWidget extends Composite {

	private static TreasureWidgetUiBinder uiBinder = GWT
			.create(TreasureWidgetUiBinder.class);

	interface TreasureWidgetUiBinder extends UiBinder<Widget, TreasureWidget> {
	}

	@UiField ListBox anyListBox;
	@UiField ListBox coinListBox;
	@UiField ListBox gearListBox;
	@UiField ListBox lootListBox;
	@UiField ListBox magicListBox;
	@UiField ListBox trophiesListBox;
	
	protected TreasureWidget() {
	};
	
	public TreasureWidget(Treasure treasure) {
		initWidget(uiBinder.createAndBindUi(this));
		setupWidget(treasure);
	}
	
	public void setupWidget(Treasure treasure) {
		NPCUtils.addListOptions(anyListBox, true, 0, 99, false, false);
		NPCUtils.selectByValue(anyListBox, treasure.get(TreasureType.ANY));
		NPCUtils.addListOptions(coinListBox, true, 0, 99, false, false);
		NPCUtils.selectByValue(coinListBox, treasure.get(TreasureType.COIN));
		NPCUtils.addListOptions(gearListBox, true, 0, 99, false, false);
		NPCUtils.selectByValue(gearListBox, treasure.get(TreasureType.GEAR));
		NPCUtils.addListOptions(lootListBox, true, 0, 99, false, false);
		NPCUtils.selectByValue(lootListBox, treasure.get(TreasureType.LOOT));
		NPCUtils.addListOptions(magicListBox, true, 0, 99, false, false);
		NPCUtils.selectByValue(magicListBox, treasure.get(TreasureType.MAGIC));
		NPCUtils.addListOptions(trophiesListBox, true, 0, 99, false, false);
		NPCUtils.selectByValue(trophiesListBox, treasure.get(TreasureType.TROPHIES));
		
	}

	public Treasure getTreasureFromForm() {
		Treasure treasure = new Treasure();
		int any = Integer.parseInt(NPCUtils.getSelectedItemValue(anyListBox));
		treasure.set(TreasureType.ANY, any);
		int coin = Integer.parseInt(NPCUtils.getSelectedItemValue(coinListBox));
		treasure.set(TreasureType.COIN, coin);
		int gear = Integer.parseInt(NPCUtils.getSelectedItemValue(gearListBox));
		treasure.set(TreasureType.GEAR, gear);
		int loot = Integer.parseInt(NPCUtils.getSelectedItemValue(lootListBox));
		treasure.set(TreasureType.LOOT, loot);
		int magic = Integer.parseInt(NPCUtils.getSelectedItemValue(magicListBox));
		treasure.set(TreasureType.MAGIC, magic);
		int trophies = Integer.parseInt(NPCUtils.getSelectedItemValue(trophiesListBox));
		treasure.set(TreasureType.TROPHIES, trophies);
		return treasure;
	}
	
}
