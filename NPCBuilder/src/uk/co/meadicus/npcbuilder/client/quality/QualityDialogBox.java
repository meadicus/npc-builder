package uk.co.meadicus.npcbuilder.client.quality;

import uk.co.meadicus.npcbuilder.client.NPCEditor;
import uk.co.meadicus.npcbuilder.client.npc.NPC;
import uk.co.meadicus.npcbuilder.client.util.XPDialogBox;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("unchecked")
public class QualityDialogBox extends XPDialogBox {
	
	@SuppressWarnings("rawtypes")
	private QualityUserInterface qualityInterface;
	private final Quality quality;
	private FlowPanel panel;
	
	public QualityDialogBox(Quality quality, NPCEditor npcEditor, NPC npc) {
		super("Edit quality " + quality.getIdentifyingName(), npcEditor, npc, true);
		
		this.panel = new FlowPanel();
		
		if (quality instanceof OriginQuality) {
			this.qualityInterface = new OriginQualityUserInterface();
		} else if (quality instanceof AttributesQuality) {
			this.qualityInterface = new AttributesQualityUserInterface();
		} else if (quality instanceof ConfiguredClassAbilityQuality) {
			this.qualityInterface = new ConfiguredClassAbilityQualityUserInterface();
		} else if (quality instanceof ValuedDetailQuality) {
			this.qualityInterface = new ValuedDetailQualityUserInterface();
		} else if (quality instanceof DetailQuality) {
			this.qualityInterface = new DetailQualityUserInterface();
		} else if (quality instanceof SimpleQuality) {
			this.qualityInterface = new SimpleQualityUserInterface();
		} else if (quality instanceof CustomQuality) {
			this.qualityInterface = new CustomQualityUserInterface();
		}
		this.quality = quality;
				
		setContent();
	}

	@Override
	protected int calculateXP() {
		return (int)qualityFromForm().getXp().getValue();
	}

	@Override
	public void init() {
		// Setup the form from the quality
		this.qualityInterface.setupInterface(panel, quality, this, getNpc().getConfig());
		super.init();
	}

	private Quality qualityFromForm() {
		Quality formQuality = this.quality.clone();
		this.qualityInterface.populateFromInterface(panel, formQuality);		
		return formQuality;
	}
	
	@Override
	protected Widget getContent() {
		return this.panel;
	}

	@Override
	protected boolean onOk() {
		// What if the quality has been emptied, indicating removal of the quality
		Quality quality = qualityFromForm();
		if (quality.getXp().getValue() != 0 || quality.getBaseXp() == 0) {
			getNpc().getQualities().put(quality.getIdentifyingName(), quality);
		} else {
			getNpc().getQualities().remove(quality.getIdentifyingName());
		}
			
		return true;
	}

}
