package uk.co.meadicus.npcbuilder.client.template.quality;

import uk.co.meadicus.npcbuilder.client.quality.AttributesQuality;
import uk.co.meadicus.npcbuilder.client.quality.CustomQuality;
import uk.co.meadicus.npcbuilder.client.quality.DetailQuality;
import uk.co.meadicus.npcbuilder.client.quality.OriginQuality;
import uk.co.meadicus.npcbuilder.client.quality.Quality;
import uk.co.meadicus.npcbuilder.client.quality.SimpleQuality;
import uk.co.meadicus.npcbuilder.client.quality.ValuedDetailQuality;

public abstract class QualityMergerFactory {
	
	public static QualityMerger<? extends Quality> createQualityMerger(Quality quality) {

		QualityMerger<? extends Quality> merger = null;
		
		if (quality instanceof OriginQuality) {
			merger = null;
		} else if (quality instanceof AttributesQuality) {
			merger = null;
		} else if (quality instanceof ValuedDetailQuality) {
			merger = new ValuedDetailQualityMerger((ValuedDetailQuality)quality);
		} else if (quality instanceof DetailQuality) {
			merger = new DetailQualityMerger((DetailQuality)quality);
		} else if (quality instanceof SimpleQuality) {
			merger = new SimpleQualityMerger((SimpleQuality)quality);
		} else if (quality instanceof CustomQuality) {
			merger = new CustomQualityMerger((CustomQuality)quality);
		}
		
		return merger;
	}
}
