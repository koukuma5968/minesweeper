package com.controller;

import java.awt.Container;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.event.MouseInputListener;

import com.controller.adapter.MineSelectAdapter;
import com.controller.message.MessageDialogInterface;
import com.controller.messageDialog.MessageDialogImpl;
import com.model.MessageDialogBean;
import com.model.MineLabel;
import com.model.filed.BomFiledBean;

public class MineSelectListener extends MineSelectAdapter implements MouseInputListener {

    public MineSelectListener() {
        super(new BomFiledBean());
    }

    @Override
    public void mousePressed(MouseEvent e) {

        MineLabel label = (MineLabel) e.getComponent();
        label.setIcon(new ImageIcon(super.getBean().getFiledMap().get(Integer.valueOf(label.getId()))));

        Container con = label.getParent();

        MessageDialogBean bean = new MessageDialogBean();
        bean.setContainer(con);
        bean.setShowFlag(super.getBean().getBoms().get(label.getId()));

        MessageDialogInterface magDig = new MessageDialogImpl(bean);
        magDig.showDialog();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // 処理なし
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
