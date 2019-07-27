package uk.co.meadicus.npcbuilder.client.treasure.generator.support;

public class TableRoll {

	private final String tableName;
	private final int roll;
	
	public TableRoll(String tableName, int roll) {
		super();
		this.tableName = tableName;
		this.roll = roll;
	}
	
	public String getTableName() {
		return tableName;
	}
	
	public int getRoll() {
		return roll;
	}
	
}
