package com.view;

import com.model.filed.BomFiledBean;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class MinePanel extends StackPane {

    public MinePanel() {
        super();
    }

    public void init(BomFiledBean bomBean) {

        GridPane grid = new GridPane();
        grid.setMaxSize(bomBean.getColCount() * 20, bomBean.getRowCount() * 20);
        grid.setMinSize(bomBean.getColCount() * 20, bomBean.getRowCount() * 20);

        int index = 1;
        for (int row=0; row < bomBean.getRowCount(); row++) {

            for (int col = 0; col< bomBean.getColCount(); col++) {
                Label label = new Label();
                label.setId(String.valueOf(index));
                label.setText(label.getId());

                grid.add(label, col, row);
                index++;
            }
        }

        super.getChildren().add(grid);
        NumberBinding binding = Bindings.min(super.widthProperty().divide(bomBean.getColCount() * 20),
                                               super.heightProperty().divide(bomBean.getRowCount() * 20));
        grid.scaleXProperty().bind(binding);
        grid.scaleYProperty().bind(binding);
    }

}
