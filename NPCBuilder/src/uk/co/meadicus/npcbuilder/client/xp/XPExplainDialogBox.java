package uk.co.meadicus.npcbuilder.client.xp;

import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;

public class XPExplainDialogBox extends Composite {

	private static XPExplainDialogBoxUiBinder uiBinder = GWT
			.create(XPExplainDialogBoxUiBinder.class);

	interface XPExplainDialogBoxUiBinder extends
			UiBinder<Widget, XPExplainDialogBox> {
	}

	protected @UiField DialogBox mainPanel;
	protected @UiField FlowPanel contentPanel;
	protected @UiField Tree xpTree;
	protected @UiField Button closeButton;

	public XPExplainDialogBox(XP xp) {
		initWidget(uiBinder.createAndBindUi(this));
		
		// create tree
		xpTree.clear();
		TreeItem baseItem = new TreeItem("Total = " + xp + "xp");
		addXPBreakdown(baseItem, xp);
		xpTree.addItem(baseItem);
		baseItem.setState(true);
		
		// assign close button event
		closeButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
	}

	private void addXPBreakdown(TreeItem treeItem, XP xp) {
		if (xp instanceof ComplexXP) {
			ComplexXP complexXP = (ComplexXP) xp;
			for (Map.Entry<String, XP> entry : complexXP.getComponents().entrySet()) {
				TreeItem item = new TreeItem(entry.getKey().toLowerCase() + " = " + entry.getValue() + "xp");
				treeItem.addItem(item);
				addXPBreakdown(item, entry.getValue());
			}
		}
	}
		
	public void show() {
		mainPanel.center();
	}
	
	public void hide() {
		mainPanel.hide();
	}
}
