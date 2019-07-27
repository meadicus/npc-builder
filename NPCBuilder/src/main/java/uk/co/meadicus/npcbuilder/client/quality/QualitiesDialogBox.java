package uk.co.meadicus.npcbuilder.client.quality;

import java.util.Map;

import uk.co.meadicus.npcbuilder.client.NPCEditor;
import uk.co.meadicus.npcbuilder.client.npc.NPC;
import uk.co.meadicus.npcbuilder.client.util.XPDialogBox;

import com.google.gwt.user.client.ui.Widget;

public class QualitiesDialogBox extends XPDialogBox {

	private QualitiesDialogWidget qualitiesWidget = null;
	
	public QualitiesDialogBox(NPCEditor npcEditor, NPC npc) {
		super("Qualities", npcEditor, npc, true, true);
		this.qualitiesWidget = new QualitiesDialogWidget(getNpc(), getNpcEditor(), this);
		setContent();
	}

	
	protected Widget getContent() {
		return this.qualitiesWidget;
	}

	
	public void init() {
		this.qualitiesWidget.init();
		super.init();
	}

	
	protected int calculateXP() {
		Map<String, Quality> qualities = getNpc().getQualities();
		int xp = 0;
		for (String qualityName : qualities.keySet()) {
			xp += qualities.get(qualityName).getXp().getValue();
		}
		return xp;
	}
	
	
	protected boolean onOk() {
		return true;
	}
	
	

}
