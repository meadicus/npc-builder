package uk.co.meadicus.npcbuilder.client.spellcasting;

import java.util.ArrayList;
import java.util.List;

public class Spellcasting {

	private int grade = 0;
	private final List<String> spells = new ArrayList<String>();
	
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public List<String> getSpells() {
		return spells;
	}

}
