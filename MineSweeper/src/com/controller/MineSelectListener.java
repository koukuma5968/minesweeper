package com.controller;

import java.awt.Container;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.event.MouseInputListener;

import com.controller.adapter.MineSelectAdapter;
import com.controller.message.MessageDialogInterface;
import com.controller.messageDialog.MessageDialogImpl;
import com.model.MineLabel;
import com.model.filed.BomFiledBean;
import com.model.messagersouce.MessageDialogBean;

public class MineSelectListener extends MineSelectAdapter implements MouseInputListener {

	public MineSelectListener(BomFiledBean bomFiledBean) {
		super(bomFiledBean);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// 処理なし
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		MineLabel label = (MineLabel) e.getComponent();
		ImageIcon icon = new ImageIcon(super.getBean().getFiledMap().get(Integer.valueOf(label.getId())));
		label.setIcon(icon);
		label.setVerticalAlignment(SwingConstants.HORIZONTAL);
		label.setHorizontalAlignment(SwingConstants.CENTER);

		Container con = label.getParent();

		MessageDialogBean bean = new MessageDialogBean();
		bean.setContainer(con);
		bean.setShowFlag(super.getBean().getBoms().get(label.getId()));

		MessageDialogInterface magDig = new MessageDialogImpl(bean, super.getBean());
		magDig.showDialog();

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// 処理なし
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// 処理なし
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// 処理なし

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// 処理なし
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// 処理なし
	}

}
