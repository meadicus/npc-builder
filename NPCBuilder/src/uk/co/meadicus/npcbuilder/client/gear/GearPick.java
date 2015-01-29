package uk.co.meadicus.npcbuilder.client.gear;

public class GearPick implements Comparable<GearPick> {

	private String description;
	private int calibre;
	private int quantity;
	
	public GearPick(String description, int calibre, int quantity) {
		super();
		this.description = description;
		this.calibre = calibre;
		this.quantity = quantity;
	}
	
	protected GearPick() {
		super();
		this.description = "";
		this.calibre = 1;
		this.quantity = 1;
	}

	public String getDescription() {
		return description;
	}

	public int getCalibre() {
		return calibre;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * Compares by calibre then by name
	 * @param pick the pick to compare to
	 * @return
	 */
	@Override
	public int compareTo(GearPick pick) {
		Integer cal = new Integer(getCalibre());
		int compare = cal.compareTo(pick.getCalibre());
		
		if (compare == 0) {
			compare = getDescription().compareTo(pick.getDescription());
		}
		
		return compare;
	}
	
}
