package uk.co.meadicus.npcbuilder.client.npc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import uk.co.meadicus.npcbuilder.client.gear.GearPick;
import uk.co.meadicus.npcbuilder.client.quality.AttributeModifier;
import uk.co.meadicus.npcbuilder.client.quality.AttributesQuality;
import uk.co.meadicus.npcbuilder.client.quality.Quality;
import uk.co.meadicus.npcbuilder.client.quality.SimpleQuality;
import uk.co.meadicus.npcbuilder.client.skill.Skill;
import uk.co.meadicus.npcbuilder.client.xp.ComplexXP;
import uk.co.meadicus.npcbuilder.client.xp.XP;


public class SpycraftNPCImpl extends NPCImpl implements SpycraftNPC {
		
	private SCNPCType type = SCNPCType.STANDARD;
	private int wealth = 0;
	private final SpycraftConfig config;
	private final List<GearPick> weapons = new ArrayList<GearPick>();
	private final List<GearPick> gear = new ArrayList<GearPick>();
	private final List<GearPick> vehicles = new ArrayList<GearPick>();
	
	private static SpycraftNPCImpl singleton; 
	private SpycraftNPCImpl() {
		this.config = SpycraftConfig.getInstance();
	}
	
	public static SpycraftNPCImpl getInstance() {
		if (singleton == null) {
			singleton = new SpycraftNPCImpl();
		}
		return singleton;
	}
	
	
	public SpycraftConfig getConfig() {
		return config;
	}

	public int getWealth() {
		return wealth;
	}

	public void setWealth(int wealth) {
		this.wealth = wealth;
	}

	public SCNPCType getType() {
		return type;
	}

	public List<GearPick> getWeapons() {
		return weapons;
	}

	public List<GearPick> getGear() {
		return gear;
	}

	public List<GearPick> getVehicles() {
		return vehicles;
	}
	public void setType(SCNPCType type) {
		if (type != this.type) {
			this.type = type;
			updateAfterTypeChange();
		}
	}
	
	private void updateAfterTypeChange() {
		if (getType() == SCNPCType.ANIMAL) {
			setComp(0);
			setWealth(0);
		}
		// Update attribute qualities as superior attribute as the defaults may have changed
		for (Quality quality : getQualities().values()) {
			if (quality instanceof AttributesQuality) {
				AttributesQuality attributesQuality = (AttributesQuality) quality;
				AttributesQuality newAttributesQuality = (AttributesQuality) getConfig().getQualityFactory().getQuality(attributesQuality.getIdentifyingName());
				newAttributesQuality.setAttributeModifiers(attributesQuality.getAttributeModifiers());
				getQualities().put(quality.getIdentifyingName(), newAttributesQuality);
			}
		}
	}
	
	
	public boolean isGroup() {
		return hasQuality("minion") || hasQuality("horde");
	}
	
	
	public XP getXp() {
		ComplexXP xp = new ComplexXP(true);
		
		// traits
		ComplexXP traitsxp = new ComplexXP();
		traitsxp.put("init", getInit());
		traitsxp.put("atk", getAtk());
		traitsxp.put("def", getDef());
		traitsxp.put("res", getRes());
		traitsxp.put("health", getHlth());
		traitsxp.put("comp", getComp());
		xp.put("traits", traitsxp);
		
		// skills
		ComplexXP skillsxp = new ComplexXP();
		for (Skill skill : getSkills()) {
			skillsxp.put(skill.getName(), skill.getRating());
		}
		xp.put("skills", skillsxp);
		
		// gear
		xp.put("gear", xpFromGear(getWeapons(), getGear(), getVehicles()));
		
		// qualities
		ComplexXP qualitiesxp = new ComplexXP();
		for (String qualityName : getQualities().keySet()) {
			qualitiesxp.put(qualityName, getQualities().get(qualityName).getXp());
		}
		xp.put("qualities", qualitiesxp);
		
		return xp;
	}

