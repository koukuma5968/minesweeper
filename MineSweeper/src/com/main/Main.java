package com.main;

import com.model.filed.BomFiledBean;
import com.view.MineSweeperFrame;

public class Main {

	public static void main(String[] args) {

		MineSweeperFrame f = new MineSweeperFrame();
		BomFiledBean bean = new BomFiledBean();
		bean.setBomCount(30);
		bean.setColCount(10);
		bean.setRowCount(10);
		f.init(bean);

	}

}
