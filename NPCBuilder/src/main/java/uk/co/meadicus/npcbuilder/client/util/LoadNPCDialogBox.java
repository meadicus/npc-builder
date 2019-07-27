package uk.co.meadicus.npcbuilder.client.util;

import uk.co.meadicus.npcbuilder.client.NPCEditor;
import uk.co.meadicus.npcbuilder.client.npc.NPC;

import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class LoadNPCDialogBox extends XPDialogBox {

	private final TextArea textArea;
	
	public LoadNPCDialogBox(NPCEditor npcEditor, NPC npc) {
		super("Load Previous NPC", npcEditor, npc, false);
		textArea = new TextArea();
		textArea.setCharacterWidth(60);
		textArea.setVisibleLines(8);
		super.setContent();
	}

	
	protected int calculateXP() {
		return 0;
	}

	
	protected Widget getContent() {
		return textArea;
	}

	
	protected boolean onOk() {
		String stats = textArea.getText();
		getNpcEditor().getRenderer().parseStatBlock(getNpc(), stats);
		getNpcEditor().updateStatBlock();
		return true;
	}

	
	public void init() {
		this.textArea.setText("");
		super.init();
	}

}
