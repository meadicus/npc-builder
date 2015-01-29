package uk.co.meadicus.npcbuilder.client.attack;

public abstract class DamageAttack extends Attack {

	protected static final String DEFAULT_DAMAGE_TYPE = "lethal";

	private String aligned = "";
	private String damageType = DEFAULT_DAMAGE_TYPE;
	private int armourPiercing = 0;
	private boolean bleed = false;
	private String diseased = "";
	private int keen = 0;
	private String venomous = "";

	protected void copyTo(DamageAttack to) {
		super.copyTo(to);
		to.setAligned(getAligned());
		to.setDamageType(getDamageType());
		to.setArmourPiercing(getArmourPiercing());
		to.setBleed(isBleed());
		to.setDiseased(getDiseased());
		to.setKeen(getKeen());
		to.setVenomous(getVenomous());
	}

	protected String getAligned() {
		return aligned;
	}

	protected void setAligned(String aligned) {
		this.aligned = aligned;
	}

	protected String getDamageType() {
		return damageType;
	}

	protected void setDamageType(String damageType) {
		this.damageType = damageType;
	}

	protected int getArmourPiercing() {
		return armourPiercing;
	}

	protected void setArmourPiercing(int armourPiercing) {
		this.armourPiercing = armourPiercing;
	}

	protected boolean isBleed() {
		return bleed;
	}

	protected void setBleed(boolean bleed) {
		this.bleed = bleed;
	}

	protected String getDiseased() {
		return diseased;
	}

	protected void setDiseased(String diseased) {
		this.diseased = diseased;
	}

	protected int getKeen() {
		return keen;
	}

	protected void setKeen(int keen) {
		this.keen = keen;
	}

	protected String getVenomous() {
		return venomous;
	}

	protected void setVenomous(String venomous) {
		this.venomous = venomous;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((aligned == null) ? 0 : aligned.hashCode());
		result = prime * result + armourPiercing;
		result = prime * result + (bleed ? 1231 : 1237);
		result = prime * result
				+ ((damageType == null) ? 0 : damageType.hashCode());
		result = prime * result
				+ ((diseased == null) ? 0 : diseased.hashCode());
		result = prime * result + keen;
		result = prime * result
				+ ((venomous == null) ? 0 : venomous.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		DamageAttack other = (DamageAttack) obj;
		if (aligned == null) {
			if (other.aligned != null)
				return false;
		} else if (!aligned.equals(other.aligned))
			return false;
		if (armourPiercing != other.armourPiercing)
			return false;
		if (bleed != other.bleed)
			return false;
		if (damageType == null) {
			if (other.damageType != null)
				return false;
		} else if (!damageType.equals(other.damageType))
			return false;
		if (diseased == null) {
			if (other.diseased != null)
				return false;
		} else if (!diseased.equals(other.diseased))
			return false;
		if (keen != other.keen)
			return false;
		if (venomous == null) {
			if (other.venomous != null)
				return false;
		} else if (!venomous.equals(other.venomous))
			return false;
		return true;
	}
	
	
}
