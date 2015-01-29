package uk.co.meadicus.npcbuilder.client.treasure.generator;

public class TreasureGenSpec {

	private final int xp;
	private final int tl;
	private final int mod;
	
	public TreasureGenSpec(int xp, int tl, int mod) {
		super();
		this.xp = xp;
		this.tl = tl;
		this.mod = mod;
	}

	public int getXp() {
		return xp;
	}

	public int getTl() {
		return tl;
	}

	public int getMod() {
		return mod;
	}
	
}
