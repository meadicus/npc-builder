package uk.co.meadicus.npcbuilder.client.util;

import uk.co.meadicus.npcbuilder.client.NPCEditor;
import uk.co.meadicus.npcbuilder.client.npc.SpycraftNPC;

public abstract class SpycraftXPDialogBox extends XPDialogBox {

	protected SpycraftXPDialogBox(String title, NPCEditor npcEditor, SpycraftNPC npc, boolean hasXp) {
		super(title, npcEditor, npc, hasXp);
	}

	protected SpycraftXPDialogBox(String title, NPCEditor npcEditor, SpycraftNPC npc,
			boolean hasXp, boolean doneButtonOnly) {
		super(title, npcEditor, npc, hasXp, doneButtonOnly);
	}

	protected SpycraftNPC getSpycraftNpc() {
		return (SpycraftNPC) getNpc();
	}
	
}
