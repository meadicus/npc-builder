package uk.co.meadicus.npcbuilder.client.npc.form;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.co.meadicus.npcbuilder.client.xp.ComplexXP;
import uk.co.meadicus.npcbuilder.client.xp.HasXP;
import uk.co.meadicus.npcbuilder.client.xp.XP;


public class Mobility implements HasXP {

	public enum MotionType {
		WALKER("Walker"),
		BURROWER("Burrower"),
		FLYER("Flyer"),
		SWIMMER("Swimmer"),
		IMMOBILE("Immobile");
		
		final private String displayText;
				
		private MotionType(String displayText) {
			this.displayText = displayText;
		}

		protected String getDisplayText() {
			return displayText;
		}

		
		public String toString() {
			return getDisplayText();
		}
		
		public static MotionType fromString(String text) {
			try {
				return MotionType.valueOf(text.toUpperCase());
			} catch (IllegalArgumentException ex) {
				// unknown motion type, nothing to do
			}
			// synonyms
			if (text.equalsIgnoreCase("flier")) {
				return MotionType.FLYER;
			}
			return null;
		}
		
		public static String[] displayValues() {
			List<String> values = new ArrayList<String>();
			for (MotionType type : MotionType.values()) {
				values.add(type.toString());
			}
			return values.toArray(new String[values.size()]);
		}
	}
	
	private final Map<MotionType, Integer> speedMap = new HashMap<MotionType, Integer>();
	private MotionType primaryMotionType;

	public Mobility(MotionType primary) {
		for (MotionType motion : MotionType.values()) {
			getSpeedMap().put(motion, 0);
		}
		setPrimaryMotionType(primary);
	}
	
	public Mobility(MotionType primary, int primarySpeed) {
		for (MotionType motion : MotionType.values()) {
			getSpeedMap().put(motion, 0);
		}
		setPrimaryMotionType(primary);
		getSpeedMap().put(primary, primarySpeed);
	}

	public Mobility clone() {
		Mobility mobility = new Mobility(this.getPrimaryMotionType());
		
		for (Map.Entry<MotionType, Integer> entry : getSpeedMap().entrySet()) {
			mobility.setSpeed(entry.getKey(), entry.getValue());
		}
		
		return mobility;
	}
	
	public XP getXp() {
		ComplexXP xp = new ComplexXP();

		if (getPrimaryMotionType() == MotionType.IMMOBILE) {
			xp.put(MotionType.IMMOBILE.toString().toLowerCase(), -5);
		} else {
			for (MotionType motion : getSpeedMap().keySet()) {
				int speed = getSpeedMap().get(motion);
				if (speed > 0) {
					int xpval = 0;
					// if primary 30ft is free
					if (motion == getPrimaryMotionType()) {
						xpval = Math.max(0, speed - 30) / 10;
					} else {
						xpval = speed / 10;
					}
					xp.put(motion.toString().toLowerCase() + " " + speed, xpval);
				}
			}
		}			
		
		return xp;
	}
	
	public MotionType getPrimaryMotionType() {
		return primaryMotionType;
	}

	public void setPrimaryMotionType(MotionType primaryMotionType) {
		this.primaryMotionType = primaryMotionType;
	}

	public Map<MotionType, Integer> getSpeedMap() {
		return speedMap;
	}
	
	public void setSpeed(MotionType motion, int speed) {
		getSpeedMap().put(motion, speed);
	}
	
	public List<MotionType> getOrderedMotionTypes() {
		List<MotionType> motionTypes = new ArrayList<MotionType>();
		
		// primary type
		motionTypes.add(getPrimaryMotionType());
		
		if (getPrimaryMotionType() != MotionType.IMMOBILE) {
			for (MotionType motion : getSpeedMap().keySet()) {
				if (motion != getPrimaryMotionType()) {
					int speed = getSpeedMap().get(motion);
					if (speed > 0) {
						motionTypes.add(motion);
					}
				}
			}
		}
		
		Collections.sort(motionTypes, new MotionTypeComparator(this));
		
		return motionTypes;
	}
	
	private static class MotionTypeComparator implements Comparator<MotionType> {
		private final Mobility mobility;
		protected MotionTypeComparator(Mobility mobility) {
			this.mobility = mobility;
		}

		public int compare(MotionType motion0, MotionType motion1) {
			if (motion0 == motion1) {
				return 0;
			} else if (motion0 == this.mobility.primaryMotionType) {
				return -1;
			} else if (motion1 == this.mobility.primaryMotionType) {
				return 1;
			} else {
				return -(this.mobility.getSpeedMap().get(motion0).compareTo(this.mobility.getSpeedMap().get(motion1)));
			}
		}		
	}
}
