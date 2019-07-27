package uk.co.meadicus.npcbuilder.client.quality;

public abstract class OptionsQuality extends Quality {

	private String[] options;
		
	protected OptionsQuality(String name, double baseXp, String restrictions, String[] options) {
		super(name, baseXp, restrictions);
		this.options = options;
	}

	public String[] getOptions() {
		return options;
	}

}
