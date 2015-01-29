package uk.co.meadicus.npcbuilder.client;

import uk.co.meadicus.npcbuilder.client.generators.NameGeneratorDialogBox;
import uk.co.meadicus.npcbuilder.client.generators.TreasureGeneratorDialogBox;
import uk.co.meadicus.npcbuilder.client.npc.FantasyCraftNPCImpl;
import uk.co.meadicus.npcbuilder.client.npc.NPC;
import uk.co.meadicus.npcbuilder.client.npc.SpycraftNPCImpl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class AppWidget extends Composite {

	private static AppWidgetUiBinder uiBinder = GWT
			.create(AppWidgetUiBinder.class);

	interface AppWidgetUiBinder extends UiBinder<Widget, AppWidget> {
	}

	@UiField ControlBar controlBar;
	@UiField ChooseBuilderPanel choosePanel;
	@UiField Panel editArea;
	@UiField Panel loadingPanel;

	private NPC npc;

	enum BUILDER_TYPE {
		SPYCRAFT,
		FANTASYCRAFT;
	}
	
	public AppWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		
		loadingPanel.setVisible(false);
		
		ClickHandler fcClickHandler = new CustomClickHandler(this, BUILDER_TYPE.FANTASYCRAFT);
		ClickHandler scClickHandler = new CustomClickHandler(this, BUILDER_TYPE.SPYCRAFT);
		
		// bind buttons
		choosePanel.fcButton.addClickHandler(fcClickHandler);
		controlBar.fcButton.addClickHandler(fcClickHandler);
		choosePanel.scButton.addClickHandler(scClickHandler);
		controlBar.scButton.addClickHandler(scClickHandler);
		
		choosePanel.fcNameButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				NameGeneratorDialogBox dialog = new NameGeneratorDialogBox(null, null, false);
				dialog.show();
			}
		});
		choosePanel.fcTreasureButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				TreasureGeneratorDialogBox dialog = new TreasureGeneratorDialogBox();
				dialog.show();
				
			}
		});
		
	}
	
	private static class CustomClickHandler implements ClickHandler {

		private final AppWidget appWidget;
		private final BUILDER_TYPE type;
				
		public CustomClickHandler(AppWidget appWidget, BUILDER_TYPE type) {
			super();
			this.appWidget = appWidget;
			this.type = type;
		}

		@Override
		public void onClick(ClickEvent event) {
			switch(this.type)
			{
			case FANTASYCRAFT:
				this.appWidget.startFantasyCraftEditor();
				break;
			case SPYCRAFT:
				this.appWidget.startSpycraftEditor();
			}
			
		}
	}

	/**
	 * Launch the spycraft NPC builder
	 */
	public void startSpycraftEditor() {
		startSpycraftEditor(null);
	}

	/**
	 * Launch the spycraft NPC builder
	 */
	public void startSpycraftEditor(String statblock) {
		this.npc = SpycraftNPCImpl.getInstance();
		this.npc.setName("Bob the NPC");
		
		NPCEditor npcEditor = new SpycraftNPCEditor((SpycraftNPCImpl)this.npc, this.npc.getConfig().getQualityFactory());		
		
		editArea.clear();
		editArea.add(npcEditor);
		
		controlBar.scButton.setEnabled(false);
		controlBar.fcButton.setEnabled(true);
	}
	
	/**
	 * Launch the fantasy craft NPC builder
	 */
	public void startFantasyCraftEditor() {
		startFantasyCraftEditor(null);
	}

	/**
	 * Launch the fantasy craft NPC builder
	 */
	public void startFantasyCraftEditor(String statblock) {
		this.npc = FantasyCraftNPCImpl.getInstance();
		this.npc.setName("Bob the FC NPC");
		
		NPCEditor npcEditor = new FantasyCraftNPCEditor((FantasyCraftNPCImpl)this.npc, this.npc.getConfig().getQualityFactory());		
		
		editArea.clear();
		editArea.add(npcEditor);
		
		controlBar.fcButton.setEnabled(false);
		controlBar.scButton.setEnabled(true);
	}
	
}
