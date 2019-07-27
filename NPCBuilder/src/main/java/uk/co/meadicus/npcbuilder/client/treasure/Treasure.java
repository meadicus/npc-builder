package uk.co.meadicus.npcbuilder.client.treasure;

import java.util.SortedMap;
import java.util.TreeMap;


public class Treasure {

	public enum TreasureType {
		ANY("Any"),
		COIN("Coin"),
		GEAR("Gear"),
		LOOT("Loot"),
		MAGIC("Magic"),
		TROPHIES("Trophies");
		
		final private String displayText;
				
		private TreasureType(String displayText) {
			this.displayText = displayText;
		}

		protected String getDisplayText() {
			return displayText;
		}

		
		public String toString() {
			return getDisplayText();
		}
		
		public static TreasureType fromString(String text) {
			return TreasureType.valueOf(text.toUpperCase());
		}
	}
	
	private final SortedMap<TreasureType, Integer> treasureMap = new TreeMap<TreasureType, Integer>();

	private SortedMap<TreasureType, Integer> getTreasureMap() {
		return treasureMap;
	}
	
	public int get(TreasureType type) {
		if (getTreasureMap().containsKey(type)) {
			return getTreasureMap().get(type);
		} else {
			return 0;
		}			
	}
	
	public void set(TreasureType type, int val) {
		if (val == 0) {
			getTreasureMap().remove(type);
		} else {
			getTreasureMap().put(type, val);
		}
	}
	
	public boolean isEmpty() {
		return getTreasureMap().isEmpty();
	}
}
