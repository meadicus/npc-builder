package uk.co.meadicus.npcbuilder.client.npc;

import java.util.List;
import java.util.Map;

import uk.co.meadicus.npcbuilder.client.quality.Quality;
import uk.co.meadicus.npcbuilder.client.skill.Skill;
import uk.co.meadicus.npcbuilder.client.xp.HasXP;

public interface NPC extends Attributes, HasXP {
	
	public void reset();
	
	public Config getConfig();
		
	public Map<String, Quality> getQualities();
	
	public boolean hasQuality(Quality quality);
	
	public boolean hasQuality(String qualityName);

	public List<Skill> getSkills();
	
	public String getName();

	public void setName(String name);
	
	public int getInit();

	public void setInit(int init);

	public int getAtk();

	public void setAtk(int atk);

	public int getDef();

	public void setDef(int def);

	public int getRes();

	public void setRes(int res);

	public int getHlth();

	public void setHlth(int hlth);

	public int getComp();

	public void setComp(int comp);

	public NPC getNPCToRender();
}
