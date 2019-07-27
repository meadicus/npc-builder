package uk.co.meadicus.npcbuilder.client.npc.form;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

import uk.co.meadicus.npcbuilder.client.npc.form.Mobility.MotionType;
import uk.co.meadicus.npcbuilder.client.util.NPCUtils;

public class MobilityWidget extends Composite {

	private static MobilityWidgetUiBinder uiBinder = GWT.create(MobilityWidgetUiBinder.class);

	interface MobilityWidgetUiBinder extends UiBinder<Widget, MobilityWidget> {
	}

	@UiField
	ListBox motionTypeListBox;
	@UiField
	ListBox walkerSpeedListBox;
	@UiField
	ListBox burrowerSpeedListBox;
	@UiField
	ListBox flyerSpeedListBox;
	@UiField
	ListBox swimmerSpeedListBox;
	@UiField
	Panel speedEditArea;

	public MobilityWidget(final ChangeHandler changeHandler) {
		initWidget(uiBinder.createAndBindUi(this));

		// setup the primary mobility type box
		NPCUtils.addListOptions(motionTypeListBox, true, MotionType.displayValues(), false);
		motionTypeListBox.addChangeHandler(event -> {
			motionTypeChanged();
			changeHandler.onChange(event);
		});

		setupSpeedListBox(walkerSpeedListBox, changeHandler);
		setupSpeedListBox(burrowerSpeedListBox, changeHandler);
		setupSpeedListBox(flyerSpeedListBox, changeHandler);
		setupSpeedListBox(swimmerSpeedListBox, changeHandler);

	}

	private void setupSpeedListBox(ListBox listBox, ChangeHandler changeHandler) {
		NPCUtils.addListOptions(listBox, true, 0, 201, 10, false, false);
		listBox.addChangeHandler(changeHandler);
	}

	protected void selectNPCValues(Mobility mobility) {
		NPCUtils.selectByValue(motionTypeListBox, mobility.getPrimaryMotionType().toString());
		NPCUtils.selectByValue(walkerSpeedListBox, mobility.getSpeedMap().get(MotionType.WALKER));
		NPCUtils.selectByValue(burrowerSpeedListBox, mobility.getSpeedMap().get(MotionType.BURROWER));
		NPCUtils.selectByValue(flyerSpeedListBox, mobility.getSpeedMap().get(MotionType.FLYER));
		NPCUtils.selectByValue(swimmerSpeedListBox, mobility.getSpeedMap().get(MotionType.SWIMMER));
	}

	private void motionTypeChanged() {
		// get the selected type
		MotionType motion = MotionType.fromString(NPCUtils.getSelectedItemValue(motionTypeListBox));

		if (motion == MotionType.IMMOBILE) {
			speedEditArea.setVisible(false);
		} else {
			speedEditArea.setVisible(true);
		}
	}

	protected Mobility mobilityFromWidget() {
		MotionType motion = MotionType.fromString(NPCUtils.getSelectedItemValue(motionTypeListBox));

		Mobility mobility = new Mobility(motion);

		if (motion != MotionType.IMMOBILE) {
			// walk
			int walk = Integer.parseInt(NPCUtils.getSelectedItemValue(walkerSpeedListBox));
			mobility.setSpeed(MotionType.WALKER, walk);
			// burrow
			int burrow = Integer.parseInt(NPCUtils.getSelectedItemValue(burrowerSpeedListBox));
			mobility.setSpeed(MotionType.BURROWER, burrow);
			// fly
			int fly = Integer.parseInt(NPCUtils.getSelectedItemValue(flyerSpeedListBox));
			mobility.setSpeed(MotionType.FLYER, fly);
			// swim
			int swim = Integer.parseInt(NPCUtils.getSelectedItemValue(swimmerSpeedListBox));
			mobility.setSpeed(MotionType.SWIMMER, swim);
		}

		return mobility;
	}

}
