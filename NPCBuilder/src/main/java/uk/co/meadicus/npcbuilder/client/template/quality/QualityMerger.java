package uk.co.meadicus.npcbuilder.client.template.quality;

import uk.co.meadicus.npcbuilder.client.quality.Quality;

@SuppressWarnings("unchecked")
public abstract class QualityMerger<T extends Quality> {

	protected final T baseQuality;
	
	public QualityMerger(T baseQuality) {
		this.baseQuality = baseQuality;
	}
	
	public T mergeIn(Quality in) {
		// property type checking isn't reliable in GWT, so we just force
		return qualifiedMergeIn((T)in);
	}
	
	protected abstract T qualifiedMergeIn(T in);
	
	/**
	 * When merging a quality out, if nothing is left of the original quality this returns null
	 * @param remove
	 * @return base quality without any of the remove quality, or null
	 */
	public T mergeOut(Quality out) {
		// property type checking isn't reliable in GWT, so we just force
		return qualifiedMergeOut((T)out);
	}
	
	protected abstract T qualifiedMergeOut(T out);
}
