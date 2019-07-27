package uk.co.meadicus.npcbuilder.client.attack;

import uk.co.meadicus.npcbuilder.client.NPCEditor;
import uk.co.meadicus.npcbuilder.client.npc.FantasyCraftNPC;
import uk.co.meadicus.npcbuilder.client.util.FantasyCraftXPDialogBox;

import com.google.gwt.user.client.ui.Widget;

public class AttacksDialogBox extends FantasyCraftXPDialogBox {

	private AttacksDialogWidget widget = null;
	
	public AttacksDialogBox(NPCEditor npcEditor,
			FantasyCraftNPC npc) {
		super("Attacks", npcEditor, npc, true, true);
		widget = new AttacksDialogWidget(getFantasyCraftNpc(), getNpcEditor(), this);
		setContent();
	}

	
	public void init() {
		this.widget.setAttacksList(getFantasyCraftNpc());
		super.init();
	}

	
	protected int calculateXP() {
		return widget.getXp();
	}

	
	protected Widget getContent() {
		return widget;
	}

	
	protected boolean onOk() {
		return true;
	}

}
