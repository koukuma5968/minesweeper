package com.model;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import com.controller.MineSelectListener;
import com.controller.SelectListenerInterface;

public class MineLabel extends JLabel {

	/**
	 *
	 */
	private static final long serialVersionUID = 3712611637094329282L;
	private int id = 0;

	public MineLabel(int index, EtchedBorder border, SelectListenerInterface mineLis) {
		super();
		setId(index);
		setBorder(border);
		setOpaque(true);
		addMouseListener((MineSelectListener) mineLis);
		setVerticalAlignment(SwingConstants.HORIZONTAL);
		setHorizontalAlignment(SwingConstants.CENTER);

	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
