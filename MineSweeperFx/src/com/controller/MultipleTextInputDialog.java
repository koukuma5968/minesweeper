package com.controller;

import com.sun.javafx.scene.control.skin.resources.ControlResources;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class MultipleTextInputDialog extends Dialog<String[]> {

   private final GridPane grid;
   private final TextField[] textFields;

   public MultipleTextInputDialog() {
       final DialogPane dialogPane = getDialogPane();

       // -- textfield
       this.textFields = new TextField[] {new TextField(), new TextField(), new TextField()};
       this.textFields[0].setMaxWidth(Double.MAX_VALUE);
       this.textFields[1].setMaxWidth(Double.MAX_VALUE);
       this.textFields[2].setMaxWidth(Double.MAX_VALUE);
//       GridPane.setHgrow(this.textFields[0], Priority.ALWAYS);
//       GridPane.setFillWidth(this.textFields[0], true);

       this.grid = new GridPane();
       this.grid.setHgap(10);
       this.grid.setMaxWidth(Double.MAX_VALUE);
       this.grid.setAlignment(Pos.CENTER_LEFT);

       dialogPane.contentTextProperty().addListener(o -> updateGrid());

       setTitle(ControlResources.getString("Dialog.confirm.title"));
       dialogPane.setHeaderText(ControlResources.getString("Dialog.confirm.header"));
       dialogPane.getStyleClass().add("text-input-dialog");
       dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

       updateGrid();

       setResultConverter((dialogButton) -> {
           ButtonData data = dialogButton == null ? null : dialogButton.getButtonData();
           return data == ButtonData.OK_DONE ? new String[] {
                   this.textFields[0].getText(),
                   this.textFields[1].getText(),
                   this.textFields[2].getText()} : null;
       });
   }

   public final TextField[] getEditor() {
       return this.textFields;
   }

   private void updateGrid() {
       this.grid.getChildren().clear();

       this.grid.add(this.textFields[0], 1, 0);
       this.grid.add(this.textFields[1], 1, 1);
       this.grid.add(this.textFields[2], 1, 2);

       getDialogPane().setContent(grid);

       Platform.runLater(() -> this.textFields[0].requestFocus());
   }
}
