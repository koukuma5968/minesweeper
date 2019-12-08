package com.view;

import com.controller.MineSelectListener;
import com.controller.SelectListenerInterface;
import com.model.MineLabel;
import com.model.filed.BomFiledBean;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class MinePanel extends StackPane {

	public MinePanel() {
		super();
	}

	SelectListenerInterface mineLis = null;

	public void init(BomFiledBean bomBean) {

		GridPane grid = new GridPane();
		grid.setMaxSize(bomBean.getColCount() * 20, bomBean.getRowCount() * 20);
		grid.setMinSize(bomBean.getColCount() * 20, bomBean.getRowCount() * 20);

		mineLis = new MineSelectListener(bomBean);

		setLabel(grid, bomBean);

		super.getChildren().add(grid);
		NumberBinding binding = Bindings.min(super.widthProperty().divide(bomBean.getColCount() * 20),
											super.heightProperty().divide(bomBean.getRowCount() * 20));
		grid.scaleXProperty().bind(binding);
		grid.scaleYProperty().bind(binding);
	}

	public void rePaint(BomFiledBean bomBean) {

		setLabel((GridPane) this.getChildren().get(0), bomBean);

	}

	private void setLabel(GridPane grid, BomFiledBean bomBean) {

		int index = 1;
		for (int row=0; row < bomBean.getRowCount(); row++) {

			for (int col = 0; col< bomBean.getColCount(); col++) {
				MineLabel label = new MineLabel(index, mineLis);

				grid.add(label, col, row);
				index++;
			}
		}

	}
}
