package com.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.controller.adapter.MineSelectAdapter;
import com.controller.message.MessageDialogInterface;
import com.controller.messageDialog.MessageDialogImpl;
import com.model.MineLabel;
import com.model.filed.BomFiledBean;
import com.model.messagersouce.MessageDialogBean;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;

public class MineSelectListener extends MineSelectAdapter implements EventHandler<MouseEvent> {

	public MineSelectListener(BomFiledBean bomBean) {
		super(bomBean);
	}

	@Override
	public void handle(MouseEvent event) {

		if (event.getClickCount() == 1) {
			System.out.println(event.getSource());
			MineLabel label = (MineLabel) event.getSource();
			Image img;
			try {
				img = new Image(new FileInputStream(this.getBean().getFiledMap().get(Integer.valueOf(label.getIndex()))));
				BackgroundImage bimg = new BackgroundImage(img, null, null, null, new BackgroundSize(10, 20, false, false, true, false));
				Background bg = new Background(bimg);
				label.setPadding(new Insets(0));
				label.setBackground(bg);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			Parent con = label.getParent();

			MessageDialogBean bean = new MessageDialogBean();
			bean.setContainer(con);
			boolean bitFlag = super.getBean().getBoms().get(label.getIndex());
			bean.setShowFlag(bitFlag);
			super.getBean().getAllBit().set(label.getIndex(), bitFlag);

			MessageDialogInterface magDig = new MessageDialogImpl(bean, super.getBean());
			int show = magDig.showDialog();

			if (show == 0) {
				if (super.checkRemovalBom()) {
					show = magDig.clearDialog();
				}
			}

			if (show == 1) {
				super.setAllFiled();
			}

		}

	}

}
