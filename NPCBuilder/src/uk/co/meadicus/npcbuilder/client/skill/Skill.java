package uk.co.meadicus.npcbuilder.client.skill;

public class Skill implements Comparable<Skill> {

	private final String name;
	private int rating;
	
	public Skill(String name, int rating) {
		super();
		this.name = name;
		this.rating = rating;
	}

	public String getName() {
		return name;
	}

	public int getRating() {
		return rating;
	}

	protected void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * Compares by skill name for alphabetic sorting.
	 */
	@Override
	public int compareTo(Skill obj) {
		return getName().compareToIgnoreCase(obj.getName());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Skill other = (Skill) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
		
}
