package uk.co.meadicus.npcbuilder.client.npc.form;

import uk.co.meadicus.npcbuilder.client.NPCEditor;
import uk.co.meadicus.npcbuilder.client.npc.FantasyCraftNPC;
import uk.co.meadicus.npcbuilder.client.util.FantasyCraftXPDialogBox;

import com.google.gwt.user.client.ui.Widget;

public class MobilityDialogBox extends FantasyCraftXPDialogBox {

	private MobilityWidget mobilityWidget;
	
	public MobilityDialogBox(NPCEditor npcEditor, FantasyCraftNPC npc) {
		super("Mobility", npcEditor, npc, true);
		mobilityWidget = new MobilityWidget(this);
		setContent();
	}
	
	
	protected int calculateXP() {
		return (int)mobilityFromDialog().getXp().getValue();
	}

	
	protected Widget getContent() {
		return mobilityWidget;
	}

	
	
	public void init() {
		mobilityWidget.selectNPCValues(getFantasyCraftNpc().getMobility());
		super.init();
	}

	
	protected boolean onOk() {
		Mobility mobility = mobilityWidget.mobilityFromWidget();
		getFantasyCraftNpc().setMobility(mobility);
		return true;
	}
	
	private Mobility mobilityFromDialog() {
		return mobilityWidget.mobilityFromWidget();
	}

}
