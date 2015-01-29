package uk.co.meadicus.npcbuilder.client.xp;

public class SimpleXP extends XP {

	private final double value;
	
	public SimpleXP(int value) {
		super();
		this.value = value;
	}
	
	public SimpleXP(double value) {
		super();
		this.value = value;
	}
	
	public SimpleXP(int value, boolean autofix) {
		super(autofix);
		this.value = value;
	}
	
	public SimpleXP(double value, boolean autofix) {
		super(autofix);
		this.value = value;
	}

	@Override
	public double getValue() {
		if (isAutoFix()) {
			return fixXp(this.value);
		} else {
			return this.value;
		}
	}

}
