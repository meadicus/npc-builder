package uk.co.meadicus.npcbuilder.client.npc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import uk.co.meadicus.npcbuilder.client.quality.Quality;
import uk.co.meadicus.npcbuilder.client.skill.Skill;

public abstract class NPCImpl implements NPC {
	
	private String name = "New NPC";
	private int init = 1, atk = 1, def = 1, res = 1, hlth = 1, comp = 1;
	private final SortedMap<String, Quality> qualities = new TreeMap<String, Quality>();
	private final List<Skill> skills = new ArrayList<Skill>();
		
	public void reset() {
		setName("New NPC");
		setInit(1); setAtk(1); setDef(1);
		setRes(1); setHlth(1); setComp(1);
		getQualities().clear();
		getSkills().clear();
	}
	
	public abstract Config getConfig();
	
	
	public Map<String, Quality> getQualities() {
		return qualities;
	}
	
	public boolean hasQuality(Quality quality) {
		return getQualities().containsKey(quality.getIdentifyingName());
	}
	
	public boolean hasQuality(String qualityName) {
		return getQualities().containsKey(qualityName);
	}

	public List<Skill> getSkills() {
		return skills;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getInit() {
		return init;
	}

	public void setInit(int init) {
		this.init = init;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getRes() {
		return res;
	}

	public void setRes(int res) {
		this.res = res;
	}

	public int getHlth() {
		return hlth;
	}

	public void setHlth(int hlth) {
		this.hlth = hlth;
	}

	public int getComp() {
		return comp;
	}

	public void setComp(int comp) {
		this.comp = comp;
	}

	
	public int getAttribute(Attribute attribute) {
		int attr = 0;
		switch(attribute) {
		case STR:
			attr = getStr();
			break;
		case DEX:
			attr = getDex();
			break;
		case CON:
			attr = getCon();
			break;
		case INT:
			attr = getInt();
			break;
		case WIS:
			attr = getWis();
			break;
		case CHA:
			attr = getCha();
		}
		return attr;
	}

	public NPC getNPCToRender() {
		return this;
	}
}
