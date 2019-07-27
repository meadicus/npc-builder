package uk.co.meadicus.npcbuilder.client.template;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import uk.co.meadicus.npcbuilder.client.attack.Attack;
import uk.co.meadicus.npcbuilder.client.npc.FantasyCraftConfig;
import uk.co.meadicus.npcbuilder.client.npc.FantasyCraftConfig.FCNPCType;
import uk.co.meadicus.npcbuilder.client.npc.FantasyCraftNPC;
import uk.co.meadicus.npcbuilder.client.npc.NPC;
import uk.co.meadicus.npcbuilder.client.npc.Size;
import uk.co.meadicus.npcbuilder.client.npc.form.Mobility;
import uk.co.meadicus.npcbuilder.client.quality.Quality;
import uk.co.meadicus.npcbuilder.client.skill.Skill;
import uk.co.meadicus.npcbuilder.client.spellcasting.Spellcasting;
import uk.co.meadicus.npcbuilder.client.treasure.Treasure;
import uk.co.meadicus.npcbuilder.client.xp.XP;

public class TemplatedFantasyCraftNPC implements FantasyCraftNPC {

	private final FantasyCraftNPC wrappedNPC;
	private final FantasyCraftTemplate template;

	public TemplatedFantasyCraftNPC(FantasyCraftNPC wrappedNPC, FantasyCraftTemplate template) {
		super();
		this.wrappedNPC = wrappedNPC;
		this.template = template;
	}

	public void reset() {
		wrappedNPC.reset();
	}

	public Map<String, Quality> getQualities() {
		Map<String, Quality> map;
		if (template.getQualities() == null) {
			map = wrappedNPC.getQualities();
		} else {
			map = TemplateApplicator.addQualities(wrappedNPC.getQualities(), template.getQualities());
		}
		return map;
	}

	public FantasyCraftConfig getConfig() {
		return wrappedNPC.getConfig();
	}

	public boolean hasQuality(Quality quality) {
		return wrappedNPC.hasQuality(quality);
	}

	public boolean hasQuality(String qualityName) {
		return wrappedNPC.hasQuality(qualityName);
	}

	public XP getXp() {
		return wrappedNPC.getXp();
	}

	public String getName() {
		return template.getName() + "(T) " + wrappedNPC.getName();
	}

	public int getInit() {
		return wrappedNPC.getInit() + template.getStatMods()[0];
	}
	
	public int getAtk() {
		return wrappedNPC.getAtk() + template.getStatMods()[1];
	}

	public int getDef() {
		return wrappedNPC.getDef() + template.getStatMods()[2];
	}

	public int getRes() {
		return wrappedNPC.getRes() + template.getStatMods()[3];
	}

	public int getHlth() {
		return wrappedNPC.getHlth() + template.getStatMods()[4];
	}
	
	public int getComp() {
		return wrappedNPC.getComp() + template.getStatMods()[5];
	}

	public int getStr() {
		return wrappedNPC.getStr() + template.getAttrMods()[0];
	}

	public int getDex() {
		return wrappedNPC.getDex() + template.getAttrMods()[1];
	}

	public int getCon() {
		return wrappedNPC.getCon() + template.getAttrMods()[2];
	}

	public int getInt() {
		return wrappedNPC.getInt() + template.getAttrMods()[3];
	}

	public int getWis() {
		return wrappedNPC.getWis() + template.getAttrMods()[4];
	}

	public int getCha() {
		return wrappedNPC.getCha() + template.getAttrMods()[5];
	}

	public int getReach() {
		return Math.max(1, wrappedNPC.getReach() + template.getReachMod());
	}

	public Set<FCNPCType> getType() {
		Set<FCNPCType> types;
		if (template.getType() != null) {
			types = new HashSet<FCNPCType>();
			types.add(template.getType());
		} else if (template.getTypes() == null) {
			types = wrappedNPC.getType();
		} else {
			types = TemplateApplicator.addTypes(wrappedNPC.getType(), template.getTypes());
		}
		return types;
	}

	public Mobility getMobility() {
		Mobility mobility = wrappedNPC.getMobility();
		if (template.getSpeedMod() != 0 || template.getMobility() != null) {
			return TemplateApplicator.addMobility(mobility, template.getMobility(), template.getSpeedMod());
		}
		return mobility;
	}

