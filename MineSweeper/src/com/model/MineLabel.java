package com.model;

import javax.swing.JLabel;

public class MineLabel extends JLabel {

    /**
     *
     */
    private static final long serialVersionUID = 3712611637094329282L;
    private int id = 0;

    public MineLabel() {
        super();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