	public XP xpFromGear(Collection<GearPick> weapons,
						  Collection<GearPick> gear,
						  Collection<GearPick> vehicles) {
		int wpnxp = 0, gerxp = 0, vehxp = 0;

		// weapons
		for (GearPick weapon : weapons) {
			wpnxp += weapon.getCalibre() * weapon.getQuantity();
		}
		// expect 1 weapon
		if (wpnxp == 0) {
			wpnxp = -2;
		} else {
			wpnxp -= 1;
		}
		
		// gear
		for (GearPick pick : gear) {
			gerxp += pick.getCalibre() * pick.getQuantity();
		}
		// expect 1 gear
		if (gerxp == 0) {
			gerxp = -2;
		} else {
			gerxp -= 1;
		}
		
		// vehicle
		for (GearPick vehicle : vehicles) {
			vehxp += vehicle.getCalibre() * vehicle.getQuantity();
		}
		//modify totals by type
		if (getType() == SCNPCType.SPECIAL || isGroup()) {
			// expect 1 vehicle
			if (vehxp == 0) {
				vehxp = -2;
			} else {
				vehxp -= 1;
			}
		}
		
		ComplexXP xp = new ComplexXP();
		xp.put("weapons", wpnxp);
		xp.put("gear", gerxp);
		xp.put("vehicles", vehxp);
		return xp;
	}
	
	
	public void reset() {
		super.reset();
		this.getWeapons().clear();
		this.getWeapons().add(new GearPick("", 1, 1));
		this.getGear().clear();
		this.getGear().add(new GearPick("", 1, 1));
		this.getVehicles().clear();
		if (this.getType() == SCNPCType.SPECIAL) {
			this.getVehicles().add(new GearPick("", 1, 1));
		}
	}

	
	public Size getSize() {
		Size size = Size.MEDIUM;
		
		// Check for the 'hulking' or 'undersized' qualities
		SimpleQuality sizeQuality = null;
		if (getQualities().containsKey("hulking")) {
			sizeQuality = (SimpleQuality) getQualities().get("hulking");
		} else if (getQualities().containsKey("undersized")) {
			sizeQuality = (SimpleQuality) getQualities().get("undersized");
		}
		
		if (sizeQuality != null) {
			String sizename = sizeQuality.getUse();
			size = Size.fromString(sizename);
		}
		
		return size;		
	}
	
	public int getSpeed() {
		int speed = 30;
		
		// look for fleet or sluggish
		SimpleQuality speedQuality = null;
		if (getQualities().containsKey("fleet")) {
			speedQuality = (SimpleQuality) getQualities().get("fleet");
		} else if (getQualities().containsKey("sluggish")) {
			speedQuality = (SimpleQuality) getQualities().get("sluggish");
		}
		
		if (speedQuality != null) {
			String speedDescription = speedQuality.getUse();
			
			speedDescription = speedDescription.replace("ft", "");
			speedDescription = speedDescription.replace("+", "");
			
			speed += Integer.parseInt(speedDescription);
		}
		
		return speed;
	}
	
	private int calculateAttribute(Attribute attribute) {
		int score = getConfig().getDefaultAttributes(this.getType()).getAttribute(attribute);

		for (Quality quality : getQualities().values()) {
			if (quality instanceof AttributeModifier) {
				score += ((AttributeModifier)quality).getAttributeModifiers().getAttribute(attribute);
			}
		}
		
		return score;
	}
	
	
	public int getCha() {
		return calculateAttribute(Attribute.CHA);
	}

	
	public int getCon() {
		return calculateAttribute(Attribute.CON);
	}

	
	public int getDex() {
		return calculateAttribute(Attribute.DEX);
	}

	
	public int getInt() {
		return calculateAttribute(Attribute.INT);
	}

	
	public int getStr() {
		return calculateAttribute(Attribute.STR);
	}

	
	public int getWis() {
		return calculateAttribute(Attribute.WIS);
	}
}