	public Size getSize() {
		Size size = wrappedNPC.getSize();
		if (template.getSize() != null) {
			size = template.getSize();
		}
		return size.resize(template.getSizeMod());
	}

	public int getFootprintX() {
		Size wrappedSize = wrappedNPC.getSize();
		Size size = getSize();
		int wrappedFootprint = wrappedNPC.getFootprintX();
		return TemplateApplicator.updatefootprint(wrappedSize, wrappedFootprint, size);
	}
	
	public int getFootprintY() {
		Size wrappedSize = wrappedNPC.getSize();
		Size size = getSize();
		int wrappedFootprint = wrappedNPC.getFootprintY();
		return TemplateApplicator.updatefootprint(wrappedSize, wrappedFootprint, size);
	}

	public int getAttribute(Attribute attribute) {
		return wrappedNPC.getAttribute(attribute);
	}

	public List<Skill> getSkills() {
		List<Skill> skills;
		if (template.getSkills() == null) {
			skills = wrappedNPC.getSkills();
		} else {
			skills = TemplateApplicator.addSkills(wrappedNPC.getSkills(), template.getSkills());
		}
		return skills;
	}

	public List<Attack> getAttacks() {
		
		List<Attack> attacks;
		if (template.getAttacksMod() == 0 && template.getAttacks() == null) {
			attacks = wrappedNPC.getAttacks();
		} else {
			attacks = TemplateApplicator.addAttacks(wrappedNPC.getAttacks(), template.getAttacks(), template.getAttacksMod());
		}
		
		return attacks;
		
	}
	
	
	
	
	
	
	
	
	
	
	// --------- setters from here on down ----------
	
	public void setStr(int str) {
		wrappedNPC.setStr(str);
	}

	public void setDex(int dex) {
		wrappedNPC.setDex(dex);
	}

	public void setCon(int con) {
		wrappedNPC.setCon(con);
	}

	public void setName(String name) {
		wrappedNPC.setName(name);
	}

	public void setInt(int intel) {
		wrappedNPC.setInt(intel);
	}

	public void setWis(int wis) {
		wrappedNPC.setWis(wis);
	}

	public void setInit(int init) {
		wrappedNPC.setInit(init);
	}
	public void setCha(int cha) {
		wrappedNPC.setCha(cha);
	}

	public void setAtk(int atk) {
		wrappedNPC.setAtk(atk);
	}

	public void setSize(Size size) {
		wrappedNPC.setSize(size);
	}

	public void setDef(int def) {
		wrappedNPC.setDef(def);
	}

	public void setFootprintX(int footprintX) {
		wrappedNPC.setFootprintX(footprintX);
	}

	public void setRes(int res) {
		wrappedNPC.setRes(res);
	}

	public void setHlth(int hlth) {
		wrappedNPC.setHlth(hlth);
	}

	public void setFootprintY(int footprintY) {
		wrappedNPC.setFootprintY(footprintY);
	}

	public void setComp(int comp) {
		wrappedNPC.setComp(comp);
	}

	public void setReach(int reach) {
		wrappedNPC.setReach(reach);
	}

	public void setMobility(Mobility mobility) {
		wrappedNPC.setMobility(mobility);
	}

	public Spellcasting getSpellcasting() {
		return wrappedNPC.getSpellcasting();
	}

	public void setSpellcasting(Spellcasting spellcasting) {
		wrappedNPC.setSpellcasting(spellcasting);
	}

	public String getMountsAndVehicles() {
		return wrappedNPC.getMountsAndVehicles();
	}

	public void setMountsAndVehicles(String mountsAndVehicles) {
		wrappedNPC.setMountsAndVehicles(mountsAndVehicles);
	}

	public String getGear() {
		return wrappedNPC.getGear();
	}

	public void setGear(String gear) {
		wrappedNPC.setGear(gear);
	}

	public Treasure getTreasure() {
		return wrappedNPC.getTreasure();
	}

	public void setTreasure(Treasure treasure) {
		wrappedNPC.setTreasure(treasure);
	}

	public List<FantasyCraftTemplate> getTemplates() {
		return wrappedNPC.getTemplates();
	}

	
	public NPC getNPCToRender() {
		// TODO Auto-generated method stub
		return null;
	}	
	
}
