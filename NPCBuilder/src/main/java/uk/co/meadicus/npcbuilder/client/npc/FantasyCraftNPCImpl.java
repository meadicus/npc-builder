package uk.co.meadicus.npcbuilder.client.npc;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import uk.co.meadicus.npcbuilder.client.attack.Attack;
import uk.co.meadicus.npcbuilder.client.npc.FantasyCraftConfig.FCNPCType;
import uk.co.meadicus.npcbuilder.client.npc.form.Mobility;
import uk.co.meadicus.npcbuilder.client.npc.form.Mobility.MotionType;
import uk.co.meadicus.npcbuilder.client.skill.Skill;
import uk.co.meadicus.npcbuilder.client.spellcasting.Spellcasting;
import uk.co.meadicus.npcbuilder.client.template.FantasyCraftTemplate;
import uk.co.meadicus.npcbuilder.client.template.TemplatedFantasyCraftNPC;
import uk.co.meadicus.npcbuilder.client.treasure.Treasure;
import uk.co.meadicus.npcbuilder.client.util.Roman;
import uk.co.meadicus.npcbuilder.client.xp.ComplexXP;
import uk.co.meadicus.npcbuilder.client.xp.XP;

public class FantasyCraftNPCImpl extends NPCImpl implements FantasyCraftNPC {

	private int str = 10, dex = 10, con = 10, intel = 10, wis = 10, cha = 10;
	private Size size = Size.MEDIUM;
	private int footprintX = 1, footprintY = 1;
	private int reach = 1;
	private final Set<FantasyCraftConfig.FCNPCType> type = new LinkedHashSet<FantasyCraftConfig.FCNPCType>();
	private Mobility mobility = new Mobility(MotionType.WALKER, 30);
	private Spellcasting spellcasting = new Spellcasting();
	private String mountsAndVehicles = "";
	private String gear = "";
	private Treasure treasure = new Treasure();
	private final List<Attack> attacks = new ArrayList<Attack>();
	private final List<FantasyCraftTemplate> templates = new ArrayList<FantasyCraftTemplate>();

	private final FantasyCraftConfig config;
	
	private static FantasyCraftNPCImpl singleton;
	private FantasyCraftNPCImpl() {
		this.config = FantasyCraftConfig.getInstance();
		this.type.add(FantasyCraftConfig.FCNPCType.FOLK);
	}
	public static FantasyCraftNPCImpl getInstance() {
		if (singleton == null) {
			singleton = new FantasyCraftNPCImpl();
		}
		return singleton;
	}
	
	/* (non-Javadoc)
	 * @see uk.co.meadicus.npcbuilder.client.npc.MasterCraftNPC#getConfig()
	 */
	
	public FantasyCraftConfig getConfig() {		
		return config;
	}

	/* (non-Javadoc)
	 * @see uk.co.meadicus.npcbuilder.client.npc.MasterCraftNPC#getXp()
	 */
	
	public XP getXp() {
		ComplexXP xp = new ComplexXP(true);
		
		// type
		ComplexXP typexp = new ComplexXP();
		for (FCNPCType type : getType()) {
			typexp.put(type.getDisplayText(), type.getXpValue());
		}
		xp.put("type", typexp);
		
		// reach
		if (getReach() > 1) {
			xp.put("reach (" + getReach() + ")", Math.max(0, (getReach() - 1)));
		}
		
		// attributes
		ComplexXP attrsxp = new ComplexXP();
		for (Attribute attribute : Attribute.values()) {
			int attr = getAttribute(attribute);
			if (attr > 10) {
				attrsxp.put(attribute.getDisplayText(), attr - 10);
			}
		}
		xp.put("attributes", attrsxp);
		
		// traits
		ComplexXP traitsxp = new ComplexXP();
		traitsxp.put("init", getInit());
		traitsxp.put("atk", getAtk());
		traitsxp.put("def", getDef());
		traitsxp.put("res", getRes());
		traitsxp.put("health", getHlth());
		traitsxp.put("comp", getComp());
		xp.put("traits", traitsxp);
		
		// mobility
		xp.put("mobility", getMobility().getXp());
		
		// template
		ComplexXP templatexp = new ComplexXP();
		for (FantasyCraftTemplate template : getTemplates()) {
			templatexp.put(template.getName(), template.getXp());
		}
		xp.put("templates", templatexp);

		// skills
		ComplexXP skillsxp = new ComplexXP();
		for (Skill skill : getSkills()) {
			skillsxp.put(skill.getName(), skill.getRating());
		}
		xp.put("skills", skillsxp);

		// spellcasting
		if (getSpellcasting() != null && getSpellcasting().getGrade() > 0) {
			xp.put("spellcasting (" + Roman.toRoman(getSpellcasting().getGrade()) + ")", getSpellcasting().getGrade());
		}
		
		// qualities
		ComplexXP qualitiesxp = new ComplexXP();
		for (String qualityName : getQualities().keySet()) {
			qualitiesxp.put(qualityName, getQualities().get(qualityName).getXp());
		}
		xp.put("qualities", qualitiesxp);
		
		
		// attacks
		ComplexXP attacksxp = new ComplexXP();
		for (Attack attack : getAttacks()) {
			attacksxp.put(attack.renderTitle(), attack.getXp());
		}
		xp.put("attacks", attacksxp);
		
		return xp;
	}

	
	public void reset() {
		super.reset();
		setStr(10);	setDex(10); setCon(10);
		setInt(10); setWis(10); setCha(10);
		setSize(Size.MEDIUM);
		getType().clear();
		getType().add(FCNPCType.FOLK);
		setMobility(new Mobility(MotionType.WALKER, 30));
		setReach(1);
		setFootprintX(1);
		setFootprintY(1);
		setSpellcasting(new Spellcasting());
		setTreasure(new Treasure());
		setMountsAndVehicles("");
		setGear("");
		getAttacks().clear();
		getTemplates().clear();
	}

