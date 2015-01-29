package uk.co.meadicus.npcbuilder.client.attack;

import uk.co.meadicus.npcbuilder.client.util.NPCUtils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class AreaWidget extends Composite implements HasChangeHandlers {

	private static AreaWidgetUiBinder uiBinder = GWT
			.create(AreaWidgetUiBinder.class);

	interface AreaWidgetUiBinder extends UiBinder<Widget, AreaWidget> {
	}
	
	@UiField ListBox areaType;
	@UiField Label unitDescription;
	@UiField ListBox unitQuantity;

	private boolean optionsInitialised = false;
	private boolean includeBlank = true;
	
	public AreaWidget() {
		initWidget(uiBinder.createAndBindUi(this));
				
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		
		this.initOptions();
		
		// bind action to update unit selector and descriptor on change of the type
		areaType.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				// get the descriptor for the new type
				updateUnitSelectorFields(getArea());
			}
		});
	}
	
	private void initOptions() {

		if (!optionsInitialised) {
			// setup the selectors
			NPCUtils.addListOptions(areaType, true, AttacksConfig.areaUpgrades.keySet(), isIncludeBlank());
			NPCUtils.addListOptions(unitQuantity, true, 1, 100, false, false);
			optionsInitialised = true;
		}
	}

	public void setArea(Area area) {
		this.initOptions();
		// select value by this area
		if (area == null) {
			NPCUtils.selectByValue(areaType, "");
			unitDescription.setText("");
			unitQuantity.setEnabled(false);
		} else {
			NPCUtils.selectByValue(areaType, area.getAreaName());
			updateUnitSelectorFields(area);
		}
	}

	private void updateUnitSelectorFields(Area area) {
		if (area == null || area.getUpgrade().getDistanceDescriptor().isEmpty()) {
			unitDescription.setText("");
			unitQuantity.setEnabled(false);
		} else {
			unitQuantity.setEnabled(true);
			unitDescription.setText(area.getUpgrade().getDistanceUnit() + area.getUpgrade().getDistanceDescriptor());
			NPCUtils.selectByValue(unitQuantity, area.getAreaQuantity());
		}
	}
	
	public Area getArea() {
		String name = NPCUtils.getSelectedItemValue(areaType);
		if (name == null || name.trim().isEmpty()) {
			return null;
		} else {
			int quantity = Integer.parseInt(NPCUtils.getSelectedItemValue(unitQuantity));
			return new Area(name.trim(), quantity);
		}
	}

	public void setEnabled(boolean enabled) {
		areaType.setEnabled(enabled);
		unitQuantity.setEnabled(enabled);
	}
	
	public boolean isIncludeBlank() {
		return includeBlank;
	}

	public void setIncludeBlank(boolean includeBlank) {
		this.includeBlank = includeBlank;
	}

	private static class AreaWidgetHandlerRegistration implements HandlerRegistration {

		private HandlerRegistration areaTypeHandlerRegistration;
		private HandlerRegistration unitQuantityHandlerRegistration;
		
		protected AreaWidgetHandlerRegistration(
				HandlerRegistration areaTypeHandlerRegistration,
				HandlerRegistration unitQuantityHandlerRegistration) {
			super();
			this.areaTypeHandlerRegistration = areaTypeHandlerRegistration;
			this.unitQuantityHandlerRegistration = unitQuantityHandlerRegistration;
		}

		@Override
		public void removeHandler() {
			this.areaTypeHandlerRegistration.removeHandler();
			this.unitQuantityHandlerRegistration.removeHandler();
		}
		
	}
	
	@Override
	public HandlerRegistration addChangeHandler(ChangeHandler handler) {
		HandlerRegistration areaTypeHandlerRegistration = areaType.addChangeHandler(handler);
		HandlerRegistration unitQuantityHandlerRegistration = unitQuantity.addChangeHandler(handler);
		return new AreaWidgetHandlerRegistration(areaTypeHandlerRegistration, unitQuantityHandlerRegistration);
	}
	
}
