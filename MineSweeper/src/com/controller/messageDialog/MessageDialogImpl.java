package com.controller.messageDialog;

import java.awt.Component;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.controller.message.MessageDialogInterface;
import com.model.MineLabel;
import com.model.filed.BomFiledBean;
import com.model.messagersouce.MessageDialogBean;
import com.model.messagersouce.Messages;
import com.model.util.Constant;
import com.view.MineSweeperFrame;

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

			int select = JOptionPane.showConfirmDialog(new JPanel(),
					Messages.getString("Gameover.message"), //$NON-NLS-1$
					Messages.getString("Gameover.title"), //$NON-NLS-1$
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE
					, new ImageIcon(Constant.IMAGE_PATH + Constant.SHOW_IMAGE));
			return chekResult(select);
		}
		return 0;
	}

	@Override
	public int clearDialog() {
		int select = JOptionPane.showConfirmDialog(new JPanel(),
				Messages.getString("Gameclear.message"), //$NON-NLS-1$
				Messages.getString("Gameclear.title"), //$NON-NLS-1$
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE
				, new ImageIcon(Constant.IMAGE_PATH + Constant.SHOW_IMAGE));
		select = select == JOptionPane.NO_OPTION ? 3 : select;
		return chekResult(select);
	}

	private int chekResult(int select) {

		int ret = 0;
		if (select == JOptionPane.YES_OPTION) {
			// リスタート
			for (Component com : ((Container) this.msgBean.getContainer()).getComponents()) {
				MineLabel l = (MineLabel) com;
				l.setIcon(null);
				l.setBackground(null);
			}
			ret = 1;
		} else if (select == JOptionPane.NO_OPTION) {
			// 難易度変更
			MineSweeperFrame f = null;
			for (;this.msgBean.getContainer() != null; this.msgBean.setContainer(((Component) this.msgBean.getContainer()).getParent())) {
				if (this.msgBean.getContainer() instanceof MineSweeperFrame) {
					f = (MineSweeperFrame) this.msgBean.getContainer();

					f.dispose();
					f = new MineSweeperFrame();
					f.init(this.bomFiledBean);
					f.repaint();

					break;
				}
			}
			ret = 2;
		} else {
			System.exit(0);
			ret = 3;
		}
		return ret;
	}
}
