package uk.co.meadicus.npcbuilder.client.generators;

import uk.co.meadicus.npcbuilder.client.NPCEditor;
import uk.co.meadicus.npcbuilder.client.npc.NPC;
import uk.co.meadicus.npcbuilder.client.util.XPDialogBox;

import com.google.gwt.user.client.ui.Widget;

public class NameGeneratorDialogBox extends XPDialogBox {

	private NameGenerator nameGenerator = null;
	
	public NameGeneratorDialogBox(NPCEditor npcEditor, NPC npc) {
		this(npcEditor, npc, true);
	}

	public NameGeneratorDialogBox(NPCEditor npcEditor, NPC npc, boolean useButton) {
		super("Random Name Generator", npcEditor, npc, false, !useButton);
		nameGenerator = new NameGenerator();
		if (useButton) {
			this.okButton.setText("Use Name");
		}
		setContent();
	}

	@Override
	protected int calculateXP() {
		return 0;
	}

	@Override
	protected Widget getContent() {
		return this.nameGenerator;
	}

	@Override
	protected boolean onOk() {
		if (getNpc() != null) {
			getNpc().setName(this.nameGenerator.getName());
		}
		return true;
	}

}
