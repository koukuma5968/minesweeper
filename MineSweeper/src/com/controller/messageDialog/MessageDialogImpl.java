package com.controller.messageDialog;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.controller.message.MessageDialogInterface;
import com.model.MessageDialogBean;
import com.model.MineLabel;
import com.model.messagersouce.Messages;
import com.model.util.Constant;
import com.view.MineSweeperFrame;

public class MessageDialogImpl implements MessageDialogInterface {

    private MessageDialogBean msgBean = null;

    public MessageDialogImpl(MessageDialogBean msgBean) {
        this.msgBean = msgBean;
    }

    @Override
    public void showDialog() {

        if (this.msgBean.isShowFlag()) {

            int select = JOptionPane.showConfirmDialog(new JPanel(),
                    Messages.getString(Messages.getString("MessageDialog.message")), //$NON-NLS-1$
                    Messages.getString(Messages.getString("MessageDialog.title")), //$NON-NLS-1$
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE
                    , new ImageIcon(Constant.IMAGE_PATH + Constant.SHOW_IMAGE));

            if (select == JOptionPane.YES_OPTION) {
                // リスタート
                for (Component com : this.msgBean.getContainer().getComponents()) {
                    MineLabel l = (MineLabel) com;
                    l.setIcon(null);
                }
            } else {
                // 難易度変更
                MineSweeperFrame f = null;
                for (;this.msgBean.getContainer() != null; this.msgBean.setContainer(this.msgBean.getContainer().getParent())) {
                    if (this.msgBean.getContainer() instanceof MineSweeperFrame) {
                        f = (MineSweeperFrame) this.msgBean.getContainer();

                        f.dispose();
                        f = new MineSweeperFrame();
                        f.init(30, 10, 10);
                        f.repaint();

                        break;
                    }
                }
            }
        }
    }

}
