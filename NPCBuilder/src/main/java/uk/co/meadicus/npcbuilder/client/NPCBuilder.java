package uk.co.meadicus.npcbuilder.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class NPCBuilder implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		RootPanel.get("siteloading").getElement().removeFromParent();
		RootPanel workPanel = RootPanel.get("workArea");
		AppWidget appWidget = new AppWidget();
		workPanel.add(appWidget);
		
		// Check for a request parameter
		String fcstatblock = Window.Location.getParameter("fcnpc");
		
		if (fcstatblock != null && !fcstatblock.trim().isEmpty()) {
			appWidget.startFantasyCraftEditor(fcstatblock);
		}
		
		String scstatblock = Window.Location.getParameter("scnpc");
		
		if (scstatblock != null && !scstatblock.trim().isEmpty()) {
			appWidget.startFantasyCraftEditor(scstatblock);
		}
	}
		
}
