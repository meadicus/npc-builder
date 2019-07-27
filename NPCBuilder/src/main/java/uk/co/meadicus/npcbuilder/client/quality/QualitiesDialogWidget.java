package uk.co.meadicus.npcbuilder.client.quality;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

import uk.co.meadicus.npcbuilder.client.NPCEditor;
import uk.co.meadicus.npcbuilder.client.npc.NPC;
import uk.co.meadicus.npcbuilder.client.util.NPCUtils;

public class QualitiesDialogWidget extends Composite {

	private static QualitiesDialogWidgetUiBinder uiBinder = GWT.create(QualitiesDialogWidgetUiBinder.class);

	interface QualitiesDialogWidgetUiBinder extends UiBinder<Widget, QualitiesDialogWidget> {
	}

	private final NPC npc;

	@UiField
	ListBox newQualityListBox;
	@UiField
	Button addButton;
	@UiField
	ListBox editQualityListBox;
	@UiField
	Button editButton;
	@UiField
	Button removeButton;
	@UiField
	Label customQualityNote;

	public QualitiesDialogWidget(final NPC npc, final NPCEditor npcEditor,
			final QualitiesDialogBox qualitiesDialogBox) {
		initWidget(uiBinder.createAndBindUi(this));
		this.npc = npc;

		// initialise the quality list
		Map<String, List<String>> allQualities = getNpc().getConfig().getQualityFactory().getQualityNames();
		NPCUtils.addListOptionGroups(this.newQualityListBox, true, allQualities);

		// bind event to add quality button
		this.addButton.addClickHandler(event -> {
			qualitiesDialogBox.hide();
			String qualityName = NPCUtils.getSelectedItemValue(newQualityListBox);

			Quality quality;
			if (getNpc().hasQuality(qualityName)) {
				quality = getNpc().getQualities().get(qualityName);
			} else {
				quality = getNpc().getConfig().getQualityFactory().getQuality(qualityName);
			}
			// Window.alert("adding quality : " + quality.getName());
			QualityDialogBox qualityDialog = new QualityDialogBox(quality, npcEditor, getNpc());
			qualityDialog.init();
			qualityDialog.show();
		});

		// bind event to edit quality button
		this.editButton.addClickHandler(event -> editSelectedQuality(npcEditor, qualitiesDialogBox));

		// bind event to remove quality button
		this.removeButton.addClickHandler(event -> {
			String qualityName = NPCUtils.getSelectedItemValue(editQualityListBox);
			getNpc().getQualities().remove(qualityName);
			qualitiesDialogBox.hide();
			qualitiesDialogBox.updateStatBlock();
		});

		this.editQualityListBox.addDoubleClickHandler(event -> editSelectedQuality(npcEditor, qualitiesDialogBox));

	}

	private void editSelectedQuality(final NPCEditor npcEditor, final QualitiesDialogBox qualitiesDialogBox) {
		qualitiesDialogBox.hide();
		String qualityName = NPCUtils.getSelectedItemValue(this.editQualityListBox);
		Quality quality = getNpc().getQualities().get(qualityName);
		QualityDialogBox qualityDialog = new QualityDialogBox(quality, npcEditor, getNpc());
		qualityDialog.init();
		qualityDialog.show();
	}

	public void init() {

		boolean hasCustom = false;
		// setup edit quality list
		Map<String, Quality> qualities = getNpc().getQualities();
		Map<String, String> qualityListItems = new TreeMap<String, String>();
		for (String qualityName : qualities.keySet()) {
			Quality quality = qualities.get(qualityName);
			String qualityDescription = qualityName;
			if (quality instanceof CustomQuality) {
				qualityDescription += "*";
				hasCustom = true;
			}
			qualityDescription += " [" + quality.getXp() + "xp]";
			qualityListItems.put(qualityDescription, qualityName);
		}
		NPCUtils.addListOptions(this.editQualityListBox, true, qualityListItems, false);

		customQualityNote.setVisible(hasCustom);

		// If there aren no qualities disable the edit and remove buttons
		if (this.editQualityListBox.getItemCount() > 0) {
			if (this.editQualityListBox.getSelectedIndex() < 0) {
				this.editQualityListBox.setItemSelected(0, true);
			}
			this.editButton.setEnabled(true);
			this.removeButton.setEnabled(true);
		} else {
			// edit and remove buttons disabled at startup
			this.editButton.setEnabled(false);
			this.removeButton.setEnabled(false);
		}
	}

	private NPC getNpc() {
		return npc;
	}

}
