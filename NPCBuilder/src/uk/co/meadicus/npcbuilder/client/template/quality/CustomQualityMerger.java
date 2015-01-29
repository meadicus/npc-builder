package uk.co.meadicus.npcbuilder.client.template.quality;

import uk.co.meadicus.npcbuilder.client.quality.CustomQuality;

public class CustomQualityMerger extends QualityMerger<CustomQuality> {

	public CustomQualityMerger(CustomQuality baseQuality) {
		super(baseQuality);
	}

	@Override
	protected CustomQuality qualifiedMergeIn(CustomQuality in) {
		
		CustomQuality quality = baseQuality.clone();
		
		if (!in.getDetails().equals(quality.getDetails())) {
			String newDetails = quality.getDetails() + ", " + in.getDetails();
			double newxp = quality.getBaseXp() + in.getBaseXp();
			quality.setDetails(newDetails);
			quality.setXp((int)newxp);
		}
		
		return quality;
	}

	@Override
	protected CustomQuality qualifiedMergeOut(CustomQuality out) {
		
		CustomQuality quality = baseQuality.clone();
		
		if (out.getDetails().equals(quality.getDetails())) {
			quality = null;
		} else if (quality.getDetails().contains(out.getDetails())) {
			String newDetails = quality.getDetails().replace(out.getDetails(), "");
			newDetails = newDetails.replaceFirst("^\\s*,\\s*", "");
			newDetails = newDetails.replaceFirst("\\s*,\\s*$", "");			
			double newxp = quality.getBaseXp() - out.getBaseXp();
			quality.setDetails(newDetails.trim());
			quality.setXp((int)newxp);
		}
		
		return quality;
	}

}
