package com.controller.messageDialog;

import com.sun.javafx.scene.control.skin.resources.ControlResources;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class MultipleTextInputDialog extends Dialog<String[]> {

	private final GridPane grid;
	private final TextField[] textFields;
	private final Label[] labels;
	private String[] textId = new String[] {"bom", "col", "row"};
	private String[] labelText = new String[] {"bom数", "列数", "行数"};


	public MultipleTextInputDialog(int fieldCount) {
		final DialogPane dialogPane = getDialogPane();

		// -- textfield
		this.textFields = new TextField[fieldCount];
		int i = 0;
		while (i < fieldCount) {
			this.textFields[i] = new TextField();
			this.textFields[i].setMaxWidth(Double.SIZE);
			i++;
		}

		this.labels = new Label[fieldCount];
		int j = 0;
		while (j < fieldCount) {
			this.labels[j] = new Label();
			j++;
		}

		this.grid = new GridPane();
		this.grid.setHgap(10);
		this.grid.setMaxWidth(Double.MAX_VALUE);
		this.grid.setAlignment(Pos.CENTER_LEFT);

		dialogPane.contentTextProperty().addListener(o -> updateGrid());

		dialogPane.setHeaderText(ControlResources.getString("Dialog.confirm.header"));
		dialogPane.getStyleClass().add("text-input-dialog");
		dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

		updateGrid();

		setResultConverter((dialogButton) -> {
			ButtonData data = dialogButton == null ? null : dialogButton.getButtonData();
			String[] ret = new String[this.textFields.length];
			int k = 0;
			for (TextField text : this.textFields) {
				ret[k] = text.getText();
				k++;
			}
			return data == ButtonData.OK_DONE ? ret : new String[] {""};
		});

		setDisp();
	}

	public final TextField[] getFields() {
		return this.textFields;
	}

	public final Label[] getLabels() {
		return this.labels;
	}

	private void updateGrid() {
		this.grid.getChildren().clear();

		int i = 0;
		for (TextField text : this.textFields) {
			this.grid.add(text, 2, i);
			i++;
		}

		int j = 0;
		for (Label label : this.labels) {
			this.grid.add(label, 1, j);
			j++;
		}

		getDialogPane().setContent(grid);

		Platform.runLater(() -> this.textFields[0].requestFocus());
	}

	private void setDisp() {

		setTitle(ControlResources.getString("Dialog.confirm.title"));
		getDialogPane().setPrefSize(150, 200);

		int i = 0;
		for (Label dialogLabel : getLabels()) {
			dialogLabel.setText(labelText[i]);
			i++;
		}

		int j = 0;
		for (TextField fields : getFields()) {
			fields.setId(textId[j]);
			j++;
		}

	}
}
