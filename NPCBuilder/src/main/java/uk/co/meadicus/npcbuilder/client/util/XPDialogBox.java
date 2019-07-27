package uk.co.meadicus.npcbuilder.client.util;

import uk.co.meadicus.npcbuilder.client.NPCEditor;
import uk.co.meadicus.npcbuilder.client.npc.NPC;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public abstract class XPDialogBox extends Composite implements ChangeHandler, ClickHandler {

	private static XPDialogBoxUiBinder uiBinder = GWT
			.create(XPDialogBoxUiBinder.class);

	interface XPDialogBoxUiBinder extends UiBinder<Widget, XPDialogBox> {
	}

	protected @UiField DialogBox mainPanel;
	protected @UiField FlowPanel contentPanel;
	protected @UiField FlowPanel xpPanel;
	protected @UiField Button okButton;
	protected @UiField Button cancelButton;
	protected @UiField Label xpTotal;

	private final NPC npc;
	private final NPCEditor npcEditor;
	
	protected XPDialogBox(final String title, final NPCEditor npcEditor, final NPC npc, boolean hasXp) {
		this(title, npcEditor, npc, hasXp, false);
	}

	protected XPDialogBox(final String title, final NPCEditor npcEditor, final NPC npc, boolean hasXp, boolean doneButtonOnly) {
		initWidget(uiBinder.createAndBindUi(this));
		
		mainPanel.setText(title);
		
		this.npc = npc;
		this.npcEditor = npcEditor;
		
		// set the xp score visibility
		if (!hasXp) {
			xpPanel.setVisible(false);
		}
		
		// assign button events
		okButton.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (onOk()) {
					hide();
					if (npc != null) {
						updateStatBlock();
					}
				}
			}
		});
		
		if (doneButtonOnly) {
			okButton.setText("Close");
			cancelButton.removeFromParent();
		} else {
			cancelButton.addClickHandler(new ClickHandler() {
				
				public void onClick(ClickEvent event) {
					hide();
				}
			});
		}
		
	}
	
	protected void setContent() {
		// set dialog content
		contentPanel.add(getContent());
	}

	public void init() {
		updateXP();
	}

	protected abstract Widget getContent();
	
	protected abstract boolean onOk();

	protected NPC getNpc() {
		return npc;
	}
		
	public void show() {
		mainPanel.center();
	}
	
	public void hide() {
		mainPanel.hide();
	}
	
	protected NPCEditor getNpcEditor() {
		return npcEditor;
	}

	public void updateStatBlock() {
		getNpcEditor().updateStatBlock();
	}	
	
	protected abstract int calculateXP();
	
	public void updateXP() {
		showXP(calculateXP());
	}
	
	protected void showXP(int xp) {
		xpTotal.setText(Integer.toString(xp));
	}

	
	public void onChange(ChangeEvent event) {
		// recalculate and update xp total
		updateXP();
	}

	
	public void onClick(ClickEvent event) {
		// recalculate and update xp total
		updateXP();
	}
	
}
