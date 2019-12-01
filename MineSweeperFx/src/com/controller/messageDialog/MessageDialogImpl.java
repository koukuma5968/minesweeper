package com.controller.messageDialog;

import java.util.Optional;

import com.controller.message.MessageDialogInterface;
import com.model.MineLabel;
import com.model.filed.BomFiledBean;
import com.model.messagersouce.MessageDialogBean;
import com.model.messagersouce.Messages;
import com.view.MinePanel;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;

public class MessageDialogImpl implements MessageDialogInterface {

	private MessageDialogBean msgBean = null;
	private BomFiledBean bomFiledBean = null;

	public MessageDialogImpl(MessageDialogBean msgBean, BomFiledBean bomFiledBean) {
		this.msgBean = msgBean;
		this.bomFiledBean = bomFiledBean;
	}

	@Override
	public void showDialog() {

		if (this.msgBean.isShowFlag()) {
			Alert dialog = new Alert( AlertType.NONE,
					Messages.getString(Messages.getString("MessageDialog.message")),
					ButtonType.YES, ButtonType.NO, ButtonType.CANCEL );

			Optional<ButtonType> result = dialog.showAndWait();

			if (result.isPresent() && result.get() == ButtonType.YES) {
				// リスタート
				for (Node node : ((GridPane) msgBean.getContainer()).getChildren()) {
					MineLabel label = (MineLabel) node;
					label.setBackground(null);
				}
			} else if (result.isPresent() && result.get() == ButtonType.NO) {
				MultipleTextInputDialog inputDialog = new MultipleTextInputDialog(3);

				Optional<String[]> inputRet = inputDialog.showAndWait();
				String[] res = inputRet.get();

				bomFiledBean.setBomCount(Integer.valueOf(res[0]));
				bomFiledBean.setRowCount(Integer.valueOf(res[1]));
				bomFiledBean.setColCount(Integer.valueOf(res[2]));

				MinePanel pane = (MinePanel) ((GridPane) msgBean.getContainer()).getParent();

				pane.init(bomFiledBean);

			} else {
				Platform.exit();
			}
		}
	}

}
