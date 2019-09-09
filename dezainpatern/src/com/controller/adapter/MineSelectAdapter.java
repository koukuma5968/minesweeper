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

    public MineSelectAdapter(BomFiledBean bean) {
        this.bean = bean;
    }

    @Override
    public void setMineRandom(int bomCount, int filedCount) {

        BitSet bit = new BitSet();
        Random random = new Random();

        for (int i = 0; i < bomCount;) {
            int bom = random.nextInt(filedCount);

            if (!bit.get(bom)) {
                bit.set(bom, true);
                i++;
            }
        }

        this.bean.setBoms(bit);
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

        int col = 1;
        for (int row = 1; row <= rowCount; row++) {
            int nextCol = colCount * row;
            for (;col <= nextCol; col++) {
                if (this.bean.getBoms().get(col)) {
                    fMap.put(col, Constant.IMAGE_PATH + Constant.BOM_IMAGE);
                } else {
                    byte count = 0;

                    // 上、左上、右上
                    int topPrevious = col - colCount;
                    count = getTopAndBottomPoint(topPrevious, col, rowCount, colCount, count);

                    // 下、左下、右下
                    int lastNext = col + colCount;
                    count = getTopAndBottomPoint(lastNext, col, rowCount, colCount, count);

                    // 左
                    int previous = col - 1;
                    // 右
                    int next = col + 1;
                    count = getLeftAndRightPoint(previous, next, col, count);

                    fMap.put(col, Constant.IMAGE_PATH.concat(WHITE_IMAGE.valueOf(Constant.WHITE.concat(String.valueOf(count))).getKeyName()));
                }
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

}
