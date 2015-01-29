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

public class NaturalAttackWidget extends AttackEditWidget {

	private static NaturalAttackWidgetUiBinder uiBinder = GWT
			.create(NaturalAttackWidgetUiBinder.class);

	interface NaturalAttackWidgetUiBinder extends
			UiBinder<Widget, NaturalAttackWidget> {
	}

	@UiField ListBox attackType;
	@UiField ListBox attackGrade;
	@UiField ListBox attackQuantity;
	@UiField ListBox damageType;
	@UiField TextBox aligned;
	@UiField TextBox diseased;
	@UiField TextBox venomous;
	@UiField ListBox armourPeircing;
	@UiField ListBox keen;
	@UiField ListBox reach;
	@UiField CheckBox bleed;
	@UiField CheckBox finesse;
	@UiField CheckBox grab;
	@UiField CheckBox trip;
	
	public NaturalAttackWidget(NaturalAttack attack, XPDialogBox eventHandler) {
		initWidget(uiBinder.createAndBindUi(this));
		
		// setup the list boxes
		NPCUtils.addListOptions(attackType, true, AttacksConfig.naturalAttacks, false);
		NPCUtils.selectByValue(attackType, attack.getTitle());
		NPCUtils.addListOptions(attackGrade, true, 1, 6, true, false);
		NPCUtils.selectByValue(attackGrade, attack.getGrade());
		NPCUtils.addListOptions(attackQuantity, true, 1, 100, false, false);
		NPCUtils.selectByValue(attackQuantity, attack.getQuantity());
		NPCUtils.addListOptions(damageType, true, AttacksConfig.damageTypes, false);
		NPCUtils.selectByValue(damageType, attack.getDamageType());
		NPCUtils.addListOptions(armourPeircing, true, 0, 21, AttacksConfig.APMultiplier, false, false);
		NPCUtils.selectByValue(armourPeircing, attack.getArmourPiercing());
		NPCUtils.addListOptions(keen, true, 0, 61, AttacksConfig.keenMultiplier, false, false);
		NPCUtils.selectByValue(keen, attack.getKeen());
		NPCUtils.addListOptions(reach, true, 0, 10, false, false);
		NPCUtils.selectByValue(reach, attack.getReach());
		
		// setup the text boxes
		aligned.setValue(attack.getAligned());
		diseased.setValue(attack.getDiseased());
		venomous.setValue(attack.getVenomous());
		
		// setup the checkboxes
		bleed.setValue(attack.isBleed());
		finesse.setValue(attack.isFinesse());
		grab.setValue(attack.isGrab());
		trip.setValue(attack.isTrip());
		
		// add the change handler to everything
		attackType.addChangeHandler(eventHandler);
		attackGrade.addChangeHandler(eventHandler);
		attackQuantity.addChangeHandler(eventHandler);
		damageType.addChangeHandler(eventHandler);
		armourPeircing.addChangeHandler(eventHandler);
		keen.addChangeHandler(eventHandler);
		reach.addChangeHandler(eventHandler);
		aligned.addChangeHandler(eventHandler);
		diseased.addChangeHandler(eventHandler);
		venomous.addChangeHandler(eventHandler);
		bleed.addClickHandler(eventHandler);
		finesse.addClickHandler(eventHandler);
		grab.addClickHandler(eventHandler);
		trip.addClickHandler(eventHandler);
	}

	@Override
	public Attack getAttack() {
		NaturalAttack attack = new NaturalAttack();
		
		attack.setTitle(NPCUtils.getSelectedItemValue(attackType));
		attack.setGrade(Integer.parseInt(NPCUtils.getSelectedItemValue(attackGrade)));
		attack.setQuantity(Integer.parseInt(NPCUtils.getSelectedItemValue(attackQuantity)));
		attack.setDamageType(NPCUtils.getSelectedItemValue(damageType));
		attack.setArmourPiercing(Integer.parseInt(NPCUtils.getSelectedItemValue(armourPeircing)));
		attack.setKeen(Integer.parseInt(NPCUtils.getSelectedItemValue(keen)));
		attack.setReach(Integer.parseInt(NPCUtils.getSelectedItemValue(reach)));
		attack.setAligned(aligned.getValue().trim());
		attack.setDiseased(diseased.getValue().trim());
		attack.setVenomous(venomous.getValue().trim());
		attack.setBleed(bleed.getValue());
		attack.setFinesse(finesse.getValue());
		attack.setGrab(grab.getValue());
		attack.setTrip(trip.getValue());
		
		return attack;
	}

}
