package uk.co.meadicus.npcbuilder.client.template;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import uk.co.meadicus.npcbuilder.client.attack.Attack;
import uk.co.meadicus.npcbuilder.client.npc.FantasyCraftConfig.FCNPCType;
import uk.co.meadicus.npcbuilder.client.npc.Size;
import uk.co.meadicus.npcbuilder.client.npc.form.Mobility;
import uk.co.meadicus.npcbuilder.client.npc.form.Mobility.MotionType;
import uk.co.meadicus.npcbuilder.client.quality.Quality;
import uk.co.meadicus.npcbuilder.client.skill.Skill;
import uk.co.meadicus.npcbuilder.client.template.quality.QualityMerger;
import uk.co.meadicus.npcbuilder.client.template.quality.QualityMergerFactory;

/**
 * Class contains code for adding and removing the non-simple components of a template.
 * 
 * @author mead
 */
public abstract class TemplateApplicator {

	
	// Types
	
	public static Set<FCNPCType> addTypes(Set<FCNPCType> base, Set<FCNPCType> add) {
		Set<FCNPCType> types = new LinkedHashSet<FCNPCType>(base);
		types.addAll(add);		
		return types;
	}
	
	public static Set<FCNPCType> removeTypes(Set<FCNPCType> base, Set<FCNPCType> remove) {
		Set<FCNPCType> types = new LinkedHashSet<FCNPCType>(base);
		types.removeAll(remove);
		return types;
	}
	
	// Skills
	
	public static List<Skill> addSkills(List<Skill> base, List<Skill> add) {

		List<Skill> skills = new ArrayList<Skill>(base);		
		
		/*
		 * Only to add a skill if it isn't already present, or if it
		 * is present but with a lower rating 
		 */
		for (Skill addSkill : add) {
			if (skills.contains(addSkill)) {
				Skill baseSkill = skills.get(skills.indexOf(addSkill));
				if (baseSkill.getRating() < addSkill.getRating()) {
					skills.remove(baseSkill);
					skills.add(addSkill);
				}
			} else {
				skills.add(addSkill);
			}
		}
		
		return skills;
	}
	
	public static List<Skill> removeSkills(List<Skill> base, List<Skill> remove) {

		List<Skill> skills = new ArrayList<Skill>(base);

		/*
		 * Only to remove a skill if it is present, and if it
		 * is present but with a equal or lower rating 
		 */
		for (Skill removeSkill : remove) {
			if (skills.contains(removeSkill)) {
				Skill baseSkill = skills.get(skills.indexOf(removeSkill));
				if (baseSkill.getRating() <= removeSkill.getRating()) {
					skills.remove(baseSkill);
				}
			}
		}
		
		return skills;
	}
	
	// Qualities
	
	public static Map<String, Quality> addQualities(Map<String, Quality> base, List<Quality> add) {

		Map<String, Quality> map = new TreeMap<String, Quality>(base);
		for (Quality addQuality : add) {
			if (map.containsKey(addQuality.getIdentifyingName())) {
				Quality baseQuality = map.get(addQuality.getIdentifyingName());
				QualityMerger<? extends Quality> merger = QualityMergerFactory.createQualityMerger(baseQuality);
				if (merger != null) {
					Quality mergedQuality = merger.mergeIn(addQuality);
					map.put(baseQuality.getIdentifyingName(), mergedQuality);
				} else {
					map.put(addQuality.getIdentifyingName(), addQuality);
				}
			} else {			
				map.put(addQuality.getIdentifyingName(), addQuality);
			}
		}
		
		return map;
	}
	
	public static Map<String, Quality> removeQualities(Map<String, Quality> base, List<Quality> remove) {

		Map<String, Quality> map = new TreeMap<String, Quality>(base);
		for (Quality removeQuality : remove) {
			if (map.containsKey(removeQuality.getIdentifyingName())) {
				Quality baseQuality = map.get(removeQuality.getIdentifyingName());
				QualityMerger<? extends Quality> merger = QualityMergerFactory.createQualityMerger(baseQuality);
				if (merger !=  null) {
					Quality mergedQuality = merger.mergeOut(removeQuality);
					if (mergedQuality == null) {
						map.remove(baseQuality.getIdentifyingName());
					} else {
						map.put(baseQuality.getIdentifyingName(), mergedQuality);
					}
				} else {
					map.remove(baseQuality.getIdentifyingName());
				}
			}
		}
		
		return map;
	}
	
