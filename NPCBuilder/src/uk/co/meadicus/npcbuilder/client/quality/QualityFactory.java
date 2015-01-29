package uk.co.meadicus.npcbuilder.client.quality;

import java.util.List;
import java.util.Map;

/**
 * Class for building quality objects.
 * 
 * @author mead
 */
public abstract class QualityFactory {
	
	public abstract Quality getQuality(String name);

	public Quality parseQuality(String text) {
		// get quality name
		text = text.trim().replaceFirst("[,.]+$", "");
		String name = text.replaceAll("^\\W+|\\W+$", "");
		if (text.indexOf("(") > 0) {
			name = text.substring(0,text.indexOf("(")).trim();
		} else if (name.matches("^.+ ([VIX]+|\\d+)$")) {
			int lastSpace = name.lastIndexOf(" ");
			name = name.substring(0, lastSpace).trim();
		}
				
		// lookup quality instance from config, clone it
		name = name.toLowerCase();
		Quality newquality = getQuality(name);
		
		// call parse method on new quality
		if (newquality != null) {
			newquality.parseQuality(text);
		}
		
		return newquality;
	}
		
	public abstract Map<String, List<String>> getQualityNames();
}
