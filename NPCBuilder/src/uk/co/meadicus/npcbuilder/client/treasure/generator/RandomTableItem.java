package uk.co.meadicus.npcbuilder.client.treasure.generator;

import java.util.List;

public interface RandomTableItem {

	String getDescription();
	Integer getValue();
	Integer getPageRef();
	List<RandomTableItem> getChildItems();
}
