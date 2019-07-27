package uk.co.meadicus.npcbuilder.client.npc;

import java.util.List;

import uk.co.meadicus.npcbuilder.client.npc.Attributes.Attribute;
import uk.co.meadicus.npcbuilder.client.quality.QualityFactory;
import uk.co.meadicus.npcbuilder.client.skill.Skill;

public interface Config {
		
	public int getMaxSkills();
	public List<String> getAvailableSkills();
	public QualityFactory getQualityFactory();
	public Attribute[] getSkillAttributes(Skill skill);
	public int getDefaultMaxUses();
	
	
	public int computeInit(int init, int TL);
	public int computeAtk(int atk, int TL);
	public int computeDef(int def, int TL);
	public int computeRes(int res, int TL);
	public int computeHlth(int hlth, int TL);
	public int computeComp(int comp, int TL);
	public int computeSkill(int skill, int TL);
}
