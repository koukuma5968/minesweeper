package com.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import com.controller.MineSelectListener;
import com.controller.SelectListenerInterface;
import com.model.MineLabel;
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

    public void init(int bomCount, int rowCount, int colCount) {

        System.out.println(super.getTitle());
        JPanel pane = new JPanel();
        GridLayout layout = new GridLayout();
        layout.setRows(rowCount);
        layout.setColumns(colCount);
        pane.setLayout(layout);

        SelectListenerInterface mineLis = new MineSelectListener();
        mineLis.setMineRandom(bomCount, rowCount * colCount);
        mineLis.setStartEndPoint(rowCount, colCount);
        mineLis.setFiledMap(rowCount, colCount);

        EtchedBorder border = new EtchedBorder(EtchedBorder.RAISED, Color.WHITE, Color.BLACK);

        int index = 1;
        for (int i=0; i<rowCount; i++) {

            for (int j = 0; j< colCount; j++) {
                MineLabel label = new MineLabel();
                label.setId(index);
                label.setBorder(border);
                label.setOpaque(true);
                label.addMouseListener((MouseListener) mineLis);
                label.setVerticalAlignment(SwingConstants.HORIZONTAL);
                label.setHorizontalAlignment(SwingConstants.CENTER);

                pane.add(label);
                index++;
            }
        }

        super.getContentPane().add(pane);
        super.setResizable(true);
        super.setVisible(true);
    }
}
