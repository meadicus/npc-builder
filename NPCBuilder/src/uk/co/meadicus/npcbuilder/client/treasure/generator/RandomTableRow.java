package uk.co.meadicus.npcbuilder.client.treasure.generator;

import java.util.ArrayList;
import java.util.List;

import uk.co.meadicus.npcbuilder.client.treasure.generator.support.SpecApplicator;
import uk.co.meadicus.npcbuilder.client.treasure.generator.support.TableRoll;
import uk.co.meadicus.npcbuilder.client.treasure.generator.support.ValueAlgorithm;
import uk.co.meadicus.npcbuilder.client.treasure.generator.support.ZeroSpecApplicator;


public class RandomTableRow {

	private final String description;
	private final ValueAlgorithm valueAlgorithm;
	private final Integer pageRef;
	private final List<RandomTable> tables = new ArrayList<RandomTable>();
	private final SpecApplicator specApplicator;
	private final boolean fork;
	private final boolean visible;
	
	public static final String CHILD_ITEM_REPLACE_PATTERN = "~~~";

	public RandomTableRow(String description, ValueAlgorithm valueAlgorithm,
			Integer pageRef, RandomTable table, SpecApplicator specApplicator,
			boolean fork, boolean visible) {
		this.description = description;
		this.valueAlgorithm = valueAlgorithm;
		this.pageRef = pageRef;
		if (table != null) {
			this.tables.add(table);
		}
		this.specApplicator = specApplicator;
		this.fork = fork;
		this.visible = visible;
	}
	
	public RandomTableRow(String description, ValueAlgorithm valueAlgorithm,
	Integer pageRef, RandomTable table, SpecApplicator specApplicator, boolean fork) {
		this(description, valueAlgorithm, pageRef, table,
				specApplicator, fork, true);
	}

	public RandomTableRow(String description, ValueAlgorithm valueAlgorithm,
	Integer pageRef, RandomTable table, SpecApplicator specApplicator) {
		this(description, valueAlgorithm, pageRef, table,
				specApplicator, false, true);
	}
	
	public RandomTableRow(String description, ValueAlgorithm valueAlgorithm,
			RandomTable table, SpecApplicator specApplicator) {
		this(description, valueAlgorithm, null, table, specApplicator, false, true);
	}
	
	public RandomTableRow(String description, ValueAlgorithm valueAlgorithm,
			Integer pageRef) {
		this(description, valueAlgorithm, pageRef, null, null, false, true);
	}

	public RandomTableRow(String description, RandomTable table, Integer pageRef) {
		this(description, null, pageRef, table, new ZeroSpecApplicator(), false, true);
	}

	public RandomTableRow(String description, RandomTable table, ValueAlgorithm valueAlgorithm) {
		this(description, valueAlgorithm, null, table, new ZeroSpecApplicator(), false, true);
	}

	public RandomTableRow(String description, RandomTable table, RandomTable table2, Integer pageRef) {
		this(description, null, pageRef, table, new ZeroSpecApplicator(), false, true);
		this.addChildTable(table2);
	}
	
	public RandomTableRow(String description, RandomTable table) {
		this(description, null, null, table, new ZeroSpecApplicator(), false, true);
	}
	
	public RandomTableRow(String description, RandomTable table, boolean visible) {
		this(description, null, null, table, new ZeroSpecApplicator(), false, visible);
	}
	
	public RandomTableRow(String description, RandomTable table, SpecApplicator specApplicator) {
		this(description, null, null, table, specApplicator, false, true);
	}
	
	public RandomTableRow(String description, RandomTable table, RandomTable table2) {
		this(description, null, null, table, new ZeroSpecApplicator(), false, true);
		this.addChildTable(table2);
	}
	
	public RandomTableRow(String description, RandomTable table, RandomTable table2, boolean fork) {
		this(description, null, null, table, new ZeroSpecApplicator(), fork, true);
		this.addChildTable(table2);
	}
	
	public RandomTableRow(String description, RandomTable table, RandomTable table2, SpecApplicator specApplicator) {
		this(description, null, null, table, specApplicator, false, true);
		this.addChildTable(table2);
	}
	
	public RandomTableRow(String description, RandomTable table, RandomTable table2, SpecApplicator specApplicator, boolean fork) {
		this(description, null, null, table, specApplicator, fork, true);
		this.addChildTable(table2);
	}
	
	public RandomTableRow(String description, ValueAlgorithm valueAlgorithm) {
		this(description, valueAlgorithm, null, null, null, false, true);
	}
	
	public RandomTableRow(String description) {
		this(description, null, null, null, null, false, true);
	}
	
	public String getDescription() {
		return description;
	}
	
	public ValueAlgorithm getValueAlgorithm() {
		return valueAlgorithm;
	}
	
	private Integer getValue(TreasureGenSpec spec, List<TableRoll> diceRolls) {
		if (valueAlgorithm != null) {
			return valueAlgorithm.getValue(spec, diceRolls);
		} else {
			return null;
		}
	}
	
	public Integer getPageRef() {
		return pageRef;
	}
	
	public void addChildTable(RandomTable table) {
		tables.add(table);
	}
		
	public RandomTableItem getAsItem(TreasureGenSpec spec, List<TableRoll> diceRolls) {
		RandomTableItem item;
		
		if (this.tables.isEmpty()) {
			item = new SimpleRandomTableItem(this.description, this.getValue(spec, diceRolls), this.pageRef);
		} else {
			Integer newValue = this.getValue(spec, diceRolls);
			String newDescription = this.description;

			item = new SimpleRandomTableItem(newDescription, newValue, this.pageRef);
			
			List<String> rolledTables = new ArrayList<String>();
			for (RandomTable table : this.tables) {
				int rollMod = this.specApplicator.getRollMod(spec);
				List<TableRoll> extensionRolls = new ArrayList<TableRoll>();
				RandomTableItem itemExtension = table.getRandomItem(rollMod, spec, extensionRolls);
				
				// If we've already rolled on this table, as a child table, then re-roll duplicates
				while (rolledTables.contains(table.getName()) && newDescription.contains(itemExtension.getDescription())) {
					extensionRolls.clear();
					itemExtension = table.getRandomItem(rollMod, spec, extensionRolls);
				}
				
				if (this.fork) {
					item.getChildItems().add(itemExtension);
				} else if (!visible) {
					item = itemExtension;
				} else {
					if (newValue == null) {
						newValue = itemExtension.getValue();
					} else if (itemExtension.getValue() != null) {
						newValue += itemExtension.getValue();
					}
					if (!itemExtension.getDescription().equals(newDescription)) {
						if (newDescription.contains(CHILD_ITEM_REPLACE_PATTERN)) {
							newDescription = newDescription.replaceFirst(CHILD_ITEM_REPLACE_PATTERN, itemExtension.getDescription());
						} else {
							if (!newDescription.endsWith(":")) {
								newDescription += ",";
							}
							newDescription += " " + itemExtension.getDescription();
						}
					}
					item = new SimpleRandomTableItem(newDescription, newValue, this.pageRef);
					if (!itemExtension.getChildItems().isEmpty()) {
						item.getChildItems().addAll(itemExtension.getChildItems());
					}
					diceRolls.addAll(extensionRolls);
				}
				rolledTables.add(table.getName());
			}
		}
		return item;
	}

}
