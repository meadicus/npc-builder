package uk.co.meadicus.npcbuilder.client.attack;

import uk.co.meadicus.npcbuilder.client.xp.SimpleXP;
import uk.co.meadicus.npcbuilder.client.xp.XP;

public class WeaponAttack extends Attack {

	private String description = "weapon details go here";

	
	public XP getXp() {
		return new SimpleXP(0);
	}

	public WeaponAttack clone() {
		WeaponAttack attack = new WeaponAttack();
		attack.setName(getName());
		attack.setDescription(getDescription());
		return attack;
	}
	
	
	public void parse(String title, String description) {
		setName(title);
		setDescription(description);
	}

	
	public String renderDescription() {
		return getDescription();
	}

	
	public String renderTitle() {
		return getName();
	}

	protected String getDescription() {
		return description;
	}

	protected void setDescription(String description) {
		this.description = description;
	}

	
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		return result;
	}

	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		WeaponAttack other = (WeaponAttack) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}

	
}
