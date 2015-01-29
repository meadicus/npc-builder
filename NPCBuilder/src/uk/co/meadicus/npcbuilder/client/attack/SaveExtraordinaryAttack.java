package uk.co.meadicus.npcbuilder.client.attack;

import uk.co.meadicus.npcbuilder.client.util.Roman;
import uk.co.meadicus.npcbuilder.client.xp.ComplexXP;
import uk.co.meadicus.npcbuilder.client.xp.XP;

public class SaveExtraordinaryAttack extends Attack {

	private Area area = null;
	private String supernaturalAttack = "";
	
	public SaveExtraordinaryAttack() {
		setTitle(AttacksConfig.DEFAULT_SAVE_ATTACK);
	}
	
	public SaveExtraordinaryAttack clone() {
		SaveExtraordinaryAttack attack = new SaveExtraordinaryAttack();
		copyTo(attack);
		return attack;
	}
	
	protected void copyTo(SaveExtraordinaryAttack to) {
		super.copyTo(to);
		to.setArea(getArea());
		to.setSupernaturalAttack(getSupernaturalAttack());
	}
	
	@Override
	public XP getXp() {
		ComplexXP xp = new ComplexXP();
		
		xp.put("base", AttacksConfig.saveAttacks.get(getTitle()) * getGrade());
		
		if (!getSupernaturalAttack().isEmpty()) {
			xp.put(getSupernaturalAttack(), 2);
		} else if (getArea() != null) {
			xp.put("area", getArea().getXp());
		}
		
		return xp;
	}

	@Override
	public void parse(String title, String description) {
		setName(title);
		
		// parse details
		String[] detail = description.split("[:;]\\s+");
		
		// split title into attack type and calibre
		int lastSpace = detail[0].lastIndexOf(" ");
		String type = detail[0].substring(0, lastSpace);
		if (type.endsWith("attack")) {
			type = type.substring(0, type.length()-6).trim();
		}
		setTitle(type);
		setGrade(Roman.toInt(detail[0].substring(lastSpace+1).trim()));
		
		for (int i = 1; i < detail.length; ++i) {
			String datum = detail[i].trim();
			if (datum.startsWith("linked to attack")) {
				this.supernaturalAttack = datum.substring(16).trim();
			} else {
				Area parsedArea = Area.parse(datum);
				if (parsedArea != null) {
					setArea(parsedArea);
				}
			}
		}
	}

	@Override
	public String renderDescription() {
		String output = getTitle() + " " + Roman.toRoman(getGrade());

		if (!getSupernaturalAttack().isEmpty()) {
			output += ": linked to attack " + getSupernaturalAttack();
		} else if (getArea() != null) {
			output += ": " + getArea().render();
		}
		return output;
	}

	@Override
	public String renderTitle() {
		return getName();
	}

	protected Area getArea() {
		return area;
	}

	protected void setArea(Area area) {
		this.area = area;
	}

	protected String getSupernaturalAttack() {
		return supernaturalAttack;
	}

	protected void setSupernaturalAttack(String supernaturalAttack) {
		this.supernaturalAttack = supernaturalAttack;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime
				* result
				+ ((supernaturalAttack == null) ? 0 : supernaturalAttack
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SaveExtraordinaryAttack other = (SaveExtraordinaryAttack) obj;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (supernaturalAttack == null) {
			if (other.supernaturalAttack != null)
				return false;
		} else if (!supernaturalAttack.equals(other.supernaturalAttack))
			return false;
		return true;
	}

	
}
