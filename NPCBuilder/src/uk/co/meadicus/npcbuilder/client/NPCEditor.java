package uk.co.meadicus.npcbuilder.client;

import java.util.Date;

import uk.co.meadicus.npcbuilder.client.npc.NPC;
import uk.co.meadicus.npcbuilder.client.quality.QualitiesDialogBox;
import uk.co.meadicus.npcbuilder.client.render.NPCRenderer;
import uk.co.meadicus.npcbuilder.client.skill.SkillsDialogBox;
import uk.co.meadicus.npcbuilder.client.stats.NPCStatter;
import uk.co.meadicus.npcbuilder.client.util.LoadNPCDialogBox;
import uk.co.meadicus.npcbuilder.client.util.NPCUtils;
import uk.co.meadicus.npcbuilder.client.xp.XP;
import uk.co.meadicus.npcbuilder.client.xp.XPExplainDialogBox;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.http.client.URL;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;

public abstract class NPCEditor extends Composite {
	
	private final NPC npc;
	
	@UiField TextBox title;
	@UiField Button loadPreviousButton;
	@UiField Button newNPCButton;
	@UiField HTML statBlock;
	@UiField ListBox initListBox;
	@UiField ListBox atkListBox;
	@UiField ListBox defListBox;
	@UiField ListBox resListBox;
	@UiField ListBox hlthListBox;
	@UiField ListBox compListBox;
	@UiField Button skillsButton;
	@UiField Button qualitiesButton;
	@UiField ListBox tlListBox;
	@UiField HTML tlStatBlock;
	@UiField Button explainButton;

	final SkillsDialogBox skillsDialog;
	final QualitiesDialogBox qualitiesDialog;
	final LoadNPCDialogBox loadNpcDialog;
	protected NPCStatter<NPC> statter;
	protected NPCRenderer<NPC> renderer;
	
	protected NPCEditor(NPC npc) {
		super();
		this.npc = npc;
		this.npc.reset();
		
		this.skillsDialog = new SkillsDialogBox(this, getNpc());
		this.qualitiesDialog = new QualitiesDialogBox(this, getNpc());
		this.loadNpcDialog = new LoadNPCDialogBox(this, getNpc());
	}
	
	protected void loadSavedNPC() {

		String savedStats = loadNPC();
		try {
			if (savedStats != null && !savedStats.trim().isEmpty()) {
				this.renderer.parseStatBlock(this.npc, savedStats);
			}
		} catch (Exception ex) {
			Window.alert("An error occured loading the previous NPC, some details may be incorrect.");
		}
	}