	public NPC getNPCToRender() {
		FantasyCraftNPC npcToRender = this;
		for (FantasyCraftTemplate template : this.getTemplates()) {
			npcToRender = new TemplatedFantasyCraftNPC(npcToRender, template);
		}
		return npcToRender;
	}
	
	public void setStr(int str) {
		this.str = str;
	}

	public void setDex(int dex) {
		this.dex = dex;
	}

	public void setCon(int con) {
		this.con = con;
	}

	public void setInt(int intel) {
		this.intel = intel;
	}

	public void setWis(int wis) {
		this.wis = wis;
	}

	public void setCha(int cha) {
		this.cha = cha;
	}
	
	/* (non-Javadoc)
	 * @see uk.co.meadicus.npcbuilder.client.npc.MasterCraftNPC#getCha()
	 */
	
	public int getCha() {
		return this.cha;
	}

	/* (non-Javadoc)
	 * @see uk.co.meadicus.npcbuilder.client.npc.MasterCraftNPC#getCon()
	 */
	
	public int getCon() {
		return this.con;
	}

	/* (non-Javadoc)
	 * @see uk.co.meadicus.npcbuilder.client.npc.MasterCraftNPC#getDex()
	 */
	
	public int getDex() {
		return this.dex;
	}

	/* (non-Javadoc)
	 * @see uk.co.meadicus.npcbuilder.client.npc.MasterCraftNPC#getInt()
	 */
	
	public int getInt() {
		return this.intel;
	}

	/* (non-Javadoc)
	 * @see uk.co.meadicus.npcbuilder.client.npc.MasterCraftNPC#getStr()
	 */
	
	public int getStr() {
		return this.str;
	}

	/* (non-Javadoc)
	 * @see uk.co.meadicus.npcbuilder.client.npc.MasterCraftNPC#getWis()
	 */
	
	public int getWis() {
		return this.wis;
	}
	
	/* (non-Javadoc)
	 * @see uk.co.meadicus.npcbuilder.client.npc.MasterCraftNPC#getSize()
	 */
	
	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	/* (non-Javadoc)
	 * @see uk.co.meadicus.npcbuilder.client.npc.MasterCraftNPC#getFootprintX()
	 */
	
	public int getFootprintX() {
		return footprintX;
	}

	public void setFootprintX(int footprintX) {
		this.footprintX = footprintX;
	}

	/* (non-Javadoc)
	 * @see uk.co.meadicus.npcbuilder.client.npc.MasterCraftNPC#getFootprintY()
	 */
	
	public int getFootprintY() {
		return footprintY;
	}

	public void setFootprintY(int footprintY) {
		this.footprintY = footprintY;
	}

	/* (non-Javadoc)
	 * @see uk.co.meadicus.npcbuilder.client.npc.MasterCraftNPC#getReach()
	 */
	
	public int getReach() {
		return reach;
	}

	public void setReach(int reach) {
		this.reach = reach;
	}

	/* (non-Javadoc)
	 * @see uk.co.meadicus.npcbuilder.client.npc.MasterCraftNPC#getType()
	 */
	
	public Set<FantasyCraftConfig.FCNPCType> getType() {
		return type;
	}

	/* (non-Javadoc)
	 * @see uk.co.meadicus.npcbuilder.client.npc.MasterCraftNPC#getMobility()
	 */
	
	public Mobility getMobility() {
		return mobility;
	}

	public void setMobility(Mobility mobility) {
		this.mobility = mobility;
	}

	/* (non-Javadoc)
	 * @see uk.co.meadicus.npcbuilder.client.npc.MasterCraftNPC#getSpellcasting()
	 */
	
	public Spellcasting getSpellcasting() {
		return spellcasting;
	}

	public void setSpellcasting(Spellcasting spellcasting) {
		this.spellcasting = spellcasting;
	}

	/* (non-Javadoc)
	 * @see uk.co.meadicus.npcbuilder.client.npc.MasterCraftNPC#getMountsAndVehicles()
	 */
	
	public String getMountsAndVehicles() {
		return mountsAndVehicles;
	}

	public void setMountsAndVehicles(String mountsAndVehicles) {
		this.mountsAndVehicles = mountsAndVehicles;
	}

	/* (non-Javadoc)
	 * @see uk.co.meadicus.npcbuilder.client.npc.MasterCraftNPC#getGear()
	 */
	
	public String getGear() {
		return gear;
	}

	public void setGear(String gear) {
		this.gear = gear;
	}

	/* (non-Javadoc)
	 * @see uk.co.meadicus.npcbuilder.client.npc.MasterCraftNPC#getTreasure()
	 */
	
	public Treasure getTreasure() {
		return treasure;
	}
	
	public void setTreasure(Treasure treasure) {
		this.treasure = treasure;
	}

	/* (non-Javadoc)
	 * @see uk.co.meadicus.npcbuilder.client.npc.MasterCraftNPC#getAttacks()
	 */
	
	public List<Attack> getAttacks() {
		return attacks;
	}

	public List<FantasyCraftTemplate> getTemplates() {
		return templates;
	}

}
