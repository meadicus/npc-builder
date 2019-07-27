package uk.co.meadicus.npcbuilder.client.attack;

import uk.co.meadicus.npcbuilder.client.attack.AttacksConfig.AreaUpgrade;
import uk.co.meadicus.npcbuilder.client.xp.HasXP;
import uk.co.meadicus.npcbuilder.client.xp.SimpleXP;
import uk.co.meadicus.npcbuilder.client.xp.XP;

public class Area implements HasXP {
	
	private String areaName;
	private int areaQuantity;
	
	protected Area(String name, int quantity) {
		setAreaName(name);
		setAreaQuantity(quantity);
	}
	
	private Area() {
		setAreaName("");
		setAreaQuantity(1);
	}
	
	
	public XP getXp() {
		if (areaName != null && !areaName.isEmpty()) {
			if (getUpgrade().getDistanceUnit() > 0) {
				return new SimpleXP(getUpgrade().getXpMultiplier() * this.areaQuantity);
			} else {
				return new SimpleXP(getUpgrade().getXpMultiplier());
			}
		} else {
			return new SimpleXP(0);
		}
	}
	
	protected String render() {
		String output = getAreaName();
		if (!getUpgrade().getDistanceDescriptor().isEmpty()) {
			AreaUpgrade areaUpgrade = getUpgrade();
			output += " " + getAreaQuantity() * areaUpgrade.getDistanceUnit();
			output += areaUpgrade.getDistanceDescriptor();
		}
		return output;
	}
	
	protected static Area parse(String text) {
		Area area = null;
		// is this is an area type
		for (String areaName : AttacksConfig.areaUpgrades.keySet()) {
			if (text.startsWith(areaName) || text.endsWith(areaName)) {
				area = new Area();
				area.setAreaName(areaName);
				if (area.getUpgrade().getDistanceDescriptor().isEmpty()) {
					area.setAreaQuantity(1);
				} else {
					String distanceRegex = "^[^\\d]*(\\d+)\\s*ft.*";
					String spec = text.replace(areaName, "").trim();
					if (spec.matches(distanceRegex)) {
						String amount = spec.replaceFirst(distanceRegex, "$1");
						int distance = Integer.parseInt(amount);
						area.setAreaQuantity(distance / area.getUpgrade().getDistanceUnit());
					} else {
						area.setAreaQuantity(1);
					}
					
				}
			}
		}
		return area;
	}
		
	protected AreaUpgrade getUpgrade() {
		return AttacksConfig.areaUpgrades.get(getAreaName());
	}
	
	protected String getAreaName() {
		return areaName;
	}
	protected void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	protected int getAreaQuantity() {
		return areaQuantity;
	}
	protected void setAreaQuantity(int areaQuantity) {
		this.areaQuantity = areaQuantity;
	}

	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((areaName == null) ? 0 : areaName.hashCode());
		result = prime * result + areaQuantity;
		return result;
	}

	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Area other = (Area) obj;
		if (areaName == null) {
			if (other.areaName != null)
				return false;
		} else if (!areaName.equals(other.areaName))
			return false;
		if (areaQuantity != other.areaQuantity)
			return false;
		return true;
	}

}
