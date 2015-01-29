package uk.co.meadicus.npcbuilder.client.quality;

import uk.co.meadicus.npcbuilder.client.xp.HasXP;


public abstract class Quality implements Cloneable, HasXP {

	private String name;
	private double baseXp;
	private String restrictions;
	
	protected Quality(String name, double baseXp, String restrictions) {
		super();
		this.name = name;
		this.baseXp = baseXp;
		this.restrictions = restrictions;
	}
	
	/**
	 * Get the html representation of this quality.
	 * 
	 * @return
	 */
	public String render() {
		String output = "";
		output += "<span>";
		output += "<em>" + this.name + "</em>";
		
		String details = renderDetails();
		if (details != null && !details.isEmpty()) {
			// if details is just a roman numeral, or digits, don't put it in brackets
			if (details.matches("^([VIX]+|\\d+)$")) {
				output += " " + renderDetails();
			} else {
				output += " (" + renderDetails() + ")";
			}
		}
		
		output += "</span>";
		
		return output;
	}
		
	public double getBaseXp() {
		return baseXp;
	}
	
	public String getName() {
		return name;
	}

	public String getRestrictions() {
		return restrictions;
	}

	public abstract Quality clone();

	protected Quality parseQuality(String text) {
		String details = "";
		if (text.indexOf("(") > 0) {
			// copy text from within the brackets into use
			details = text.substring(text.indexOf("(")+1, text.lastIndexOf(")"));
		} else if (text.matches("^.+ ([VIX]+|\\d+)$")) {
			int lastSpace = text.lastIndexOf(" ");
			details = text.substring(lastSpace).trim();
		}
		reset();
		parseDetails(details);
		return this;
	}

	public String getIdentifyingName() {
		return this.name;
	}
	
	protected abstract void reset();
	
	protected abstract String renderDetails();
	
	protected abstract void parseDetails(String details);
}
