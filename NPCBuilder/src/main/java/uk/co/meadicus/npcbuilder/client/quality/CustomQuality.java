package uk.co.meadicus.npcbuilder.client.quality;

import uk.co.meadicus.npcbuilder.client.xp.SimpleXP;
import uk.co.meadicus.npcbuilder.client.xp.XP;

public class CustomQuality extends Quality {

	private int xp;
	private String title;
	private String details;
	
	protected CustomQuality() {
		super("custom", 0, null);
	}

	
	public XP getXp() {
		return new SimpleXP(this.xp);
	}

	
	public CustomQuality clone() {
		CustomQuality quality = new CustomQuality();
		quality.setDetails(this.details);
		quality.setTitle(this.title);
		quality.setXp(this.xp);
		return quality;
	}

	
	protected void reset() {
		this.xp = 0;
		this.details = "";
		this.title = "custom";
	}

	
	protected String renderDetails() {
		String renderedDetails = "";
		if (this.details != null && !this.details.isEmpty()) {
			renderedDetails += this.details + " ";
		}
		renderedDetails += this.xp + "XP";
		return renderedDetails;
	}

	
	protected void parseDetails(String details) {
		if (details.matches("-?\\d+((xp)|(XP))")) {
			this.xp = Integer.parseInt(details.substring(0, details.length()-2));
		} else if (details.matches(".+\\s-?\\d+((xp)|(XP))")) {
			// split on last white space
			String[] parts = details.split("\\s+");
			String lastPart = parts[parts.length-1];
			this.xp = Integer.parseInt(lastPart.substring(0, lastPart.length()-2));
			this.details = details.substring(0, details.length() - lastPart.length()).trim();
		} else { // no xp value at end of details
			this.details = details;
		}
	}
	
	/**
	 * Get the html representation of this quality.
	 * 
	 * @return
	 */
	public String render() {
		String output = "";
		output += "<span>";
		output += "<em>" + this.title + "</em>";
		
		output += " (" + renderDetails() + ")";
		
		output += "</span>";
		
		return output;
	}

	protected Quality parseQuality(String text) {
		String details = "";
		reset();
		if (text.indexOf("(") > 0) {
			// copy text from within the brackets into use
			details = text.substring(text.indexOf("(")+1, text.lastIndexOf(")"));
			this.title = text.substring(0,text.indexOf("(")).trim();
		}
		parseDetails(details.trim());
		return this;
	}

	public String getIdentifyingName() {
		return this.getTitle();
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}
	public double getBaseXp() {
		return this.xp;
	}

}
