package uk.co.meadicus.npcbuilder.client.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.co.meadicus.npcbuilder.client.NPCEditor;
import uk.co.meadicus.npcbuilder.client.npc.FantasyCraftNPC;
import uk.co.meadicus.npcbuilder.client.util.FantasyCraftXPDialogBox;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class TemplateDialogBox extends FantasyCraftXPDialogBox {
	
	private final Map<String, CheckBox> checkBoxes = new HashMap<String, CheckBox>();
	
	public TemplateDialogBox(NPCEditor npcEditor, FantasyCraftNPC npc) {
		super("Template", npcEditor, npc, true);
		setContent();
	}
	
	@Override
	protected Widget getContent() {
		HorizontalPanel panel = new HorizontalPanel();
		
		for (Map.Entry<String, List<String>> group : FantasyCraftTemplateFactory.getInstance().getTemplatesMap().entrySet()) {
			FlowPanel gpanel = new FlowPanel();
			Label label = new Label(group.getKey());
			gpanel.add(label);
			for (String template : group.getValue()) {
				CheckBox cb = new CheckBox(template);
				cb.setFormValue(template);
				this.checkBoxes.put(template, cb);
				cb.addClickHandler(this);
				FlowPanel fpanel = new FlowPanel();
				fpanel.add(cb);
				gpanel.add(fpanel);
			}
			panel.add(gpanel);
		}
		
		return panel;
	}

	@Override
	public void init() {
		for (String template : FantasyCraftTemplateFactory.getInstance().getTemplateNames()) {
			checkBoxes.get(template).setValue(false);
		}
		for (FantasyCraftTemplate template : getFantasyCraftNpc().getTemplates()) {
			checkBoxes.get(template.getName()).setValue(true);
		}
		super.init();
	}

	private List<FantasyCraftTemplate> templatesFromDialog() {
		List<FantasyCraftTemplate> types = new ArrayList<FantasyCraftTemplate>();
		for (String template : FantasyCraftTemplateFactory.getInstance().getTemplateNames()) {
			if (checkBoxes.get(template).getValue()) {
				types.add(FantasyCraftTemplateFactory.getInstance().getTemplate(template));
			}
		}
		return types;
	}

	@Override
	protected boolean onOk() {
		List<FantasyCraftTemplate> templates = templatesFromDialog();
		getFantasyCraftNpc().getTemplates().clear();
		getFantasyCraftNpc().getTemplates().addAll(templates);
		return true;
	}

	@Override
	protected int calculateXP() {
		int xptotal = 0;
		for (FantasyCraftTemplate template : templatesFromDialog()) {
			xptotal += template.getXp().getValue();
		}
		return xptotal;
	}
	
}
