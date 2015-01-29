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

	@Override
	public void init() {
		this.widget.setAttacksList(getFantasyCraftNpc());
		super.init();
	}

	@Override
	protected int calculateXP() {
		return widget.getXp();
	}

	@Override
	protected Widget getContent() {
		return widget;
	}

	@Override
	protected boolean onOk() {
		return true;
	}

}
