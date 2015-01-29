package uk.co.meadicus.npcbuilder.client.npc;

/**
 * Interface for something which has attributes
 * 
 * @author mead
 */
public interface Attributes {

	public enum Attribute {
		STR("Str"),
		DEX("Dex"),
		CON("Con"),
		INT("Int"),
		WIS("Wis"),
		CHA("Cha");
		
		final private String displayText;
				
		private Attribute(String displayText) {
			this.displayText = displayText;
		}

		protected String getDisplayText() {
			return displayText;
		}

		@Override
		public String toString() {
			return getDisplayText();
		}
		
		public static Attribute fromString(String text) {
			return Attribute.valueOf(text.toUpperCase());
		}
	}
	
	public int getStr();
	public int getDex();
	public int getCon();
	public int getInt();
	public int getWis();
	public int getCha();
	public int getAttribute(Attribute attribute);
}
