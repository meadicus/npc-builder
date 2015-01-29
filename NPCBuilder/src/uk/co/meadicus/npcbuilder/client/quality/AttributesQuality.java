package uk.co.meadicus.npcbuilder.client.quality;

import java.util.List;

import uk.co.meadicus.npcbuilder.client.npc.Attributes;
import uk.co.meadicus.npcbuilder.client.npc.Attributes.Attribute;
import uk.co.meadicus.npcbuilder.client.npc.AttributesImpl;
import uk.co.meadicus.npcbuilder.client.util.NPCUtils;
import uk.co.meadicus.npcbuilder.client.xp.ComplexXP;
import uk.co.meadicus.npcbuilder.client.xp.XP;

public class AttributesQuality extends Quality implements AttributeModifier {

	private final Attributes defaultAttributes;
	private AttributesImpl attributeModifiers;
	private final int limit;
	
	protected AttributesQuality(String name, Attributes attributes, int limit) {
		super(name, 0, null);
		this.defaultAttributes = attributes;
		this.attributeModifiers = new AttributesImpl(0,0,0,0,0,0);
		this.limit = limit;
	}
	
	public int getLimit() {
		return limit;
	}

	protected Attributes getDefaultAttributes() {
		return defaultAttributes;
	}
	
	public AttributesImpl getAttributeModifiers() {
		return this.attributeModifiers;
	}
	
	public void setAttributeModifiers(Attributes modifiers) {
		this.attributeModifiers = new AttributesImpl(modifiers);
	}

	protected Attributes getAttributes() {
		AttributesImpl attributes = new AttributesImpl(getDefaultAttributes());
		attributes.applyModifiers(getAttributeModifiers());
		return attributes;
	}
	
	@Override
	public Quality clone() {
		AttributesQuality cloneQuality = new AttributesQuality(getName(),
															   getDefaultAttributes(),
															   getLimit());
		cloneQuality.setAttributeModifiers(getAttributeModifiers());
		return cloneQuality;
	}

	@Override
	public XP getXp() {
		ComplexXP xp = new ComplexXP(true);
		for (Attribute attribute : Attributes.Attribute.values()) {
			// Get the default value
			int defaultValue = getDefaultAttributes().getAttribute(attribute);
			// Get the chosen value
			int value = getAttributes().getAttribute(attribute);
			// Add the difference
			xp.put(attribute.toString(), value - defaultValue);
		}
		return xp;
	}

	@Override
	protected String renderDetails() {
		String details = "";
		String delim = "";
		for (Attribute attribute : Attributes.Attribute.values()) {
			// Get the default value
			int defaultValue = getDefaultAttributes().getAttribute(attribute);
			// Get the chosen value
			int value = getAttributes().getAttribute(attribute);
			
			if (value != defaultValue) {
				details += delim;
				details += attribute.toString();
				details += " ";
				details += value;
				delim = ", ";
			}
		}
		return details;
	}

	@Override
	protected void reset() {
		setAttributeModifiers(new AttributesImpl(0,0,0,0,0,0));
	}
	
	@Override
	protected void parseDetails(String details) {
		List<String> uses = NPCUtils.bracketAwareSplit(details, ',');
		for (String use : uses) {
			// Assume 'attr ##'
			String attr = use.trim().substring(0, 3);
			Attribute attribute = Attribute.fromString(attr);
			if (attribute != null) {
				int score = Integer.parseInt(use.trim().substring(3).trim());
				int scoreDiff = score - getDefaultAttributes().getAttribute(attribute);
				getAttributeModifiers().setAttribute(attribute, scoreDiff);
			}
		}
	}

}
