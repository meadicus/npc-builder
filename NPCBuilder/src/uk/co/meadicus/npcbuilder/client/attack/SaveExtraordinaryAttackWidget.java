package uk.co.meadicus.npcbuilder.client.attack;

import uk.co.meadicus.npcbuilder.client.util.NPCUtils;
import uk.co.meadicus.npcbuilder.client.util.XPDialogBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class SaveExtraordinaryAttackWidget extends AttackEditWidget implements ClickHandler {

	private static SaveExtraordinaryAttackWidgetUiBinder uiBinder = GWT
			.create(SaveExtraordinaryAttackWidgetUiBinder.class);

	interface SaveExtraordinaryAttackWidgetUiBinder extends
			UiBinder<Widget, SaveExtraordinaryAttackWidget> {
	}

	@UiField TextBox attackName;
	@UiField ListBox attackType;
	@UiField ListBox attackGrade;
	@UiField RadioButton noUpgrade;
	@UiField RadioButton attackLink;
	@UiField TextBox linkedAttack;
	@UiField RadioButton areaUpgrade;
	@UiField AreaWidget area;
	
	private final ClickHandler parentClickHandler;
	
	public SaveExtraordinaryAttackWidget(SaveExtraordinaryAttack attack, XPDialogBox eventHandler) {
		initWidget(uiBinder.createAndBindUi(this));
		
		parentClickHandler = eventHandler;
		
		// setup list boxes
		NPCUtils.addListOptions(attackType, true, AttacksConfig.saveAttacks.keySet(), false);
		NPCUtils.selectByValue(attackType, attack.getTitle());
		NPCUtils.addListOptions(attackGrade, true, 1, 6, true, false);
		NPCUtils.selectByValue(attackGrade, attack.getGrade());
		
		// setup the text boxes
		attackName.setValue(attack.getName());
		linkedAttack.setValue(attack.getSupernaturalAttack());
		
		// setup area widget
		area.setArea(attack.getArea());
		
		// setup the radio buttons
		if (!attack.getSupernaturalAttack().isEmpty()) {
			attackLink.setValue(true);
			area.setEnabled(false);
		} else if (attack.getArea() != null) {
			areaUpgrade.setValue(true);
			linkedAttack.setEnabled(false);
		} else {
			noUpgrade.setValue(true);
			area.setEnabled(false);
			linkedAttack.setEnabled(false);
		}
		
		// bind events to changing the radio selection
		attackLink.addClickHandler(this);
		areaUpgrade.addClickHandler(this);
		noUpgrade.addClickHandler(this);
		
		// bind xp update parent event
		attackType.addChangeHandler(eventHandler);
		attackGrade.addChangeHandler(eventHandler);
		linkedAttack.addChangeHandler(eventHandler);
		area.addChangeHandler(eventHandler);
	}

	@Override
	public Attack getAttack() {
		SaveExtraordinaryAttack attack = new SaveExtraordinaryAttack();
		
		attack.setName(attackName.getValue().trim());
		attack.setTitle(NPCUtils.getSelectedItemValue(attackType));
		attack.setGrade(Integer.parseInt(NPCUtils.getSelectedItemValue(attackGrade)));
		
		if (attackLink.getValue()) {
			attack.setSupernaturalAttack(linkedAttack.getValue().trim());
		} else if (areaUpgrade.getValue()) {
			attack.setArea(area.getArea());
		}
		
		return attack;
	}

	/**
	 * This event action is triggered by changing the radio selection status
	 */
	@Override
	public void onClick(ClickEvent event) {
		if (noUpgrade.getValue()) {
			area.setEnabled(false);
			linkedAttack.setEnabled(false);			
		} else if (attackLink.getValue()) {
			area.setEnabled(false);
			linkedAttack.setEnabled(true);
		} else if (areaUpgrade.getValue()) {
			linkedAttack.setEnabled(false);
			area.setEnabled(true);
		}
		this.parentClickHandler.onClick(event);
	}

}
