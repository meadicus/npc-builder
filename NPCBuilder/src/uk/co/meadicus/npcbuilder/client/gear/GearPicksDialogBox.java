package uk.co.meadicus.npcbuilder.client.gear;

import java.util.List;

import uk.co.meadicus.npcbuilder.client.NPCEditor;
import uk.co.meadicus.npcbuilder.client.npc.SpycraftNPC;
import uk.co.meadicus.npcbuilder.client.util.SpycraftXPDialogBox;

import com.google.gwt.user.client.ui.Widget;

public class GearPicksDialogBox extends SpycraftXPDialogBox {

	private GearPicksDialogWidget contentWidget;
		
	public GearPicksDialogBox(NPCEditor npcEditor, SpycraftNPC npc) {
		super("Gear", npcEditor, npc, true);
		this.contentWidget = new GearPicksDialogWidget(this);
		setContent();
	}	

	@Override
	protected Widget getContent() {
		return this.contentWidget;
	}

	@Override
	public void init() {
		this.contentWidget.setFromNPC(getSpycraftNpc());
		
		super.init();				
	}
	
	@Override
	protected int calculateXP() {
		List<GearPick> weapons = this.contentWidget.getWeaponPicks(false);
		List<GearPick> gear = this.contentWidget.getGearPicks(false);
		List<GearPick> vehicles = this.contentWidget.getVehiclePicks(false);
		return (int)getSpycraftNpc().xpFromGear(weapons, gear, vehicles).getValue();
	}
	
	@Override
	protected boolean onOk() {
		// get the picks from the form
		List<GearPick> weapons = this.contentWidget.getWeaponPicks(true);
		List<GearPick> gear = this.contentWidget.getGearPicks(true);
		List<GearPick> vehicles = this.contentWidget.getVehiclePicks(true);
				
		// add the picks to the npc
		getSpycraftNpc().getWeapons().clear();
		getSpycraftNpc().getWeapons().addAll(weapons);
		getSpycraftNpc().getGear().clear();
		getSpycraftNpc().getGear().addAll(gear);
		getSpycraftNpc().getVehicles().clear();
		getSpycraftNpc().getVehicles().addAll(vehicles);
		
		return true;
	}
	
}
