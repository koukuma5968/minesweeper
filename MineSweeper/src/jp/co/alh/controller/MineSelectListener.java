package jp.co.alh.controller;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.util.BitSet;
import java.util.LinkedHashMap;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import jp.co.alh.model.MineLabel;
import jp.co.alh.view.MineSweeperFrame;

public class MineSelectListener extends MouseInputAdapter {

    private BitSet boms = null;
    private BitSet sPoint = null;
    private BitSet ePoint = null;
    private LinkedHashMap<Integer, ImageIcon> filedMap = null;
    private static final String WHITE = "WHITE_";
    private static final String IMAGE_PATH = MineSelectListener.class.getResource("../image/").getPath();
    private static final ImageIcon BOM_IMAGE = new ImageIcon(IMAGE_PATH + "BOM.JPG");

    private enum WHITE_IMAGE {

        WHITE_0("WHITE_0.JPG"),
        WHITE_1("WHITE_1.JPG"),
        WHITE_2("WHITE_2.JPG"),
        WHITE_3("WHITE_3.JPG"),
        WHITE_4("WHITE_4.JPG"),
        WHITE_5("WHITE_5.JPG"),
        WHITE_6("WHITE_6.JPG"),
        WHITE_7("WHITE_7.JPG"),
        WHITE_8("WHITE_8.JPG");

        private WHITE_IMAGE(String keyName) {
            setKeyName(keyName);
        }

        private String keyName = null;

        protected void setKeyName(String keyName) {
            this.keyName = keyName;
        }

        protected String getKeyName() {
            return this.keyName;
        }
    }

    public MineSelectListener(int bomCount, int rowCount, int colCount) {
        super();
        this.boms = getMineRandom(bomCount, rowCount * colCount);
        setStartEndPoint(rowCount, colCount);
        this.filedMap = getFiledMap(rowCount, colCount);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        MineLabel label = (MineLabel) e.getComponent();
        label.setIcon(this.filedMap.get(label.getId()));
        Container con = label.getParent();
        showMesssege(boms.get(label.getId()), con);

    }

    private static BitSet getMineRandom(int bomCount, int filedCount) {
        BitSet bit = new BitSet();
        Random random = new Random();

        for (int i = 0; i < bomCount;) {
            int bom = random.nextInt(filedCount);

            if (!bit.get(bom)) {
                bit.set(bom, true);
                i++;
            }
        }

        return bit;
    }

    private void setStartEndPoint(int rowCount, int colCount) {
        this.sPoint = new BitSet();
        this.ePoint = new BitSet();
        for (int row = 1; row <= rowCount; row++) {
            int eLine = colCount * row;
            int sLine = eLine - colCount + 1;
            this.sPoint.set(sLine, true);
            this.ePoint.set(eLine, true);
        }
    }

    @SuppressWarnings("boxing")
    private LinkedHashMap<Integer, ImageIcon> getFiledMap(int rowCount, int colCount) {

        LinkedHashMap<Integer, ImageIcon> fMap = new LinkedHashMap<>();

        int col = 1;
        for (int row = 1; row <= rowCount; row++) {
            int nextCol = colCount * row;
            for (;col <= nextCol; col++) {
                if (this.boms.get(col)) {
                    fMap.put(col, BOM_IMAGE);
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

                    fMap.put(col, new ImageIcon(IMAGE_PATH.concat(WHITE_IMAGE.valueOf(WHITE.concat(String.valueOf(count))).getKeyName())));
                }
            }
        }
        return fMap;
    }

    private byte getTopAndBottomPoint(int nPoint, int oPoint, int rowCount, int colCount, byte count) {

        byte ret = count;
        if (0 < nPoint && nPoint <= rowCount * colCount) {
            if (this.boms.get(nPoint)) {
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
        if (!this.sPoint.get(oPoint) && boms.get(left)) {
            ret = (byte) (ret + 1);
        }
        // 右
        if (!this.ePoint.get(oPoint) && boms.get(right)) {
            ret = (byte) (ret + 1);;
        }
        return ret;
    }

    private void showMesssege(boolean b, Container con) {
        if (b) {
            int select = JOptionPane.showConfirmDialog(new JPanel(),
                    "はい：再挑戦\nいいえ：やりなおし", "ゲームオーバー", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE
                    , new ImageIcon(IMAGE_PATH + "show.png"));
            if (select == JOptionPane.YES_OPTION) {
                // リスタート
                for (Component com : con.getComponents()) {
                    MineLabel l = (MineLabel) com;
                    l.setIcon(null);
                }
            } else {
                // 難易度変更
                MineSweeperFrame f = null;
                for (;con != null; con = con.getParent() ) {
                    if (con instanceof MineSweeperFrame) {
                        f = (MineSweeperFrame) con;
                        break;
                    }
                }
                f.dispose();
                f = new MineSweeperFrame();
                f.init(30, 10, 10);
                f.repaint();
            }
        }
    }
}
