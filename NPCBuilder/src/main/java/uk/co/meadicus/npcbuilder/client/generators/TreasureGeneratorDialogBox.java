package uk.co.meadicus.npcbuilder.client.generators;

import uk.co.meadicus.npcbuilder.client.treasure.TreasureGeneratorWidget;
import uk.co.meadicus.npcbuilder.client.util.XPDialogBox;

import com.google.gwt.user.client.ui.Widget;

public class TreasureGeneratorDialogBox extends XPDialogBox {

	private TreasureGeneratorWidget treasureGenerator = null;
	
	public TreasureGeneratorDialogBox() {
		super("Random Treasure Generator (beta)", null, null, false, true);
		treasureGenerator = new TreasureGeneratorWidget();
		this.cancelButton.setVisible(false);
		setContent();
	}

	
	protected Widget getContent() {
		return this.treasureGenerator;
	}

	
	protected boolean onOk() {
		return true;
	}

	
	protected int calculateXP() {
		return 0;
	}

}
