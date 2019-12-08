package com.controller.messageDialog;

import java.util.Optional;

import com.controller.message.MessageDialogInterface;
import com.model.filed.BomFiledBean;
import com.model.messagersouce.MessageDialogBean;
import com.model.messagersouce.Messages;
import com.view.MinePanel;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MessageDialogImpl implements MessageDialogInterface {

	private MessageDialogBean msgBean = null;
	private BomFiledBean bomFiledBean = null;

	public MessageDialogImpl(MessageDialogBean msgBean, BomFiledBean bomFiledBean) {
		this.msgBean = msgBean;
		this.bomFiledBean = bomFiledBean;
	}

	@Override
	public int showDialog() {

		if (this.msgBean.isShowFlag()) {
			Alert dialog = new Alert( AlertType.INFORMATION,
					Messages.getString("Gameover.message"),
					ButtonType.YES, ButtonType.NO, ButtonType.CLOSE );
			dialog.setTitle(Messages.getString("Gameover.title"));
			dialog.setHeaderText(null);

			return chekResult(dialog);
		}
		return 0;
	}

	@Override
	public int clearDialog() {

		Alert dialog = new Alert( AlertType.INFORMATION,
				Messages.getString("Gameclear.message"),
				ButtonType.YES, ButtonType.CLOSE );
		dialog.setTitle(Messages.getString("Gameclear.title"));
		dialog.setHeaderText(null);

		return chekResult(dialog);
	}

	private int chekResult(Alert dialog) {

		int ret = 0;

		Optional<ButtonType> result = dialog.showAndWait();

		if (result.isPresent() && result.get() == ButtonType.YES) {
			// リスタート
			MinePanel p = (MinePanel) ((GridPane) msgBean.getContainer()).getParent();
			p.rePaint(bomFiledBean);
			ret = 1;

		} else if (result.isPresent() && result.get() == ButtonType.NO) {

			String[] res = null;
			do {
				MultipleTextInputDialog inputDialog = new MultipleTextInputDialog(3);

				Optional<String[]> inputResult = inputDialog.showAndWait();
				res = inputResult.get();
				if (res == null) {
					break;
				}
			} while (Integer.valueOf(res[0]) > (Integer.valueOf(res[1]) * Integer.valueOf(res[2])));

			bomFiledBean.setBomCount(Integer.valueOf(res[0]));
			bomFiledBean.setRowCount(Integer.valueOf(res[1]));
			bomFiledBean.setColCount(Integer.valueOf(res[2]));

			MinePanel pane = (MinePanel) ((GridPane) msgBean.getContainer()).getParent();
			Scene scene = pane.getScene();
			Stage oldStage = (Stage) scene.getWindow();
			oldStage.close();
			oldStage = null;

			pane = new MinePanel();
			pane.init(bomFiledBean);
			Stage newStage = new Stage();
			newStage.setScene(new Scene(pane, 600, 600));
			newStage.show();
			ret = 2;

		} else {
			Platform.exit();
			ret = 3;
		}
		return ret;
	}
}