	// Attacks
	
	public static List<Attack> addAttacks(List<Attack> base, List<Attack> add, int attackMod) {

		List<Attack> attacks = new ArrayList<Attack>();
		
		// Apply attackMod first, being careful not to modify the underlying attack object
		if (attackMod != 0) {
			for (Attack baseAttack : base) {
				Attack clone = baseAttack.clone();
				int grade = clone.getGrade();
				int newGrade = grade + attackMod;
				newGrade = Math.max(1, Math.min(5, newGrade));
				clone.setGrade(newGrade);
				attacks.add(clone);
			}
		} else {
			attacks.addAll(base);
		}
		
		// Then add any template attacks.
		if (add != null && !add.isEmpty()) {
			for (Attack attack : add) {
				attacks.add(attack);
			}
		}
		
		return attacks;
	}
	
	public static List<Attack> removeAttacks(List<Attack> base, List<Attack> remove, int attackMod) {

		List<Attack> attacks = new ArrayList<Attack>();
		
		for (Attack baseAttack : base) {
			if (remove != null && remove.contains(baseAttack)) {
				// Nothing to do
			} else if (attackMod != 0) {
				Attack clone = baseAttack.clone();
				int grade = clone.getGrade();
				int newGrade = grade - attackMod;
				newGrade = Math.max(1, Math.min(5, newGrade));
				clone.setGrade(newGrade);
				attacks.add(clone);
			} else {
				attacks.add(baseAttack);
			}
		}
				
		return attacks;
	}
	
	// Mobility
	
	public static Mobility addMobility(Mobility base, Mobility add, int speedMod) {
		Mobility mobility = base.clone();
		
		if (speedMod != 0) {
			for (Map.Entry<MotionType, Integer> entry : base.getSpeedMap().entrySet()) {
				if (entry.getValue() > 0) {
					int newSpeed = entry.getValue() + speedMod;
					if (newSpeed > 0) {
						mobility.setSpeed(entry.getKey(), newSpeed);
					}
				}
			}
		}
		
		if (add != null) {
			for (Map.Entry<MotionType, Integer> entry : add.getSpeedMap().entrySet()) {
				if (mobility.getSpeedMap().containsKey(entry.getKey())) {
					if (mobility.getSpeedMap().get(entry.getKey()) < entry.getValue()) {
						mobility.setSpeed(entry.getKey(), entry.getValue());
					}
				} else {
					mobility.setSpeed(entry.getKey(), entry.getValue());
				}
			}
		}
		
		return mobility;
	}
	
	public static Mobility removeMobility(Mobility base, Mobility remove, int speedMod) {
		Mobility mobility = base.clone();
		
		if (remove != null) {
			for (Map.Entry<MotionType, Integer> entry : remove.getSpeedMap().entrySet()) {
				if (mobility.getSpeedMap().containsKey(entry.getKey())) {
					if (mobility.getSpeedMap().get(entry.getKey()) <= entry.getValue()) {
						mobility.getSpeedMap().remove(entry.getKey());
					}
				}
			}
		}
		
		if (speedMod != 0) {
			for (Map.Entry<MotionType, Integer> entry : mobility.getSpeedMap().entrySet()) {
				int newSpeed = entry.getValue() - speedMod;
				if (newSpeed > 0) {
					mobility.setSpeed(entry.getKey(), newSpeed);
				}
			}
		}
		
		return mobility;
	}
	
	/**
	 * Footprint calculator
	 * 
	 * @param wrappedSize
	 * @param wrappedFootprint
	 * @param newSize
	 * @return
	 */
	public static int updatefootprint(Size wrappedSize, int wrappedFootprint, Size newSize) {
		double footprint = wrappedFootprint;
		if (!newSize.equals(wrappedSize)) {
			if (newSize.compareTo(Size.MEDIUM) <= 0) {
				footprint = 1;
			} else {
				footprint *= Math.pow(2.0, (double)(Math.min(6, wrappedSize.differenceTo(newSize))));
			}
		}
		return Math.max(1, (int)footprint);
	}
	
}
