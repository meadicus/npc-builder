package uk.co.meadicus.npcbuilder.client.gear;

import uk.co.meadicus.npcbuilder.client.NPCEditor;
import uk.co.meadicus.npcbuilder.client.npc.FantasyCraftNPC;
import uk.co.meadicus.npcbuilder.client.util.FantasyCraftXPDialogBox;

import com.google.gwt.user.client.ui.Widget;

public class FantasyCraftGearDialogBox extends FantasyCraftXPDialogBox {

	FantasyCraftGearWidget widget;
	
	public FantasyCraftGearDialogBox(NPCEditor npcEditor, FantasyCraftNPC npc) {
		super("Gear", npcEditor, npc, false);
		setContent();
	}

	@Override
	protected int calculateXP() {
		return 0;
	}

	@Override
	protected Widget getContent() {
		widget = new FantasyCraftGearWidget();
		return widget;
	}

	@Override
	public void init() {
		super.init();
		this.widget.init(getFantasyCraftNpc());
	}

	@Override
	protected boolean onOk() {
		getFantasyCraftNpc().setMountsAndVehicles(widget.getMountsAndVehicles());
		getFantasyCraftNpc().setGear(widget.getGear());
		return true;
	}

}
