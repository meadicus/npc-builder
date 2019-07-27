package uk.co.meadicus.npcbuilder.client.attack;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class WeaponAttackWidget extends AttackEditWidget {

	private static WeaponAttackWidgetUiBinder uiBinder = GWT
			.create(WeaponAttackWidgetUiBinder.class);

	interface WeaponAttackWidgetUiBinder extends
			UiBinder<Widget, WeaponAttackWidget> {
	}
	
	@UiField TextBox weaponName;
	@UiField TextArea weaponDescription;

	public WeaponAttackWidget(WeaponAttack attack) {
		initWidget(uiBinder.createAndBindUi(this));
		
		this.weaponName.setValue(attack.getName());
		this.weaponDescription.setValue(attack.getDescription());
	}

	
	public Attack getAttack() {
		String name = this.weaponName.getValue();
		String description = this.weaponDescription.getValue();
		WeaponAttack attack = new WeaponAttack();
		attack.setName(name);
		attack.setDescription(description);
		return attack;
	}

}
