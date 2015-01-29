package uk.co.meadicus.npcbuilder.client.attack;

import uk.co.meadicus.npcbuilder.client.util.NPCUtils;
import uk.co.meadicus.npcbuilder.client.util.XPDialogBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class DamageExtraordinaryAttackWidget extends AttackEditWidget {

	private static DamageExtraordinaryAttackWidgetUiBinder uiBinder = GWT
			.create(DamageExtraordinaryAttackWidgetUiBinder.class);

	interface DamageExtraordinaryAttackWidgetUiBinder extends
			UiBinder<Widget, DamageExtraordinaryAttackWidget> {
	}

	@UiField TextBox attackName;
	@UiField ListBox attackType;
	@UiField ListBox attackGrade;
	@UiField ListBox damageType;
	@UiField TextBox aligned;
	@UiField TextBox diseased;
	@UiField TextBox venomous;
	@UiField ListBox armourPeircing;
	@UiField ListBox keen;
	@UiField CheckBox bleed;
	@UiField AreaWidget area;
	
	public DamageExtraordinaryAttackWidget(DamageExtraordinaryAttack attack, XPDialogBox eventHandler) {
		initWidget(uiBinder.createAndBindUi(this));
		
		// setup list boxes
		NPCUtils.addListOptions(attackType, true, AttacksConfig.damageAttacks.keySet(), false);
		NPCUtils.selectByValue(attackType, attack.getTitle());
		NPCUtils.addListOptions(attackGrade, true, 1, 6, true, false);
		NPCUtils.selectByValue(attackGrade, attack.getGrade());
		NPCUtils.addListOptions(damageType, true, AttacksConfig.damageTypes, false);
		NPCUtils.selectByValue(damageType, attack.getDamageType());
		NPCUtils.addListOptions(armourPeircing, true, 0, 21, AttacksConfig.APMultiplier, false, false);
		NPCUtils.selectByValue(armourPeircing, attack.getArmourPiercing());
		NPCUtils.addListOptions(keen, true, 0, 61, AttacksConfig.keenMultiplier, false, false);
		NPCUtils.selectByValue(keen, attack.getKeen());
				
		// setup text boxes
		attackName.setValue(attack.getName());
		aligned.setValue(attack.getAligned());
		diseased.setValue(attack.getDiseased());
		venomous.setValue(attack.getVenomous());
		
		// setup checkboxes
		bleed.setValue(attack.isBleed());
		
		// setup area
		area.setArea(attack.getArea());
		
		// add the change handler to everything
		attackType.addChangeHandler(eventHandler);
		attackGrade.addChangeHandler(eventHandler);
		damageType.addChangeHandler(eventHandler);
		armourPeircing.addChangeHandler(eventHandler);
		keen.addChangeHandler(eventHandler);
		aligned.addChangeHandler(eventHandler);
		diseased.addChangeHandler(eventHandler);
		venomous.addChangeHandler(eventHandler);
		bleed.addClickHandler(eventHandler);
		area.addChangeHandler(eventHandler);
	}

	@Override
	public Attack getAttack() {
		DamageExtraordinaryAttack attack = new DamageExtraordinaryAttack();
		
		attack.setName(attackName.getValue().trim());
		attack.setTitle(NPCUtils.getSelectedItemValue(attackType));
		attack.setGrade(Integer.parseInt(NPCUtils.getSelectedItemValue(attackGrade)));
		attack.setDamageType(NPCUtils.getSelectedItemValue(damageType));
		attack.setAligned(aligned.getValue().trim());
		attack.setDiseased(diseased.getValue().trim());
		attack.setVenomous(venomous.getValue().trim());
		attack.setArmourPiercing(Integer.parseInt(NPCUtils.getSelectedItemValue(armourPeircing)));
		attack.setKeen(Integer.parseInt(NPCUtils.getSelectedItemValue(keen)));
		attack.setBleed(bleed.getValue());
		attack.setArea(area.getArea());
		
		return attack;
	}

}
