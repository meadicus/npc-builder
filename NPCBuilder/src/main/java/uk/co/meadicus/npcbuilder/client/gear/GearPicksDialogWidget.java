package uk.co.meadicus.npcbuilder.client.gear;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

import uk.co.meadicus.npcbuilder.client.npc.SpycraftNPC;

public class GearPicksDialogWidget extends Composite {

	private static GearPicksDialogWidgetUiBinder uiBinder = GWT.create(GearPicksDialogWidgetUiBinder.class);

	interface GearPicksDialogWidgetUiBinder extends UiBinder<Widget, GearPicksDialogWidget> {
	}

	@UiField
	Button addWeaponButton;
	@UiField
	Panel weaponPicksPanel;
	@UiField
	Button addGearButton;
	@UiField
	Panel gearPicksPanel;
	@UiField
	Button addVehicleButton;
	@UiField
	Panel vehiclePicksPanel;

	private final ChangeHandler changeHandler;

	private List<GearPickEditWidget> weaponEditWidgets = new ArrayList<GearPickEditWidget>();
	private List<GearPickEditWidget> gearEditWidgets = new ArrayList<GearPickEditWidget>();
	private List<GearPickEditWidget> vehicleEditWidgets = new ArrayList<GearPickEditWidget>();

	public GearPicksDialogWidget(ChangeHandler changeHandler) {
		initWidget(uiBinder.createAndBindUi(this));

		this.changeHandler = changeHandler;

		// setup the addpick buttons
		this.addWeaponButton.addClickHandler(event -> {
			GearPick pick = new GearPick("", 1, 1);
			addPickEditWidget(pick, weaponEditWidgets, weaponPicksPanel);
		});
		this.addGearButton.addClickHandler(event -> {
			GearPick pick = new GearPick("", 1, 1);
			addPickEditWidget(pick, gearEditWidgets, gearPicksPanel);
		});
		this.addVehicleButton.addClickHandler(event -> {
			GearPick pick = new GearPick("", 1, 1);
			addPickEditWidget(pick, vehicleEditWidgets, vehiclePicksPanel);
		});
	}

	protected void setFromNPC(SpycraftNPC npc) {

		this.weaponPicksPanel.clear();
		this.gearPicksPanel.clear();
		this.vehiclePicksPanel.clear();
		this.weaponEditWidgets.clear();
		this.gearEditWidgets.clear();
		this.vehicleEditWidgets.clear();

		// Add a pick edit widgets
		addPickEditWidgets(npc.getWeapons(), this.weaponEditWidgets, this.weaponPicksPanel);
		addPickEditWidgets(npc.getGear(), this.gearEditWidgets, this.gearPicksPanel);
		addPickEditWidgets(npc.getVehicles(), this.vehicleEditWidgets, this.vehiclePicksPanel);
	}

	protected void addPickEditWidgets(Collection<GearPick> picks, final Collection<GearPickEditWidget> widgets,
			final Panel panel) {
		for (GearPick pick : picks) {
			addPickEditWidget(pick, widgets, panel);
		}
	}

	protected void addPickEditWidget(GearPick pick, final Collection<GearPickEditWidget> widgets, final Panel panel) {
		final GearPickEditWidget pickEditWidget = new GearPickEditWidget(pick, this.changeHandler);
		widgets.add(pickEditWidget);
		panel.add(pickEditWidget);
		// setup remove button
		pickEditWidget.removeButton.addClickHandler(event -> {
			widgets.remove(pickEditWidget);
			panel.remove(pickEditWidget);
		});
	}

	public List<GearPick> getWeaponPicks(boolean sortAndMerge) {
		return getPicks(this.weaponEditWidgets, sortAndMerge);
	}

	public List<GearPick> getGearPicks(boolean sortAndMerge) {
		return getPicks(this.gearEditWidgets, sortAndMerge);
	}

	public List<GearPick> getVehiclePicks(boolean sortAndMerge) {
		return getPicks(this.vehicleEditWidgets, sortAndMerge);
	}

	protected List<GearPick> getPicks(List<GearPickEditWidget> widgets, boolean sortAndMerge) {
		List<GearPick> picks = new ArrayList<GearPick>();
		for (GearPickEditWidget widget : widgets) {
			picks.add(widget.getGearPick());
		}
		if (sortAndMerge) {
			Collections.sort(picks);
			picks = mergeDuplicates(picks);
		}
		return picks;
	}

	protected List<GearPick> mergeDuplicates(List<GearPick> picks) {
		if (!picks.isEmpty()) {
			ListIterator<GearPick> ittr = picks.listIterator();
			GearPick currentPick = ittr.next();
			while (ittr.hasNext()) {
				GearPick nextPick = ittr.next();
				// If thery're the same in description and calibre
				if (currentPick.compareTo(nextPick) == 0) {
					int totalQ = currentPick.getQuantity() + nextPick.getQuantity();
					currentPick.setQuantity(totalQ);
					// remove the item retrieved by the next() method call
					ittr.remove();
				}
			}
		}
		return picks;
	}

}
