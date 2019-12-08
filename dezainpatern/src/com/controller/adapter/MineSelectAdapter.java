package com.controller.adapter;

import java.util.BitSet;
import java.util.LinkedHashMap;
import java.util.Random;

import com.controller.SelectListenerInterface;
import com.model.filed.BomFiledBean;
import com.model.util.Constant;
import com.model.util.Constant.WHITE_IMAGE;

public abstract class MineSelectAdapter implements SelectListenerInterface {

	private BomFiledBean bean = new BomFiledBean();

	public BomFiledBean getBean() {
		return bean;
	}

	public MineSelectAdapter(BomFiledBean bomFiledBean) {
		this.bean = bomFiledBean;
		setMineRandom(bomFiledBean.getBomCount(), bomFiledBean.getRowCount() * bomFiledBean.getColCount());
		setStartEndPoint(bomFiledBean.getRowCount(), bomFiledBean.getColCount());
		setFiledMap(bomFiledBean.getRowCount(), bomFiledBean.getColCount());
		setAllFiled();
	}

	@Override
	public void setMineRandom(int bomCount, int filedCount) {

		BitSet bombit = new BitSet();
		Random random = new Random();

		for (int i = 0; i < bomCount;) {
			int bom = random.nextInt(filedCount) + 1;

			if (!bombit.get(bom)) {
				bombit.set(bom, true);
				i++;
			}
		}

		this.bean.setBoms(bombit);
	}

	@Override
	public void setStartEndPoint(int rowCount, int colCount) {

		BitSet sPoint = new BitSet();
		BitSet ePoint = new BitSet();

		for (int row = 1; row <= rowCount; row++) {
			int eLine = colCount * row;
			int sLine = eLine - colCount + 1;
			sPoint.set(sLine, true);
			ePoint.set(eLine, true);
		}

		this.bean.setsPoint(sPoint);
		this.bean.setePoint(ePoint);
	}

	@Override
	public void setFiledMap(int rowCount, int colCount) {

		LinkedHashMap<Integer, String> fMap = new LinkedHashMap<>();

		int index = 1;
		for (int row = 0; row < rowCount; row++) {
			for (int col = 0; col < colCount; col++) {
				if (this.bean.getBoms().get(index)) {
					fMap.put(index, Constant.IMAGE_PATH + Constant.BOM_IMAGE);
				} else {
					byte count = 0;

					// 上、左上、右上
					int topPrevious = index - colCount;
					count = getTopAndBottomPoint(topPrevious, index, rowCount, colCount, count);

					// 下、左下、右下
					int lastNext = index + colCount;
					count = getTopAndBottomPoint(lastNext, index, rowCount, colCount, count);

					// 左
					int previous = index - 1;
					// 右
					int next = index + 1;
					count = getLeftAndRightPoint(previous, next, index, count);

					fMap.put(index, Constant.IMAGE_PATH.concat(WHITE_IMAGE.valueOf(Constant.WHITE.concat(String.valueOf(count))).getKeyName()));
				}
				index++;
			}
		}
		this.bean.setFiledMap(fMap);
	}

	private byte getTopAndBottomPoint(int nPoint, int oPoint, int rowCount, int colCount, byte count) {

		byte ret = count;
		if (0 < nPoint && nPoint <= rowCount * colCount) {
			if (this.bean.getBoms().get(nPoint)) {
				ret = (byte) (ret + 1);
			}
			int left = nPoint - 1;
			int right = nPoint + 1;
			ret = getLeftAndRightPoint(left, right, oPoint, ret);
		}
		return ret;
	}

	private byte getLeftAndRightPoint(int left, int right, int oPoint, byte count) {

		byte ret = count;
		// 左
		if (!this.bean.getsPoint().get(oPoint) && this.bean.getBoms().get(left)) {
			ret = (byte) (ret + 1);
		}
		// 右
		if (!this.bean.getePoint().get(oPoint) && this.bean.getBoms().get(right)) {
			ret = (byte) (ret + 1);;
		}
		return ret;
	}

	protected void setAllFiled() {

		int allCount = this.bean.getRowCount() * this.bean.getColCount() + 1;
		BitSet allBit = new BitSet(allCount);
		allBit.set(1, allCount);
		allBit.andNot(bean.getBoms());

		this.bean.setAllBit(allBit);

	}

	protected boolean checkRemovalBom() {

		return this.bean.getAllBit().isEmpty();
	}
}
