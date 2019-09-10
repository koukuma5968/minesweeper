package com.model.filed;

import java.util.BitSet;
import java.util.LinkedHashMap;

public class BomFiledBean {

    private BitSet boms = null;

    private BitSet sPoint = null;

    private BitSet ePoint = null;

    private LinkedHashMap<Integer, String> filedMap = null;

    private int bomCount = 0;

    private int rowCount = 0;

    private int colCount = 0;

    public BitSet getBoms() {
        return boms;
    }

    public void setBoms(BitSet boms) {
        this.boms = boms;
    }

    public BitSet getsPoint() {
        return sPoint;
    }

    public void setsPoint(BitSet sPoint) {
        this.sPoint = sPoint;
    }

    public BitSet getePoint() {
        return ePoint;
    }

    public void setePoint(BitSet ePoint) {
        this.ePoint = ePoint;
    }

    public LinkedHashMap<Integer, String> getFiledMap() {
        return filedMap;
    }

    public void setFiledMap(LinkedHashMap<Integer, String> filedMap) {
        this.filedMap = filedMap;
    }

    public int getBomCount() {
        return bomCount;
    }

    public void setBomCount(int bomCount) {
        this.bomCount = bomCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getColCount() {
        return colCount;
    }

    public void setColCount(int colCount) {
        this.colCount = colCount;
    }

}
