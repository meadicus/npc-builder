package uk.co.meadicus.npcbuilder.client.npc;

import java.util.Collection;
import java.util.List;

import uk.co.meadicus.npcbuilder.client.gear.GearPick;
import uk.co.meadicus.npcbuilder.client.xp.XP;


public interface SpycraftNPC extends NPC {
	
	public static enum SCNPCType {		
		STANDARD("Standard"),
		SPECIAL("Special"),
		ANIMAL("Animal");

		final private String displayText;
				
		private SCNPCType(String displayText) {
			this.displayText = displayText;
		}

		protected String getDisplayText() {
			return displayText;
		}

		@Override
		public String toString() {
			return getDisplayText();
		}
		
		public static String[] displayValues() {
			String[] values = {STANDARD.getDisplayText(), SPECIAL.getDisplayText(), ANIMAL.getDisplayText()};
			return values;
		}

	}
	
	public SpycraftConfig getConfig();
	
	public List<GearPick> getWeapons();

	public List<GearPick> getGear();

	public List<GearPick> getVehicles();

	public int getWealth();
	
	public void setWealth(int wealth);

	public SCNPCType getType();

	public void setType(SCNPCType type);
	
	public Size getSize();
	
	public int getSpeed();
	
	public XP xpFromGear(Collection<GearPick> weapons,
			  Collection<GearPick> gear,
			  Collection<GearPick> vehicles);
}
