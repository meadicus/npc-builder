package uk.co.meadicus.npcbuilder.client.npc;

public final class AttributesImpl implements Attributes {

	private int str = 0, dex = 0, con = 0, intel = 0, wis = 0, cha = 0;
		
	public AttributesImpl(int str, int dex, int con, int intel, int wis,
			int cha) {
		super();
		this.str = str;
		this.dex = dex;
		this.con = con;
		this.intel = intel;
		this.wis = wis;
		this.cha = cha;
	}

	public AttributesImpl(int[] stats) {
		super();
		if (stats.length < 6) {
			throw new IllegalArgumentException("Wrong number of stats");
		}
		this.str = stats[0];
		this.dex = stats[1];
		this.con = stats[2];
		this.intel = stats[3];
		this.wis = stats[4];
		this.cha = stats[5];		
	}

	public AttributesImpl(Attributes attributes) {
		super();
		this.str = attributes.getStr();
		this.dex = attributes.getDex();
		this.con = attributes.getCon();
		this.intel = attributes.getInt();
		this.wis = attributes.getWis();
		this.cha = attributes.getCha();
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

	public void setAttribute(Attribute attribute, int score) {

		switch(attribute) {
		case STR:
			setStr(score);
			break;
		case DEX:
			setDex(score);
			break;
		case CON:
			setCon(score);
			break;
		case INT:
			setInt(score);
			break;
		case WIS:
			setWis(score);
			break;
		case CHA:
			setCha(score);
		}
	}
	
	public void applyModifiers(Attributes modifiers) {
		for (Attribute attribute : Attribute.values()) {
			this.setAttribute(attribute, Math.max(1, this.getAttribute(attribute) + modifiers.getAttribute(attribute)));
		}
	}
	
	
	public int getCha() {
		return this.cha;
	}

	
	public int getCon() {
		return this.con;
	}

	
	public int getDex() {
		return this.dex;
	}

	
	public int getInt() {
		return this.intel;
	}

	
	public int getStr() {
		return this.str;
	}

	
	public int getWis() {
		return this.wis;
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

}