	protected void initCommonNPCWidgets() {
		
		title.setText(npc.getName());
		statBlock.setHTML(this.renderer.renderStatBlock(npc.getNPCToRender()));
		
		title.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				npc.setName(title.getText());
				updateStatBlock();
			}			
		});
		title.addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				npc.setName(title.getText());
				updateStatBlock();
			}			
		});	
		
		// setup the trait selectors
		NPCUtils.addListOptions(initListBox, true, 1, 11, true, false);
		NPCUtils.selectByValue(initListBox, npc.getInit());
		initListBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				npc.setInit(Integer.parseInt(initListBox.getValue(initListBox.getSelectedIndex())));
				updateStatBlock();
			}			
		});
		NPCUtils.addListOptions(atkListBox, true, 1, 11, true, false);
		NPCUtils.selectByValue(atkListBox, npc.getAtk());
		atkListBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				npc.setAtk(Integer.parseInt(atkListBox.getValue(atkListBox.getSelectedIndex())));
				updateStatBlock();
			}			
		});
		NPCUtils.addListOptions(defListBox, true, 1, 11, true, false);
		NPCUtils.selectByValue(defListBox, npc.getDef());
		defListBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				npc.setDef(Integer.parseInt(defListBox.getValue(defListBox.getSelectedIndex())));
				updateStatBlock();
			}			
		});
		NPCUtils.addListOptions(resListBox, true, 1, 11, true, false);
		NPCUtils.selectByValue(resListBox, npc.getRes());
		resListBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				npc.setRes(Integer.parseInt(resListBox.getValue(resListBox.getSelectedIndex())));	
				updateStatBlock();
			}			
		});
		NPCUtils.addListOptions(hlthListBox, true, 1, 11, true, false);
		NPCUtils.selectByValue(hlthListBox, npc.getHlth());
		hlthListBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				npc.setHlth(Integer.parseInt(hlthListBox.getValue(hlthListBox.getSelectedIndex())));
				updateStatBlock();				
			}			
		});
		NPCUtils.addListOptions(compListBox, true, 0, 11, true, false);
		NPCUtils.selectByValue(compListBox, npc.getComp());
		compListBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				npc.setComp(Integer.parseInt(compListBox.getValue(compListBox.getSelectedIndex())));
				updateStatBlock();
			}			
		});
		
		// setup TL selector
		NPCUtils.addListOptions(tlListBox, true, 0, 21, false, false);
		tlListBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				updateTLStatBlock();
			}
		});		
		
		// setup buttons
		skillsButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				skillsDialog.init();
				skillsDialog.show();
			}
		});
		qualitiesButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				qualitiesDialog.init();
				qualitiesDialog.show();
			}			
		});
		loadPreviousButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				loadNpcDialog.init();
				loadNpcDialog.show();
			}
		});
		newNPCButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				boolean ok = Window.confirm("This will discard your current NPC, are you sure?");
				if (ok) {
					npc.reset();
					updateStatBlock();
				}
			}
		});
		explainButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				XP xp = npc.getXp();
				XPExplainDialogBox explainDialogBox = new XPExplainDialogBox(xp);
				explainDialogBox.show();
			}
		});
		
	}

	public void updateStatBlock() {
		statBlock.setHTML(renderer.renderStatBlock(npc.getNPCToRender()));
		saveNPC();
		updateWidgetsFromNPC();
		updateTLStatBlock();
	}
	
	protected void updateTLStatBlock() {
		// render threat level block ?
		int threatLevel = getThreatLevel();
		if (threatLevel == 0) {
			tlStatBlock.setVisible(false);
			tlStatBlock.setHTML("");
		} else {
			tlStatBlock.setVisible(true);
			tlStatBlock.setHTML("<hr/>" + getStatter().statNPC(getNpc().getNPCToRender(), threatLevel));
			
		}
		
	}
	
	protected abstract String getCookieName();
	
	@SuppressWarnings("deprecation")
	protected void saveNPC() {
		Date currentDate = new Date();
		Date cookieDate = new Date(currentDate.getYear()+10, currentDate.getMonth(), currentDate.getDate());
		String stats = statBlock.getText();
		stats = URL.encodeQueryString(stats);
		Cookies.setCookie(getCookieName(), stats, cookieDate);
	}
	
	protected String loadNPC() {
		String stats = Cookies.getCookie(getCookieName());
		if (stats != null) {
			stats = URL.decodeQueryString(stats);
		}
		return stats;
	}
	
	protected void updateWidgetsFromNPC() {
		title.setText(npc.getName());

		NPCUtils.selectByValue(initListBox, npc.getInit());
		NPCUtils.selectByValue(atkListBox, npc.getAtk());
		NPCUtils.selectByValue(defListBox, npc.getDef());
		NPCUtils.selectByValue(resListBox, npc.getRes());
		NPCUtils.selectByValue(hlthListBox, npc.getHlth());
		NPCUtils.selectByValue(compListBox, npc.getComp());
	}
	
	public NPC getNpc() {
		return npc;
	}
	
	public int getThreatLevel() {
		return Integer.parseInt(NPCUtils.getSelectedItemValue(tlListBox));
	}
	
	public HandlerRegistration addTLListener(ChangeHandler handler) {
		return this.tlListBox.addChangeHandler(handler);
	}

	public NPCStatter<NPC> getStatter() {
		return statter;
	}

	@SuppressWarnings("unchecked")
	protected void setStatter(NPCStatter<? extends NPC> statter) {
		this.statter = (NPCStatter<NPC>) statter;
	}

	public NPCRenderer<NPC> getRenderer() {
		return renderer;
	}

	@SuppressWarnings("unchecked")
	protected void setRenderer(NPCRenderer<? extends NPC> renderer) {
		this.renderer = (NPCRenderer<NPC>) renderer;
	}
}
