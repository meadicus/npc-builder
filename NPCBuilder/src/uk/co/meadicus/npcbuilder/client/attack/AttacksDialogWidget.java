package uk.co.meadicus.npcbuilder.client.attack;

import java.util.LinkedHashMap;
import java.util.Map;

import uk.co.meadicus.npcbuilder.client.NPCEditor;
import uk.co.meadicus.npcbuilder.client.npc.FantasyCraftNPC;
import uk.co.meadicus.npcbuilder.client.util.NPCUtils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class AttacksDialogWidget extends Composite {

	private static AttacksDialogWidgetUiBinder uiBinder = GWT
			.create(AttacksDialogWidgetUiBinder.class);

	interface AttacksDialogWidgetUiBinder extends
			UiBinder<Widget, AttacksDialogWidget> {
	}
	
	private final FantasyCraftNPC npc;
	private int xp;
	
	@UiField Button addWeaponButton;
	@UiField Button addNaturalButton;
	@UiField Button addDamageEOButton;
	@UiField Button addSaveEOButton;
	@UiField ListBox editAttackListBox;
	@UiField Button editButton;
	@UiField Button removeButton;
	
	public AttacksDialogWidget(final FantasyCraftNPC npc, final NPCEditor npcEditor, final AttacksDialogBox attacksDialogBox) {
		initWidget(uiBinder.createAndBindUi(this));
		this.npc = npc;
				
		// Bind events to buttons

		this.addWeaponButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				attacksDialogBox.hide();
				Attack attack = new WeaponAttack();
				AttackDialogBox attackDialog = new AttackDialogBox(npcEditor, getNpc(), attack);
				attackDialog.init();
				attackDialog.show();
			}
		});
		this.addNaturalButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				attacksDialogBox.hide();
				Attack attack = new NaturalAttack();
				AttackDialogBox attackDialog = new AttackDialogBox(npcEditor, getNpc(), attack);
				attackDialog.init();
				attackDialog.show();
			}
		});
		this.addDamageEOButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				attacksDialogBox.hide();
				Attack attack = new DamageExtraordinaryAttack();
				AttackDialogBox attackDialog = new AttackDialogBox(npcEditor, getNpc(), attack);
				attackDialog.init();
				attackDialog.show();
			}
		});
		this.addSaveEOButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				attacksDialogBox.hide();
				Attack attack = new SaveExtraordinaryAttack();
				AttackDialogBox attackDialog = new AttackDialogBox(npcEditor, getNpc(), attack);
				attackDialog.init();
				attackDialog.show();
			}
		});
		
		// bind event to edit quality button
		this.editButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				editSelectedAttack(npcEditor, attacksDialogBox);
			}
		});

		this.editAttackListBox.addDoubleClickHandler(new DoubleClickHandler() {
			@Override
			public void onDoubleClick(DoubleClickEvent event) {
				editSelectedAttack(npcEditor, attacksDialogBox);
			}
		});
		
		// bind event to remove quality button
		this.removeButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				int attackIndex = Integer.parseInt(NPCUtils.getSelectedItemValue(editAttackListBox));				
				getNpc().getAttacks().remove(attackIndex);
				attacksDialogBox.hide();
				attacksDialogBox.updateStatBlock();
			}
		});
		
	}

	protected void setAttacksList(final FantasyCraftNPC npc) {
		int xptotal = 0;
		
		// List current attacks
		Map<String, String> weaponAttacks = new LinkedHashMap<String, String>();
		Map<String, String> naturalAttacks = new LinkedHashMap<String, String>();
		Map<String, String> damageEOAttacks = new LinkedHashMap<String, String>();
		Map<String, String> saveEOAttacks = new LinkedHashMap<String, String>();
		
		for (int i = 0; i< npc.getAttacks().size(); ++i) {
			Attack attack = npc.getAttacks().get(i);
			String attackIndex = Integer.toString(i);
			String attackLabel = attack.renderTitle();
			int attackxp = (int)attack.getXp().getValue();
			if (attackxp != 0) {
				attackLabel += " [" + attackxp + "xp]";
				xptotal += attackxp;
			}
			String description = attack.renderDescription().trim();
			if (!description.isEmpty()) {
				attackLabel += " (" + attack.renderDescription() + ")";
			}
			
			if (attack instanceof WeaponAttack) {
				weaponAttacks.put(attackLabel, attackIndex);
			} else if (attack instanceof NaturalAttack) {
				naturalAttacks.put(attackLabel, attackIndex);
			} else if (attack instanceof DamageExtraordinaryAttack) {
				damageEOAttacks.put(attackLabel, attackIndex);
			} else {
				saveEOAttacks.put(attackLabel, attackIndex);
			}
		}
		
		NPCUtils.addListOptionGroup(editAttackListBox, true, "Weapons", weaponAttacks);
		NPCUtils.addListOptionGroup(editAttackListBox, false, "Natural Attacks", naturalAttacks);
		NPCUtils.addListOptionGroup(editAttackListBox, false, "Damage Extraordinary Attacks", damageEOAttacks);
		NPCUtils.addListOptionGroup(editAttackListBox, false, "Save Extraordinary Attacks", saveEOAttacks);
		
		setXp(xptotal);

		if (this.editAttackListBox.getItemCount() > 0) {
			if (this.editAttackListBox.getSelectedIndex() < 0) {
				this.editAttackListBox.setItemSelected(0, true);
			}
			this.editButton.setEnabled(true);
			this.removeButton.setEnabled(true);
		} else {
			// edit and remove buttons disabled at startup
			this.editButton.setEnabled(false);
			this.removeButton.setEnabled(false);
		}
	}
	
	private FantasyCraftNPC getNpc() {
		return npc;
	}

	protected int getXp() {
		return xp;
	}

	protected void setXp(int xp) {
		this.xp = xp;
	}

	private void editSelectedAttack(final NPCEditor npcEditor,
			final AttacksDialogBox attacksDialogBox) {
		attacksDialogBox.hide();
		int attackIndex = Integer.parseInt(NPCUtils.getSelectedItemValue(this.editAttackListBox));
		AttackDialogBox attackDialog = new AttackDialogBox(npcEditor, getNpc(), attackIndex);
		attackDialog.init();
		attackDialog.show();
	}
}
