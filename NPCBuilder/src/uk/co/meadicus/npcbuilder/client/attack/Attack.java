package uk.co.meadicus.npcbuilder.client.attack;

import uk.co.meadicus.npcbuilder.client.xp.HasXP;

public abstract class Attack implements Cloneable, HasXP {

	private String name = "undefined";
	private String title = "";
	private int grade = 1;
	
	public static Attack parse(String text) {
		String title = text;
		String details = "";
		if (text.indexOf("(") > 0) {
			title = text.substring(0, text.indexOf("("));
			// copy text from within the brackets into use
			details = text.substring(text.indexOf("(")+1, text.lastIndexOf(")"));
		}
		return parseAttack(title.trim(), details.trim());
	}
	
	private static Attack parseAttack(String title, String description) {
		Attack attack = null;
		
		// natural attack = talon II × 4
		if (title.matches(".+ [VIX]+( × \\d+)?")) {
			String attackType = title.replaceFirst("^(.+) [VIX]+( × \\d+)?$", "$1").toLowerCase().trim();
			if (AttacksConfig.naturalAttacks.contains(attackType)) {
				attack = new NaturalAttack();
			}			
		}		
		
		if (attack == null) {
			// get the first item of the description
			String firstpart = description;
			if (description.contains(":") || description.contains(";")) {
				firstpart = description.replaceFirst("^([^:;]+)[:;].*", "$1");
			}
			
			// damage attack = custom (rotting (attack)? II; stuff)
			// save attack = custom (baffling (attack)? II; stuff)
			if (firstpart.matches(".+ [VIX]+")) {
				String attackType = firstpart.substring(0, firstpart.lastIndexOf(" ")).toLowerCase().trim();
				if (attackType.endsWith("attack")) {
					attackType = attackType.substring(0, attackType.length()-6).trim();
				}
				if (AttacksConfig.damageAttacks.containsKey(attackType)) {
					attack = new DamageExtraordinaryAttack();
				} else if (AttacksConfig.saveAttacks.containsKey(attackType)) {
					attack = new SaveExtraordinaryAttack();
				}
			}			
			
			if (attack == null) {
				// weapon attack = custom (custom)
				attack = new WeaponAttack();
			}
		}
		
		attack.parse(title, description);
		
		return attack;
	}
	
	public abstract Attack clone();
	
	protected void copyTo(Attack to) {
		to.setName(getName());
		to.setGrade(getGrade());
		to.setTitle(getTitle());
	}
	
	public abstract void parse(String title, String description);
		
	public final String render() {
		String output = renderTitle();
		String description = renderDescription().trim();
		if (!description.isEmpty()) {
			output += " (" + description + ")"; 
		}
		return output;
	}

	public abstract String renderTitle();
	
	public abstract String renderDescription();
	
	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getTitle() {
		return title;
	}

	protected void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + grade;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attack other = (Attack) obj;
		if (grade != other.grade)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
}
