package uk.co.meadicus.npcbuilder.client.npc;

import java.util.List;
import java.util.Set;

import uk.co.meadicus.npcbuilder.client.attack.Attack;
import uk.co.meadicus.npcbuilder.client.npc.form.Mobility;
import uk.co.meadicus.npcbuilder.client.spellcasting.Spellcasting;
import uk.co.meadicus.npcbuilder.client.template.FantasyCraftTemplate;
import uk.co.meadicus.npcbuilder.client.treasure.Treasure;
import uk.co.meadicus.npcbuilder.client.xp.XP;

public interface FantasyCraftNPC extends NPC {

	public abstract FantasyCraftConfig getConfig();

	public abstract XP getXp();

	public void setStr(int str);

	public void setDex(int dex);

	public void setCon(int con);

	public void setInt(int intel);

	public void setWis(int wis);

	public void setCha(int cha);
	
	public abstract Size getSize();

	public void setSize(Size size);
	
	public abstract int getFootprintX();
	
	public void setFootprintX(int footprintX);

	public abstract int getFootprintY();
	
	public void setFootprintY(int footprintY);

	public abstract int getReach();
	
	public void setReach(int reach);

	public abstract Set<FantasyCraftConfig.FCNPCType> getType();

	public abstract Mobility getMobility();
	
	public void setMobility(Mobility mobility);

	public abstract Spellcasting getSpellcasting();
	
	public void setSpellcasting(Spellcasting spellcasting);

	public abstract String getMountsAndVehicles();
	
	public void setMountsAndVehicles(String mountsAndVehicles);

	public abstract String getGear();
	
	public void setGear(String gear);

	public abstract Treasure getTreasure();
	
	public void setTreasure(Treasure treasure);

	public abstract List<Attack> getAttacks();

	public List<FantasyCraftTemplate> getTemplates();
}
