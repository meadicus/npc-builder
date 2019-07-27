package uk.co.meadicus.npcbuilder.client.treasure;

import uk.co.meadicus.npcbuilder.client.NPCEditor;
import uk.co.meadicus.npcbuilder.client.npc.FantasyCraftNPC;
import uk.co.meadicus.npcbuilder.client.util.FantasyCraftXPDialogBox;

import com.google.gwt.user.client.ui.Widget;

public class TreasureDialogBox extends FantasyCraftXPDialogBox {

	private TreasureWidget widget;
	
	public TreasureDialogBox(NPCEditor npcEditor, FantasyCraftNPC npc) {
		super("Treasure", npcEditor, npc, false);
		super.setContent();
	}

	
	protected int calculateXP() {
		return 0;
	}
	
	
	public void init() {
		widget.setupWidget(getFantasyCraftNpc().getTreasure());
		super.init();
	}
	
	protected Widget getContent() {
		widget = new TreasureWidget(getFantasyCraftNpc().getTreasure());
		return widget;
	}

	
	protected boolean onOk() {
		getFantasyCraftNpc().setTreasure(widget.getTreasureFromForm());
		return true;
	}

}
