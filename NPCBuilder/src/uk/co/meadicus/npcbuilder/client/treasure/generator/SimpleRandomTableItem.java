package uk.co.meadicus.npcbuilder.client.treasure.generator;

import java.util.ArrayList;
import java.util.List;

public class SimpleRandomTableItem implements RandomTableItem {

	private final String description;
	private final Integer value;
	private final Integer pageRef;
	private final List<RandomTableItem> childItems = new ArrayList<RandomTableItem>();
	
	public SimpleRandomTableItem(String description, Integer value, Integer pageRef) {
		this.description = description;
		this.value = value;
		this.pageRef = pageRef;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public Integer getValue() {
		return this.value;
	}

	@Override
	public List<RandomTableItem> getChildItems() {
		return this.childItems;
	}

	@Override
	public Integer getPageRef() {
		return this.pageRef;
	}

}
