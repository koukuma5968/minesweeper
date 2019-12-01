package com.view;

import java.util.Optional;

import com.controller.messageDialog.MultipleTextInputDialog;
import com.model.filed.BomFiledBean;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MineSweeperStage extends Application {

	private BomFiledBean bomBean = new BomFiledBean();

	@Override
	public void start(Stage primaryStage) throws Exception {

		MultipleTextInputDialog inputDialog = new MultipleTextInputDialog(3);

		Optional<String[]> result = inputDialog.showAndWait();
		String[] res = result.get();
		this.bomBean = new BomFiledBean();

		bomBean.setBomCount(Integer.valueOf(res[0]));
		bomBean.setRowCount(Integer.valueOf(res[1]));
		bomBean.setColCount(Integer.valueOf(res[2]));

		MinePanel pane = new MinePanel();

		pane.init(bomBean);

		Scene scene = new Scene(pane, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
