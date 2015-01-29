package uk.co.meadicus.npcbuilder.client.spellcasting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import uk.co.meadicus.npcbuilder.client.NPCEditor;
import uk.co.meadicus.npcbuilder.client.npc.FantasyCraftNPC;
import uk.co.meadicus.npcbuilder.client.util.FantasyCraftXPDialogBox;
import uk.co.meadicus.npcbuilder.client.util.NPCUtils;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class SpellcastingDialogBox extends FantasyCraftXPDialogBox {

	private final List<TextBox> textBoxes = new ArrayList<TextBox>();
	private FlowPanel spellsPanel;
	private ListBox gradeListBox;
	
	public SpellcastingDialogBox(NPCEditor npcEditor, FantasyCraftNPC npc) {
		super("Spellcasting", npcEditor, npc, true);
		setContent();
	}

	@Override
	protected int calculateXP() {
		return spellcastingFromWidget().getGrade();
	}

	@Override
	protected Widget getContent() {
		FlowPanel panel = new FlowPanel();
		
		// grade selector
		InlineLabel label = new InlineLabel("Skill grade:");
		panel.add(label);
		this.gradeListBox = new ListBox();
		NPCUtils.addListOptions(gradeListBox, false, 0, 11, true, false);
		gradeListBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				updateSpellBoxes();
			}
		});
		panel.add(gradeListBox);
				
		// create the right number of spell boxes
		this.spellsPanel = new FlowPanel();
		panel.add(spellsPanel);
		updateSpellBoxes();		
		
		return panel;
	}

	@Override
	public void init() {
		NPCUtils.selectByValue(gradeListBox, getFantasyCraftNpc().getSpellcasting().getGrade());
		updateSpellBoxes();

		// update the content of the spell boxes
		for (int i = 0; i < getFantasyCraftNpc().getSpellcasting().getSpells().size(); ++i) {
			if (i < this.textBoxes.size()) {
				this.textBoxes.get(i).setText(getFantasyCraftNpc().getSpellcasting().getSpells().get(i));
			}
		}
		
		super.init();
	}

	@Override
	protected boolean onOk() {
		getFantasyCraftNpc().setSpellcasting(spellcastingFromWidget());
		return true;
	}
	
	private void updateSpellBoxes() {
		int grade = Integer.parseInt(NPCUtils.getSelectedItemValue(this.gradeListBox));
		
		while (this.textBoxes.size() < grade) {
			TextBox textBox = new TextBox();
			textBox.getElement().getStyle().setDisplay(Display.BLOCK);
			this.textBoxes.add(textBox);
			this.spellsPanel.add(textBox);
		}
		
		while (grade < this.textBoxes.size()) {
			TextBox lastTextBox = this.textBoxes.get(this.textBoxes.size() - 1);
			lastTextBox.removeFromParent();
			this.textBoxes.remove(this.textBoxes.size() - 1);
		}
	}
	
	private Spellcasting spellcastingFromWidget() {
		Spellcasting spellcasting = new Spellcasting();
		int grade = Integer.parseInt(NPCUtils.getSelectedItemValue(this.gradeListBox));
		spellcasting.setGrade(grade);
		
		for (int i = 0; i < this.textBoxes.size(); ++i) {
			if (i < grade) {
				spellcasting.getSpells().add(this.textBoxes.get(i).getText());
			}
		}
		
		Collections.sort(spellcasting.getSpells());
		
		return spellcasting;
	}

}
