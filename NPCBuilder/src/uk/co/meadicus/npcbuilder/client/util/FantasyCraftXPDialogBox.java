package uk.co.meadicus.npcbuilder.client.util;

import uk.co.meadicus.npcbuilder.client.NPCEditor;
import uk.co.meadicus.npcbuilder.client.npc.FantasyCraftNPC;

public abstract class FantasyCraftXPDialogBox extends XPDialogBox {

	public FantasyCraftXPDialogBox(String title, NPCEditor npcEditor, FantasyCraftNPC npc,
			boolean hasXp) {
		super(title, npcEditor, npc, hasXp);
	}
	public FantasyCraftXPDialogBox(String title, NPCEditor npcEditor, FantasyCraftNPC npc,
			boolean hasXp, boolean doneButtonOnly) {
		super(title, npcEditor, npc, hasXp, doneButtonOnly);
	}

	protected FantasyCraftNPC getFantasyCraftNpc() {
		return (FantasyCraftNPC) getNpc();
	}

}
