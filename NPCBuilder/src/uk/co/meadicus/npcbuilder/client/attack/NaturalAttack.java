package uk.co.meadicus.npcbuilder.client.attack;

import java.util.List;

import uk.co.meadicus.npcbuilder.client.util.NPCUtils;
import uk.co.meadicus.npcbuilder.client.util.Roman;
import uk.co.meadicus.npcbuilder.client.xp.ComplexXP;
import uk.co.meadicus.npcbuilder.client.xp.XP;

public class NaturalAttack extends DamageAttack {
	
	private int quantity = 1;
	private boolean finesse = false;
	private boolean grab = false;
	private int reach = 0;
	private boolean trip = false;
	
	public NaturalAttack() {
		setTitle(AttacksConfig.naturalAttacks.get(0));
	}
		
	public NaturalAttack clone() {
		NaturalAttack attack = new NaturalAttack();
		copyTo(attack);
		return attack;
	}
	
	protected void copyTo(NaturalAttack to) {
		super.copyTo(to);
		to.setQuantity(getQuantity());
		to.setFinesse(isFinesse());
		to.setGrab(isGrab());
		to.setReach(getReach());
		to.setTrip(isTrip());
	}
	
	@Override
	public XP getXp() {
		ComplexXP xp = new ComplexXP();
		
		xp.put("base", getGrade() * 2 * getQuantity());
				
		if (!getDamageType().equals(DEFAULT_DAMAGE_TYPE)) {	xp.put(getDamageType(), 2);	}
		if (!getAligned().isEmpty()) { xp.put(getAligned(), 2); }
		if (!getDiseased().isEmpty()) { xp.put(getDiseased(), 2); }
		if (!getVenomous().isEmpty()) { xp.put(getVenomous(), 2); }
		
		if (getArmourPiercing() > 0) {
			xp.put("armour piercing (" + getArmourPiercing() + ")", (getArmourPiercing() / AttacksConfig.APMultiplier) * 2);
		}
		if (isBleed()) { xp.put("bleed", 2); }
		if (isFinesse()) { xp.put("finesse", 2); }
		if (isGrab()) { xp.put("grab", 2); }
		if (getKeen() > 0) {
			xp.put("keen (" + getKeen() + ")", (getKeen() / AttacksConfig.keenMultiplier) * 2);
		}
		if (getReach() > 0) {
			xp.put("reach (" + getReach() + ")", getReach() * 2);
		}
		if (isTrip()) { xp.put("trip", 2); }
		
		return xp;
	}

	@Override
	public void parse(String title, String description) {
		// if there is a quantity multiplier
		if (title.matches(".+( × \\d+)$")) {
			int lastSpace = title.lastIndexOf(" ");
			this.quantity = Integer.parseInt(title.substring(lastSpace+1).trim());
			// remove after the multiplier
			int multiply = title.lastIndexOf("×");
			title = title.substring(0, multiply).trim();
		}
		
		// split title into attack type and calibre
		int lastSpace = title.lastIndexOf(" ");
		setTitle(title.substring(0, lastSpace).toLowerCase());
		setGrade(Roman.toInt(title.substring(lastSpace+1).trim()));
		
		// parse details
		List<String> detail = NPCUtils.bracketAwareSplit(description, ';');
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
			} else if (datum.equals("finesse")) {
				setFinesse(true);
			} else if (datum.equals("grab")) {
				setGrab(true);
			} else if (datum.startsWith("keen")) {
				setKeen(Integer.parseInt(datum.substring(4).trim()));
			} else if (datum.startsWith("reach")) {
				setReach(Integer.parseInt(datum.substring(5).trim()));
			} else if (datum.equals("trip")) {
				setTrip(true);
			}
		}
	}

	@Override
	public String renderDescription() {
		
		String upgrades = "";
		String delim = "";
		if (!getDamageType().equals(DEFAULT_DAMAGE_TYPE)) {
			upgrades += "damage type: " + getDamageType();
			delim = "; ";
		}
		if (!getAligned().isEmpty()) {
			upgrades += delim;
			upgrades += "aligned: " + getAligned();
			delim = "; ";
		}
		if (!getDiseased().isEmpty()) {
			upgrades += delim;
			upgrades += "diseased: " + getDiseased();
			delim = "; ";
		}
		if (!getVenomous().isEmpty()) {
			upgrades += delim;
			upgrades += "venomous: " + getVenomous();
			delim = "; ";
		}
		if (getArmourPiercing() > 0) {
			upgrades += delim;
			upgrades += "armour piercing " + getArmourPiercing();
			delim = "; ";
		}
		if (isBleed()) {
			upgrades += delim;
			upgrades += "bleed";
			delim = "; ";
		}
		if (isFinesse()) {
			upgrades += delim;
			upgrades += "finesse";
			delim = "; ";
		}
		if (isGrab()) {
			upgrades += delim;
			upgrades += "grab";
			delim = "; ";
		}
		if (getKeen() > 0) {
			upgrades += delim;
			upgrades += "keen " + getKeen();
			delim = "; ";
		}
		if (getReach() > 0) {
			upgrades += delim;
			upgrades += "reach " + getReach();
			delim = "; ";
		}
		if (isTrip()) {
			upgrades += delim;
			upgrades += "trip";
		}
		
		return upgrades;
	}

	@Override
	public String renderTitle() {
		String output = NPCUtils.toCapCase(this.getTitle()) + " " + Roman.toRoman(getGrade());
		if (getQuantity() > 1) {
			output += " × " + getQuantity();
		}
		return output;
	}

	protected int getQuantity() {
		return quantity;
	}

	protected void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	protected boolean isFinesse() {
		return finesse;
	}

	protected void setFinesse(boolean finesse) {
		this.finesse = finesse;
	}

	protected boolean isGrab() {
		return grab;
	}

	protected void setGrab(boolean grab) {
		this.grab = grab;
	}

	protected int getReach() {
		return reach;
	}

	protected void setReach(int reach) {
		this.reach = reach;
	}

	protected boolean isTrip() {
		return trip;
	}

	protected void setTrip(boolean trip) {
		this.trip = trip;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (finesse ? 1231 : 1237);
		result = prime * result + (grab ? 1231 : 1237);
		result = prime * result + quantity;
		result = prime * result + reach;
		result = prime * result + (trip ? 1231 : 1237);
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
		NaturalAttack other = (NaturalAttack) obj;
		if (finesse != other.finesse)
			return false;
		if (grab != other.grab)
			return false;
		if (quantity != other.quantity)
			return false;
		if (reach != other.reach)
			return false;
		if (trip != other.trip)
			return false;
		return true;
	}

	
}
