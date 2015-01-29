package uk.co.meadicus.npcbuilder.client.quality;

import uk.co.meadicus.npcbuilder.client.npc.Config;

import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Panel;

public interface QualityUserInterface<Q extends Quality> {
			
	void setupInterface(Panel panel,
						Q quality,
						ChangeHandler changeHandler,
						Config config);
	
	void populateFromInterface(Panel panel, Q quality);
	
}
