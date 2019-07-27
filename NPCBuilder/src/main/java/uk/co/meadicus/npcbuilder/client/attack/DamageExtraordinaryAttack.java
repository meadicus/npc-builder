package uk.co.meadicus.npcbuilder.client.attack;

import java.util.List;

import uk.co.meadicus.npcbuilder.client.util.NPCUtils;
import uk.co.meadicus.npcbuilder.client.util.Roman;
import uk.co.meadicus.npcbuilder.client.xp.ComplexXP;
import uk.co.meadicus.npcbuilder.client.xp.XP;

public class DamageExtraordinaryAttack extends DamageAttack {

	private Area area = null;
	
	public DamageExtraordinaryAttack() {
		setTitle(AttacksConfig.DEFAULT_DAMAGE_ATTACK);
	}
	
	public DamageExtraordinaryAttack clone() {
		DamageExtraordinaryAttack attack = new DamageExtraordinaryAttack();
		copyTo(attack);
		return attack;
	}
	
	protected void copyTo(DamageExtraordinaryAttack to) {
		super.copyTo(to);
		to.setTitle(getTitle());
		to.setArea(getArea());
	}
	
	
	public XP getXp() {
		ComplexXP xp = new ComplexXP();
		
		xp.put("base", AttacksConfig.damageAttacks.get(getTitle()) * getGrade());
				
		if (!getDamageType().equals(DEFAULT_DAMAGE_TYPE)) {	xp.put(getDamageType(), 2);	}
		if (!getAligned().isEmpty()) { xp.put(getAligned(), 2); }
		if (!getDiseased().isEmpty()) { xp.put(getDiseased(), 2); }
		if (!getVenomous().isEmpty()) { xp.put(getVenomous(), 2); }
		
		if (getArmourPiercing() > 0) {
			xp.put("armour piercing (" + getArmourPiercing() + ")", (getArmourPiercing() / AttacksConfig.APMultiplier) * 2);
		}
		if (isBleed()) { xp.put("bleed", 2); }
		if (getKeen() > 0) {
			xp.put("keen (" + getKeen() + ")", (getKeen() / AttacksConfig.keenMultiplier) * 2);
		}
		if (getArea() != null) {
			xp.put("area", getArea().getXp());
		}
		
		return xp;
	}

	
	public void parse(String title, String description) {
		setName(title);

		// parse details
		// get the first item of the description
		String firstpart = description;
		if (description.contains(":") || description.contains(";")) {
				firstpart = description.replaceFirst("^([^:;]+)[:;].*", "$1");
		}
		
		// split title into attack type and calibre
		int lastSpace = firstpart.lastIndexOf(" ");
		String type = firstpart.substring(0, lastSpace);
		if (type.endsWith("attack")) {
			type = type.substring(0, type.length()-6).trim();
		}
		setTitle(type);
		setGrade(Roman.toInt(firstpart.substring(lastSpace+1).trim()));
		

		List<String> detail = NPCUtils.bracketAwareSplit(description.substring(firstpart.length()+1),';');
		
		for (String datum : detail) {
			if (datum.startsWith("damage type:")) {
				setDamageType(datum.substring(12).trim());
			} else if (datum.startsWith("aligned:")) {
				setAligned(datum.substring(8).trim());
			} else if (datum.startsWith("diseased:")) {
				setDiseased(datum.substring(9).trim());
			} else if (datum.startsWith("venomous:")) {
				setVenomous(datum.substring(9).trim());
			} else if (datum.startsWith("armour piercing")) {
				setArmourPiercing(Integer.parseInt(datum.substring(15).trim()));
			} else if (datum.equals("bleed")) {
				setBleed(true);
			} else if (datum.startsWith("keen")) {
				setKeen(Integer.parseInt(datum.substring(4).trim()));
			} else {
				Area parsedArea = Area.parse(datum);
				if (parsedArea != null) {
					setArea(parsedArea);
				}
			}
		}
	}

	
	public String renderDescription() {
		String output = getTitle() + " " + Roman.toRoman(getGrade());

		String delimeter = ": ";
		
		if (getArea() != null) {
			output += delimeter;
			output += getArea().render();
			delimeter = "; ";
		}
		if (!getDamageType().endsWith(DEFAULT_DAMAGE_TYPE)) {
			output += delimeter;
			output += "damage type: " + getDamageType();
			delimeter = "; ";
		}
		if (!getAligned().isEmpty()) {
			output += delimeter;
			output += "aligned: " + getAligned();
			delimeter = "; ";
		}
		if (!getDiseased().isEmpty()) {
			output += delimeter;
			output += "diseased: " + getDiseased();
			delimeter = "; ";
		}
		if (!getVenomous().isEmpty()) {
			output += delimeter;
			output += "venomous: " + getVenomous();
			delimeter = "; ";
		}
		if (getArmourPiercing() > 0) {
			output += delimeter;
			output += "armour piercing " + getArmourPiercing();
			delimeter = "; ";
		}
		if (isBleed()) {
			output += delimeter;
			output += "bleed";
			delimeter = "; ";
		}
		if (getKeen() > 0) {
			output += delimeter;
			output += "keen " + getKeen();
		}
		
		return output;
	}

	
	public String renderTitle() {
		return getName();
	}

	protected Area getArea() {
		return area;
	}

	protected void setArea(Area area) {
		this.area = area;
	}

	
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		return result;
	}

	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		DamageExtraordinaryAttack other = (DamageExtraordinaryAttack) obj;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		return true;
	}


}
