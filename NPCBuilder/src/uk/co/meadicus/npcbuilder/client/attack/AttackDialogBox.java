package uk.co.meadicus.npcbuilder.client.attack;

import uk.co.meadicus.npcbuilder.client.NPCEditor;
import uk.co.meadicus.npcbuilder.client.npc.FantasyCraftNPC;
import uk.co.meadicus.npcbuilder.client.util.FantasyCraftXPDialogBox;

import com.google.gwt.user.client.ui.Widget;

public class AttackDialogBox extends FantasyCraftXPDialogBox {
	
	private AttackEditWidget widget = null;
	private int attackIndex;

	public AttackDialogBox(NPCEditor npcEditor,
			FantasyCraftNPC npc, Attack attack) {
		this(npcEditor, npc, attack, -1);
		
	}
	public AttackDialogBox(NPCEditor npcEditor,
			FantasyCraftNPC npc, int attackIndex) {
		this(npcEditor, npc, npc.getAttacks().get(attackIndex), attackIndex);
	}

	private AttackDialogBox(NPCEditor npcEditor,
			FantasyCraftNPC npc, Attack attack, int attackIndex) {
		super("Attack", npcEditor, npc, true);
		setAttackIndex(attackIndex);
		
		if (attack instanceof WeaponAttack) {
			setWidget(new WeaponAttackWidget((WeaponAttack)attack));
		} else if (attack instanceof NaturalAttack) {
			setWidget(new NaturalAttackWidget((NaturalAttack)attack, this));
		} else if (attack instanceof DamageExtraordinaryAttack) {
			setWidget(new DamageExtraordinaryAttackWidget((DamageExtraordinaryAttack)attack, this));
		} else {
			setWidget(new SaveExtraordinaryAttackWidget((SaveExtraordinaryAttack)attack, this));
		}
		
		setContent();
		
	}

	@Override
	protected int calculateXP() {
		return (int)getWidget().getAttack().getXp().getValue();
	}

	@Override
	protected Widget getContent() {
		return widget;
	}

	@Override
	protected boolean onOk() {
		if (getAttackIndex() < 0) {
			getFantasyCraftNpc().getAttacks().add(getWidget().getAttack());
		} else {
			getFantasyCraftNpc().getAttacks().set(getAttackIndex(), getWidget().getAttack());
		}
		return true;
	}

	protected int getAttackIndex() {
		return attackIndex;
	}

	protected void setAttackIndex(int attackIndex) {
		this.attackIndex = attackIndex;
	}

	protected AttackEditWidget getWidget() {
		return widget;
	}

	protected void setWidget(AttackEditWidget widget) {
		this.widget = widget;
	}

}
