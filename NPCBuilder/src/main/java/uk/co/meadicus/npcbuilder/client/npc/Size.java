package uk.co.meadicus.npcbuilder.client.npc;


public enum Size {
	NUISANCE("Nuisance", 0),
	FINE("Fine", 1),
	DIMINUATIVE("Diminuative", 2),
	TINY("Tiny", 3),
	SMALL("Small", 4),
	MEDIUM("Medium", 5),
	LARGE("Large", 6),
	HUGE("Huge", 7),
	GARGANTUAN("Gargantuan", 8),
	COLOSSAL("Colossal", 9),
	ENORMOUS("Enormous", 10),
	VAST("Vast", 11);

	final private String displayText;
	final private int scale;
			
	private Size(String displayText, int scale) {
		this.displayText = displayText;
		this.scale = scale;
	}

	protected String getDisplayText() {
		return displayText;
	}

	
	public String toString() {
		return getDisplayText();
	}
	
	public Size resize(int mod) {
		int newScale = Math.max(0, Math.min(11, this.scale + mod));
		Size newSize = null;
		for (Size size : Size.values()) {
			if (size.scale == newScale) {
				newSize = size;
				break;
			}
		}
		return newSize;
	}
	
	public int differenceTo(Size size) {
		return (size.scale - this.scale);
	}
	
	public static Size fromString(String text) {
		return Size.valueOf(text.toUpperCase());
	}
	
}
