package uk.co.meadicus.npcbuilder.client.quality;

import java.util.List;

import uk.co.meadicus.npcbuilder.client.npc.Attributes;
import uk.co.meadicus.npcbuilder.client.npc.Attributes.Attribute;
import uk.co.meadicus.npcbuilder.client.npc.AttributesImpl;
import uk.co.meadicus.npcbuilder.client.util.NPCUtils;
import uk.co.meadicus.npcbuilder.client.xp.SimpleXP;
import uk.co.meadicus.npcbuilder.client.xp.XP;

public class OriginQuality extends Quality implements AttributeModifier {

	private static final String DETAILS_DELIM = " ~ ";
	
	private String title;
	private AttributesImpl attributeModifiers;
	private String extra;
		
	public OriginQuality(String name, double baseXp, String restrictions) {
		super(name, baseXp, restrictions);
		this.title = "";
		this.attributeModifiers = new AttributesImpl(0,0,0,0,0,0);
		this.extra = "";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public AttributesImpl getAttributeModifiers() {
		return attributeModifiers;
	}

	public void setAttributeModifiers(Attributes attributeModifiers) {
		this.attributeModifiers = new AttributesImpl(attributeModifiers);
	}

	
	public OriginQuality clone() {
		OriginQuality cloneQuality = new OriginQuality(getName(), getBaseXp(), getRestrictions());
		cloneQuality.setTitle(getName());
		cloneQuality.setExtra(getExtra());
		cloneQuality.setAttributeModifiers(getAttributeModifiers());
		return cloneQuality;
	}

	
	public XP getXp() {
		if (getName().trim().isEmpty()) {
			return new SimpleXP(0);
		}
		return new SimpleXP(getBaseXp(), true);
	}

	
	protected String renderDetails() {
		String details = getTitle();
		
		String attrDetails = "";
		String attrDelim = "";
		for (Attribute attribute : Attribute.values()) {			
			int modifier = getAttributeModifiers().getAttribute(attribute);
			if (modifier != 0) {
				attrDetails += attrDelim;
				attrDetails += attribute.toString();
				attrDetails += " ";
				attrDetails += NPCUtils.renderModifier(modifier);
				attrDelim = ", ";
			}
		}
		
		if (!attrDetails.isEmpty()) {
			details += DETAILS_DELIM;
			details += attrDetails.trim();			
		}
		
		if (!getExtra().trim().isEmpty()) {
			details += DETAILS_DELIM;
			details += getExtra();
		}
		
		return details;
	}

	
	protected void reset() {
		setTitle("");
		setAttributeModifiers(new AttributesImpl(0,0,0,0,0,0));
		setExtra("");
	}

	
	protected void parseDetails(String details) {
		String[] components = details.split(DETAILS_DELIM);
		
		setTitle(components[0].trim());
		

		if (components.length > 2) {
			setExtra(components[2].trim());
			parseAttributeModifiers(components[1].trim());			
		} else	if (components.length > 1) {
			// could be extra or attribute modifiers
			String text = components[1].trim();
			if (text.contains(",") || text.matches("\\p{Alhpa}{3}\\s+\\d+")) {
				parseAttributeModifiers(text);
			} else {
				setExtra(text);					
			}
		}
		
	}
	
	private void parseAttributeModifiers(String text) {
		List<String> uses = NPCUtils.bracketAwareSplit(text, ',');
		for (String use : uses) {
			// Assume 'attr' 'score'
			String attr = use.trim().substring(0, 3);
			Attribute attribute = Attribute.fromString(attr);
			if (attribute != null) {
				int score = Integer.parseInt(use.trim().substring(3).trim().replace("+", ""));
				getAttributeModifiers().setAttribute(attribute, score);
			}
		}
	}
}
