package com.model;

import com.controller.MineSelectListener;
import com.controller.SelectListenerInterface;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class MineLabel extends Label {

	private int index = 0;

	public MineLabel(int index, SelectListenerInterface mineLis) {
		super();
		setIndex(index);
		super.setPrefHeight(50);
		super.setPrefWidth(50);
		super.setText(getId());
		Border border = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
		super.setBorder(border);
		setBackground(new Background(new BackgroundFill(Color.GAINSBORO, CornerRadii.EMPTY, Insets.EMPTY )));
		setOnMouseClicked((MineSelectListener) mineLis);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
