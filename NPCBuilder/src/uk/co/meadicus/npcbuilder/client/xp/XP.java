package uk.co.meadicus.npcbuilder.client.xp;

public abstract class XP {
	
	boolean autofix = false;
	
	public XP() {
		// nothing to do
	}
	
	public XP(boolean autofix) {
		this.autofix = autofix;
	}

	public abstract double getValue();
	
	protected int fixXp(double xp) {
		return (xp < 0) ? (int)Math.floor(xp) : (int)Math.ceil(xp);
	}
	
	protected boolean isAutoFix() {
		return this.autofix;
	}

	@Override
	public String toString() {
		if (Math.floor(getValue()) == getValue()) {
			return Integer.toString((int)getValue());
		} else {
			return Double.toString(getValue());
		}
	}
	
	
}
