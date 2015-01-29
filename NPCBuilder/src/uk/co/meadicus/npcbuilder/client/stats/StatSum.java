package uk.co.meadicus.npcbuilder.client.stats;

import java.util.ArrayList;
import java.util.List;

import uk.co.meadicus.npcbuilder.client.util.NPCUtils;

public class StatSum {

	private static class StatSumItem {
		private final int value;
		private final String name;
		private final boolean renderIfZero; 
		protected StatSumItem(int value, String name, boolean renderIfZero) {
			super();
			this.value = value;
			this.name = name;
			this.renderIfZero = renderIfZero;
		}
		public final int getValue() {
			return value;
		}
		public final String getName() {
			return name;
		}
		public final boolean isRenderIfZero() {
			return renderIfZero;
		}
	}
	
	private final List<StatSumItem> items = new ArrayList<StatSumItem>();
	private boolean totalIsModifier = true;

	public void add(int val, String name, boolean renderIfZero) {
		StatSumItem item = new StatSumItem(val, name, renderIfZero);
		getItems().add(item);
	}
	
	private int getTotal() {
		int total = 0;
		for (StatSumItem item : getItems()) {
			total += item.getValue();
		}
		return total;
	}
	
	public String render() {
		String output = "";
		if (isTotalIsModifier()) {
			output += NPCUtils.renderModifier(getTotal());
		} else {
			output += Integer.toString(getTotal());
		}
		
		String sum = "";
		int sumItems = 0;
		for (StatSumItem item : getItems()) {
			if (item.getValue() != 0 || item.isRenderIfZero()) {
				// don't put a '+' in front of the first number if positive
				if (sum.isEmpty()) {	
					sum = Integer.toString(item.getValue());
				} else {
					sum += NPCUtils.renderModifier(item.getValue());
				}
				// if there is an item name to render
				if (!(item.getName() == null || item.getName().isEmpty())) {
					sum += "(" + item.getName() + ")";
				}
				++ sumItems;
			}
		}
		
		if (sumItems > 1) {
			output += "=" + sum;
		}
			
		return output;
	}
	
	private final boolean isTotalIsModifier() {
		return totalIsModifier;
	}

	public final void setTotalIsModifier(boolean totalIsModifier) {
		this.totalIsModifier = totalIsModifier;
	}

	private final List<StatSumItem> getItems() {
		return items;
	}
	
}
