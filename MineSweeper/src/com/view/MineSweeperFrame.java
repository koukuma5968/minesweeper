package com.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import com.controller.MineSelectListener;
import com.controller.SelectListenerInterface;
import com.model.MineLabel;
import com.model.filed.BomFiledBean;
import com.model.messagersouce.Messages;

public class MineSweeperFrame extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = -7916270204086009470L;

	public MineSweeperFrame() {
		super();
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setBounds(100, 100, 400, 400);
		super.setTitle(Messages.getString(Messages.getString("MineSweeperFrame.title"))); //$NON-NLS-1$
	}

	public void init(BomFiledBean bean) {

		System.out.println(super.getTitle());
		JPanel pane = new JPanel();
		GridLayout layout = new GridLayout();
		layout.setRows(bean.getRowCount());
		layout.setColumns(bean.getColCount());
		pane.setLayout(layout);

		SelectListenerInterface mineLis = new MineSelectListener(bean);

		EtchedBorder border = new EtchedBorder(EtchedBorder.RAISED, Color.WHITE, Color.BLACK);

		int index = 1;
		for (int i = 0; i < bean.getRowCount(); i++) {

			for (int j = 0; j < bean.getColCount(); j++) {
				MineLabel label = new MineLabel(index, border, mineLis);

				pane.add(label);
				index++;
			}
		}

		super.getContentPane().add(pane);
		super.setResizable(true);
		super.setVisible(true);
	}

}
