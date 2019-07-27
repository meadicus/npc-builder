package uk.co.meadicus.npcbuilder.client.npc.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.co.meadicus.npcbuilder.client.NPCEditor;
import uk.co.meadicus.npcbuilder.client.npc.FantasyCraftNPC;
import uk.co.meadicus.npcbuilder.client.npc.FantasyCraftConfig.FCNPCType;
import uk.co.meadicus.npcbuilder.client.util.FantasyCraftXPDialogBox;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class MultiTypeDialogBox extends FantasyCraftXPDialogBox {
	
	private final Map<FCNPCType, CheckBox> checkBoxes = new HashMap<FCNPCType, CheckBox>();
	
	public MultiTypeDialogBox(NPCEditor npcEditor, FantasyCraftNPC npc) {
		super("Type", npcEditor, npc, true);
		setContent();
	}
	
	
	protected Widget getContent() {
		FlowPanel panel = new FlowPanel();
		
		for (FCNPCType type : FCNPCType.values()) {
			CheckBox cb = new CheckBox(type.toString());
			cb.setFormValue(type.toString());
			this.checkBoxes.put(type, cb);
			cb.addClickHandler(this);
			FlowPanel fpanel = new FlowPanel();
			fpanel.add(cb);
			panel.add(fpanel);
		}
		
		return panel;
	}

	
	public void init() {
		for (FCNPCType type : FCNPCType.values()) {			
			if (getFantasyCraftNpc().getType().contains(type)) {
				checkBoxes.get(type).setValue(true);
			} else {
				checkBoxes.get(type).setValue(false);
			}
		}
		super.init();
	}

	private List<FCNPCType> typesFromDialog() {
		List<FCNPCType> types = new ArrayList<FCNPCType>();
		for (FCNPCType type : FCNPCType.values()) {
			if (checkBoxes.get(type).getValue()) {
				types.add(type);
			}
		}
		return types;
	}

	
	protected boolean onOk() {
		List<FCNPCType> types = typesFromDialog();
		getFantasyCraftNpc().getType().clear();
		getFantasyCraftNpc().getType().addAll(types);
		return true;
	}

	
	protected int calculateXP() {
		int xptotal = 0;
		for (FCNPCType type : typesFromDialog()) {
			xptotal += type.getXpValue();
		}
		return xptotal;
	}
	
}
