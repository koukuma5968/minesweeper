package com.model;

import java.awt.Container;

public class MessageDialogBean {

    private boolean showFlag = false;

    private Container container = null;

    public boolean isShowFlag() {
        return this.showFlag;
    }

    public void setShowFlag(boolean showFlag) {
        this.showFlag = showFlag;
    }

    public Container getContainer() {
        return this.container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

}
